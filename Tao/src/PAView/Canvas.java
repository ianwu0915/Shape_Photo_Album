package PAView;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import PhotoAlbumModel.IAlbum;
import PhotoAlbumModel.IShape;

/**
 * This Canvas class extends JPanel class and is implemented with a few more methods in order to
 * simulate the graphical display section of the graphic window.
 */
public class Canvas extends JPanel {
  private IAlbum album;

  /**
   * This is the constructor of this class.
   * @param album an album of shape to be display
   */
  public Canvas(IAlbum album) {
    this.album = album;
    this.setPreferredSize(new Dimension(1200, 1000));
  }

  /**
   * This is the setter method for album.
   * @param album a new album to display
   */
  public void setAlbum(IAlbum album) {
    this.album = album;
  }

  /**
   * This overridden paint() method is to paint this canvas with the information
   * in the given album.
   * @param g the <code>Graphics</code> context in which to paint
   */
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    for (IShape shape : album.getPhotos()) {
      double[] color = shape.getColor();
      switch (shape.getType()) {
        case "rectangle":
          Rectangle2D.Double r = new Rectangle2D.Double(shape.getRefPointX(), shape.getRefPointY(), shape.getXValue(), shape.getYValue());
          g2d.setColor(new Color((int) Math.round(color[0] * 255), (int) Math.round(color[1] * 255), (int) Math.round(color[2] * 255)));
          g2d.fill(r);
          break;
        case "oval":
          Ellipse2D.Double e = new Ellipse2D.Double(shape.getRefPointX(), shape.getRefPointY(), shape.getXValue(), shape.getYValue());
          g2d.setColor(new Color((int) Math.round(color[0] * 255), (int) Math.round(color[1] * 255), (int) Math.round(color[2] * 255)));
          g2d.fill(e);
          break;
        default:
      }
    }
  }
}
