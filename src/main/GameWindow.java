package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import board.*;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;
import piece.pieces.King;
import player.PlayerColor;

public class GameWindow extends JPanel implements Runnable {
	private final int INTERVAL = 100000000;
	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 800;
	private final int FPS = 5;
	private final double DRAW_INTERVAL = INTERVAL / FPS;
	private final Board CHESS_BOARD = new Board();
	private final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH);
	private final Mouse PLAYER_MOUSE = new Mouse();

	private static PlayerColor playerColor = PlayerColor.WHITE;
	private static PlayerColor turnColor = PlayerColor.WHITE;
	private ArrayList<Tile> highlightedTiles = new ArrayList<Tile>();
	private Tile selectedTile = null;
	private Piece selectedPiece = null;
	private Graphics2D graphics2d = null;
	private Thread gameThread = null;
	private JFrame jFrame = null;

	// CONSTRUCTOR
	protected GameWindow(JFrame jFrame) {
		this.jFrame = jFrame;
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
			if (getSelectedPiece().getMoveableTiles() != null) {
				// Draws the indicatorImage while Piece is selected
				getSelectedPiece().drawIndicators(graphics2d, getSelectedPiece().getMoveableTiles());
			}
			if (getSelectedPiece().getCaptureableTiles() != null) {
				// Highlights captureableTile while Piece is selected
				setHighlightedTiles(getSelectedPiece().getCaptureableTiles());
				getSelectedPiece().drawCaptureableTiles(graphics2d,
						getSelectedPiece().getCaptureableTiles());
			}
			if (getSelectedPiece().getPieceType() == PieceType.KING) {
				// Draws indicatorImage if King can Castle 2 files left and/or right
				King king = (King) getSelectedPiece();
				ArrayList<Tile> castleTiles = new ArrayList<Tile>();
				if (king.canCastleLeft(getSelectedPiece().getPieceColor())) {
					castleTiles.add(king.getLeftCastleTile());
				}
				if (king.canCastleRight(getSelectedPiece().getPieceColor())) {
					castleTiles.add(king.getRightCastleTile());

				}
				if (castleTiles != null) {
					king.drawIndicators(graphics2d, castleTiles);
				}
			}
		}
		if (getSelectedPiece() == null) {
			// Restores original TileColor after a capture/move
			restoreTileColors();
		}
	}

	private void update() {
		if (PLAYER_MOUSE.isMousePressed()) {
			// Selects the Tile the PLAYER_MOUSE is on
			setSelectedTile(PLAYER_MOUSE.getHoveringTile());
			if (getSelectedPiece() == null) {
				// Selects Piece if selectedTile is not empty and contains Piece of same Color
				if (getSelectedTile().isPieceOnTile()) {
					if (getSelectedTile().getPiece().isPieceColorTurnColor()) {
						setSelectedPiece(getSelectedTile().getPiece());
					}
				}
			}
			if (getSelectedPiece() != null && getSelectedTile().isPieceOnTile()) {
				// Re-selects Piece if selectedTile contains Piece of same Color
				if (getSelectedTile().getPiece().isPieceColorTurnColor()) {
					setSelectedPiece(getSelectedTile().getPiece());
					restoreTileColors();
				}
			}
			if (getSelectedPiece() != null && getSelectedPiece().getMoveableTiles() != null) {
				// Moves the Piece if selectedTile is in getMoveableTiles()
				if (getSelectedPiece().getMoveableTiles().contains(getSelectedTile())) {
					movePiece(getSelectedTile(), getSelectedPiece());
					endTurn();
				}
			}
			if (getSelectedPiece() != null && getSelectedPiece().getCaptureableTiles() != null) {
				// Captures the Piece if selectedTile is in getCaptureableTiles()
				if (getSelectedPiece().getCaptureableTiles().contains(getSelectedTile())) {
					capturePiece(getSelectedTile(), getSelectedPiece());
					endTurn();
				}
			}
			if (getSelectedPiece() != null && getSelectedPiece().getPieceType() == PieceType.KING) {
				// Draws indicatorImage if King can Castle 2 files left and/or right
				King king = (King) getSelectedPiece();
				if (king.getLeftCastleTile() == getSelectedTile()) {
					if (king.canCastleLeft(king.getPieceColor())) {
						castleLeft(king.getPieceColor());
						endTurn();
					}
				}
				if (king.getRightCastleTile() == getSelectedTile()
						&& king.canCastleRight(king.getPieceColor())) {
					castleRight(king.getPieceColor());
					endTurn();
				}
			}
			setSelectedTile(null);
		}
	}

	// BOOLEANS

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

	private ArrayList<Tile> getHighlightedTiles() {
		return highlightedTiles;
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

	private void setHighlightedTiles(ArrayList<Tile> tiles) {
		if (tiles != null) {
			for (Tile tile : tiles) {
				highlightedTiles.add(tile);
			}
		}
	}

	// VOID
	private void movePiece(Tile tile, Piece piece) {
		piece.getTile().removePiece();
		piece.setTile(tile);
		piece.incrementMoveCount();
		tile.setPiece(piece);
		setSelectedPiece(null);
	}

	private void capturePiece(Tile tile, Piece piece) {
		CHESS_BOARD.removePieceFromBoard(tile.getPiece());
		tile.removePiece();
		movePiece(tile, piece);
	}

	private void castleLeft(PieceColor kingColor) {
		movePiece(Board.getChessBoard()[Board.getKing(kingColor).getFile() - 2][Board.getKing(kingColor)
				.getRank()], Board.getKing(kingColor));
		if (kingColor == PieceColor.WHITE) {
			movePiece(Board.getChessBoard()[3][7], Board.getChessBoard()[0][7].getPiece());
		} else {
			movePiece(Board.getChessBoard()[3][0], Board.getChessBoard()[0][0].getPiece());
		}
	}

	private void castleRight(PieceColor kingColor) {
		movePiece(Board.getChessBoard()[Board.getKing(kingColor).getFile() + 2][Board.getKing(kingColor)
				.getRank()], Board.getKing(kingColor));
		if (kingColor == PieceColor.WHITE) {
			movePiece(Board.getChessBoard()[5][7], Board.getChessBoard()[7][7].getPiece());
		} else {
			movePiece(Board.getChessBoard()[5][0], Board.getChessBoard()[7][0].getPiece());
		}
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
		if (getHighlightedTiles() != null) {
			for (Tile tile : getHighlightedTiles()) {
				switch (tile.getTileColor()) {
				case TileColor.LIGHT_RED:
					tile.setTileColor(TileColor.LIGHT);
					break;
				case TileColor.DARK_RED:
					tile.setTileColor(TileColor.DARK);
					break;
				default:
					break;
				}
			}
		}
		setHighlightedTiles(new ArrayList<Tile>());
	}
}
