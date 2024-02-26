package PAController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import PAView.GraphicReader;
import PAView.IPAView;
import PAView.WebWriter;
import PhotoAlbumModel.IPAModel;

/**
 * This is PAController class is to simulate the photo album controller.
 */
public class PAController implements IPAController {
  private IPAModel model;
  private IPAView view;
  private String input;
  private String type;
  private String output;
  private int width;
  private int height;

  /**
   * This is the constructor of this class.
   * @param model the photo album model
   */
  public PAController(IPAModel model) {
    this.model = model;
    input = "";
    type = "";
    output = "out.html";
    width = -1;
    height = -1;
  }

  /**
   * This is the setter method for input.
   * @param filename new input file
   * @return True if the operation succeeds, false otherwise
   */
  public boolean setInput(String filename) {
    File tmp = new File(filename);
    if (tmp.exists()) {
      input = filename;
      return true;
    }

    return false;
  }

  /**
   * This method is to reset the photo album model.
   */
  public void resetModel() {
    model.reset();
  }

  /**
   * This is the getter method for view.
   * @return an IPAView instance
   */
  public IPAView getView() {
    return view;
  }

  /**
   * This method is to load the data in the input file to model.
   */
  public void readFile() {
    if (input.isBlank()) {
      System.out.println("Missing input file");
      System.exit(1);
    }

    try {
      File f = new File(input);
      Scanner reader = new Scanner(f);
      while (reader.hasNext()) {
        String s = reader.nextLine();
        s.toLowerCase();
        if (s.isBlank() || s.charAt(0) == '#') continue;
        String[] a = s.split("\\s+");

        switch (a[0]) {
          case "shape":
            if (a.length != 10) {
              System.out.println("Wrong shape creation");
              break;
            }
            model.addPhotoToAlbum(a[1], a[2], Double.parseDouble(a[3]), Double.parseDouble(a[4]),
                    Double.parseDouble(a[5]), Double.parseDouble(a[6]),
                    Double.parseDouble(a[7]) / 255, Double.parseDouble(a[8]) / 255,
                    Double.parseDouble(a[9]) / 255);
            break;
          case "move":
            if (a.length != 4) {
              System.out.println("Wrong movement");
              break;
            }
            model.editReferencePoint(a[1], Double.parseDouble(a[2]), Double.parseDouble(a[3]));
            break;
          case "color":
            if (a.length != 5) {
              System.out.println("Wrong color change");
              break;
            }
            model.changeColor(a[1], Double.parseDouble(a[2]) / 255,
                    Double.parseDouble(a[3]) / 255, Double.parseDouble(a[4]) / 255);
            break;
          case "resize":
            if (a.length != 4) {
              System.out.println("Wrong resizing");
              break;
            }
            model.editXYValue(a[1], Double.parseDouble(a[2]), Double.parseDouble(a[3]));
            break;
          case "remove":
            if (a.length != 2) {
              System.out.println("Wrong removing");
              break;
            }
            model.deletePhotoFromAlbum(a[1]);
            break;
          case "snapshot":
            String description = "";
            for (int i = 1; i < a.length; i++) {
              if (i == a.length - 1) {
                description += a[i];
                break;
              }
              description += a[i] + " ";
            }
            model.snapshot(description);
            break;
          default:
            System.out.println("Wrong commands");
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Unable to open the file");
      System.exit(1);

    }
  }

  /**
   * This method is to read the String array from command line or any String array with valid
   * format.
   * Passing invalid command line to this method would cause the program to stop.
   * @param commandLine a String array representing command line arguments
   */
  public void readCommandLine(String[] commandLine) {
    input = "";
    type = "";
    output = "out.html";
    width = -1;
    height = -1;

    if (commandLine.length < 4) {
      System.out.println("There should be at least 4 command-line arguments");
      System.exit(1);
    }

    if (commandLine.length > 8) {
      System.out.println("There should be at most 8 command-line arguments");
      System.exit(1);
    }


    for (int i = 0; i < commandLine.length; i++) {
      if ((commandLine[i].equals("-in") && input.isEmpty())) {
        if (i + 1 < commandLine.length) {
          input = commandLine[i + 1];
        } else {
          System.out.println("Missing input file");
          System.exit(1);
        }
      }

      if ((commandLine[i].equals("-v") || commandLine[i].equals("-view")) && type.isEmpty()) {
        if (i + 1 < commandLine.length) {
          type = commandLine[i + 1];
        } else {
          System.out.println("Missing type of view");
          System.exit(1);
        }
      }

      if (commandLine[i].equals("-out")) {
        if (i + 1 < commandLine.length) {
          if (!commandLine[i + 1].isBlank()) {
            output = commandLine[i + 1];
          }
        }
      }

      try {
        int tmp = Integer.parseInt(commandLine[i]);
        if (width == -1) {
          width = tmp;
        } else if (height == -1) {
          height = tmp;
        }
      } catch (NumberFormatException e1) {
      }
    }

    if (width <= 0) width = 1000;
    if (height <= 0) height = 1000;
  }

  /**
   * This method is to update view with current status.
   * If there are no snapshots in the model, do nothing.
   */
  public void updateView() {
    IPAView res = null;

    if (model.getSnapshots().isEmpty()) {
      return;
    }

    if (type.equalsIgnoreCase("graphical")) {
      res = new GraphicReader(model.getSnapshots(), model.getSnapshotIDList(), width, height);
    } else if (type.equalsIgnoreCase("web")) {
      res = new WebWriter(model.getSnapshots(), output);
    } else {
      System.out.println("Invalid type of view");
      System.exit(1);
    }

    view = res;
  }
}
