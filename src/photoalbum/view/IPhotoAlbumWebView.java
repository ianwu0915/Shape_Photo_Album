package photoalbum.view;

import java.io.IOException;

/**
 * This interface is the view of photo album application
 * for web display using html.
 */
public interface IPhotoAlbumWebView {
  /**
   * Converts the photo album model to an HTML file and writes it to the output file.
   *
   * @throws IOException if an I/O error occurs while writing the output file
   */
  void convertToHtml() throws IOException;
}
