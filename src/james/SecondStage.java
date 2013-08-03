package james;

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

/*
 * http://stackoverflow.com/questions/9997851/javafx-2-0-subwindow
 */

public class SecondStage extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// setup some dynamic data to display.
		final String STANDARD_TEXT = "Every Good Boy Deserves Fruit";
		final String ALTERNATE_TEXT = "Good Boys Deserve Fruit Always";
		final Label label = new Label(STANDARD_TEXT);

		// configure the primary stage.
		StackPane primaryLayout = new StackPane();
		primaryLayout.getChildren().add(label);
		primaryLayout
				.setStyle("-fx-background-color: lightgreen; -fx-padding: 10;");
		primaryStage.setScene(new Scene(primaryLayout, 200, 100));
		primaryStage.setTitle("Primary Stage");

		// configure the secondary stage.
		final Stage secondaryStage = new Stage(StageStyle.DECORATED);
		CheckBox alternateTextCheck = new CheckBox("Show alternate text");
		alternateTextCheck.selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> selected,
							Boolean oldValue, Boolean newValue) {
						if (newValue)
							label.setText(ALTERNATE_TEXT);
						else
							label.setText(STANDARD_TEXT);
					}
				});
		StackPane secondaryLayout = new StackPane();
		secondaryLayout.getChildren().add(alternateTextCheck);
		secondaryLayout
				.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		secondaryStage.setScene(new Scene(secondaryLayout, 200, 100));
		secondaryStage.setTitle("Secondary Stage");

		// specify stage locations.
		secondaryStage.setX(400);
		secondaryStage.setY(200);
		primaryStage.setX(400);
		primaryStage.setY(350);

		// add a trigger to hide the secondary stage when the primary stage is
		// hidden.
		// this will cause all stages to be hidden (which will cause the app to
		// terminate).
		primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent onClosing) {
				secondaryStage.hide();
			}
		});

		// show both stages.
		primaryStage.show();
		secondaryStage.show();
	}
}