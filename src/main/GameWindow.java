package main;

import javax.swing.JPanel;
import java.awt.Color;
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

	// VARIABLES - PRIMITIVE
	protected static int windowWidth = 800;
	protected static int windowHeight = 800;
	private int tileSize = Tile.getTileSize();
	private int fps = 120;
	private double drawInterval = INTERVAL / fps;

	// VARIABLES - CLASS INSTANCES
	private Board chessBoard = new Board();
	private Mouse playerMouse = new Mouse();
	private Tile hoveringTile = null;
	private Tile selectedTile = null;
	private Piece selectedPiece = null;
	public static PlayerColor playerColor = PlayerColor.WHITE;

	// VARIABLES - NON-PRIMITIVE
	private Color windowBackgroundColor = Color.DARK_GRAY;
	private Dimension windowDimension = new Dimension(windowWidth, windowHeight);
	private Graphics2D graphics2d = null;
	private Thread gameThread;

	// CONSTRUCTOR
	protected GameWindow() {
		setPreferredSize(windowDimension);
		setBackground(windowBackgroundColor);
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
			deltaTime += (currentTime - previousTime) / drawInterval;
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
			if (selectedPiece == null) {
				if (isSelectedTilePieceColorAlsoPlayerColor(selectedTile)) {
					setSelectedPiece(selectedTile.getTilePiece());
				}
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
