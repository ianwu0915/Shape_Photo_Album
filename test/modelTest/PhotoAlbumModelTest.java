package modelTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import photoalbum.model.Color;
import photoalbum.model.IPhotoAlbumModel;
import photoalbum.model.IShape;
import photoalbum.model.PhotoAlbumModel;
import photoalbum.model.Point;
import photoalbum.model.Snapshot;
import photoalbum.model.oval;
import photoalbum.model.rectangle;

/**
 * This is a JUnit test for the PhotoAlbum class.
 */
public class PhotoAlbumModelTest {

  private IPhotoAlbumModel photoAlbum;
  private Snapshot snapshot1;
  private Snapshot snapshot2;

  /**
   * Set up a photoAlbum for the test.
   */
  @Before
  public void setUp() {
    photoAlbum = new PhotoAlbumModel();
  }

  /**
   * Test createShape().
   */
  @Test
  public void createShapeTest() {
    Assert.assertEquals(0, photoAlbum.getShapeList().size());

    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 20, 50);
    Color color = new Color(1, 0, 0);
    Point position = new Point(0, 0);
    IShape rect1 = new rectangle("R", color, position, 20, 50);
    Assert.assertEquals(rect1, photoAlbum.getShapeList().get(0));

    Assert.assertEquals(1, photoAlbum.getShapeList().size());

    photoAlbum.createShape("oval", "O", 1, 0, 0,
            0, 0, 20, 50);
    IShape oval1 = new oval("R", color, position, 20, 50);
    Assert.assertEquals(oval1, photoAlbum.getShapeList().get(1));
  }

  /**
   * Test createSnapShot().
   */
  @Test
  public void createSnapShotTest() {
    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 20, 50);

    photoAlbum.createSnapShot("First snapshot");
    Assert.assertEquals(1, photoAlbum.getSnapshotList().size());
    snapshot1 = new Snapshot(photoAlbum.getShapeList(), "First snapshot");
    Assert.assertEquals(photoAlbum.getSnapshotList().get(0), snapshot1);

    photoAlbum.createShape("oval", "O", 1, 0, 0,
            0, 0, 20, 50);

    photoAlbum.createSnapShot("Second snapshot");
    snapshot2 = new Snapshot(photoAlbum.getShapeList(), "Second snapshot");
    Assert.assertEquals(2, photoAlbum.getSnapshotList().size());
  }

  /**
   * Test createSnapShot(): duplicate name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void InvalidCreateSnapShotTest() {
    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 100, 100);
  }

  /**
   * Test printSnapShots().
   */
  @Test
  public void printSnapShots() {

    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.createSnapShot("First snapshot");
    photoAlbum.createShape("oval", "O", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.createSnapShot("Second snapshot");

    StringBuilder sb = new StringBuilder();
    sb.append("Printing Snapshots\n");
    sb.append(photoAlbum.getSnapshotList().get(0).toString());
    sb.append("\n");
    sb.append(photoAlbum.getSnapshotList().get(1).toString());

    Assert.assertEquals(sb.toString(), photoAlbum.printSnapShots());
  }

  /**
   * Test moveShape().
   */
  @Test
  public void moveShape() {
    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.moveShape("R", 10, 10);
    Point position = new Point(10, 10);
    Assert.assertEquals(position, photoAlbum.getShapeList().get(0).getPosition());

    photoAlbum.createShape("oval", "O", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.moveShape("O", 100, 100);
    Point position1 = new Point(100, 100);
    Assert.assertEquals(position, photoAlbum.getShapeList().get(0).getPosition());
  }

  /**
   * Test resizeShape().
   */
  @Test
  public void reSizeShape() {
    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.reSizeShape("R", 10, 10);
    double width = photoAlbum.getShapeList().get(0).getWidth();
    double height = photoAlbum.getShapeList().get(0).getHeight();
    Assert.assertEquals(10, height, 0.0001);
  }

  /**
   * Test changeShapeColor().
   */
  @Test
  public void changeShapeColor() {
    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.changeShapeColor("R", 0, 0, 0);
    Color color = new Color(0, 0, 0);
    Color newColor = photoAlbum.getShapeList().get(0).getColor();
    Assert.assertEquals(color, newColor);
  }

  /**
   * Test restSnapshotsList().
   */
  @Test
  public void restSnapshotsList() {
    photoAlbum.createShape("rectangle", "R", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.createSnapShot("First snapshot");
    String id1 = photoAlbum.getSnapshotList().get(0).getID();
    photoAlbum.createShape("oval", "O", 1, 0, 0,
            0, 0, 20, 50);
    photoAlbum.createSnapShot("Second snapshot");
    String id2 = photoAlbum.getSnapshotList().get(1).getID();

    String reset = String.format("List of snapshots taken before reset: [%s, %s]", id1, id2);
    Assert.assertEquals(reset, photoAlbum.resetSnapshotsList());
    Assert.assertEquals(0, photoAlbum.getSnapshotList().size());
    Assert.assertEquals(2, photoAlbum.getShapeList().size());
  }
}
