package ex;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BackSpace extends Application {

	private final ObservableList<String> strings = FXCollections
			.observableArrayList("Option 1", "Option 2", "Option 3",
					"Option 4", "Option 5", "Option 6",
					"Long ComboBox item 1 2 3 4 5 6 7 8 9", "Option 7",
					"Option 8", "Option 9", "Option 10", "Option 12",
					"Option 13", "Option 14", "Option 15", "Option 16",
					"Option 17", "Option 18", "Option 19", "Option 20",
					"Option 21", "Option 22", "Option 23", "Option 24",
					"Option 25", "Option 26", "Option 27", "Option 28",
					"Option 29", "Option 30", "Option 31", "Option 32",
					"Option 33", "Option 34", "Option 35", "Option 36",
					"Option 37", "Option 38", "Option 39", "Option 40",
					"Option 41", "Option 42", "Option 43", "Option 44",
					"Option 45", "Option 46", "Option 47", "Option 48",
					"Option 49", "Option 50", "Option 51", "Option 52",
					"Option 53", "Option 54", "Option 55", "Option 56",
					"Option 57", "Option 58", "Option 59", "Option 60",
					"Option 61", "Option 62", "Option 63", "Option 64",
					"Option 65", "Option 66", "Option 67", "Option 68",
					"Option 69", "Option 70", "Option 71", "Option 72",
					"Option 73", "Option 74", "Option 75");
	private final ObservableList<String> fonts = FXCollections
			.observableArrayList(Font.getFamilies());

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("ComboBox");

		// non-editable column
		VBox nonEditBox = new VBox(15);

		ComboBox comboBox1 = new ComboBox();
		comboBox1.setEditable(true);
		comboBox1.setItems(FXCollections.observableArrayList(strings.subList(0,
				4)));
		comboBox1.sceneProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				System.out.println("sceneProperty changed!");
			}
		});

		nonEditBox.getChildren().add(comboBox1);

		ComboBox comboBox2 = new ComboBox();
		comboBox2.setEditable(true);
		comboBox2.setItems(FXCollections.observableArrayList(strings.subList(0,
				5)));
		nonEditBox.getChildren().add(comboBox2);

		TextField textField1 = new TextField();
		nonEditBox.getChildren().add(textField1);

		nonEditBox.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)
						|| keyEvent.getCode().equals(KeyCode.DELETE)) {
					System.out.println("VBox is receiving key event !");
				}
			}
		});

		HBox vbox = new HBox(20);
		vbox.setLayoutX(40);
		vbox.setLayoutY(25);

		vbox.getChildren().addAll(nonEditBox);
		Scene scene = new Scene(new Group(vbox), 620, 190);

		stage.setScene(scene);
		stage.show();

		// scene.impl_focusOwnerProperty().addListener(new
		// ChangeListener<Node>() {
		// public void changed(ObservableValue<? extends Node> ov, Node t, Node
		// t1) {
		// System.out.println("focus moved from " + t + " to " + t1);
		// }
		// });
	}
}