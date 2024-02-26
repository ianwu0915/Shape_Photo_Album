package PAView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import PhotoAlbumModel.IShape;
import PhotoAlbumModel.Snapshot;

/**
 * This WebWriter class is used to generate a html file with the data in photo album model.
 */
public class WebWriter implements IPAView {
  private List<Snapshot> snapshots;
  private String output;

  /**
   * This is the constructor of this class.
   * @param snapshots a list of snapshots
   * @param output the name of the output file
   */
  public WebWriter(List<Snapshot> snapshots, String output) {
    this.snapshots = snapshots;
    this.output = output;
  }

  /**
   * This method is to generate a String output representing the html format of the view.
   * It translates the data in photo album model to a html format.
   * @return a String output of html format
   */
  private String generateHTML() {
    String res = "<!DOCTYPE html>\n"
            + "<html lang=\"en\">\n"
            + "<head>\n"
            + "    <meta charset=\"UTF-8\">\n"
            + "    <title>Testing!!</title>\n"
            + "    <?xml version=\"1.0\" standalone=\"no\"?>\n"
            + "</head>\n"
            + "<body>\n";

    for (Snapshot ss : snapshots) {
      String s = "<svg width=\"1200\" height=\"1000\" viewBox=\"0 0 1200 1000\"\n"
              + "xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
              + "<rect x=\"1\" y=\"1\" width=\"1196\" height=\"996\"\n"
              + "      fill=\"#caffbf\" stroke=\"blue\" stroke-width=\"4\"/>\n"
              + "<text x=\"10\" y=\"40\"\n"
              + "      font-family=\"Ariel\" font-size=\"40\" fill=\"black\" font-weight=\"bold\" >\n"
              + "      "
              + ss.getId() + "\n"
              + "</text>\n"
              + "<text x=\"10\" y=\"80\"\n"
              + "      font-family=\"Ariel\" font-size=\"25\" fill=\"black\">\n"
              + "      " + ss.getDescription() + "\n"
              + "</text>\n";

      for (IShape shape : ss.getShapeInfo().getPhotos()) {
        switch (shape.getType()) {
          case "rectangle":
            s += "<rect x=\""+ (3 + (int)Math.round(shape.getRefPointX()))
                    + "\" y=\"" + (100 + (int)Math.round(shape.getRefPointY()))
                    + "\" width=\"" + ((int)Math.round(shape.getXValue()))
                    + "\" height=\"" + ((int)Math.round(shape.getYValue()))
                    + "\"\n"
                    + "      fill=\""
                    + String.format("rgb(%.1f%%, %.1f%%, %.1f%%)", shape.getColor()[0] * 100,
                    shape.getColor()[1] * 100, shape.getColor()[2] * 100)
                    + "\"/>\n";
            break;
          case "oval":
            s += "<ellipse transform=\"translate("
                    + ((int)Math.round(shape.getRefPointX()) + 3)
                    + " "
                    + ((int)Math.round(shape.getRefPointY()) + 100 )
                    + ")\"\n"
                    + "         rx=\"" + ((int)Math.round(shape.getXValue())) + "\" "
                    + "ry=\"" + ((int)Math.round(shape.getYValue())) + "\"\n"
                    + "         fill=\""
                    + String.format("rgb(%.1f%%, %.1f%%, %.1f%%)", shape.getColor()[0] * 100,
                    shape.getColor()[1] * 100, shape.getColor()[2] * 100)
                    + "\"/>\n";
            break;
          default:
            System.out.println("Wrong Type");
        }
      }
      s += "</svg>\n";
      res += s;
    }
    res += "</body>\n";
    res += "</html>\n";
    return res;
  }

  /**
   * This method is to export the String of html format to a html file.
   */
  public void view() {
    File newFile = new File(output);

    try {
      newFile.createNewFile();
      FileWriter writer = new FileWriter(output);
      writer.write(generateHTML());
      writer.close();

    } catch (IOException e) {
      System.out.println("I/O error occurred");
    }
  }
}
