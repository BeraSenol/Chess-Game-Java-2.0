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

	public static PlayerColor playerColor = PlayerColor.WHITE;
	private PlayerColor turnColor = PlayerColor.WHITE;
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
		if (getSelectedPiece() != null && isPieceColorPlayerColor(getSelectedPiece())) {
			// Draws the indicatorImage if Piece is selected
			getSelectedPiece().drawIndicators(graphics2d, getSelectedPiece().getMoveableTiles());
			getSelectedPiece().drawCaptureableTiles(graphics2d, getSelectedPiece().getCaptureableTiles());
		}
	}

	private void update() {
		if (PLAYER_MOUSE.isMousePressed()) {
			// Selects the Tile the PLAYER_MOUSE is on
			setSelectedTile(PLAYER_MOUSE.getHoveringTile());
			if (getSelectedPiece() == null) {
				if (getSelectedTile().isPieceOnTile()
						&& isPieceColorPlayerColor(getSelectedTile().getPiece())) {
					// Selects the Piece if the selectedTile is not empty
					setSelectedPiece(getSelectedTile().getPiece());
				}
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

	// BOOLEANS
	public boolean isPieceColorPlayerColor(Piece piece) {
		if (piece.getPieceColor().name() == getTurnColor().name()) {
			return true;
		} else {
			return false;
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

	private PlayerColor getTurnColor() {
		return turnColor;
	}

	// SETTERS
	private void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
	}

	private void setSelectedPiece(Piece piece) {
		this.selectedPiece = piece;
	}

	private void setTurnColor(PlayerColor turnColor) {
		this.turnColor = turnColor;
	}

	// VOID
	private void movePiece(Tile tile, Piece piece) {
		piece.getTile().removePiece();
		piece.setTile(tile);
		tile.setPiece(piece);
		setSelectedPiece(null);
		endTurn();
	}

	private void endTurn() {
		if (getTurnColor() == PlayerColor.WHITE) {
			setTurnColor(PlayerColor.BLACK);
		} else {
			setTurnColor(PlayerColor.WHITE);
		}
	}
}
