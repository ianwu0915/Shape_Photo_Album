package photoalbum.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import photoalbum.model.IPhotoAlbumModel;
import photoalbum.model.IShape;
import photoalbum.model.ISnapshot;

/**
 * This class represents a view for photo album application in HTML format.
 * It provides a method to convert
 * an IPhotoAlbumModel instance to an HTML file.
 */
public class PhotoAlbumWebView implements IPhotoAlbumWebView {
  private List<ISnapshot> snapshotList;
  private IPhotoAlbumModel photoAlbumModel;
  private String outputName;
  private int xMax;
  private int yMAx;

  /**
   * Constructs a new PhotoAlbumWebView instance
   * with the specified photo album model and output file name.
   *
   * @param photoAlbumModel the photo album model to use
   * @param outPutName      the output file name
   * @throws IOException if an I/O error occurs while creating the output file
   */

  public PhotoAlbumWebView(IPhotoAlbumModel photoAlbumModel, String outPutName, int xMax, int yMAx) throws IOException {
    this.photoAlbumModel = photoAlbumModel;
    this.outputName = outPutName;
    this.xMax = xMax;
    this.yMAx = yMAx;
  }

  /**
   * Converts the photo album model to an HTML file and writes it to the output file.
   *
   * @throws IOException if an I/O error occurs while writing the output file
   */
  public void convertToHtml() throws IOException {
    this.snapshotList = this.photoAlbumModel.getSnapshotList();

    StringBuilder html = new StringBuilder();
    StringBuilder svg = new StringBuilder();

    for (ISnapshot snapshot : snapshotList) {

      svg.append("<svg width= \""+ xMax + "\" height=\"" + yMAx
              + "\" viewBox=\"0 0 " + xMax + " " + yMAx + "\" "
              + "xmlns=\"http://www.w3.org/2000/svg\">\n");

      svg.append("<rect x=\"1\" y=\"1\" width =\"1196\" height=\"996\" fill=\"#3A5A74\" "
              + "stroke=\"white\" stroke-width=\"4\"/>\n");

      svg.append("<text x=\"10\" y=\"60\" "
              + "font-family=\"Ariel\" font-size=\"35\" fill=\"black\" font-weight=\"bold\" >\n");
      svg.append(snapshot.getID() + "\n");
      svg.append("</text>\n");
      svg.append("<text x=\"10\" y=\"110\" "
              + "font-family=\"Ariel\" font-size=\"25\" fill=\"black\" font-weight=\"normal\" >\n");
      svg.append("Description: " + snapshot.getDescription());
      svg.append("</text>\n");

      for (IShape shape : snapshot.getShapeList()) {
        if (shape.getType().equals("rectangle")) {
          svg.append("<rect x=\"" + (shape.getPosition().getX() + 3)
                  + "\" y=\"" + (shape.getPosition().getY() + 150)
                  + "\" width=\"" + shape.getWidth() + "\" height=\"" + shape.getHeight()
                  + "\" fill=\"rgb" + shape.getColor() + "\" />\n");
        } else if (shape.getType().equals("oval")) {
          svg.append("<ellipse cx=\"" + (shape.getPosition().getX() + 3)
                  + "\" cy=\"" + (shape.getPosition().getY() + 150)
                  + "\" rx=\"" + shape.getWidth()  + "\" ry=\"" + shape.getHeight()
                  + "\" fill=\"rgb" + shape.getColor() + "\" />\n");
        }
      }
      svg.append("</svg>");
    }

    html.append("<html>\n");
    html.append("<head>\n");
    html.append("<title>PhotoAlbum</title>\n");
    html.append("</head>\n");
    html.append("<body>\n");
    html.append(svg);
    html.append("</body>\n");
    html.append("</html>");

    FileWriter fileWriter = new FileWriter(outputName);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    bufferedWriter.write(html.toString());
    bufferedWriter.close();
  }
}
