package PhotoAlbumModel;

/**
 * This Shape class is simulate a shape object.
 */
public class Shape implements IShape {
  public static final String[] validType = {"rectangle", "oval"};
  private String name;
  private String type;
  private double referencePoint[];
  private double xValue;
  private double yValue;
  private double[] color;

  /**
   * This is the constructor of this class.
   * It is set to private, so the only way to create a instance is through the static factory
   * method.
   * @param name name of this shape instance.
   * @param type the type of this shape instance.
   * @param rPointX x-axis of the reference point.
   * @param rPointY y-axis of the reference point.
   * @param xValue the value describing the shape in x dimension.
   * @param yValue the value describing the shape in y dimension.
   * @param red factor of red color.
   * @param green factor of green color.
   * @param blue factor of blue color.
   */
  private Shape(String name, String type, double rPointX, double rPointY, double xValue,
               double yValue, double red, double green, double blue) {
    this.name = name;
    this.type = type;
    color = new double[] {validColorFactor(red), validColorFactor(green), validColorFactor(blue)};
    referencePoint = new double[] {rPointX, rPointY};
    this.xValue = xValue;
    this.yValue = yValue;
  }

  /**
   * This is the factory method to create a shape instance.
   * It returns null, if any of the given parameters are invalid.
   * @param name name of this shape instance.
   * @param type the type of this shape instance.
   * @param rPointX x-axis of the reference point.
   * @param rPointY y-axis of the reference point.
   * @param xValue the value describing the shape in x dimension.
   * @param yValue the value describing the shape in y dimension.
   * @param red factor of red color.
   * @param green factor of green color.
   * @param blue factor of blue color.
   * @return a shape instance or null if any of the parameters are invalid.
   */
  public static Shape makeShape(String name, String type, double rPointX, double rPointY,
                                double xValue, double yValue, double red, double green,
                                double blue) {
    if (name == null || name.isBlank()) return null;

    if (type == null) return null;

    if (xValue <= 0 || yValue <= 0) return null;

    boolean valid = false;
    for (String each : validType) {
      if (each.equalsIgnoreCase(type)) {
        valid = true;
        break;
      }
    }

    if (!valid) return null;

    return new Shape(name, type.toLowerCase(), rPointX, rPointY, xValue, yValue, red, green, blue);
  }

  /**
   * This is the getter method for name.
   * @return the name of this shape instance.
   */
  public String getName() {
    return name;
  }

  /**
   * This is the getter method for type.
   * @return the type of this shape instance.
   */
  public String getType() {
    return type;
  }

  /**
   * This is the getter method for referencePoint[0].
   * @return the x-axis of the reference point.
   */
  public double getRefPointX() {
    return referencePoint[0];
  }

  /**
   * This is the getter method for referencePoint[1].
   * @return the y-axis of the reference point.
   */
  public double getRefPointY() {
    return referencePoint[1];
  }

  /**
   * This is the setter method for referencePoint.
   * @param rPointX a new value for reference point's x-axis.
   * @param rPointY a new value for reference point's y-axis.
   */
  public void setReferencePoint(double rPointX, double rPointY) {
    referencePoint[0] = rPointX;
    referencePoint[1] = rPointY;
  }

  /**
   * This is the getter method for xValue.
   * @return a value that describes this shape's x dimension.
   */
  public double getXValue() {
    return xValue;
  }

  /**
   * This is the getter method for yValue.
   * @return a value that describes this shape's y dimension.
   */
  public double getYValue() {
    return yValue;
  }

  /**
   * This is the setter method for xValue and yValue.
   * It resizes the shape instance.
   * @param xValue a new value that describes this shape's x dimension.
   * @param yValue a new value that describes this shape's y dimension.
   */
  public void resize(double xValue, double yValue) {
    if (xValue <= 0 || yValue <= 0) return;

    this.xValue = xValue;
    this.yValue = yValue;
  }

  /**
   * This is the getter method for color.
   * It returns a copy of color, so color can't be change from outside.
   * @return a copy of color.
   */
  public double[] getColor() {
    double [] copy = new double[3];

    int i = 0;
    for (double each: color) {
      copy[i++] = each;
    }

    return copy;
  }

  /**
   * This is the setter for color.
   * @param red a new factor of red color.
   * @param green a new factor of green color.
   * @param blue a new factor of blue color.
   */
  public void changeColor(double red, double green, double blue) {

    color[0] = validColorFactor(red);
    color[1] = validColorFactor(green);
    color[2] = validColorFactor(blue);
  }

  /**
   * This method is to copy the current shape instance.
   * @return a copied shape instance with same attributes.
   */
  public Shape copy() {
    double[] tmpColor = getColor();
    Shape copiedShape = new Shape(getName(), getType(), getRefPointX(), getRefPointY(),
            getXValue(), getYValue(), tmpColor[0], tmpColor[1], tmpColor[2]);

    return copiedShape;
  }

  /**
   * This is the private helper to clamp the invalid color factor.
   * Any values less than 0 will be set to 0. Any values more than 1 will be set to 1.
   * @param value a value to be checked.
   * @return a value after clamping.
   */
  private double validColorFactor(double value) {
    if (value < 0) {
      value = 0.0;
    } else if (value > 1) {
      value = 1.0;
    }

    return value;
  }

  /**
   * This is the overridden toString() method.
   * It returns a String representing the information of this shape instance.
   * @return a string of shape's info.
   */
  @Override
  public String toString() {
    String s = String.format("Name: %s\n", getName());
    s += String.format("Type: %s\n", getType());
    double [] tmpColor = getColor();
    switch (type) {
      case "rectangle":
        s += String.format("Min corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, ", getRefPointX(),
                getRefPointY(), getXValue(), getYValue());
        break;
      case "oval":
        s += String.format("Center: (%.1f,%.1f), X radius: %.1f, Y radius: %.1f, ", getRefPointX(),
                getRefPointY(), getXValue(), getYValue());
        break;
      default:
        s += "Wrong type";
    }

    s += String.format("Color: (%.1f,%.1f,%.1f)\n", tmpColor[0], tmpColor[1], tmpColor[2]);

    return s;
  }
}
