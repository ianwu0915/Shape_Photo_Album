package photoalbum.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;

import photoalbum.model.IPhotoAlbumModel;
import photoalbum.model.IShape;
import photoalbum.model.ISnapshot;

/**
 * The PhotoAlbumView class is the View in the MVC design pattern.
 * It represents the user interface for an interactive Graphical view
 */
public class PhotoAlbumView extends JFrame implements ActionListener, IPhotoAlbumView {

  private JComboBox<String> snapshotComboBox;
  private final HashMap<String, Image> snapshotImageList;
  private final List<ISnapshot> snapshotList;
  private final List<Image> imageList;
  private IPhotoAlbumModel photoAlbumModel;
  private final JPanel bottomPanel;
  private final JPanel topPanel;
  private final JPanel middlePanel;
  private final JButton prev;
  private final JButton next;
  private final JButton select;
  private final JButton quit;
  private ISnapshot currentSnapshot;


  public PhotoAlbumView(IPhotoAlbumModel photoAlbumModel) {

    //set the snapshotList as the one from model
    this.photoAlbumModel = photoAlbumModel;
    this.snapshotList = this.photoAlbumModel.getSnapshotList();
    this.snapshotImageList = new HashMap<>();
    this.imageList = new ArrayList<>();

    //Create every snapshot as Image

    this.setTitle("cs5004 Shapes Photo Album Viewer");
    this.setSize(1000, 1000); //sets x and y of frame;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
    this.setVisible(true);

    //ImageIcon image = new ImageIcon(".png"); // create an ImageIcon
    //setIconImage(image.getImage());
    this.getContentPane().setBackground(Color.blue); //change color of background or new Color(r,g,b);
    this.setLayout(new BorderLayout());
    this.currentSnapshot = null;

    topPanel = new JPanel();
    topPanel.setBackground(Color.pink);
    topPanel.setPreferredSize(new Dimension(1000, 60));
    topPanel.setLayout(new BorderLayout());

    middlePanel = new JPanel();
    middlePanel.setBackground(Color.BLUE);
//    middlePanel.setPreferredSize(new Dimension(1000, 800));
    middlePanel.setBounds(0, 25, 1000, 800);
    middlePanel.setLayout(new FlowLayout());

    bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.ORANGE);
    bottomPanel.setPreferredSize(new Dimension(1000, 100));
//    bottomPanel.setBounds(0, 400, 1000, 100);
    bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));

    prev = new JButton("<< Prev <<");
//    prev.setBounds(150, 450, 60, 20 );
    prev.setPreferredSize(new Dimension(120, 30));
    prev.addActionListener(this);

    select = new JButton("^^ Select ^^");
//    prev.setBounds(220, 450, 60, 20 );
    select.setPreferredSize(new Dimension(120, 30));
    select.addActionListener(this);

    next = new JButton(">> Next >>");
//    next.setBounds(290, 450, 60, 20);
    next.setPreferredSize(new Dimension(120, 30));
    next.addActionListener(this);

    quit = new JButton("xx Quit xx");
