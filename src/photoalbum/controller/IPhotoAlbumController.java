package photoalbum.controller;

/**
 * This interface is for the Controller of the photo album application
 * in the MVC design pattern.
 * It receives the instruction from input and delivers it to the Model to process, and
 * transfer the processed data for View to display.
 */
public interface IPhotoAlbumController {

  /**
   * Reads commands from the input text file to create and manipulate
   * shapes and snapshots according to commands in the Model.
   * It will then pass the information that stored in Model to either Graphical View
   * or Web View to display.
   */
  void read();
}
