package photoalbum.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code PhotoAlbum} class represents a photo album that contains various shapes and snapshots
 * of those shapes. It provides methods to create, manipulate and delete shapes, as well as to take
 * snapshots of the current state of the album. Additionally, it provides methods to reset the album
 * and retrieve information about its contents.
 */
public class PhotoAlbumModel implements IPhotoAlbumModel {

  private final List<IShape> shapeList;
  private final List<ISnapshot> snapshotList;
  private final List<String> snapshotIDList;

  /**
   * Constructs a new photo album with empty lists of shapes and snapshots.
   */
  public PhotoAlbumModel() {
    shapeList = new ArrayList<>();
    snapshotList = new ArrayList<>();
    snapshotIDList = new ArrayList<>();
  }

  /**
   * Returns a list of all shapes in the photo album.
   *
   * @return a list of all shapes in the photo album
   */
  public List<IShape> getShapeList() {
    return shapeList;
  }

  /**
   * Returns a list of all snapshots taken in the photo album.
   *
   * @return a list of all snapshots taken in the photo album
   */
  public List<ISnapshot> getSnapshotList() {
    return snapshotList;
  }

  public List<String> getSnapshotIDList() {
    return snapshotIDList;
  }

  /**
   * Creates a new shape of the given kind with the given parameters
   * and adds it to the list of shapes
   * in the photo album.
   *
   * @param kind      the kind of shape to create ("rectangle" or "oval")
   * @param name      the name of the shape
   * @param red       the red component of the color of the shape
   * @param green     the green component of the color of the shape
   * @param blue      the blue component of the color of the shape
   * @param xPosition the x-coordinate of the shape's position
   * @param yPosition the y-coordinate of the shape's position
   * @param width     the width of the shape
   * @param height    the height of the shape
   * @return the new shape that was created
   * @throws IllegalArgumentException if the kind of shape is invalid
   */
  @Override
  public IShape createShape(String kind, String name,
                            int red, int green, int blue,
                            double xPosition, double yPosition,
                            int width, int height) throws IllegalArgumentException {
    if (kind == null || kind.isEmpty()) {
      throw new IllegalArgumentException();
    }

    Color color = new Color(red, green, blue);
    Point position = new Point(xPosition, yPosition);

    for (IShape shape : this.shapeList) {
      if (shape.getName() == name) {
        throw new IllegalArgumentException("Duplicate name is not allowed");
      }
    }

    switch (kind.toLowerCase()) {
      case "rectangle" -> {
        IShape shape = new rectangle(name, color, position, width, height);
        shapeList.add(shape);
        return shape;
      }
      case "oval" -> {
        IShape shape1 = new oval(name, color, position, width, height);
        shapeList.add(shape1);
        return shape1;
      }
      default -> throw new IllegalArgumentException("No such type of shape");
    }
  }

  /**
   * Creates a new snapshot of the current state of the photo album with the given description and
   * adds it to the list of snapshots.
   *
   * @param description a description of the snapshot
   * @return the new snapshot that was created
   */
  @Override
  public ISnapshot createSnapShot(String description) {
    //deep copy the current shapeList
    List<IShape> copyList = new ArrayList<>();
    for (IShape shape : shapeList) {
      //deep copy the shape;
      if (shape.getType() == "rectangle") {
        Point positionCopy = new Point(shape.getPosition().getX(), shape.getPosition().getY());

        IShape shapeCopy = new rectangle(shape.getName(), shape.getColor(),
                positionCopy, shape.getWidth(), shape.getHeight());
        copyList.add(shapeCopy);
      }
      if (shape.getType() == "oval") {
        Point positionCopy = new Point(shape.getPosition().getX(), shape.getPosition().getY());
        IShape shapeCopy = new oval(shape.getName(), shape.getColor(),
                positionCopy, shape.getWidth(), shape.getHeight());
        copyList.add(shapeCopy);
      }
    }
    Snapshot snapshot = new Snapshot(copyList, description);
    snapshotList.add(snapshot);
    snapshotIDList.add(snapshot.getID());
    return snapshot;
  }

  /**
   * Returns a string representation of all snapshots taken in the photo album.
   *
   * @return a string representation of all snapshots taken in the photo album
   */
  @Override
  public String printSnapShots() {
    StringBuilder sb = new StringBuilder();
    int size = snapshotList.size();

    sb.append("Printing Snapshots\n");
    for (int i = 0; i < size; i++) {
      sb.append(snapshotList.get(i).toString());
      if (i < size - 1) {
        sb.append("\n");
      }
    }
    System.out.println(sb);
    return sb.toString();

  }

  /**
   * Moves the shape with the given name to the specified position.
   *
   * @param name the name of the shape to move
   * @param x    the x-coordinate of the new position
   * @param y    the y-coordinate of the new position
   */

  @Override
  public void moveShape(String name, double x, double y) {
    for (IShape shape : shapeList) {
      if (shape.getName().equals(name)) {
        shape.move(x, y);
      }
    }
  }

  /**
   * Resize the shape with the given name to the given width and height.
   *
   * @param name   the name of the shape to resize
   * @param width  the new width of the shape
   * @param height the new height of the shape
   */
  @Override
  public void reSizeShape(String name, int width, int height) {
    for (IShape shape : shapeList) {
      if (shape.getName().equals(name)) {
        shape.reSize(width, height);
      }
    }
  }

  /**
   * Change the color of the shape with the given name to the given rgb value.
   *
   * @param name the name of the shape to change the color
   * @param r    the new red component of the shape's color
   * @param g    the new green component of the shape's color
   * @param b    the new blue component of the shape's color
   */
  @Override
  public void changeShapeColor(String name, int r, int g, int b) {
    Color color = new Color(r, g, b);
    for (IShape shape : shapeList) {
      if (shape.getName().equals(name)) {
        shape.changeColor(color);
      }
    }
  }

  /**
   * Get the snapshot by its id.
   *
   * @param id the id of the snapshots
   * @return the snapshot if found or return null
   */


  @Override
  public ISnapshot getSnapshot(String id) {
    for (ISnapshot snapShot : snapshotList) {
      if (snapShot.getID().equals(id)) {
        return snapShot;
      }
    }
    return null;
  }

  /**
   * Remove the shape with the given name.
   *
   * @param shapeName the name of the shape to remove
   */
  @Override
  public void removeShape(String shapeName) {
    for (IShape shape : shapeList) {
      if (shape.getName().equals(shapeName)) {
        shapeList.remove(shape);
        break;
      }
    }
  }

  /**
   * Reset the snapshot list of the photo album,
   * print and return the lists of id of snapshots before the reset as string.
   *
   * @return the lists of id of snapshots before the reset
   */
  @Override
  public String resetSnapshotsList() {
    StringBuilder sb = new StringBuilder();
    int size = snapshotList.size();
    sb.append("List of snapshots taken before reset: [");
    for (int i = 0; i < size; i++) {
      sb.append(snapshotList.get(i).getID());
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    System.out.println(sb);
    snapshotList.clear();
    snapshotIDList.clear();
    return sb.toString();
  }

  /**
   * Reset the shapeList of the photo album.
   */
  @Override
  public void resetShapes() {
    shapeList.clear();
  }
}