//    quit.setBounds(360, 450, 60, 20);
    quit.setPreferredSize(new Dimension(120, 30));
    quit.addActionListener(this);

    bottomPanel.add(prev);
    bottomPanel.add(select);
    bottomPanel.add(next);
    bottomPanel.add(quit);

    this.add(topPanel, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.WEST);
    this.add(bottomPanel, BorderLayout.SOUTH);
    topPanel.repaint();
    middlePanel.repaint();
    bottomPanel.repaint();

  }

  /**
   * Gets the middle panel.
   *
   * @return the middle panel
   */
  public JPanel getMiddlePanel() {
    return middlePanel;
  }

  /**
   * Gets the current snapshot.
   *
   * @return the current snapshot
   */
  public ISnapshot getCurrentSnapshot() {
    return currentSnapshot;
  }

  public void setCurrentSnapshot(ISnapshot currentSnapshot) {
    this.currentSnapshot = currentSnapshot;
  }

  /**
   * Gets the list of snapshots.
   *
   * @return the list of snapshots.
   */
  public List<ISnapshot> getSnapshotList() {
    return snapshotList;
  }

  /**
   * Gets the map of snapshot images.
   *
   * @return the map of snapshot images.
   */
  public HashMap<String, Image> getSnapshotImageList() {
    return snapshotImageList;
  }

  /**
   * Gets the list of images.
   *
   * @return the list of images.
   */
  public List<Image> getImageList() {
    return imageList;
  }

  /**
   * Sets the photo album model.
   *
   * @param model the photo album model.
   */
  public void setPhotoAlbumModel(IPhotoAlbumModel model) {
    this.photoAlbumModel = model;
  }

  /**
   * Gets the photo album model.
   *
   * @return the photo album model.
   */
  public IPhotoAlbumModel getPhotoAlbumModel() {
    return photoAlbumModel;
  }

  /**
   * Creates a graph from the list of shapes.
   *
   * @param shapes the list of shapes.
   * @return the graph image.
   */
  public Image createGraphs(List<IShape> shapes) {

    BufferedImage image = new BufferedImage(middlePanel.getWidth(), middlePanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
//    BufferedImage image = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.getGraphics();

    // draw the shapes
    for (IShape shape : shapes) {
      int red = shape.getColor().getR();
      int green = shape.getColor().getG();
      int blue = shape.getColor().getB();

      g.setColor(new Color(red, green, blue));

      switch (shape.getType()) {
        case "rectangle":
          g.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  shape.getWidth(), shape.getHeight());
          break;
        case "oval":
          double x = shape.getPosition().getX() - shape.getWidth();
          double y = shape.getPosition().getY() - shape.getHeight();
          g.fillOval((int) x, (int) y,
                  shape.getWidth() * 2, shape.getHeight() * 2);
          break;
        default:
          System.err.println("Unknown shape kind: " + shape.getType());
      }
    }
    return image;
  }

  /**
   * This function returns the key of a specified value in the hashmap.
   */

  public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
    for (Map.Entry<K, V> entry : map.entrySet()) {
      if (Objects.equals(value, entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == quit) {
      System.exit(0);
    }

    if (e.getSource() == next) {
      int i = this.imageList.indexOf(this.snapshotImageList.get(currentSnapshot.getID()));

      if (i < imageList.size() - 1) {
        JLabel label = new JLabel(new ImageIcon(this.imageList.get(i + 1)));
        middlePanel.removeAll();
        middlePanel.add(label, BorderLayout.CENTER);
        middlePanel.revalidate();
        middlePanel.repaint();

        //update the currentSnapshot
        String currentId = getKeyByValue(this.snapshotImageList, this.imageList.get(i + 1));
        currentSnapshot = photoAlbumModel.getSnapshot(currentId);
        this.view();
      } else {
        JOptionPane.showMessageDialog(this,
                "There is no next snapshot", "Warning",
                JOptionPane.WARNING_MESSAGE);
      }
    }

    if (e.getSource() == prev) {

      int i = this.imageList.indexOf(this.snapshotImageList.get(currentSnapshot.getID()));

      if (i > 0) {
        JLabel label = new JLabel(new ImageIcon(this.imageList.get(i - 1)));
        middlePanel.removeAll();
        middlePanel.add(label, BorderLayout.CENTER);
        middlePanel.revalidate();
        middlePanel.repaint();

        //update teh currentSnapshot
        String currentId = getKeyByValue(this.snapshotImageList, this.imageList.get(i - 1));
        currentSnapshot = photoAlbumModel.getSnapshot(currentId);
        this.view();
      } else {
        JLabel message = new JLabel("There is no previous snapshot", SwingConstants.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(message, BorderLayout.CENTER);
        // show warning message with custom component
        JOptionPane.showMessageDialog(this.getParent(), panel, "Warning", JOptionPane.WARNING_MESSAGE);
      }
    }

    if (e.getSource() == select) {

      //create a combo-box to choose the snapshot of choice
      String[] options = photoAlbumModel.getSnapshotIDList().toArray(new String[0]);
      snapshotComboBox = new JComboBox<String>(options);
      snapshotComboBox.setPreferredSize(new Dimension(150, 50));
      snapshotComboBox.setMaximumRowCount(10);

      int result = javax.swing.JOptionPane.showConfirmDialog(this, snapshotComboBox,
              "Select Snapshot", javax.swing.JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.PLAIN_MESSAGE, null);

      if (result == javax.swing.JOptionPane.OK_OPTION) {
        // get the selected
        String selectedSnapshotId = (String) snapshotComboBox.getSelectedItem();
        // get the snapshot by selecting the id;
        //display the one chosen

        this.currentSnapshot = photoAlbumModel.getSnapshot(selectedSnapshotId);
        JLabel label = new JLabel(new ImageIcon(this.snapshotImageList.get(selectedSnapshotId)));
        middlePanel.removeAll();
        middlePanel.add(label, BorderLayout.CENTER);
        middlePanel.revalidate();
        middlePanel.repaint();
        this.view();
      }
    }
  }

  /**
   * Create a text area for the description of snapshot at the top panel.
   */
  public void view() {
    JTextArea textArea = new JTextArea(currentSnapshot.getID() + "\n"
            + currentSnapshot.getDescription());
    Font font = new Font(textArea.getFont().getName(), Font.PLAIN, 20);
    textArea.setFont(font);
    textArea.setBackground(Color.PINK);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    topPanel.removeAll();
    topPanel.add(textArea, BorderLayout.CENTER);
    topPanel.revalidate();
    topPanel.repaint();
  }
}
