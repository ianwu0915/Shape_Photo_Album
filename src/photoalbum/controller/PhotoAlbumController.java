package photoalbum.controller;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

import photoalbum.model.IPhotoAlbumModel;
import photoalbum.model.ISnapshot;

import photoalbum.view.PhotoAlbumView;
import photoalbum.view.PhotoAlbumWebView;

/**
 * The controller for the photo album application, responsible for reading commands
 * from an input text file and interact with the model and view to create and manipulate
 * shapes and snapshots.
 */
public class PhotoAlbumController implements IPhotoAlbumController {
  private File input;
  private IPhotoAlbumModel photoAlbumModel;
  private PhotoAlbumView photoAlbumView;
  private PhotoAlbumWebView photoAlbumWebView;
  private Scanner scanner;

  /**
   * Constructor of Controller for Graphical View.
   *
   * @param photoAlbumModel the Model of the photo album application.
   * @param input           the input text file.
   * @param photoAlbumView  the View of the photo album application for Graphical display.
   */

  public PhotoAlbumController(IPhotoAlbumModel photoAlbumModel, File input, PhotoAlbumView photoAlbumView) {
    if (input == null) {
      throw new IllegalArgumentException();
    }
    this.photoAlbumModel = photoAlbumModel;
    this.photoAlbumView = photoAlbumView;
    this.input = input;
    this.photoAlbumWebView = null;
  }

  /**
   * Constructor of Controller for Web View.
   *
   * @param photoAlbumModel   the Model of the photo album application.
   * @param input             the input text file.
   * @param photoAlbumWebView the View of the photo album application for Web display.
   */
  public PhotoAlbumController(IPhotoAlbumModel photoAlbumModel, File input, PhotoAlbumWebView photoAlbumWebView) {
    if (input == null) {
      throw new IllegalArgumentException();
    }
    this.photoAlbumModel = photoAlbumModel;
    this.photoAlbumWebView = photoAlbumWebView;
    this.input = input;
    this.photoAlbumView = null;
  }

  /**
   * Reads commands from the input text file to create and manipulate
   * shapes and snapshots according to commands in the Model.
   * It will then pass the information that stored in Model to either Graphical View
   * or Web View to display.
   */
  public void read() {
    try {
      scanner = new Scanner(this.input);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim(); // remove the head and tail spaces.
        if (line.isEmpty() || line.startsWith("#")) { //ignore empty line and comment(# ...)
          continue;
        }
        String[] tokens = line.split("\\s+");
        String command = tokens[0];

        switch (command.toLowerCase()) {
          case "shape":
            String name = tokens[1];
            String kind = tokens[2];
            double xPosition = Double.parseDouble(tokens[3]);
            double yPosition = Double.parseDouble(tokens[4]);
            int width = Integer.parseInt(tokens[5]);
            int height = Integer.parseInt(tokens[6]);

            int red = Integer.parseInt(tokens[7]);
            int green = Integer.parseInt(tokens[8]);
            int blue = Integer.parseInt(tokens[9]);

            photoAlbumModel.createShape(kind, name, red, green, blue,
                    xPosition, yPosition, width, height);
            break;

          case "move":
            String shapeName = tokens[1];
            double newX = Double.parseDouble(tokens[2]);
            double newY = Double.parseDouble(tokens[3]);
            photoAlbumModel.moveShape(shapeName, newX, newY);
            break;
          case "resize":
            String shapeName2 = tokens[1];
            int newWidth = Integer.parseInt(tokens[2]);
            int newHeight = Integer.parseInt(tokens[3]);
            photoAlbumModel.reSizeShape(shapeName2, newWidth, newHeight);
            break;

          case "color":
            String shapeName3 = tokens[1];
            int newRed = Integer.parseInt(tokens[2]);
            int newGreen = Integer.parseInt(tokens[3]);
            int newBlue = Integer.parseInt(tokens[4]);
            photoAlbumModel.changeShapeColor(shapeName3, newRed, newGreen, newBlue);
            break;

          case "snapshot":
            if (tokens.length == 1) {
              photoAlbumModel.createSnapShot(" ");
              break;
            }
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < tokens.length; i++) {
              sb.append(tokens[i] + " ");
            }

            String description = sb.toString();
            photoAlbumModel.createSnapShot(description);
            break;

          case "remove":
            String shapeName4 = tokens[1];
            photoAlbumModel.removeShape(shapeName4);
            break;

          default:
            System.err.println("Unknown command: " + command);
            break;
        }
      }
      if (photoAlbumView != null && photoAlbumWebView == null) {
        for (ISnapshot snapshot : photoAlbumModel.getSnapshotList()) {
          Image temp = this.photoAlbumView.createGraphs(snapshot.getShapeList());
          photoAlbumView.getSnapshotImageList().put(snapshot.getID(), temp);
          photoAlbumView.getImageList().add(temp);
        }
        photoAlbumView.setPhotoAlbumModel(this.photoAlbumModel);
//        photoAlbumView.getPhotoAlbumModel().printSnapShots();

        photoAlbumView.setCurrentSnapshot(photoAlbumView.getSnapshotList().get(0));

        JLabel label = new JLabel(new ImageIcon(photoAlbumView.getImageList().get(0)));
        photoAlbumView.getMiddlePanel().removeAll();
        photoAlbumView.getMiddlePanel().add(label, BorderLayout.CENTER);
        photoAlbumView.getMiddlePanel().revalidate();
        photoAlbumView.getMiddlePanel().repaint();
        photoAlbumView.view();

      }

      if (photoAlbumView == null && photoAlbumWebView != null) {
        photoAlbumWebView.convertToHtml();
      }

    } catch (FileNotFoundException e) {
      System.err.println("Error: input file not found.");
      e.printStackTrace();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
