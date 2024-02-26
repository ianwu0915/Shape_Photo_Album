import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import PAController.IPAController;
import PAController.PAController;
import PAView.IPAView;
import PhotoAlbumModel.IPAModel;
import PhotoAlbumModel.PAModel;

/**
 * This PAControllerTest class is to test the built-in methods in PAController class.
 */
public class PAControllerTest {
  private IPAController controller;
  private IPAModel model;
  private IPAView view;
  private String[] commandLine;

  /**
   * This method sets up a few instances for the following test.
   */
  @Before
  public void setUp() {
    model = new PAModel();
    controller = new PAController(model);
    commandLine = "-in tests.txt -view web -out controllerTest.html".split("\\s+");
  }

  /**
   * This method is to test resetModel() method.
   */
  @Test
  public void testResetModel() {
    controller.readCommandLine(commandLine);
    controller.readFile();
    assertEquals(1, model.getSnapshots().size());

    controller.resetModel();
    assertEquals(0, model.getSnapshots().size());
  }

  /**
   * This method is to test readFile() method.
   */
  @Test
  public void testReadFile() {
    assertEquals(0, model.getSnapshots().size());
    controller.readCommandLine(commandLine);
    controller.readFile();
    assertEquals(1, model.getSnapshots().size());
  }

  /**
   * This method is to test setInput() method.
   */
  @Test
  public void testSetInput() {
    controller.readCommandLine(commandLine);
    controller.readFile();
    assertEquals(1, model.getSnapshots().size());

    File tmp = new File("tmp.txt");

    try {
      tmp.createNewFile();
      FileWriter writer = new FileWriter(tmp);
      writer.write("snapshot\n");
      writer.write("snapshot\n");
      writer.close();
    } catch (IOException e) {
      // I/O error occurred
      assertEquals(1, 0);
    }

    if (controller.setInput("tmp.txt")) {
      controller.readFile();
      assertEquals(3, model.getSnapshots().size());
    } else {
      // Something went wrong while we were creating tmp.txt
      assertEquals(1, 0);
    }

  }

  /**
   * This method is to test updateView() method.
   */
  @Test
  public void testUpdateView() {
    controller.readCommandLine(commandLine);
    controller.updateView();
    // update view with no snapshots in the model, view would stay the same
    // default view is set to null
    assertNull(controller.getView());

    controller.readFile();
    controller.updateView();

    // check the view now is the WebWriter class
    assertEquals(PAView.WebWriter.class, controller.getView().getClass());

    String[] tmp = "-view graphical -in tests.txt".split("\\s+");
    controller.readCommandLine(tmp);
    controller.updateView();

    // since we change the type of view, the view now should be the GraphicalReader class
    assertEquals(PAView.GraphicReader.class, controller.getView().getClass());
    IPAView preView = controller.getView();

    controller.resetModel();
    controller.updateView();
    // Again, if updateView() is called with no snapshot in the model, view will stay the same
    assertEquals(preView, controller.getView());
  }
}
