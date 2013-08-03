package james;

import javafx.animation.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.util.*;

/*
 * http://stackoverflow.com/questions/9966136/javafx-periodic-background-task?rq=1
 */
public class s extends Application {
	public void start(Stage primaryStage) throws Exception {
		Timeline fiveSecondsWonder = new Timeline(new KeyFrame(
				Duration.seconds(5), new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						System.out
								.println("this is called every 5 seconds on UI thread");
					}
				}));
		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		fiveSecondsWonder.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
