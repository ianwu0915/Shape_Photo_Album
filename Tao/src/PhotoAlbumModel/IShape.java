package PhotoAlbumModel;

/**
 * This IShape interface is the super-type of all shape objects.
 */
public interface IShape {
  /**
   * Implements getName() method.
   * @return the name of this instance.
   */
  String getName();

  /**
   * Implements getType() method.
   * @return the type of this instance.
   */
  String getType();

  /**
   * Implements getRefPointX() method.
   * @return x-axis of the reference point.
   */
  double getRefPointX();

  /**
   * Implements getRefPointY() method.
   * @return y-axis of the reference point.
   */
  double getRefPointY();

  /**
   * Implements setReferencePoint() method.
   * It moves the instance.
   * @param x a new value for x-axis.
   * @param y a new value for y-axis.
   */
  void setReferencePoint(double x, double y);

  /**
   * Implements getXValue() method.
   * @return a value that describes this instance's x dimension.
   */
  double getXValue();

  /**
   * Implements getYValue() method.
   * @return a value that describes this instance's y dimension.
   */
  double getYValue();

  /**
   * Implements resize() method.
   * It resizes the instance.
   * @param x a new value to describes this instance's x dimension.
   * @param y a new value to describes this instance's y dimension.
   */
  void resize(double x, double y);

  /**
   * Implements getColor() method.
   * @return a double array representing the instance's color.
   */
  double[] getColor();

  /**
   * Implements changeColor() method.
   * It changes the color of the instance.
   * @param r a new factor for red color.
   * @param g a new factor for green color.
   * @param b a new factor for blue color.
   */
  void changeColor(double r, double g, double b);

  /**
   * Implement copy() method.
   * It produces a copy of this instance.
   * @return a copy of this instance.
   */
  Shape copy();
}
