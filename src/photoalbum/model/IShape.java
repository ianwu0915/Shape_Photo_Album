package photoalbum.model;

/**
 * The {@code IShape} interface represents a geometric shape.
 * A shape has a name, a color, a type, a position, a width, and a height.
 * It can be resized, moved, and have its color changed.
 */
public interface IShape {

  /**
   * Returns the name of the shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Returns the color of the shape.
   *
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Returns the type of the shape.
   *
   * @return the type of the shape
   */
  String getType();

  /**
   * Returns the position of the shape.
   *
   * @return the position of the shape
   */
  Point getPosition();

  /**
   * Returns the width of the shape.
   *
   * @return the width of the shape
   */
  int getWidth();

  /**
   * Returns the height of the shape.
   *
   * @return the height of the shape
   */
  int getHeight();

  /**
   * Resize the shape to the given width and height.
   *
   * @param width  the new width of the shape
   * @param height the new height of the shape
   */

  void reSize(int width, int height);

  /**
   * Move ths shape to the given position.
   *
   * @param x the new x-coordinate
   * @param y the new y-coordinate
   */

  void move(double x, double y);

  /**
   * Changes the color of the shape to the given color.
   *
   * @param color the new color of the shape
   */

  void changeColor(Color color);
}