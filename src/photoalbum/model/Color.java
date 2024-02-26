package photoalbum.model;

import java.util.Objects;

/**
 * Represents a color in RGB format.
 */
public class Color {

  private int r; // The red component of the color.
  private int g; // The green component of the color.
  private int b; // The blue component of the color.

  /**
   * Creates a new Color object with the given RGB values.
   *
   * @param r The red component of the color.
   * @param g The green component of the color.
   * @param b The blue component of the color.
   * @throws IllegalArgumentException If any of the RGB values are outside the range of 0 to 255.
   */
  public Color(int r, int g, int b) {
    if (r < 0 || r > 255) {
      throw new IllegalArgumentException("Enter the value between 0 and 255!");
    }
    if (g < 0 || g > 255) {
      throw new IllegalArgumentException("Enter the value between 0 and 255!");
    }
    if (b < 0 || b > 255) {
      throw new IllegalArgumentException("Enter the value between 0 and 255!");
    }

    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the red component of the color.
   *
   * @return The red component of the color.
   */
  public int getR() {
    return r;
  }

  /**
   * Sets the red component of the color.
   *
   * @param r The new red component of the color.
   */
  public void setR(int r) {
    this.r = r;
  }

  /**
   * Returns the green component of the color.
   *
   * @return The green component of the color.
   */
  public int getG() {
    return g;
  }

  /**
   * Sets the green component of the color.
   *
   * @param g The new green component of the color.
   */
  public void setG(int g) {
    this.g = g;
  }

  /**
   * Returns the blue component of the color.
   *
   * @return The blue component of the color.
   */
  public int getB() {
    return b;
  }

  /**
   * Sets the blue component of the color.
   *
   * @param b The new blue component of the color.
   */
  public void setB(int b) {
    this.b = b;
  }

  /**
   * Compares this Color object to another object for equality.
   *
   * @param o The object to compare to.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Color color = (Color) o;
    return getR() == color.getR() && getG() == color.getG() && getB() == color.getB();
  }

  /**
   * Returns a hash code for this Color object.
   *
   * @return A hash code for this Color object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getR(), getG(), getB());
  }

  /**
   * Returns a String representation of this Color object.
   *
   * @return A String representation of this Color object.
   */
  @Override
  public String toString() {
    return String.format("(%d, %d, %d)", this.r, this.g, this.b);
  }
}
