package james;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
 * http://stackoverflow.com/questions/9165251/execute-task-in-background-in-javafx
 */
public class Minimal extends Application {

	private TilePane loadPane;
	private ProgressIndicator[] indicators = new ProgressIndicator[9];
	private Label loading[] = new Label[9];
	private Color[] colors = { Color.BLACK, Color.BLUE, Color.CRIMSON,
			Color.DARKCYAN, Color.FORESTGREEN, Color.GOLD, Color.HOTPINK,
			Color.INDIGO, Color.KHAKI };
	private int counter = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// creating Layout
		final Group root = new Group();
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		StackPane waitingPane = new StackPane();
		final ProgressBar progress = new ProgressBar();
		Label load = new Label("loading things...");
		progress.setTranslateY(-25);
		load.setTranslateY(25);
		waitingPane.getChildren().addAll(new Rectangle(400, 400, Color.WHITE),
				load, progress);
		root.getChildren().add(waitingPane);

		// Task for computing the Panels:
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				for (int i = 0; i < 20; i++) {
					try {
						Thread.sleep(new Random().nextInt(1000));
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					final double prog = i * 0.05;
					Platform.runLater(new Runnable() {
						public void run() {
							progress.setProgress(prog);
						}
					});
				}
				return null;
			}
		};

		// stateProperty for Task:
		task.stateProperty().addListener(new ChangeListener<Worker.State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable,
					State oldValue, Worker.State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					loadPanels(root);
				}
			}
		});

		// start Task
		new Thread(task).start();

		primaryStage.show();
	}

	private void loadPanels(Group root) {
		// change to loadPanel:
		root.getChildren().set(0, createLoadPane());

		// Service:
		final Service<Rectangle> RecBuilder = new Service<Rectangle>() {
			@Override
			protected Task<Rectangle> createTask() {
				return new Task<Rectangle>() {
					@Override
					protected Rectangle call() throws InterruptedException {
						updateMessage("loading rectangle . . .");
						updateProgress(0, 10);
						for (int i = 0; i < 10; i++) {
							Thread.sleep(100);
						}
						updateMessage("Finish!");
						return new Rectangle((380) / 3, (380) / 3,
								colors[counter]);
					}
				};
			}
		};

		// StateListener
		RecBuilder.stateProperty().addListener(
				new ChangeListener<Worker.State>() {
					@Override
					public void changed(
							ObservableValue<? extends Worker.State> observableValue,
							Worker.State oldState, Worker.State newState) {
						switch (newState) {
						case SCHEDULED:
							break;
						case READY:
						case RUNNING:
							break;
						case SUCCEEDED:
							Rectangle rec = RecBuilder.valueProperty()
									.getValue();
							indicators[counter].progressProperty().unbind();
							loading[counter].textProperty().unbind();
							loadPane.getChildren().set(counter, rec);
							if (counter < 8) {
								counter++;
								nextPane(RecBuilder);
							}
							break;
						case CANCELLED:
						case FAILED:
							loading[counter].textProperty().unbind();
							loading[counter].setText("Failed!");
							if (counter < 8) {
								counter++;
								nextPane(RecBuilder);
							}
							break;
						}
					}
				});

		// begin PanelBuilding:
		nextPane(RecBuilder);
	}

	private void nextPane(Service<Rectangle> recBuilder) {
		loading[counter].textProperty().bind(recBuilder.messageProperty());
		indicators[counter].visibleProperty().bind(
				recBuilder.progressProperty().isNotEqualTo(
						new SimpleDoubleProperty(
								ProgressBar.INDETERMINATE_PROGRESS)));
		recBuilder.restart();
	}

	private Node createLoadPane() {
		loadPane = new TilePane(5, 5);
		loadPane.setPrefColumns(3);
		loadPane.setPadding(new Insets(5));
		for (int i = 0; i < 9; i++) {
			StackPane waitingPane = new StackPane();
			Rectangle background = new Rectangle((380) / 3, (380) / 3,
					Color.WHITE);
			indicators[i] = new ProgressIndicator();
			indicators[i].setPrefSize(50, 50);
			indicators[i].setMaxSize(50, 50);
			indicators[i].setTranslateY(-25);
			indicators[i].setTranslateX(-10);
			loading[i] = new Label();
			loading[i].setTranslateY(25);
			waitingPane.getChildren().addAll(background, indicators[i],
					loading[i]);
			loadPane.getChildren().add(waitingPane);
		}

		return loadPane;
	}

	public static void main(String[] args) {
		launch(args);
	}

}