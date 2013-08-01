package game;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BallGame extends Application {
	private static String VIEW_GAME = "/resources/GameView.fxml";
	private static String STYLESHEET_FILE = "/resources/style.css";
	public static Image ICON = new Image(BallGame.class.getResourceAsStream("/resources/head.png"));

	@Override
	public void start(Stage stage) throws Exception {
		initGui(stage);
	}

	private void initGui(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(VIEW_GAME));
		Scene scene = SceneBuilder.create().root(root).width(500).height(530)
				.fill(Color.GRAY).build();
		scene.getStylesheets().add(STYLESHEET_FILE);
		stage.setScene(scene);
		stage.setTitle("hasCo de.com - Java FX 2 Ball Game Tutorial");
		stage.getIcons().add(ICON);
		stage.show();
	}

	public static void main(String... args) {
		launch(args);
	}

}
