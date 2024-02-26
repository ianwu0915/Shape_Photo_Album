package modelTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import photoalbum.model.Color;
import photoalbum.model.IShape;
import photoalbum.model.ISnapshot;
import photoalbum.model.Point;
import photoalbum.model.Snapshot;
import photoalbum.model.oval;
import photoalbum.model.rectangle;

/**
 * This is a JUnit test for the snapshot class.
 */

public class SnapShotTest {
  private ISnapshot snapshot1;
  private ISnapshot snapshot2;
  private List<IShape> shapeList;
  private IShape rect1;
  private IShape oval1;
  private Point point1;
  private Point point2;
  private Color red;
  private Color green;


  /**
   * Set up color, point, shape objects for the test
   */
  @Before
  public void setUp() {

    red = new Color(1, 0, 0);
    green = new Color(0, 1, 0);
    point1 = new Point(0, 0);
    point2 = new Point(20, 20);
    rect1 = new rectangle("R", red, point1, 10, 20 );
    oval1 = new oval("O", green, point2, 15, 18);
    shapeList = new ArrayList<>();
    shapeList.add(rect1);
    shapeList.add(oval1);

    snapshot1 = new Snapshot(shapeList, "Test1");
  }

  /**
   * Test getTimeStamp().
   */
  @Test
  public void getTimeStamp() {
    snapshot1 = new Snapshot(shapeList, "Test1");
    snapshot2 = new Snapshot(shapeList, "Test2");
    String timeStamp1 = snapshot1.getTimeStamp();
    String timeStamp2 = snapshot1.getTimeStamp();
    String id1 = snapshot1.getID();
    Assert.assertEquals(timeStamp1, timeStamp2);
    Assert.assertEquals(snapshot2.getTimeStamp(), snapshot1.getTimeStamp());
  }

  /**
   * Tet getShapeInformation().
   */
  @Test
  public void getShapeInformation(){
    String sb = rect1.toString() +
            oval1.toString();
    Assert.assertEquals(sb, snapshot1.getShapeInformation() );
  }

  /**
   * Test toString().
   */
  @Test
  public void toStringTest(){
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("""
            Snapshot ID: %s
            Timestamp: %s
            Description: Test1
            Shape Information:\n
            """, snapshot1.getID(), snapshot1.getTimeStamp()));
    sb.append(snapshot1.getShapeInformation());
    Assert.assertEquals(sb.toString(), snapshot1.toString());
  }
}
