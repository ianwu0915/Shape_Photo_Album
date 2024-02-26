package photoalbum.model;

import java.util.Objects;

/**
 * A class represents a oval shape extends from AbstractShape.
 */
public class oval extends AbstractShape {
  private Point center;
  private int xDiameter;
  private int yDiameter;

  /**
   * Constructs an oval object with the given name, color, corner point, width, and height.
   *
   * @param name    the name of the oval
   * @param color   the color of the oval
   * @param center  the coordinates of the center of the oval
   * @param xDiameter the width of the oval
   * @param yDiameter the height of the oval
   */

  public oval(String name, Color color,
              Point center, int xDiameter, int yDiameter) {
    super(name, color);
    checkxDiameter(xDiameter);
    checkyDiameter(yDiameter);
    checkCenter(center);
    this.type = "oval";
    this.center = center;
    this.xDiameter = xDiameter;
    this.yDiameter = yDiameter;
  }

  /**
   * Move the oval by assigning center to the given position.
   *
   * @param x the new x-coordinate of the center
   * @param y the new y-coordinate of teh center
   */
  @Override
  public void move(double x, double y) {
    center.setX(x);
    center.setY(y);
  }

  /**
   * Resize the oval to the given xDiameter and yDiameter.
   *
   * @param width  the new xDiameter of the oval
   * @param height the new yDiameter of the oval
   */
  @Override
  public void reSize(int width, int height) {
    if (width <= 0) {
      throw new IllegalArgumentException("new x diameter cannot be zero or negative");
    }

    if (height <= 0) {
      throw new IllegalArgumentException("new y diameter cannot be zero or negative");
    }
    this.xDiameter = width;
    this.yDiameter = height;
  }

  /**
   * Changes the color of the oval to the given color.
   *
   * @param color the new color of the oval
   */
  @Override
  public void changeColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("New color cannot be null");
    }
    this.color = color;
  }

  /**
   * Returns the xDiameter of the oval.
   *
   * @return the xDiameter nof the oval
   */
  public double getxDiameter() {
    return this.xDiameter;
  }

  /**
   * Returns the yDiameter of the oval.
   *
   * @return the yDiameter of the oval
   */
  public double getyDiameter() {
    return this.yDiameter;
  }

  /**
   * Returns the name of the oval.
   *
   * @return the name of the oval
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns the color of the oval.
   *
   * @return the colr of the oval
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Returns the type of the oval.
   *
   * @return the type of the oval
   */
  @Override
  public String getType() {
    return this.type;
  }

  /**
   * Returns the position of the oval.
   *
   * @return the position of the oval
   */
  @Override
  public Point getPosition() {
    return this.center;
  }

  /**
   * Returns the width of the oval.
   *
   * @return the width of the oval
   */
  @Override
  public int getWidth() {
    return this.xDiameter;
  }

  /**
   * Returns the height of the oval.
   *
   * @return the height of the oval
   */
  @Override
  public int getHeight() {
    return this.yDiameter;
  }

  /**
   * Comparing this oval with another object for equality.
   * Returns true if and only if the specified object is
   * also an oval, both ovals have the same center, xDiameter and yDiameter.
   *
   * @param o tbe object to be compared for the equality.
   * @return true if it is equal to this oval
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    oval oval = (oval) o;
    return Double.compare(oval.getxDiameter(), getxDiameter()) == 0
            && Double.compare(oval.getyDiameter(), getyDiameter()) == 0
            && Objects.equals(center, oval.center);
  }

  /**
   * Returns a hash code value for this oval.
   *
   * @return a hash code value for this oval.
   */
  @Override
  public int hashCode() {
    return Objects.hash(center, getxDiameter(), getyDiameter());
  }

  /**
   * Return a string containing the info of the oval.
   *
   * @return info of the oval
   */
  @Override
  public String toString() {
    return String.format("""
            Name: %s
            Type: %s
            Center: %s, X radius: %d, Y radius: %d, Color: %s\n
            """, name, type, center, xDiameter, yDiameter, color.toString());
  }

  /**
   * Checks the validity of the given x Radius.
   *
   * @param xDiameter the width to check
   * @throws IllegalArgumentException if the color is null
   */
  public void checkxDiameter(double xDiameter) {
    if (xDiameter <= 0) {
      throw new IllegalArgumentException("width must be larger than 0");
    }
  }

  /**
   * Checks the validity of the given y Radius.
   *
   * @param yDiameter the height to check
   * @throws IllegalArgumentException if the color is null
   */
  public void checkyDiameter(double yDiameter) {
    if (yDiameter <= 0) {
      throw new IllegalArgumentException("height must be larger than 0");
    }
  }

  /**
   * Checks the validity of the given center.
   *
   * @param center the center to check
   * @throws IllegalArgumentException if the corner is null
   */
  public void checkCenter(Point center) {
    if (center == null) {
      throw new IllegalArgumentException("center cannot be null");
    }
  }

}


