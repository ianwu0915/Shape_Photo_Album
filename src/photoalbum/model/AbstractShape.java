package photoalbum.model;

/**
 * The {@code AbstractShape} class is an abstract class
 * that implements the {@code IShape} interface.
 * It provides common functionality for all shapes such as name, type, and color.
 * It also includes a method to check the validity of the name.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected String type;
  protected Color color;

  /**
   * Constructs a new {@code AbstractShape} object with the given name and color.
   *
   * @param name  the name of the shape
   * @param color the color of the shape
   * @throws IllegalArgumentException if the name is null or empty
   */
  public AbstractShape(String name, Color color) {
    checkName(name);
    checkColor(color);
    this.name = name;
    this.color = color;
  }

  /**
   * Moves the shape to the given position.
   *
   * @param x the new x-coordinate of the shape
   * @param y the new y-coordinate of the shape
   */
  public abstract void move(double x, double y);

  /**
   * Resizes the shape to the given width and height.
   *
   * @param width  the new width of the shape
   * @param height the new height of the shape
   */
  public abstract void reSize(int width, int height);

  /**
   * Changes the color of the shape to the given color.
   *
   * @param color the new color of the shape
   */
  public abstract void changeColor(Color color);

  /**
   * Checks the validity of the given name.
   *
   * @param name the name to check
   * @throws IllegalArgumentException if the name is null or empty
   */
  public void checkName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
  }

  /**
   * Checks the validity of the given color.
   *
   * @param color the color to check
   * @throws IllegalArgumentException if the color is null
   */
  public void checkColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
  }

}
