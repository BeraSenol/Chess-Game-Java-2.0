package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import board.*;
import piece.Piece;
import player.PlayerColor;

public class GameWindow extends JPanel implements Runnable {

	// VARIABLES - CONSTANTS
	private final int INTERVAL = 100000000;
	private final int TILE_SIZE_HALF = Tile.getTileSize() / 2;
	protected final static int WINDOW_WIDTH = 800;
	protected final static int WINDOW_HEIGHT = 800;
	private final int FPS = 120;
	private final double DRAW_INTERVAL = INTERVAL / FPS;

	// VARIABLES - CLASS INSTANCES
	private Board chessBoard = new Board();
	private Mouse playerMouse = new Mouse();
	private Tile selectedTile = null;
	private Piece selectedPiece = null;
	public static PlayerColor playerColor = PlayerColor.WHITE;

	// VARIABLES - NON-PRIMITIVE
	private Dimension windowDimension = new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH);
	private Graphics2D graphics2d = null;
	private Thread gameThread = null;

	// CONSTRUCTOR
	protected GameWindow() {
		setPreferredSize(windowDimension);
		addMouseListener(playerMouse);
		addMouseMotionListener(playerMouse);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics2d = (Graphics2D) g;
		chessBoard.drawChessBoard(graphics2d);
		chessBoard.drawInitialChessPieces(graphics2d);
	}

	@Override
	public void run() {
		// Game loop
		double deltaTime = 0;
		long previousTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			deltaTime += (currentTime - previousTime) / DRAW_INTERVAL;
			previousTime = currentTime;
			if (deltaTime >= 1) {
				update();
				repaint();
				deltaTime--;
			}
		}
	}

	protected void launchGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	private void update() {
		if (playerMouse.isMousePressed()) {
			setSelectedTile(playerMouse.getHoveringTile());
			if (getSelectedPiece() == null) {
				if (isSelectedTilePieceColorAlsoPlayerColor(selectedTile)) {
					setSelectedPiece(selectedTile.getTilePiece());
				}
			}
		}
		if (!playerMouse.isMousePressed()) {
			setSelectedTile(playerMouse.getHoveringTile());
			if (getSelectedPiece() != null) {
				getSelectedPiece().setTile(selectedTile);
				setSelectedPiece(null);
			}
		}
		if (selectedPiece != null) {
			setPieceToFollowMouse(selectedPiece);
		}
	}

	// BOOLEANS
	private boolean isSelectedTilePieceColorAlsoPlayerColor(Tile tile) {
		return tile.isPieceOnTile() && tile.getTilePieceColorName() == playerColor.getPlayerColorName();
	}

	// GETTERS
	private Piece getSelectedPiece() {
		return selectedPiece;
	}

	// SETTERS
	private void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
	}

	private void setSelectedPiece(Piece piece) {
		this.selectedPiece = piece;
	}

	private void setPieceToFollowMouse(Piece piece) {
		piece.setX(playerMouse.getMouseX() - TILE_SIZE_HALF);
		piece.setY(playerMouse.getMouseY() - TILE_SIZE_HALF);
	}
}
