package photoalbum.model;

import java.util.Objects;

/**
 * A class represents a rectangle shape extends from AbstractShape.
 */
public class rectangle extends AbstractShape {
  private Point corner;
  private int width;
  private int height;

  /**
   * Constructs a rectangle object with the given name, color, corner point, width, and height.
   *
   * @param name   the name of the rectangle
   * @param color  the color of the rectangle
   * @param corner the coordinates of the top-left corner of the rectangle
   * @param width  the width of the rectangle
   * @param height the height of the rectangle
   */
  public rectangle(String name, Color color,
                   Point corner, int width, int height) {
    super(name, color);
    checkWidth(width);
    checkHeight(height);
    checkCorner(corner);
    this.type = "rectangle";
    this.corner = corner;
    this.width = width;
    this.height = height;
  }

  /**
   * Move the rectangle by assigning corner to the given position.
   *
   * @param x the new x-coordinate of the corner
   * @param y the new y-coordinate of teh corner
   */
  @Override
  public void move(double x, double y) {
    corner.setX(x);
    corner.setY(y);
  }

  /**
   * Resize the rectangle to the given width and height.
   *
   * @param width  the new width of the rectangle
   * @param height the new height of the rectangle
   */
  @Override
  public void reSize(int width, int height) {
    if (width <= 0) {
      throw new IllegalArgumentException("new width cannot be zero or negative");
    }

    if (height <= 0) {
      throw new IllegalArgumentException("new height cannot be zero or negative");
    }

    this.width = width;
    this.height = height;
  }

  /**
   * Changes the color of the rectangle to the given color.
   *
   * @param color the new color of the rectangle
   */
  @Override
  public void changeColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("New color cannot be null");
    }
    this.color = color;
  }

  /**
   * Returns the name of the rectangle.
   *
   * @return the name of the rectangle
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns the color of the rectangle.
   *
   * @return the color of the rectangle
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Returns the type of the rectangle.
   *
   * @return the type of the rectangle
   */
  @Override
  public String getType() {
    return this.type;
  }

  /**
   * Returns the position of the rectangle.
   *
   * @return the position of the rectangle
   */
  @Override
  public Point getPosition() {
    return this.corner;
  }

  /**
   * Returns the width of the rectangle.
   *
   * @return the width of the rectangle
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the rectangle.
   *
   * @return the height of the rectangle
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Comparing this rectangle with another object for equality.
   * Returns true if and only if the specified object is
   * also a rectangle, both rectangles have the same corner, width and height.
   *
   * @param o tbe object to be compared for the equality.
   * @return true if it is equal to this rectangle
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    rectangle rectangle = (rectangle) o;
    return Double.compare(rectangle.getWidth(), getWidth()) == 0
            && Double.compare(rectangle.getHeight(), getHeight()) == 0
            && Objects.equals(corner, rectangle.corner);
  }

  /**
   * Returns a hash code value for this rectangle.
   *
   * @return a hash code value for this rectangle.
   */
  @Override
  public int hashCode() {
    return Objects.hash(corner, getWidth(), getHeight());
  }

  /**
   * Return a string containing the info of the rectangle.
   *
   * @return info of the rectangle
   */
  @Override
  public String toString() {
    return String.format("""
            Name: %s
            Type: %s
            Min corner: %s, Width: %d, Height: %d, Color: %s\n
            """, name, type, corner, width, height, color);
  }

  /**
   * Checks the validity of the given width.
   *
   * @param width the width to check
   * @throws IllegalArgumentException if the color is null
   */
  public void checkWidth(double width) {
    if (width <= 0) {
      throw new IllegalArgumentException("width must be larger than 0");
    }
  }

  /**
   * Checks the validity of the given height.
   *
   * @param height the height to check
   * @throws IllegalArgumentException if the color is null
   */
  public void checkHeight(double height) {
    if (height <= 0) {
      throw new IllegalArgumentException("height must be larger than 0");
    }
  }

  /**
   * Checks the validity of the given corner.
   *
   * @param corner the corner to check
   * @throws IllegalArgumentException if the corner is null
   */
  public void checkCorner(Point corner) {
    if (corner == null) {
      throw new IllegalArgumentException("corner cannot be null");
    }
  }

}


