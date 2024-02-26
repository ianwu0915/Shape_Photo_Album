package PhotoAlbumModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import PhotoAlbumModel.*;

/**
 * This PAModelTest class is to test all the built-in methods in PAMode class.
 */
public class PAModelTest {
  private PAModel model1;
  private PAModel model2;
  private IShape shape;

  /**
   * This method sets up a few instances for the following tests.
   */
  @Before
  public void setUp() {
    model1 = new PAModel();
    model2 = new PAModel();

    model1.addPhotoToAlbum("PC1993", "oval", 99.0, 23.44, 200.0,
            150.0, 0.5, 1.0, 1.0);
    model1.addPhotoToAlbum("PC2022", "rectangle", 0.0, 0.0, 450.0,
            900.0, 1.0, 1.0, 0.0);

    shape = Shape.makeShape("PC2022", "rectangle", 0.0, 0.0,
            450.0, 900.0, 1.0, 1.0, 0.0);
  }

  /**
   * This method is to test addPhotoToAlbum() method.
   */
  @Test
  public void testAddPhotoToAlbum() {
    model2.addPhotoToAlbum("test", "oval", 0.0, -10.0, 60.0,
            30.0, 0.0, 0.1, 0.2);

    assertEquals(Shape.makeShape("test", "oval", 0.0, -10.0, 60.0,
            30.0, 0.0, 0.1, 0.2).toString(), model2.getAlbum().toString());

    model2.deletePhotoFromAlbum("test");

    IShape tmp = Shape.makeShape("KA0985", "rectangle", 200.0, 300.0,
            50.0, 60.0, 0.0, 0.0, 0.0);

    model2.addPhotoToAlbum(tmp);

    tmp.resize(23.4, 56.7);
    // If we use addPhotoAlbum(IShape obj) to add photo, it just adds a copy of the IShape
    // instance, not the same object.
    assertFalse(tmp.toString().equals(model2.getAlbum().toString()));

    tmp.resize(50.0, 60.0);
    assertEquals(tmp.toString(), model2.getAlbum().toString());
  }

  /**
   * This method is to test addPhotoToAlbum() method with invalid inputs.
   * (Passing null as name)
   */
  @Test
  public void testInvalidAddPhotoToAlbum1() {
    model2.addPhotoToAlbum(null, "oval", 100.0, 100.0, 200.0,
            200.0, 1.0, 1.0, 1.0);

    assertEquals("", model2.getAlbum().toString());
  }

  /**
   * This method is to test addPhotoToAlbum() method with invalid inputs.
   * (Passing empty name)
   */
  @Test
  public void testInvalidAddPhotoToAlbum2() {
    model2.addPhotoToAlbum("", "oval", 100.0, 100.0, 200.0,
            200.0, 1.0, 1.0, 1.0);

    assertEquals("", model2.getAlbum().toString());
  }

  /**
   * This method is to test addPhotoToAlbum() method with invalid inputs.
   * (Passing null as type)
   */
  @Test
  public void testInvalidAddPhotoToAlbum3() {
    model2.addPhotoToAlbum("WD2349", null, 100.0, 100.0, 200.0,
            200.0, 1.0, 1.0, 1.0);

    assertEquals("", model2.getAlbum().toString());
  }

  /**
   * This method is to test addPhotoToAlbum() method with invalid inputs.
   * (Passing invalid type)
   */
  @Test
  public void testInvalidAddPhotoToAlbum4() {
    model2.addPhotoToAlbum("WD2349", "triangle", 100.0, 100.0, 200.0,
            200.0, 1.0, 1.0, 1.0);

    assertEquals("", model2.getAlbum().toString());
  }

  /**
   * This method is to test addPhotoToAlbum() method with invalid inputs.
   * (Passing empty type)
   */
  @Test
  public void testInvalidAddPhotoToAlbum5() {
    model2.addPhotoToAlbum("WD2349", "", 100.0, 100.0, 200.0,
            200.0, 1.0, 1.0, 1.0);

    assertEquals("", model2.getAlbum().toString());
  }

