package photoalbum.model;

import java.util.List;

/**
 * The ISnapshot interface defines the methods
 * required to represent a snapshot of a shape's state at a specific time.
 */

public interface ISnapshot {

  public List<IShape> getShapeList();

  /**
   * Gets the unique identifier of the snapshot.
   *
   * @return the identifier of the snapshot.
   */
  String getID();

  /**
   * Gets the timestamp of the snapshot.
   *
   * @return the timestamp of the snapshot.
   */
  String getTimeStamp();

  /**
   * Gets the description of the snapshot.
   *
   * @return the description of the snapshot.
   */
  String getDescription();

  /**
   * Gets the shape information of the snapshot.
   *
   * @return the shape information of the snapshot.
   */
  String getShapeInformation();

  /**
   * Returns a string representation of the snapshot.
   *
   * @return a string representation of the snapshot.
   */
  String toString();
}
