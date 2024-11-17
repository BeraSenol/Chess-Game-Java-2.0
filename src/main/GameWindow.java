package main;

import javax.swing.JFrame;
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
	private final double FPS = 10;
	private final double DRAW_INTERVAL = INTERVAL / FPS;
	private final Board CHESS_BOARD = new Board();
	private final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH);
	private final Mouse PLAYER_MOUSE = new Mouse();

	private static PlayerColor playerColor = PlayerColor.WHITE;
	private static PlayerColor turnColor = PlayerColor.WHITE;
	private Tile selectedTile = null;
	private Piece selectedPiece = null;
	private Graphics2D graphics2d = null;
	private Thread gameThread = null;
	private JFrame jFrame = null;

	// CONSTRUCTOR
	protected GameWindow(JFrame parentFrame) {
		this.jFrame = parentFrame;
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
		if (getSelectedPiece() != null && getSelectedPiece().isPieceColorTurnColor()) {
			if (getSelectedPiece().getMoveableTiles() != null) {
				// Draws the indicatorImage if Piece is selected
				getSelectedPiece().drawIndicators(graphics2d, getSelectedPiece().getMoveableTiles());
			}
			if (getSelectedPiece().getCaptureableTiles() != null) {
				getSelectedPiece().drawCaptureableTiles(graphics2d,
						getSelectedPiece().getCaptureableTiles());
			}
		}
		if (getSelectedPiece() == null) {
			restoreTileColors();
		}
	}

	private void update() {
		if (PLAYER_MOUSE.isMousePressed()) {
			// Selects the Tile the PLAYER_MOUSE is on
			setSelectedTile(PLAYER_MOUSE.getHoveringTile());
			if (getSelectedPiece() == null) {
				if (getSelectedTile().isPieceOnTile()
						&& getSelectedTile().getPiece().isPieceColorTurnColor()) {
					// Selects the Piece if the selectedTile is not empty
					setSelectedPiece(getSelectedTile().getPiece());
				}
			}
			if (getSelectedPiece() != null && getSelectedPiece().getMoveableTiles() != null) {
				if (getSelectedPiece().getMoveableTiles().contains(getSelectedTile())) {
					// Moves the Piece if selectedTile is in getMoveableTiles()
					movePiece(getSelectedTile(), getSelectedPiece());
				}
			}
			if (getSelectedPiece() != null && getSelectedPiece().getCaptureableTiles() != null) {
				if (getSelectedPiece().getCaptureableTiles().contains(getSelectedTile())) {
					// Captures the Piece if selectedTile is in getCaptureableTiles()
					capturePiece(getSelectedTile(), getSelectedPiece());
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

	public static PlayerColor getTurnColor() {
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
		GameWindow.turnColor = turnColor;
	}

	// VOID
	private void movePiece(Tile tile, Piece piece) {
		piece.getTile().removePiece();
		piece.setTile(tile);
		tile.setPiece(piece);
		setSelectedPiece(null);
		endTurn();
	}

	private void capturePiece(Tile tile, Piece piece) {
		CHESS_BOARD.removePieceFromBoard(tile.getPiece());
		tile.removePiece();
		piece.getTile().removePiece();
		piece.setTile(tile);
		tile.setPiece(piece);
		setSelectedPiece(null);
		endTurn();
	}

	private void endTurn() {
		if (getTurnColor() == PlayerColor.WHITE) {
			setTurnColor(PlayerColor.BLACK);
			this.jFrame.setTitle("Chess Game - Black to play!");
		} else {
			setTurnColor(PlayerColor.WHITE);
			this.jFrame.setTitle("Chess Game - White to play!");
		}
	}

	private void restoreTileColors() {
		Tile targetTile = null;
		for (int i = Board.getPieceRankBlack(); i < Board.getPieceRankWhite(); i++) {
			for (int j = Board.getPieceRankBlack(); j < Board.getPieceRankWhite(); j++) {
				targetTile = Board.getChessBoard()[i][j];
				if (targetTile.getTileColor() == TileColor.LIGHT_RED) {
					targetTile.setTileColor(TileColor.LIGHT);
				}
				if (targetTile.getTileColor() == TileColor.DARK_RED) {
					targetTile.setTileColor(TileColor.DARK);
				}
			}
		}
	}
}
