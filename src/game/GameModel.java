package game;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public class GameModel {
	// amount of horizontal blocks.
	private final int INITIAL_BLOCKS_HORIZONTAL = 10;

	// amount of vertical blocks.
	private final int INITIAL_BLOCKS_VERTICAL = 5;

	// amount of blocks total = vertical * horizontal
	private final int INITIAL_AMOUNT_BLOCKS = getInitialBlocksHorizontal()
			* getInitialBlocksVertical();

	// coordinates of the ball
	private final DoubleProperty ballX = new SimpleDoubleProperty();
	private final DoubleProperty ballY = new SimpleDoubleProperty();

	// x coordinate of the paddle
	private final DoubleProperty paddleX = new SimpleDoubleProperty();

	// game is stopped?
	private final BooleanProperty gameStopped = new SimpleBooleanProperty();

	// game is lost?
	private final BooleanProperty gameLost = new SimpleBooleanProperty(false);

	// game is won?
	private final BooleanProperty gameWon = new SimpleBooleanProperty(false);

	// amount of boxes left
	private final DoubleProperty boxesLeft = new SimpleDoubleProperty(
			getInitialAmountBlocks());

	// ball is moving in direction: down?
	private boolean movingDown = true;

	// ball is moving in direction: right?
	private boolean movingRight = true;

	// ball moving speed
	private double movingSpeed = 1.0;

	// paddle drag/translate x
	private double paddleDragX = 0.0;
	private double paddleTranslateX = 0.0;

	// a collection of image elements
	private final ObservableList<ImageView> boxes = FXCollections
			.observableArrayList();

	public double getPaddleTranslateX() {
		return paddleTranslateX;
	}

	public double getPaddleDragX() {
		return paddleDragX;
	}

	public BooleanProperty getGameStopped() {
		return gameStopped;
	}

	public void setPaddleTranslateX(final double d) {
		this.paddleTranslateX = d;

	}

	public void setPaddleDragX(final double d) {
		this.paddleDragX = d;
	}

	public DoubleProperty getPaddleX() {
		return paddleX;
	}

	public int getInitialBlocksVertical() {
		return INITIAL_BLOCKS_VERTICAL;
	}

	public int getInitialBlocksHorizontal() {
		return INITIAL_BLOCKS_HORIZONTAL;
	}

	public ObservableList<ImageView> getBoxes() {
		return boxes;
	}

	public void reset() {
		getBoxesLeft().set(getInitialAmountBlocks());
		for (ImageView r : boxes) {
			r.setVisible(true);
		}
		setMovingSpeed(1.0);
		setMovingDown(true);
		setMovingRight(true);
		getBallX().setValue(250);
		getBallY().setValue(350);
		paddleX.setValue(175);
		gameStopped.set(true);
		getGameLost().set(false);
		getGameWon().set(false);
		setPaddleDragX(0.);
		setPaddleTranslateX(0.);
	}

	public DoubleProperty getBallX() {
		return ballX;
	}

	public DoubleProperty getBallY() {
		return ballY;
	}

	public BooleanProperty getGameLost() {
		return gameLost;
	}

	public BooleanProperty getGameWon() {
		return gameWon;
	}

	public DoubleProperty getBoxesLeft() {
		return boxesLeft;
	}

	public int getInitialAmountBlocks() {
		return INITIAL_AMOUNT_BLOCKS;
	}

	public void incrementSpeed() {
		if (getMovingSpeed() <= 6)
			setMovingSpeed(getMovingSpeed() + getMovingSpeed() * 0.5);
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(final boolean movingDown) {
		this.movingDown = movingDown;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(final boolean movingRight) {
		this.movingRight = movingRight;
	}

	public double getMovingSpeed() {
		return movingSpeed;
	}

	public void setMovingSpeed(final double movingSpeed) {
		this.movingSpeed = movingSpeed;
	}

}
