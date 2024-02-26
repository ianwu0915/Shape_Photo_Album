import PAController.PAController;
import PAController.IPAController;

import PAView.IPAView;
import PhotoAlbumModel.IPAModel;
import PhotoAlbumModel.PAModel;


public class PhotoAlbumMain {
  public static void main(String[] args) {

    IPAModel model = new PAModel();
    IPAController controller = new PAController(model);
    controller.readCommandLine(args);
    controller.readFile();
    controller.updateView();
    IPAView view = controller.getView();
    view.view();

  }
}