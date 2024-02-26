package PhotoAlbumModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This PAModel class is serving as a photo album model which can process certain orders to manage
 * all the other components class in this package to simulate a photo album.
 */
public class PAModel implements IPAModel {
  private Album currentAlbum;
  private List<Snapshot> snapshots;

  /**
   * This is the constructor of this class.
   */
  public PAModel() {
    currentAlbum = new Album();
    snapshots = new ArrayList<>();
  }

  /**
   * This method is to add an IShape instance with the given attributes into currentAlbum.
   * @param name name of this shape instance.
   * @param type the type of this shape instance.
   * @param rPointX x-axis of the reference point.
   * @param rPointY y-axis of the reference point.
   * @param xValue the value describing the shape in x dimension.
   * @param yValue the value describing the shape in y dimension.
   * @param red factor of red color.
   * @param green factor of green color.
   * @param blue factor of blue color.
   */
  public void addPhotoToAlbum(String name, String type, double rPointX, double rPointY,
                             double xValue, double yValue, double red, double green,
                             double blue) {
    IShape shape = Shape.makeShape(name, type, rPointX, rPointY, xValue, yValue, red, green, blue);
    if (shape == null) return;

    currentAlbum.addPhoto(shape);
  }

  /**
   * This method is to add a copy of IShape instance into currentAlbum.
   * @param shape the IShape instance we would like to copy into currentAlbum.
   */
  public void addPhotoToAlbum(IShape shape) {
    if (shape == null) return;

    currentAlbum.addPhoto(shape.copy());
  }

  /**
   * This is the getter method for currentAlbum.
   * It returns a copied version of currentAlbum.
   * @return a copy of currentAlbum.
   */
  public Album getAlbum() {
    return currentAlbum.copy();
  }

  /**
   * This method is to delete the IShape with the given name from currentAlbum.
   * @param name the name of the IShape instance we would like to delete.
   */
  public void deletePhotoFromAlbum(String name) {
    currentAlbum.deletePhoto(name);
  }

  /**
   * This method is to edit the reference point of the IShape instance with the given name.
   * @param name name of the IShape instance.
   * @param rPointX a new value for reference point's x-axis.
   * @param rPointY a new value for reference point's y-axis.
   */
  public void editReferencePoint(String name, double rPointX, double rPointY) {
    IShape tmp = currentAlbum.getPhoto(name);
    if (tmp == null) return;

    tmp.setReferencePoint(rPointX, rPointY);
  }

  /**
   * This method is to change the color of the IShape instance with the given name.
   * @param name name of the IShape instance.
   * @param red a new factor of red color.
   * @param green a new factor of green color.
   * @param blue a new factor of blue color.
   */
  public void changeColor(String name, double red, double green, double blue) {
    IShape tmp = currentAlbum.getPhoto(name);
    if (tmp == null) return;

    tmp.changeColor(red, green, blue);
  }

  /**
   * This method is to resize the IShape instance with the given name.
   * @param name name of the IShape instance.
   * @param xValue a new value that describes this shape's x dimension.
   * @param yValue a new value that describes this shape's y dimension.
   */
  public void editXYValue(String name, double xValue, double yValue) {
    IShape tmp = currentAlbum.getPhoto(name);
    if (tmp == null) return;

    tmp.resize(xValue, yValue);
  }

  /**
   * This method is to perform a snapshot to currentAlbum.
   * @param description the description of this snapshot.
   */
  public void snapshot(String description) {
    if (description == null) description = "";
    Snapshot ss = new Snapshot(description, currentAlbum.snapshot());
    snapshots.add(ss);
  }

  /**
   * This method is to gather the id of each snapshot as a list.
   * @return a list of String representing the id of each snapshot.
   */
  public List<String> getSnapshotIDList() {
    List<String> tmp = new ArrayList<>();
    snapshots.stream().forEach(ss -> tmp.add(ss.getId()));

    return tmp;
  }

  /**
   * This is the getter for snapshots.
   * It returns an unmodifiable list, so snapshots can't be edited from outside.
   * @return a list of Snapshot instances.
   */
  public List<Snapshot> getSnapshots() {
    return Collections.unmodifiableList(snapshots);
  }

  /**
   * This method is to reset this PAModel instance.
   */
  public void reset() {
    currentAlbum = new Album();
    snapshots = new ArrayList<>();
  }

  /**
   * This method is to print all the snapshots in this instance.
   */
  public void printSnapshot() {
    String s = "";

    Iterator<Snapshot> iter = snapshots.iterator();

    while (iter.hasNext()) {
      if (s.isBlank()) {
        s = iter.next().toString();
        continue;
      }

      s += "\n" + iter.next().toString();
    }

    System.out.print(s);
  }
}
