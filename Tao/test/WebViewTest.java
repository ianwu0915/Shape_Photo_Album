import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import PhotoAlbumModel.*;
import PAView.*;
import PAController.*;

/**
 * This WebViewTest class is to test the content of the html file exported from WebWriter class.
 */
public class WebViewTest {
  private String[] commandLine;
  private IPAController controller;
  private IPAModel model;
  private IPAView view;

  /**
   * This method sets up a few instances for the following tests.
   */
  @Before
  public void setUp() {
    commandLine = "-view web -in tests.txt".split("\\s+");
    model = new PAModel();
  }

  /**
   * Test Case 1
   */
  @Test
  public void test1() {
    controller = new PAController(model);
    controller.readCommandLine(commandLine);
    controller.readFile();
    controller.updateView();
    view = controller.getView();
    view.view();

    // Use File class to read the output file
    File f = new File("out.html");
    String s = "";
    try {
      Scanner scanner = new Scanner(f);

      // Reconstruct the String contents
      while (scanner.hasNext()) {

        String tmp = scanner.nextLine();

        if (!scanner.hasNext()) {
          s += tmp;
        } else {
          s += tmp + "\n";
        }
      }
    } catch (FileNotFoundException e) {
      // The output file doesn't exist (something went wrong when we created the file)
      assertEquals(1, 0);
    }

    // Comparing the contents
    assertEquals("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Testing!!</title>\n" +
            "    <?xml version=\"1.0\" standalone=\"no\"?>\n" +
            "</head>\n" +
            "<body>\n" +
            "<svg width=\"1200\" height=\"1000\" viewBox=\"0 0 1200 1000\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n" +
            "<rect x=\"1\" y=\"1\" width=\"1196\" height=\"996\"\n" +
            "      fill=\"#caffbf\" stroke=\"blue\" stroke-width=\"4\"/>\n" +
            "<text x=\"10\" y=\"40\"\n" +
            "      font-family=\"Ariel\" font-size=\"40\" fill=\"black\" font-weight=\"bold\" >\n"
            + "      " + model.getSnapshotIDList().get(0) + "\n" +
            "</text>\n" +
            "<text x=\"10\" y=\"80\"\n" +
            "      font-family=\"Ariel\" font-size=\"25\" fill=\"black\">\n" +
            "      First selfie\n" +
            "</text>\n" +
            "<ellipse transform=\"translate(303 300)\"\n" +
            "         rx=\"50\" ry=\"150\"\n" +
            "         fill=\"rgb(11.8%, 15.7%, 19.6%)\"/>\n" +
            "<rect x=\"553\" y=\"700\" width=\"250\" height=\"100\"\n" +
            "      fill=\"rgb(100.0%, 0.0%, 100.0%)\"/>\n" +
            "</svg>\n" +
            "</body>\n" +
            "</html>", s);
  }

  /**
   * Test Case 2
   */
  @Test
  public void test2() {
    controller = new PAController(model);
    controller.readCommandLine(commandLine);
    controller.readFile();
    controller.updateView();


    // Use a temporary file to give instructions of editing photo
    File tmp = new File("tmp.txt");
    try {
      tmp.createNewFile();
      FileWriter writer = new FileWriter(tmp);
      writer.write("remove obj1\n");
      writer.write("snapshot remove obj1\n");
      writer.close();
    } catch (IOException e) {
      // I/O error occurred
      assertEquals(1, 0);
    }

    if (controller.setInput("tmp.txt")) {
      controller.readFile();
      tmp.delete();

      controller.updateView();
      view = controller.getView();
      view.view();
    } else {
      // The temporary file doesn't exist
      assertEquals(1, 0);
    }

    String s = "";
    File outputFile = new File("out.html");
    try {
      Scanner sc = new Scanner(outputFile);
      while (sc.hasNext()) {
        String line = sc.nextLine();

        if (!sc.hasNext()) {
          s += line;
        } else {
          s += line + "\n";
        }
      }
    } catch (FileNotFoundException ee) {
      // The output file doesn't exit (something went wrong when we create the file)
      assertEquals(1, 0);
    }

    assertEquals("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Testing!!</title>\n" +
            "    <?xml version=\"1.0\" standalone=\"no\"?>\n" +
            "</head>\n" +
            "<body>\n" +
            "<svg width=\"1200\" height=\"1000\" viewBox=\"0 0 1200 1000\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n" +
            "<rect x=\"1\" y=\"1\" width=\"1196\" height=\"996\"\n" +
            "      fill=\"#caffbf\" stroke=\"blue\" stroke-width=\"4\"/>\n" +
            "<text x=\"10\" y=\"40\"\n" +
            "      font-family=\"Ariel\" font-size=\"40\" fill=\"black\" font-weight=\"bold\" >\n" +
            "      " + model.getSnapshotIDList().get(0) + "\n" +
            "</text>\n" +
            "<text x=\"10\" y=\"80\"\n" +
            "      font-family=\"Ariel\" font-size=\"25\" fill=\"black\">\n" +
            "      First selfie\n" +
            "</text>\n" +
            "<ellipse transform=\"translate(303 300)\"\n" +
            "         rx=\"50\" ry=\"150\"\n" +
            "         fill=\"rgb(11.8%, 15.7%, 19.6%)\"/>\n" +
            "<rect x=\"553\" y=\"700\" width=\"250\" height=\"100\"\n" +
            "      fill=\"rgb(100.0%, 0.0%, 100.0%)\"/>\n" +
            "</svg>\n" +
            "<svg width=\"1200\" height=\"1000\" viewBox=\"0 0 1200 1000\"\n" +
            "xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n" +
            "<rect x=\"1\" y=\"1\" width=\"1196\" height=\"996\"\n" +
            "      fill=\"#caffbf\" stroke=\"blue\" stroke-width=\"4\"/>\n" +
            "<text x=\"10\" y=\"40\"\n" +
            "      font-family=\"Ariel\" font-size=\"40\" fill=\"black\" font-weight=\"bold\" >\n" +
            "      " + model.getSnapshotIDList().get(1) + "\n" +
            "</text>\n" +
            "<text x=\"10\" y=\"80\"\n" +
            "      font-family=\"Ariel\" font-size=\"25\" fill=\"black\">\n" +
            "      remove obj1\n" +
            "</text>\n" +
            "<rect x=\"553\" y=\"700\" width=\"250\" height=\"100\"\n" +
            "      fill=\"rgb(100.0%, 0.0%, 100.0%)\"/>\n" +
            "</svg>\n" +
            "</body>\n" +
            "</html>", s);

  }

}
