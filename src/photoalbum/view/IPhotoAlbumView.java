package photoalbum.view;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

import photoalbum.model.IPhotoAlbumModel;
import photoalbum.model.IShape;
import photoalbum.model.ISnapshot;

/**
 * This interface if for the graphical view of the photo album application.
 * It displays an interactive graphical view for the user to view the snapshots
 * in a photo album.
 */

public interface IPhotoAlbumView {

  /**
   * Gets the list of snapshots.
   *
   * @return the list of snapshots.
   */
  List<ISnapshot> getSnapshotList();

  /**
   * Gets the map of snapshot images.
   *
   * @return the map of snapshot images.
   */
  HashMap<String, Image> getSnapshotImageList();

  /**
   * Gets the list of images.
   *
   * @return the list of images.
   */
  List<Image> getImageList();

  /**
   * Sets the photo album model.
   *
   * @param model the photo album model.
   */
  void setPhotoAlbumModel(IPhotoAlbumModel model);

  /**
   * Gets the photo album model.
   *
   * @return the photo album model.
   */
  IPhotoAlbumModel getPhotoAlbumModel();

  /**
   * Creates a graph from the list of shapes.
   *
   * @param shapes the list of shapes.
   * @return the graph image.
   */
  Image createGraphs(List<IShape> shapes);

  /**
   * Create a text area for the description of snapshot at the top panel.
   */
  void view();
}
