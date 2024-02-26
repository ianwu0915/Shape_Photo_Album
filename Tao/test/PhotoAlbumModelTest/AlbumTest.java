package PhotoAlbumModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import PhotoAlbumModel.*;

/**
 * This AlbumTest class is to test all the built-in methods in Album class.
 */
public class AlbumTest {
  private Album a1;
  private IShape shape1;
  private IShape shape2;
  private IShape shape3;

  /**
   * This method stes up a few instance for the following tests.
   */
  @Before
  public void setUp() {
    a1 = new Album();
    shape1 = Shape.makeShape("shape1", "rectangle", 25.0, 50.0,
            120.0, 255.34, 0.9, 0.2, 0.7);
    shape2 = Shape.makeShape("shape2", "oval", -125.0, -54.32,
            23.43, 1999.89, 0.5, 0.0, 0.5);
    shape3 = Shape.makeShape("shape3", "rectangle", 100.0, 300.0,
            0.01, 0.01, 0.0, 0.5, 0.0);
  }

  /**
   * This method is to test addPhoto() method.
   */
  @Test
  public void testAddPhoto() {
    a1.addPhoto(shape1);

    assertEquals("Name: shape1\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0,50.0), Width: 120.0, Height: 255.3, Color: (0.9,0.2,0.7)\n",
            a1.toString());

    a1.addPhoto(shape3);

    assertEquals("Name: shape1\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0,50.0), Width: 120.0, Height: 255.3, Color: (0.9,0.2,0.7)\n"
            + "\n"
            + "Name: shape3\n"
            + "Type: rectangle\n"
            + "Min corner: (100.0,300.0), Width: 0.0, Height: 0.0, Color: (0.0,0.5,0.0)\n",
            a1.toString());

    a1.addPhoto(shape2);

    assertEquals("Name: shape1\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0,50.0), Width: 120.0, Height: 255.3, Color: (0.9,0.2,0.7)\n"
            + "\n"
            + "Name: shape3\n"
            + "Type: rectangle\n"
            + "Min corner: (100.0,300.0), Width: 0.0, Height: 0.0, Color: (0.0,0.5,0.0)\n"
            + "\n"
            + "Name: shape2\n"
            + "Type: oval\n"
            + "Center: (-125.0,-54.3), X radius: 23.4, Y radius: 1999.9, Color: (0.5,0.0,0.5)\n",
            a1.toString());
  }

  /**
   * This method is to test addPhoto() method with invalid input.
   */
  @Test
  public void testInvalidAddPhoto() {
    a1.addPhoto(null);

    assertEquals("", a1.toString());
  }

  /**
   * This method is to test getPhoto() method.
   */
  @Test
  public void testGetPhoto() {
    a1.addPhoto(shape3);
    a1.addPhoto(shape2);
    a1.addPhoto(shape2);

    IShape tmp = a1.getPhoto("shape3");
    assertEquals(shape3.toString(), tmp.toString());

    // getPhoto() only return the IShape instance matching the given name at first occurrence.
    tmp = a1.getPhoto("shape2");
    assertEquals(shape2.toString(), tmp.toString());

    // Return null if it can't find the instance matching the name.
    tmp = a1.getPhoto("shape1");
    assertNull(tmp);

    tmp = a1.getPhoto(null);
    assertNull(tmp);
  }

  /**
   * This method is to test deletePhoto() method.
   */
  @Test
  public void testDeletePhoto() {
    a1.addPhoto(shape1);
    a1.addPhoto(shape2);
    a1.addPhoto(shape1);

    assertEquals("Name: shape1\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0,50.0), Width: 120.0, Height: 255.3, Color: (0.9,0.2,0.7)\n"
            + "\n"
            + "Name: shape2\n"
            + "Type: oval\n"
            + "Center: (-125.0,-54.3), X radius: 23.4, Y radius: 1999.9, Color: (0.5,0.0,0.5)\n"
            + "\nName: shape1\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0,50.0), Width: 120.0, Height: 255.3, Color: (0.9,0.2,0.7)\n",
            a1.toString());

    a1.deletePhoto("shape1");
    // deletePhoto() remove all the occurrences that match the name.
    assertEquals("Name: shape2\n"
            + "Type: oval\n"
            + "Center: (-125.0,-54.3), X radius: 23.4, Y radius: 1999.9, Color: (0.5,0.0,0.5)\n",
            a1.toString());

    a1.deletePhoto("shape1");
    a1.deletePhoto(null);
    // If the name is not matching any IShape instance on this Album instance or trying to delete
    // null, deletePhoto() does nothing.
    assertEquals("Name: shape2\n"
            + "Type: oval\n"
            + "Center: (-125.0,-54.3), X radius: 23.4, Y radius: 1999.9, Color: (0.5,0.0,0.5)\n",
            a1.toString());
  }

  /**
   * This method is to test snapshot() method.
   */
  @Test
  public void testSnapshot() {
    a1.addPhoto(shape2);
    IAlbum tmp = a1.snapshot();

    assertEquals(a1.toString(), tmp.toString());

    a1.addPhoto(shape2);

    // tmp is a copy of a1
    assertFalse(a1.toString().equals(tmp.toString()));

    tmp = a1.snapshot();
    assertEquals(a1.toString(), tmp.toString());
  }

  /**
   * This method is to test toString() method.
   */
  @Test
  public void testToString() {
    a1.addPhoto(shape3);

    assertEquals("Name: shape3\n"
            + "Type: rectangle\n"
            + "Min corner: (100.0,300.0), Width: 0.0, Height: 0.0, Color: (0.0,0.5,0.0)\n",
            a1.toString());
  }
}