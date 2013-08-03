package james;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @web http://java-buddy.blogspot.com/
 */
public class testScheduledExecutorService extends Application {

	Text textCounter;

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Button btn = new Button();
		btn.setText("Start ScheduledExecutorService");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startScheduledExecutorService();
			}
		});

		textCounter = new Text();

		VBox vBox = new VBox();
		vBox.getChildren().addAll(btn, textCounter);

		StackPane root = new StackPane();
		root.getChildren().add(vBox);

		Scene scene = new Scene(root, 300, 250);

		primaryStage.setTitle("java-buddy.blogspot.com");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void startScheduledExecutorService() {

		final ScheduledExecutorService scheduler = Executors
				.newScheduledThreadPool(1);

		scheduler.scheduleAtFixedRate(new Runnable() {

			int counter = 0;

			@Override
			public void run() {
				counter++;
				if (counter <= 10) {

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							textCounter.setText("isFxApplicationThread: "
									+ Platform.isFxApplicationThread() + "\n"
									+ "Counting: " + String.valueOf(counter));
						}
					});

				} else {
					scheduler.shutdown();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							textCounter.setText("isFxApplicationThread: "
									+ Platform.isFxApplicationThread() + "\n"
									+ "-Finished-");
						}
					});
				}

			}
		}, 1, 1, TimeUnit.SECONDS);
	}
}