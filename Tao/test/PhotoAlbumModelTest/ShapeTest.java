package PhotoAlbumModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import PhotoAlbumModel.Shape;

/**
 * This ShapeTest class is to test all the built-in methods in Shape class.
 */
public class ShapeTest {
  private Shape rectangle;
  private Shape oval;

  /**
   * This method sets up a few instances for the following tests.
   */
  @Before
  public void setUp() {
    rectangle = Shape.makeShape("R", "rectangle", 100.0, 200.0,
            20.0, 89.2, 1.0, 0.0, 0.0);
    oval = Shape.makeShape("OVAL", "oval", -500.0, 44.4, 60.0,
            25.3, 0.5, 0.5, 1.0);
  }

  /**
   * This method is to test makeShape() method with invalid inputs.
   * (Passing null as name)
   */
  @Test
  public void testMakeShapeWithInvalidInput1() {
    Shape test = Shape.makeShape(null, "rectangle", 100.0, 200.0,
            20.0, 89.2, 1.0, 0.0, 0.0);
    assertNull(test);
  }

  /**
   * This method is to test makeShape() method with invalid inputs.
   * (Passing empty name)
   */
  @Test
  public void testMakeShapeWithInvalidInput2() {
    Shape test = Shape.makeShape(" ", "rectangle", 100.0, 200.0,
            20.0, 89.2, 1.0, 0.0, 0.0);
    assertNull(test);
  }

  /**
   * This method is to test makeShape() method with invalid inputs.
   * (Passing null as type)
   */
  @Test
  public void testMakeShapeWithInvalidInput3() {
    Shape test = Shape.makeShape("invalid", null, 100.0, 200.0,
            20.0, 89.2, 1.0, 0.0, 0.0);
    assertNull(test);
  }

  /**
   * This method is to test makeShape() method with invalid inputs.
   * (Passing invalid type)
   */
  @Test
  public void testMakeShapeWithInvalidInput4() {
    Shape test = Shape.makeShape(null, "triangle", 100.0, 200.0,
            20.0, 89.2, 1.0, 0.0, 0.0);
    assertNull(test);
  }

  /**
   * This method is to test makeShape() method with invalid inputs.
   * (Passing empty type)
   */
  @Test
  public void testMakeShapeWithInvalidInput5() {
    Shape test = Shape.makeShape(null, "", 100.0, 200.0,
            20.0, 89.2, 1.0, 0.0, 0.0);
    assertNull(test);
  }

  /**
   * This method is to test makeShape() method with invalid inputs.
   * (Passing negative xValue)
   */
  @Test
  public void testMakeShapeWithInvalidInput6() {
    Shape test = Shape.makeShape(null, "rectangle", 100.0, 200.0,
            -20.0, 89.2, 1.0, 0.0, 0.0);
    assertNull(test);
  }

  /**
   * This method is to test getName() method.
   */
  @Test
  public void testGetName() {
    assertEquals("R", rectangle.getName());
    assertEquals("OVAL", oval.getName());
  }

  /**
   * This method is to test getType() method.
   */
  @Test
  public void testGetType() {
    assertEquals("rectangle", rectangle.getType());
    assertEquals("oval", oval.getType());
  }

  /**
   * This method is to test getRefPointX() method.
   */
  @Test
  public void testGetRefPointX() {
    assertEquals(100.0, rectangle.getRefPointX(), 0.01);
    assertEquals(-500.0, oval.getRefPointX(), 0.01);
  }

  /**
   * This method is to test getRefPointY() method.
   */
  @Test
  public void testGetRefPointY() {
    assertEquals(200.0, rectangle.getRefPointY(), 0.01);
    assertEquals(44.4, oval.getRefPointY(), 0.01);
  }

  /**
   * This method is to test setReferencePoint() method.
   */
  @Test
  public void testSetReferencePoint() {
    rectangle.setReferencePoint(1.1, -2.2);
    assertEquals(1.1, rectangle.getRefPointX(), 0.01);
    assertEquals(-2.2, rectangle.getRefPointY(), 0.01);

    oval.setReferencePoint(3.14, 159.26);
    assertEquals(3.14, oval.getRefPointX(), 0.01);
    assertEquals(159.26, oval.getRefPointY(), 0.01);
  }

  /**
   * This method is to test getXValue() method.
   */
  @Test
  public void testGetXValue() {
    assertEquals(20.0, rectangle.getXValue(), 0.01);
    assertEquals(60.0, oval.getXValue(), 0.01);
  }

  /**
   * This method is to test getYValue() method.
   */
  @Test
  public void testGetYValue() {
    assertEquals(89.2, rectangle.getYValue(), 0.01);
    assertEquals(25.3, oval.getYValue(), 0.01);
  }

  /**
   * This method is to test resize() method.
   */
  @Test
  public void testResize() {
    rectangle.resize(55.65, 123456.78);
    assertEquals(55.65, rectangle.getXValue(), 0.01);
    assertEquals(123456.78, rectangle.getYValue(), 0.01);

    // Invalid resizing
    oval.resize(-0.01, 50.3);
    assertEquals(60.0, oval.getXValue(), 0.01);
    assertEquals(25.3, oval.getYValue(), 0.01);
  }

  /**
   * This method is to test getColor() method.
   */
  @Test
  public void testGetColor() {
    double[] tmp = rectangle.getColor();
    assertEquals(1.0, tmp[0], 0.01);
    assertEquals(0.0, tmp[1], 0.01);
    assertEquals(0.0, tmp[2], 0.01);

    tmp = oval.getColor();

    assertEquals(0.5, tmp[0], 0.01);
    assertEquals(0.5, tmp[1], 0.01);
    assertEquals(1.0, tmp[2], 0.01);
  }

  /**
   * This method is to test changeColor() method.
   */
  @Test
  public void testChangeColor() {
    rectangle.changeColor(-0.05, 0.89, 1.05);
    double[] tmp = rectangle.getColor();
    // Clamp the number to 0
    assertEquals(0.0, tmp[0], 0.01);
    assertEquals(0.89, tmp[1], 0.01);
    // Clamp the number to 1.0
    assertEquals(1.0, tmp[2], 0.01);
  }

  /**
   * This method is to test copy() method.
   */
  @Test
  public void testCopy() {
    Shape test = oval.copy();
    assertEquals(oval.getName(), test.getName());
    assertEquals(oval.getType(), test.getType());
    assertEquals(oval.getRefPointX(), test.getRefPointX(), 0.01);
    assertEquals(oval.getRefPointY(), test.getRefPointY(), 0.01);
    assertEquals(oval.getXValue(), test.getXValue(), 0.01);
    assertEquals(oval.getYValue(), test.getYValue(), 0.01);
    double[] tmp1 = oval.getColor();
    double[] tmp2 = test.getColor();

    for (int i = 0; i < 3; i++) {
      assertEquals(tmp1[i], tmp2[i], 0.01);
    }
  }

  /**
   * This method is to test toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (100.0,200.0), Width: 20.0, Height: 89.2, Color: (1.0,0.0,0.0)\n",
            rectangle.toString());

    assertEquals("Name: OVAL\n"
            + "Type: oval\n"
            + "Center: (-500.0,44.4), X radius: 60.0, Y radius: 25.3, Color: (0.5,0.5,1.0)\n",
            oval.toString());
  }
}