package photoalbum.model;

import java.util.List;

/**
 * Thhis  interface represents a photo album
 * for creating shapes and taking snapshots.
 */

public interface IPhotoAlbumModel {

  /**
   * Creates a new shape object and adds it to the photo album's shape list.
   *
   * @param kind      the type of shape to create
   * @param name      the name of the shape to create
   * @param red       the red component of the shape's color
   * @param green     the green component of the shape's color
   * @param blue      the blue component of the shape's color
   * @param xPosition the x-coordinate of the shape's position
   * @param yPosition the y-coordinate of the shape's position
   * @param width     the width of the shape
   * @param height    the height of the shape
   * @return the newly created shape object
   * @throws IllegalArgumentException if any of the input values are invalid
   */
  IShape createShape(String kind, String name,
                     int red, int green, int blue,
                     double xPosition, double yPosition,
                     int width, int height) throws IllegalArgumentException;

  /**
   * Returns a list of all shapes in the photo album.
   *
   * @return a list of all shapes in the photo album
   */
  List<IShape> getShapeList();

  /**
   * Returns a list of all snapshots in the photo album.
   *
   * @return a list of all snapshots in the photo album
   */
  List<ISnapshot> getSnapshotList();

  List<String> getSnapshotIDList();

  /**
   * Creates a new snapshot of the current state of the photo album's shapes and adds it to the
   * photo album's snapshot list.
   *
   * @param description a description of the snapshot
   * @return the newly created snapshot object
   */
  ISnapshot createSnapShot(String description);

  /**
   * Returns a string representation of all snapshots in the photo album.
   *
   * @return a string representation of all snapshots in the photo album
   */
  String printSnapShots();

  /**
   * Moves the specified shape to a new position.
   *
   * @param name the name of the shape to move
   * @param x    the new x-coordinate of the shape's position
   * @param y    the new y-coordinate of the shape's position
   */
  void moveShape(String name, double x, double y);

  /**
   * Resizes the specified shape to a new width and height.
   *
   * @param name   the name of the shape to resize
   * @param width  the new width of the shape
   * @param height the new height of the shape
   */
  void reSizeShape(String name, int width, int height);

  /**
   * Changes the color of the specified shape.
   *
   * @param name the name of the shape to change the color of
   * @param r    the new red component of the shape's color
   * @param g    the new green component of the shape's color
   * @param b    the new blue component of the shape's color
   */
  void changeShapeColor(String name, int r, int g, int b);

  public ISnapshot getSnapshot(String id);

  /**
   * Removes the specified shape from the photo album.
   *
   * @param shapeName the name of the shape to remove
   */
  void removeShape(String shapeName);

  /**
   * Resets the photo album's snapshot list to an empty list.
   *
   * @return a message indicating the snapshot list has been reset
   */
  String resetSnapshotsList();

  /**
   * Resets the photo album's shape list to an empty list.
   */
  void resetShapes();

}
