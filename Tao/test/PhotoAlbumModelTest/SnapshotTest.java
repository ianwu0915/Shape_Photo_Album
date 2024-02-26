package PhotoAlbumModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import PhotoAlbumModel.*;

/**
 * This SnapshotTest class is to test all the built-in methods in Snapshot class.
 */
public class SnapshotTest {
  private Album a;
  private Snapshot s1;
  private Snapshot s2;
  private Snapshot s3;
  private String timestamp;

  /**
   * This method sets up a few instance for the following tests.
   */
  @Before
  public void setUp() {
    a = new Album();
    a.addPhoto(Shape.makeShape("PC1993", "oval", 99.0, 23.44,
            200.0, 150.0, 0.5, 1.0, 1.0));
    a.addPhoto(Shape.makeShape("PC1989", "rectangle", -99.0, 123.44,
            1000.50, 1500.0, 0.0, 0.0, 1.0));
    a.addPhoto(Shape.makeShape("PC2022", "oval", 0.0, 0.0,
            450.0, 900.0, 1.0, 1.0, 0.0));

    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    timestamp = LocalDateTime.now(ZoneId.of("America/New_York")).format(df).toString();

    s1 = new Snapshot("First selfie", a.snapshot());

    a.deletePhoto("PC1989");
    s2 = new Snapshot("Second selfie", a.snapshot());


    a.deletePhoto("PC2022");
    a.deletePhoto("PC1993");
    s3 = new Snapshot("Third selfie", a.snapshot());
  }

  /**
   * This method is to test getId() method.
   */
  @Test
  public void testGetId() {
    // Since each snapshot was taken at different time, the ID which was generated according to
    // the time should be different from each others.
    assertFalse(s1.getId().equals(s2.getId()));
    assertFalse(s2.getId().equals(s3.getId()));
    assertFalse(s3.getId().equals(s1.getId()));
  }

  /**
   * This method is to test getTimeStamp() method.
   */
  @Test
  public void testGetTimeStamp() {
    // Even though these three snapshots was taken in an extremely short period, there is still
    // a chance that this test might fail. However, it will pass most of the time.
    assertEquals(timestamp, s1.getTimeStamp());
    assertEquals(timestamp, s2.getTimeStamp());
    assertEquals(timestamp, s3.getTimeStamp());
  }

  /**
   * This method is to test getDescription() method.
   */
  @Test
  public void testGetDescription() {
    assertEquals("First selfie", s1.getDescription());
    assertEquals("Second selfie",s2.getDescription());
    assertEquals("Third selfie", s3.getDescription());
  }

  /**
   * This method is to test getShapeInfo() method.
   */
  @Test
  public void testGetShapeInfo() {
    assertEquals("Name: PC1993\n"
            + "Type: oval\n"
            + "Center: (99.0,23.4), X radius: 200.0, Y radius: 150.0, Color: (0.5,1.0,1.0)\n"
            + "\n"
            + "Name: PC1989\n"
            + "Type: rectangle\n"
            + "Min corner: (-99.0,123.4), Width: 1000.5, Height: 1500.0, Color: (0.0,0.0,1.0)\n"
            + "\n"
            + "Name: PC2022\n"
            + "Type: oval\n"
            + "Center: (0.0,0.0), X radius: 450.0, Y radius: 900.0, Color: (1.0,1.0,0.0)\n",
            s1.getShapeInfo().toString());

    assertEquals("Name: PC1993\n"
            + "Type: oval\n"
            + "Center: (99.0,23.4), X radius: 200.0, Y radius: 150.0, Color: (0.5,1.0,1.0)\n"
            + "\n"
            + "Name: PC2022\n"
            + "Type: oval\n"
            + "Center: (0.0,0.0), X radius: 450.0, Y radius: 900.0, Color: (1.0,1.0,0.0)\n",
            s2.getShapeInfo().toString());

    assertEquals("", s3.getShapeInfo().toString());
  }

  /**
   * This method is to test toString() method.
   */
  @Test
  public void testToString() {
    // Since toString() method includes Snapshot ID in the output, I can't think of a way to test
    // the String value. However, the String length should always stay the same, this is at least
    // something I can test.
    assertEquals(424, s1.toString().length());
    assertEquals(316, s2.toString().length());
    assertEquals(116, s3.toString().length());
  }
}