  /**
   * This method is to test addPhotoToAlbum() method with invalid inputs.
   * (Passing invalid xValue or yValue)
   */
  @Test
  public void testInvalidAddPhotoToAlbum6() {
    model2.addPhotoToAlbum("WD2349", "oval", 100.0, 100.0, 200.0,
            -200.0, 1.0, 1.0, 1.0);

    assertEquals("", model2.getAlbum().toString());
  }


  /**
   * This method is to test getAlbum() method.
   */
  @Test
  public void testGetAlbum() {
    Album tmp = model1.getAlbum();

    assertEquals("Name: PC1993\n"
            + "Type: oval\n"
            + "Center: (99.0,23.4), X radius: 200.0, Y radius: 150.0, Color: (0.5,1.0,1.0)\n"
            + "\n"
            + "Name: PC2022\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 450.0, Height: 900.0, Color: (1.0,1.0,0.0)\n",
            tmp.toString());

    tmp.deletePhoto("PC2022");
    // The output from getAlbum() is not a direct reference of currentAlbum. Instead, it's a copy
    // of it.
    assertFalse(model1.getAlbum().toString().equals(tmp.toString()));
  }

  /**
   * This method is to test deletePhotoFromAlbum() method.
   */
  @Test
  public void testDeletePhotoFromAlbum() {
    model1.deletePhotoFromAlbum("PC1993");

    assertEquals("Name: PC2022\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 450.0, Height: 900.0, Color: (1.0,1.0,0.0)\n",
            model1.getAlbum().toString());

    model1.deletePhotoFromAlbum("PC2022");
    assertEquals("", model1.getAlbum().toString());

    model2.addPhotoToAlbum(shape);
    model2.addPhotoToAlbum(shape);
    assertEquals("Name: PC2022\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 450.0, Height: 900.0, Color: (1.0,1.0,0.0)\n"
                    + "\n"
                    + "Name: PC2022\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 450.0, Height: 900.0, Color: (1.0,1.0,0.0)\n",
            model2.getAlbum().toString());

    model2.deletePhotoFromAlbum("PC2022");
    // deletePhotoFromAlbum() delete all the occurrences having the same name as given name.
    assertEquals("", model2.getAlbum().toString());
  }

  /**
   * This method is to test editReferencePoint() method.
   */
  @Test
  public void testEditReferencePoint() {
    model2.addPhotoToAlbum(shape);

    model2.editReferencePoint("PC2022", -99.0, -0.9);
    assertEquals(-99.0, model2.getAlbum().getPhoto("PC2022").getRefPointX(),
            0.01);
    assertEquals(-0.9, model2.getAlbum().getPhoto("PC2022").getRefPointY(),
            0.01);
    assertEquals("Name: PC2022\n"
            + "Type: rectangle\n"
            + "Min corner: (-99.0,-0.9), Width: 450.0, Height: 900.0, Color: (1.0,1.0,0.0)\n",
            model2.getAlbum().toString());
  }

  /**
   * This method is to test changeColor() method.
   */
  @Test
  public void testChangeColor() {
    model2.addPhotoToAlbum(shape);
    model2.changeColor("PC2022", 0.5, 0.3, 0.9);

    assertEquals(0.5, model2.getAlbum().getPhoto("PC2022").getColor()[0], 0.01);
    assertEquals(0.3, model2.getAlbum().getPhoto("PC2022").getColor()[1], 0.01);
    assertEquals(0.9, model2.getAlbum().getPhoto("PC2022").getColor()[2], 0.01);
    assertEquals("Name: PC2022\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 450.0, Height: 900.0, Color: (0.5,0.3,0.9)\n",
            model2.getAlbum().toString());
  }

