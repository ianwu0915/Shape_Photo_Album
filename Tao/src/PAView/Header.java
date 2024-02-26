package PAView;

import java.awt.*;
import javax.swing.*;

/**
 * This Header class extends JPanel class and is implemented with a few more methods in order to
 * simulate the header section in the graphic window.
 */
public class Header extends JPanel {
  private JLabel display;

  /**
   * This is the constructor of this class.
   * @param id the id of the snapshot
   * @param description the description of the snapshot
   */
  public Header(String id, String description) {
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.setBackground(new Color(0xffcad4));
    this.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    this.display = new JLabel();
    setHeader(id, description);

    this.add(this.display);
  }

  /**
   * This the setter method for display.
   * @param id the id of the snapshot
   * @param description the description of the snapshot
   */
  public void setHeader(String id, String description) {
    this.display.setText("<html>" + id + "<br>" + description + "</html>");
  }

}
