package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import board.*;
import piece.Piece;
import player.PlayerColor;

public class GameWindow extends JPanel implements Runnable {
	private final int INTERVAL = 100000000;
	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 800;
	private final int FPS = 60;
	private final double DRAW_INTERVAL = INTERVAL / FPS;
	private final Board CHESS_BOARD = new Board();
	private final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH);
	private final Mouse PLAYER_MOUSE = new Mouse();

	public static PlayerColor playerColor = PlayerColor.BLACK;
	private Tile selectedTile = null;
	private Piece selectedPiece = null;
	private Graphics2D graphics2d = null;
	private Thread gameThread = null;

	// CONSTRUCTOR
	protected GameWindow() {
		setPreferredSize(WINDOW_DIMENSION);
		addMouseListener(PLAYER_MOUSE);
		addMouseMotionListener(PLAYER_MOUSE);
	}

	// GAME LOOP - RUNNABLE INTERFACE
	@Override
	public void run() {
		double deltaTime = 0;
		long previousTime = System.nanoTime();
		while (gameThread != null) {
			deltaTime += (System.nanoTime() - previousTime) / DRAW_INTERVAL;
			previousTime = System.nanoTime();
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

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics2d = (Graphics2D) g;
		CHESS_BOARD.drawChessBoard(graphics2d);
		CHESS_BOARD.drawInitialChessPieces(graphics2d);
		if (getSelectedPiece() != null) {
			// Draws the indicatorImage if Piece is selected
			getSelectedPiece().drawIndicators(graphics2d, getSelectedPiece().getMoveableTiles());
		}
	}

	private void update() {
		if (PLAYER_MOUSE.isMousePressed()) {
			// Selects the Tile the PLAYER_MOUSE is on
			setSelectedTile(PLAYER_MOUSE.getHoveringTile());
			if (getSelectedTile().isPieceOnTile()) {
				// Selects the Piece if the selectedTile is not empty
				setSelectedPiece(getSelectedTile().getPiece());
			}
			if (getSelectedPiece() != null) {
				if (getSelectedPiece().getMoveableTiles().contains(getSelectedTile())) {
					// Moves the Piece if selectedTile is a moveableTile and selectedPiece is not
					// null
					movePiece(getSelectedTile(), getSelectedPiece());
				}
			}
			setSelectedTile(null);
		}
	}

	// GETTERS
	private Piece getSelectedPiece() {
		return selectedPiece;
	}

	private Tile getSelectedTile() {
		return selectedTile;
	}

	public static PlayerColor getPlayerColor() {
		return playerColor;
	}

	// SETTERS
	private void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
	}

	private void setSelectedPiece(Piece piece) {
		this.selectedPiece = piece;
	}

	// VOID
	private void movePiece(Tile tile, Piece piece) {
		piece.getTile().removePiece();
		piece.setTile(tile);
		tile.setPiece(piece);
		setSelectedPiece(null);
	}
}
