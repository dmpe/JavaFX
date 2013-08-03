package james;

import james.DataSource;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AvcHmi extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Text t = new Text(10, 50,
				"Replace/update this text periodically with data");

		Group root = new Group();
		root.getChildren().add(t);

		primaryStage.setScene(new Scene(root, 400, 300));
		primaryStage.show();

		new Thread() {
			private DataSource dataSource = new DataSource();

			{
				setDaemon(true);
			}

			@Override
			public void run() {
				try {
					for (;;) {
						Thread.sleep(100);

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								System.out.println(dataSource.getDataMap().get(
										"key1"));
							}
						});
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}