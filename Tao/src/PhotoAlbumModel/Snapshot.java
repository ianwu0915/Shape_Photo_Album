package PhotoAlbumModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * This Snapshot class is to simulate a snapshot of an album.
 */
public class Snapshot {
  private String id;
  private String timeStamp;
  private String description;
  private IAlbum shapeInfo;

  /**
   * This Snapshot class is to simulate a snapshot of an album.
   * @param description a description of this snapshot.
   * @param shapeInfo an album that was copied when the snapshot was taken.
   * @throws IllegalArgumentException If description or shapeInfo is null, throwing
   *                                  IllegalArgumentException.
   */
  public Snapshot(String description, IAlbum shapeInfo) throws IllegalArgumentException {

    if (description == null) {
      throw new IllegalArgumentException("Description can't be null");
    }

    if (shapeInfo == null) {
      throw new IllegalArgumentException("shapeInfo can't be null");
    }

    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    this.id = LocalDateTime.now(ZoneId.of("America/New_York")).toString();
    this.timeStamp = LocalDateTime.now(ZoneId.of("America/New_York")).format(df);
    this.description = description;
    this.shapeInfo = shapeInfo;
  }

  /**
   * This is the getter method for id.
   * @return the id of this instance.
   */
  public String getId() {
    return id;
  }

  /**
   * This is the getter method for timeStamp.
   * @return the timestamp of this instance.
   */
  public String getTimeStamp() {
    return timeStamp;
  }

  /**
   * This is the getter method for description.
   * @return the description of this instance.
   */
  public String getDescription() {
    return description;
  }

  /**
   * This is the getter method for shapeInfo.
   * If returns a copied version, so shapeInfo can't be edited from outside.
   * @return a copy of shapeInfo.
   */
  public IAlbum getShapeInfo() {
    return shapeInfo;
  }

  /**
   * This is the overridden toString() method.
   * It returns a String representing the information of this snapshot.
   * @return a string of snapshot's info.
   */

  @Override
  public String toString() {
    String s = String.format("Snapshot ID: %s\n", id);
    s += String.format("Timestamp: %s\n", timeStamp);
    s += String.format("Description: %s\n", description);
    s += "Shape Information:\n";

    return s + shapeInfo.toString();
  }

}
