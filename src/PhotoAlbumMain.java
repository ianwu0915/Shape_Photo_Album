import java.io.File;
import java.io.IOException;

import photoalbum.controller.PhotoAlbumController;
import photoalbum.model.PhotoAlbumModel;
import photoalbum.view.PhotoAlbumView;
import photoalbum.view.PhotoAlbumWebView;

/**
 * Main class for the photo album application.
 * Parses command line arguments and initializes the application based on user input.
 */

public class PhotoAlbumMain {

  /**
   * Parses command line arguments and displays a photo album using a graphical or web-based view.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {

    String inputFileName = null;
    String outputFileName = null;
    String viewType = null;
    int xMax = 1000;
    int yMax = 1000;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        inputFileName = args[++i];
      } else if (args[i].equals("-out")) {
        outputFileName = args[++i];
      } else if (args[i].equals("-view") || args[i].equals("-v")) {
        viewType = args[++i];
      } else if (i < args.length - 1 && isInteger(args[i]) && isInteger(args[i + 1])) {
        xMax = Integer.parseInt(args[i]);
        yMax = Integer.parseInt(args[++i]);
      }
    }

    if (inputFileName == null || viewType == null) {
      System.err.println("Usage: java CommandReader -in [inputFile] -v [view type]");
      System.exit(1);
    }

    if (viewType.equalsIgnoreCase("graphical")) {

      File inputFile = new File(inputFileName);
      PhotoAlbumModel photoAlbumModel = new PhotoAlbumModel();
      PhotoAlbumView photoAlbumView = new PhotoAlbumView(photoAlbumModel);

      new PhotoAlbumController(photoAlbumModel, inputFile,
              photoAlbumView).read();

      photoAlbumView.setSize(xMax, yMax);
      photoAlbumModel.printSnapShots();
    }

    if (viewType.equalsIgnoreCase("web")) {
      if (outputFileName == null) {
        System.err.println("Output filename required!-> -out [output name.html]");
        System.exit(1);
      }

      try {
        File inputFile = new File(inputFileName);
        PhotoAlbumModel photoAlbumModel = new PhotoAlbumModel();
        new PhotoAlbumController(photoAlbumModel, inputFile,
                new PhotoAlbumWebView(photoAlbumModel, outputFileName, xMax, yMax)).read();
        photoAlbumModel.printSnapShots();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Determines whether a string represents an integer.
   *
   * @param str The string to check.
   * @return true if the string represents an integer, false otherwise.
   */
  public static boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }
}
