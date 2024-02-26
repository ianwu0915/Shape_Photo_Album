package PhotoAlbumModel;

import java.util.List;

public interface IPAModel {

  /**
   * Implement addPhotoToAlbum() method.
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
  void addPhotoToAlbum(String name, String type, double rPointX, double rPointY, double xValue,
                       double yValue, double red, double green, double blue);

  /**
   * Implement addPhotoToAlbum() method.
   * @param shape the IShape instance we would like to copy into currentAlbum.
   */
  void addPhotoToAlbum(IShape shape);

  /**
   * Implement getAlbum() method.
   * @return a copy of currentAlbum.
   */
  Album getAlbum();

  /**
   * Implement deletePhotoFromAlbum() method.
   * @param name the name of the IShape instance we would like to delete.
   */
  void deletePhotoFromAlbum(String name);

  /**
   * Implement editReferencePoint() method.
   * @param name name of the IShape instance.
   * @param x a new value for reference point's x-axis.
   * @param y a new value for reference point's y-axis.
   */
  void editReferencePoint(String name, double x, double y);

  /**
   * Implement changeColor() method.
   * @param name name of the IShape instance.
   * @param red a new factor of red color.
   * @param green a new factor of green color.
   * @param blue a new factor of blue color.
   */
  void changeColor(String name, double red, double green, double blue);

  /**
   * Implement editXYValue() method,
   * @param name name of the IShape instance.
   * @param xValue a new value that describes this shape's x dimension.
   * @param yValue a new value that describes this shape's y dimension.
   */
  void editXYValue(String name, double xValue, double yValue);

  /**
   * Implement snapshot() method.
   * @param description the description of this snapshot.
   */
  void snapshot(String description);

  /**
   * Implement getSnapshotIDList() method.
   * @return a list of String representing the id of each snapshot.
   */
  List<String> getSnapshotIDList();

  /**
   * Implement getSnapshots() method,
   * @return a list of Snapshot instances.
   */
  List<Snapshot> getSnapshots();

  /**
   * Implement reset() method.
   */
  void reset();

  /**
   * Implement printSnapshot() method.
   */
  void printSnapshot();
}
