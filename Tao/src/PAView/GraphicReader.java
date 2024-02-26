package PAView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import PhotoAlbumModel.Snapshot;

/**
 * This GraphicReader class extends JFrame class and is implemented with a few more methods in
 * order to simulate the graphic window.
 */
public class GraphicReader extends JFrame implements IPAView {
  private Canvas canvas;
  private Header header;
  private JPanel footer;
  private JButton prev;
  private JButton select;
  private JButton next;
  private JButton quit;
  private List<Snapshot> snapshots;
  private List<String> snapshotIDs;
  private int currentInd;

  /**
   * This is the constructor of this class.
   * @param snapshots a list of snapshots
   * @param snapshotIDs a list of snapshot IDs
   * @param width width of the graphic window
   * @param height height of the graphic window
   */
  public GraphicReader(List<Snapshot> snapshots, List<String> snapshotIDs, int width, int height) {
    currentInd = 0;
    this.snapshots = snapshots;
    this.snapshotIDs = snapshotIDs;

    canvas = new Canvas(snapshots.get(0).getShapeInfo());
    header = new Header(snapshots.get(0).getId(), snapshots.get(0).getDescription());
    prev = createButton("<<  Prev  <<");
    select = createButton("^^  Select ^^");
    next = createButton(">>  Next >>");
    quit = createButton("xx  Quit xx");
    setButtonFunction();

    footer = new JPanel();
    footer.setBackground(new Color(0xa2d2ff));
    footer.setPreferredSize(new Dimension(100, 40));
    footer.add(prev);
    footer.add(select);
    footer.add(next);
    footer.add(quit);

    this.setTitle("CS5004 Photo Album");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(width, height);
    this.setLayout(new BorderLayout());
    this.add(header, BorderLayout.NORTH);
    this.add(canvas, BorderLayout.WEST);
    this.add(footer, BorderLayout.SOUTH);
  }

  /**
   * This method is to set this graphic window visible to the user.
   */
  public void view() {
    this.setVisible(true);
  }

  /**
   * This method is to call the select menu for the user to select the snapshot
   * with the corresponding id.
   */
  private void callMenu() {
    Object[] possibilities = new Object[snapshotIDs.size()];
    int i = 0;
    for (String s: snapshotIDs) {
      possibilities[i] = (Object) s;
      i++;
    }
    String s = (String)JOptionPane.showInputDialog(this, "Choose\n",
            "Menu", JOptionPane.PLAIN_MESSAGE, null, possibilities, snapshotIDs.get(0));
    if (s != null && !s.isBlank()) {
      selectPhoto(s);
    }
  }

  /**
   * This method is to set this graphic window to display the selected snapshot.
   * @param id the id of the selected snapshot
   */
  private void selectPhoto(String id) {
    int ind = 0;
    for (Snapshot ss : snapshots) {
      if (ss.getId().equals(id)) {
        currentInd = ind;
        canvas.setAlbum(ss.getShapeInfo());
        header.setHeader(ss.getId(), ss.getDescription());
        updatePicture();
        return;
      }
      ind++;
    }
  }

  /**
   * This is the private helper method to set up a JButton instance with the given text.
   * @param text the text to be assigned to the button
   * @return a JButton instance with the given text on it.
   */
  private JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setFont(new Font("Arial", Font.PLAIN, 16));
    button.setMargin(new Insets(4,4,4,4));
    return button;
  }

  /**
   * This is the private helper method to set up the buttons' functions.
   */
  private void setButtonFunction() {
    prev.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        goBack();
      }
    });

    select.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        callMenu();
      }
    });

    next.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        goNext();
      }
    });

    quit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
  }

  /**
   * This method is to call the repaint() method in both canvas and header, so they can display
   * the updated view to the user.
   */
  private void updatePicture() {
    canvas.repaint();
    header.repaint();
  }

  /**
   * This method is to make this graphic window to display the next snapshot.
   * If there is no next snapshot, an extra window will pop up with the message to remind the user.
   */
  private void goNext() {
    if (currentInd == snapshots.size() - 1) {
      JOptionPane.showMessageDialog(this,
              "End of the photo album. No snapshots to show beyond this one.");
      return;
    }
    currentInd += 1;
    Snapshot tmp = snapshots.get(currentInd);
    canvas.setAlbum(tmp.getShapeInfo());
    header.setHeader(tmp.getId(), tmp.getDescription());
    updatePicture();
  }

  /**
   * This method is to make this graphic window to display the previous snapshot.
   * If there is no previous snapshot, do nothing.
   */
  private void goBack() {
    if (currentInd == 0) {
      return;
    }
    currentInd -= 1;
    Snapshot tmp = snapshots.get(currentInd);
    canvas.setAlbum(tmp.getShapeInfo());
    header.setHeader(tmp.getId(), tmp.getDescription());
    updatePicture();
  }

}
