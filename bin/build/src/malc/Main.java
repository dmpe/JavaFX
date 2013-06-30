package malc;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Group root = new Group();
		// describes the window itself: name, size
		primaryStage.setTitle(" Aufgabe 10 by John Malc ");
		primaryStage.setScene(new Scene(root));
		// say: center on screen, user can resize, and it will in general exists
		primaryStage.centerOnScreen();
		primaryStage.setResizable(true);
		primaryStage.show();

		// Ellipse alone
		Ellipse a = new Ellipse();
		a.setFill(Color.RED);
		a.setCenterX(205);
		a.setCenterY(150);
		a.setRadiusX(80);
		a.setRadiusY(30);

		// shows Ellipse and it will add it to the group
		root.getChildren().add(new Group(a));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
