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
		hoveringTile = playerMouse.getHoveringTile();
		if (playerMouse.isMousePressed()) {
			selectedTile = hoveringTile;
			if (selectedPiece == null) {
				if (selectedTile.isPieceOnTile() && selectedTile.getTilePieceColorName() == playerColor
						.getPlayerColorName()) {
					selectedPiece = selectedTile.getTilePiece();
				}
			} else {
				selectedPiece.setX(playerMouse.getMouseX() - tileSize / 2);
				selectedPiece.setY(playerMouse.getMouseY() - tileSize / 2);
			}

		}
	}
}