  /**
   * This method is to test editXYValue() method.
   */
  @Test
  public void testEditXYValue() {
    model2.addPhotoToAlbum(shape);

    model2.editXYValue("PC2022", 205.9, 365.3);
    assertEquals(205.9, model2.getAlbum().getPhoto("PC2022").getXValue(), 0.01);
    assertEquals(365.3, model2.getAlbum().getPhoto("PC2022").getYValue(), 0.01);
    assertEquals("Name: PC2022\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 205.9, Height: 365.3, Color: (1.0,1.0,0.0)\n",
            model2.getAlbum().toString());

    model2.editXYValue("PC2022", -10.0, 80.0);
    // editXYValue() does nothing, if the input is invalid.
    assertEquals(205.9, model2.getAlbum().getPhoto("PC2022").getXValue(), 0.01);
    assertEquals(365.3, model2.getAlbum().getPhoto("PC2022").getYValue(), 0.01);
  }

  /**
   * This method is to test snapshot() method.
   */
  @Test
  public void testSnapshot() {
    assertEquals(0, model1.getSnapshots().size());
    model1.snapshot("First selfie");

    assertEquals(1, model1.getSnapshots().size());
    assertEquals("First selfie", model1.getSnapshots().get(0).getDescription());
    assertEquals(model1.getAlbum().toString(),
            model1.getSnapshots().get(0).getShapeInfo().toString());

    model1.deletePhotoFromAlbum("PC2022");
    model1.snapshot("");

    assertEquals("", model1.getSnapshots().get(1).getDescription());
    assertEquals(2, model1.getSnapshots().size());
    assertEquals(model1.getAlbum().toString(),
            model1.getSnapshots().get(1).getShapeInfo().toString());
  }

  /**
   * This method is to test getSnapshotIDList() method.
   */
  @Test
  public void testGetSnapshotIDList() {
    model1.snapshot("first");
    assertEquals(1, model1.getSnapshotIDList().size());
    assertEquals(26, model1.getSnapshotIDList().get(0).length());

    model1.deletePhotoFromAlbum("PC1993");
    model1.snapshot("second");
    assertEquals(2, model1.getSnapshotIDList().size());
    assertEquals(26, model1.getSnapshotIDList().get(1).length());

    model1.snapshot("third");
    assertEquals(3, model1.getSnapshotIDList().size());
    assertEquals(26, model1.getSnapshotIDList().get(2).length());
  }

  /**
   * This method is to test getSnapshots() method,
   */
  @Test
  public void testGetSnapshots() {
    assertEquals(new ArrayList<>(), model1.getSnapshots());

    model1.snapshot("First");
    assertEquals(1, model1.getSnapshots().size());
    assertEquals("First", model1.getSnapshots().get(0).getDescription());
    assertEquals(model1.getAlbum().toString(),
            model1.getSnapshots().get(0).getShapeInfo().toString());

    model1.deletePhotoFromAlbum("PC2022");
    model1.snapshot("After removal");
    assertEquals(2, model1.getSnapshots().size());
    assertEquals("After removal", model1.getSnapshots().get(1).getDescription());
    assertEquals(model1.getAlbum().toString(),
            model1.getSnapshots().get(1).getShapeInfo().toString());
  }

  /**
   * This method is to test reset() method.
   */
  @Test
  public void testReset() {
    model1.snapshot("1");
    model1.addPhotoToAlbum(shape);
    model1.snapshot("2");
    model1.deletePhotoFromAlbum("PC2022");
    model1.snapshot("3");

    assertEquals(3, model1.getSnapshots().size());
    assertEquals("Name: PC1993\n"
            + "Type: oval\n"
            + "Center: (99.0,23.4), X radius: 200.0, Y radius: 150.0, Color: (0.5,1.0,1.0)\n",
            model1.getAlbum().toString());

    model1.reset();
    assertEquals(0, model1.getSnapshots().size());
    assertEquals("", model1.getAlbum().toString());
  }
}