package malc3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * http://www.e-zest.net/blog/javafx-2-x-development-using-fxml/
 * @author amol.hingmire
 */
public class DemoShowHide extends Application {

	private Stage stage;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		stage = mainStage;
		Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml")); // zde proste jenom zmenit na TextArea
		Scene scene = stage.getScene();
		if (scene == null) {
			scene = new Scene(root);
			stage.setScene(scene);
		} else {
			stage.getScene().setRoot(root);
		}
		stage.sizeToScene();
		stage.show();
	}

	public Stage getStage() {
		return stage;
	}

}