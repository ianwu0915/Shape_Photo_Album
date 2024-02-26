package modelTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import photoalbum.model.Color;
import photoalbum.model.IShape;
import photoalbum.model.Point;
import photoalbum.model.oval;
import photoalbum.model.rectangle;

/**
 * This is a JUnit test for the shape class.
 */

public class ShapeTest {
  private IShape rect1;
  private IShape rect2;
  private IShape oval1;
  private IShape oval2;
  private Color red;
  private Color green;
  private Point point1;
  private Point point2;

  /**
   * Set up color, point and rectangle, oval objects for the test
   */
  @Before
  public void setUp() {
    red = new Color(1, 0, 0);
    green = new Color(0, 1, 0);
    point1 = new Point(0, 0);
    point2 = new Point(20, 20);
    rect1 = new rectangle("R", red, point1, 10, 20 );
    oval1 = new oval("O", green, point2, 15, 18);
  }

  /**
   * Test invalid input: null name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nameIsNull() {
    rect2 = new rectangle(null, red, point1, 10, 20 );

  }

  /**
   * Test invalid input: empty name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nameIsEmpty() {
    rect2 = new rectangle("", red, point1, 10, 20 );
  }

  /**
   * Test invalid input: null color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void colorIsNull() {
    rect2 = new rectangle("", null, point1, 10, 20 );
  }

  /**
   * Test invalid input: negative width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void WidthIsNegative() {
    rect2 = new rectangle("", null, point1, -1, 20 );
  }

  /**
   * Test invalid input: negative height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void HeightIsNegative() {
    rect2 = new rectangle("", null, point1, 20, -1 );
  }

  /**
   * Test invalid input: null corner.
   */
  @Test(expected = IllegalArgumentException.class)
  public void cornerIsNull() {
    rect2 = new rectangle("R", red, null, 20, -1 );
  }

  /**
   * Test move().
   */
  @Test
  public void move(){
    rect1.move(10,10);
    Assert.assertEquals(10, rect1.getPosition().getX(), 0.0001);
    Assert.assertEquals(10, rect1.getPosition().getY(), 0.0001);

    oval1.move(10,10);
    Assert.assertEquals(10, oval1.getPosition().getX(), 0.0001);
    Assert.assertEquals(10, oval1.getPosition().getY(), 0.0001);
  }

  /**
   * Test resize().
   */
  @Test
  public void reSize(){
    rect1.reSize(100,100);
    Assert.assertEquals(100, rect1.getWidth(), 0.0001);
    Assert.assertEquals(100, rect1.getHeight(), 0.0001);

    oval1.reSize(100,100);
    Assert.assertEquals(100, oval1.getWidth(), 0.0001);
    Assert.assertEquals(100, oval1.getHeight(), 0.0001);
  }

  /**
   * Test resize(): invalid width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void inValidResize1() {
    rect1.reSize(-100,100);
  }

  /**
   * Test resize(): invalid height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void inValidResize2() {
    rect1.reSize(100,-100);
  }

  /**
   * Test changeColor().
   */
  @Test
  public void changeColor(){
    Color blue = new Color(0,0, 1);
    rect1.changeColor(blue);
    Assert.assertEquals("(0.0, 0.0, 1.0)", rect1.getColor().toString());
    oval1.changeColor(blue);
    Assert.assertEquals("(0.0, 0.0, 1.0)", oval1.getColor().toString());
  }

  /**
   * Test changeColor(): null color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidChangeColor(){
    rect1.changeColor(null);
  }

  /**
   * Test toString().
   */
  @Test
  public void toStringTest() {
    Assert.assertEquals("""
            Name: R
            Type: rectangle
            Min corner: (0.0, 0.0), Width: 10, Height: 20, Color: (1.0, 0.0, 0.0)\n
            """, rect1.toString());

    Assert.assertEquals("""
            Name: O
            Type: oval
            Center: (20.0, 20.0), X radius: 15, Y radius: 18, Color: (0.0, 1.0, 0.0)\n
            """, oval1.toString());

    rect1.move(10, 10);
    rect1.reSize(100,100);
    Color blue = new Color(0,0, 1);
    rect1.changeColor(blue);
    Assert.assertEquals("""
            Name: R
            Type: rectangle
            Min corner: (10.0, 10.0), Width: 100, Height: 100, Color: (0.0, 0.0, 1.0)\n
            """, rect1.toString());

    oval1.move(10, 10);
    oval1.reSize(100,100);
    oval1.changeColor(blue);
    Assert.assertEquals("""
            Name: O
            Type: oval
            Center: (10.0, 10.0), X radius: 50, Y radius: 50, Color: (0.0, 0.0, 1.0)\n
            """, oval1.toString());
  }
}
