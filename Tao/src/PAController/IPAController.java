package PAController;

import PAView.IPAView;

public interface IPAController {

  /**
   * This method is to load the data in the input file to model.
   */
  void readFile();

  /**
   * This is the getter method for view.
   * @return an IPAView instance
   */
  IPAView getView();

  /**
   * This is the setter method for input.
   * @param filename new input file
   * @return True if the operation succeeds, false otherwise
   */
  boolean setInput(String filename);

  /**
   * This method is to read the String array from command line or any String array with valid
   * format.
   * Passing invalid command line to this method would cause the program to stop.
   * @param commandLine a String array representing command line arguments
   */
  void readCommandLine(String[] commandLine);

  /**
   * This method is to update view with current status.
   * If there are no snapshots in the model, do nothing.
   */
  void updateView();

  /**
   * This method is to reset the photo album model.
   */
  void resetModel();
}
