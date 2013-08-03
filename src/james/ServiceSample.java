package james;

import java.util.Random;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * A sample showing use of a Service to retrieve data in a background thread.
 * Selecting the Refresh button restarts the Service.
 * http://stackoverflow.com/questions
 * /13163947/how-to-update-a-set-of-javafx2-2-textfields-from-a-service
 * 
 * @see javafx.collections.FXCollections
 * @see javafx.concurrent.Service
 * @see javafx.concurrent.Task
 * @see javafx.scene.control.ProgressIndicator
 * @see javafx.scene.control.TableColumn
 * @see javafx.scene.control.TableView
 */
public class ServiceSample extends Application {

	final GetDailySalesService service = new GetDailySalesService();
	final Random random = new Random();

	private void init(Stage primaryStage) {
		Group root = new Group();
		primaryStage.setScene(new Scene(root));

		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(12));
		ListView<String> listView = new ListView<>();
		listView.setEditable(true);
		Button button = new Button("Refresh");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				service.restart();
			}
		});
		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new InstantEditingCell();
			}
		});
		listView.setPrefHeight(200);
		vbox.getChildren().addAll(listView, button);

		Region veil = new Region();
		veil.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
		ProgressIndicator p = new ProgressIndicator();
		p.setMaxSize(150, 150);

		p.progressProperty().bind(service.progressProperty());
		veil.visibleProperty().bind(service.runningProperty());
		p.visibleProperty().bind(service.runningProperty());
		listView.itemsProperty().bind(service.valueProperty());

		StackPane stack = new StackPane();
		stack.getChildren().addAll(vbox, veil, p);

		root.getChildren().add(stack);
		service.start();
	}

	/**
	 * A service for getting the DailySales data. This service exposes an
	 * ObservableList for convenience when using the service. This
	 * <code>results</code> list is final, though its contents are replaced when
	 * a service call successfully concludes.
	 */
	public class GetDailySalesService extends Service<ObservableList<String>> {

		/**
		 * Create and return the task for fetching the data. Note that this
		 * method is called on the background thread (all other code in this
		 * application is on the JavaFX Application Thread!).
		 * 
		 * @return A task
		 */
		@Override
		protected Task createTask() {
			return new GetDailySalesTask();
		}
	}

	public class GetDailySalesTask extends Task<ObservableList<String>> {
		@Override
		protected ObservableList<String> call() throws Exception {
			for (int i = 0; i < 500; i++) {
				updateProgress(i, 500);
				Thread.sleep(5);
			}
			ObservableList<String> sales = FXCollections.observableArrayList();
			sales.add("A1: " + random.nextInt());
			sales.add("A2: " + random.nextInt());
			return sales;
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	class InstantEditingCell extends ListCell<String> {
		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setGraphic(null);
			} else {
				final TextField textField = new TextField(getString());
				textField.setMinWidth(this.getWidth()
						- this.getGraphicTextGap() * 2);
				textField.focusedProperty().addListener(
						new ChangeListener<Boolean>() {
							@Override
							public void changed(
									ObservableValue<? extends Boolean> value,
									Boolean wasFocused, Boolean isFocused) {
								if (!isFocused) {
									commitEdit(textField.getText());
								}
							}
						});
				setGraphic(textField);
			}
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}

	class ClickableEditingCell extends ListCell<String> {

		private TextField textField;

		public ClickableEditingCell() {
		}

		@Override
		public void startEdit() {
			if (!isEmpty()) {
				super.startEdit();
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.selectAll();
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();

			setText((String) getItem());
			setGraphic(null);
		}

		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setText(null);
					setGraphic(textField);
				} else {
					setText(getString());
					setGraphic(null);
				}
			}
		}

		private void createTextField() {
			textField = new TextField(getString());
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()
					* 2);
			textField.focusedProperty().addListener(
					new ChangeListener<Boolean>() {
						@Override
						public void changed(
								ObservableValue<? extends Boolean> arg0,
								Boolean arg1, Boolean arg2) {
							if (!arg2) {
								commitEdit(textField.getText());
							}
						}
					});
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}

}