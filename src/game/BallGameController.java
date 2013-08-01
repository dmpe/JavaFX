package game;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BallGameController implements Initializable {
	// UI ELEMENTS
	@FXML
	private Group area;

	@FXML
	private Circle ball;

	@FXML
	private Rectangle borderTop;

	@FXML
	private Rectangle borderBottom;

	@FXML
	private Rectangle borderLeft;

	@FXML
	private Rectangle borderRight;

	@FXML
	private Rectangle paddle;

	@FXML
	private Text gameOverText;

	@FXML
	private Text winnerText;

	@FXML
	private Button startButton;

	@FXML
	private Button quitButton;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private Label remainingBlocksLabel;

	// GAME MODEL
	private final GameModel model = new GameModel();

	// GAME HEARTBEAT
	private final EventHandler<ActionEvent> pulseEvent = new EventHandler<ActionEvent>() {
		@Override
		public void handle(final ActionEvent evt) {
			checkWin();
			checkCollisions();
			updateBallPosition();
		}
	};

	// THE TIMELINE, RUNS EVERY 10MS
	private final Timeline heartbeat = TimelineBuilder.create()
			.keyFrames(new KeyFrame(new Duration(10.0), pulseEvent))
			.cycleCount(Timeline.INDEFINITE).build();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(final URL url, final ResourceBundle bundle) {
		bindPaddleMouseEvents();
		bindStartButtonEvents();
		bindQuitButtonEvents();
		bindElementsToModel();
		initializeBoxes();
		initializeGame();
		area.requestFocus();
	}

	/**
	 * binds ui elements to model state
	 */
	private void bindElementsToModel() {
		startButton.disableProperty().bind(model.getGameStopped().not());
		ball.centerXProperty().bind(model.getBallX());
		ball.centerYProperty().bind(model.getBallY());
		paddle.xProperty().bind(model.getPaddleX());
		gameOverText.visibleProperty().bind(model.getGameLost());
		winnerText.visibleProperty().bind(model.getGameWon());
		progressBar.progressProperty().bind(
				model.getBoxesLeft().subtract(model.getInitialAmountBlocks())
						.multiply(-1).divide(model.getInitialAmountBlocks()));
		remainingBlocksLabel.textProperty().bind(
				Bindings.format("%.0f boxes left", model.getBoxesLeft()));
	}

	/**
	 * initializes the game, is called for every new game
	 */
	private void initializeGame() {
		model.reset();
	}

	/**
	 * initializes the boxes.
	 */
	private void initializeBoxes() {
		int startX = 15;
		int startY = 30;
		for (int v = 1; v <= model.getInitialBlocksVertical(); v++) {
			for (int h = 1; h <= model.getInitialBlocksHorizontal(); h++) {
				int x = startX + (h * 40);
				int y = startY + (v * 40);
				ImageView imageView = ImageViewBuilder.create()
						.image(BallGame.ICON).layoutX(x).layoutY(y).build();
				model.getBoxes().add(imageView);
			}
		}
		area.getChildren().addAll(model.getBoxes());
	}

	/**
	 * creates event handler for the quit button. pressing it immediatly quits
	 * the application.
	 */
	private void bindQuitButtonEvents() {
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent evt) {
				Platform.exit();
			}
		});
	}

	/**
	 * binds events to the start button. by pressing the start button, the game
	 * is initialized and the timeline execution is started.
	 */
	private void bindStartButtonEvents() {
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent evt) {
				initializeGame();
				model.getGameStopped().set(false);
				heartbeat.playFromStart();
			}
		});
	}

	/**
	 * binds events to drag the paddle using the mouse.
	 */
	private void bindPaddleMouseEvents() {
		paddle.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent evt) {
				model.setPaddleTranslateX(model.getPaddleTranslateX() + 150);
				model.setPaddleDragX(evt.getSceneX());
			}
		});
		paddle.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent evt) {
				if (!model.getGameStopped().get()) {
					double x = model.getPaddleTranslateX() + evt.getSceneX()
							- model.getPaddleDragX();
					model.getPaddleX().setValue(x);
				}
			}
		});
	}

	/**
	 * checks if the game is won.
	 */
	private void checkWin() {
		if (0 == model.getBoxesLeft().get()) {
			model.getGameWon().set(true);
			model.getGameStopped().set(true);
			heartbeat.stop();
		}
	}

	/**
	 * checks if the ball has collisions with the walls or the paddle.
	 */
	private void checkCollisions() {
		checkBoxCollisions();
		if (ball.intersects(paddle.getBoundsInLocal())) {
			model.incrementSpeed();
			model.setMovingDown(false);
		}
		if (ball.intersects(borderTop.getBoundsInLocal())) {
			model.incrementSpeed();
			model.setMovingDown(true);
		}
		if (ball.intersects(borderBottom.getBoundsInLocal())) {
			model.getGameStopped().set(true);
			model.getGameLost().set(true);
			heartbeat.stop();
		}
		if (ball.intersects(borderLeft.getBoundsInLocal())) {
			model.incrementSpeed();
			model.setMovingRight(true);
		}
		if (ball.intersects(borderRight.getBoundsInLocal())) {
			model.incrementSpeed();
			model.setMovingRight(false);
		}
		if (paddle.intersects(borderRight.getBoundsInLocal())) {
			model.getPaddleX().set(350);
		}
		if (paddle.intersects(borderLeft.getBoundsInLocal())) {
			model.getPaddleX().set(0);
		}
	}

	/**
	 * checks if the ball collides with one or more of the boxes. if there's a
	 * collision, the box is removed.
	 */
	private void checkBoxCollisions() {
		for (ImageView r : model.getBoxes()) {
			if (r.isVisible() && ball.intersects(r.getBoundsInParent())) {
				model.getBoxesLeft().set(model.getBoxesLeft().get() - 1);
				r.setVisible(false);
			}
		}
	}

	/**
	 * updates the ball position by calculating the ball's speed, position and
	 * direction.
	 */
	private void updateBallPosition() {
		double x = model.isMovingRight() ? model.getMovingSpeed() : -model
				.getMovingSpeed();
		double y = model.isMovingDown() ? model.getMovingSpeed() : -model
				.getMovingSpeed();
		model.getBallX().set(model.getBallX().get() + x);
		model.getBallY().set(model.getBallY().get() + y);
	}
}
