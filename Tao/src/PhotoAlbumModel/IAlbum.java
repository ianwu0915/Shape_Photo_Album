package PhotoAlbumModel;

import java.util.List;

/**
 * This IAlbum interface is to restrict the ability of editing the content in Album class.
 */
public interface IAlbum {
  List<IShape> getPhotos();
}
