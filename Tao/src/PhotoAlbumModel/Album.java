package PhotoAlbumModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This Album class is simulate an album containing shapes.
 */
public class Album implements IAlbum {
  private List<IShape> photos;

  /**
   * This is the constructor of this class.
   */
  public Album() {
    photos = new ArrayList<>();
  }

  /**
   * This method is to add a new IShape instance into photos.
   * If null is passed to this method, do nothing.
   * @param photo an IShape instance.
   */
  public void addPhoto(IShape photo) {
    if (photo == null) {
      return;
    }

    photos.add(photo);
  }

  /**
   * This method is to get the IShape instance with the given name from photos.
   * @param name the name of the IShape instance.
   * @return a IShape instance or null if there is no instance having the name that is as
   * same as the given name, or the given name is null or blank.
   */
  public IShape getPhoto(String name) {
    if (name == null || name.isBlank()) return null;

    for (int i = 0; i < photos.size(); i++) {
      if (name.equals(photos.get(i).getName())) {
        return photos.get(i);
      }
    }

    return null;
  }

  /**
   * This method is the getter for photos.
   * It returns an unmodifiable list of IShape.
   * @return list of IShape on this album.
   */
  public List<IShape> getPhotos() {
    return Collections.unmodifiableList(photos);
  }

  /**
   * This method is to delete all IShape instances that have the same name as the given name.
   * @param name the name of the IShape instances that we would like to delete from this album.
   */
  public void deletePhoto(String name) {
    if (name == null || name.isBlank()) return;

    photos.removeIf(p -> p.getName().equals(name));
  }

  /**
   * This method is to perform a snapshot to the current album.
   * It produces a copied version of this instance.
   * @return a copy of this instance.
   */
  public IAlbum snapshot() {
    IAlbum snapshot = this.copy();

    return snapshot;
  }

  /**
   * This method is to create a copy of current instance.
   * @return a copy of current instance
   */
  public Album copy() {
    Album copy = new Album();

    for (IShape each: photos) {
      copy.addPhoto(each.copy());
    }

    return copy;
  }

  /**
   * This is the overridden toString() method.
   * It returns a String representing the information of this album.
   * @return a string of album's info.
   */
  @Override
  public String toString() {
    String s = "";
    Iterator<IShape> iter = photos.iterator();

    while (iter.hasNext()) {
      if (s.isBlank()) {
        s = iter.next().toString();
        continue;
      }
      s += "\n" + iter.next().toString();
    }

    return s;
  }
}
