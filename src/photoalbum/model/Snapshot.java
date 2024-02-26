package photoalbum.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


/**
 * A class representing a snapshot of a set of shapes at a given time.
 */
public class Snapshot implements ISnapshot {

  private final String id;
  private final String timeStamp;
  private final String description;

  private List<IShape> shapeList;

  /**
   * Constructs a new Snapshot with the given list of shapes and description.
   *
   * @param shapeList   the list of shapes in the snapshot
   * @param description a brief description of the snapshot
   * @throws IllegalArgumentException if the description is null or empty
   */
  public Snapshot(List<IShape> shapeList, String description) {
    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    this.timeStamp = now.format(formatter);

    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
    this.id = now.format(formatter2);

    checkDescription(description);
    this.description = description;
    this.shapeList = shapeList;
  }

  public List<IShape> getShapeList() {
    return shapeList;
  }

  /**
   * Returns the ID of the snapshot.
   *
   * @return the ID of the snapshot
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * Returns the timestamp of the snapshot.
   *
   * @return the timestamp of the snapshot
   */
  @Override
  public String getTimeStamp() {
    return this.timeStamp;
  }

  /**
   * Returns the description of the snapshot.
   *
   * @return the description of the snapshot
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Returns a string representation of the shapes in the snapshot.
   *
   * @return a string representation of the shapes in the snapshot
   */
  @Override
  public String getShapeInformation() {

    StringBuilder sb = new StringBuilder();
    int size = shapeList.size();
    for (IShape shape : shapeList) {
      sb.append(shape.toString());
    }
    return sb.toString();
  }

  /**
   * Determines whether the snapshot is equal to another object.
   *
   * @param o the object to compare to
   * @return true if the snapshot is equal to the other object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Snapshot snapshot = (Snapshot) o;
    return Objects.equals(getTimeStamp(), snapshot.getTimeStamp())
            && Objects.equals(getDescription(), snapshot.getDescription())
            && Objects.equals(shapeList, snapshot.shapeList);
  }

  /**
   * Returns the hash code of the snapshot.
   *
   * @return the hash code of the snapshot
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, getTimeStamp(), getDescription(), shapeList);
  }

  /**
   * Returns a string representation of the snapshot.
   *
   * @return a string representation of the snapshot
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("""
            Snapshot ID: %s
            Timestamp: %s
            Description: %s
            Shape Information:\n
            """, id, timeStamp, description));
    for (IShape shape : shapeList) {
      sb.append(shape.toString());
    }
    return sb.toString();
  }

  /**
   * Checks whether the given description is valid (i.e., not null or empty).
   */
  public void checkDescription(String description) {
    if (description == null || description.isEmpty()) {
      throw new IllegalArgumentException();
    }
  }
}
