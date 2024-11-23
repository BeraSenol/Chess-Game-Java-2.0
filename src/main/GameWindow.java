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
import piece.PlayerColor;
import piece.pieces.King;
import piece.pieces.Pawn;
import piece.promotion.PromotionPanel;

public class GameWindow extends JPanel implements Runnable {
	private final static Mouse PLAYER_MOUSE = new Mouse();
	private final int INTERVAL = 100000000;
	private final int FPS = 5;
	private final int WINDOW_SIZE = Tile.getTileSize() * 8;
	private final double DRAW_INTERVAL = INTERVAL / FPS;
	private final Board CHESS_BOARD = new Board();
	private final Tile[][] BOARD_TILES = Board.getBoardTiles();
	private final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_SIZE, WINDOW_SIZE);

	private static PlayerColor playerColor = PlayerColor.WHITE;
	private static PlayerColor turnColor = PlayerColor.WHITE;
	private ArrayList<Tile> highlightedTiles = new ArrayList<Tile>();
	private Tile selectedTile = null;
	private Piece selectedPiece = null;
	private Pawn enPassantPawn = null;
	private Graphics2D graphics2d = null;
	private Thread gameThread = null;
	private JFrame gameWindow = null;

	// CONSTRUCTOR
	protected GameWindow(JFrame jFrame) {
		this.gameWindow = jFrame;
		setPreferredSize(WINDOW_DIMENSION);
		addMouseListener(PLAYER_MOUSE);
		addMouseMotionListener(PLAYER_MOUSE);
	}

	// RUNNABLE INTERFACE
	@Override
	public void run() {
		// GAME LOOP - Controls how many refreshes every nanosecond
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
		// GAME LOOP - Controls what gets drawn
		super.paintComponent(g);
		graphics2d = (Graphics2D) g;
		CHESS_BOARD.drawChessBoard(graphics2d);
		if (getSelectedPiece() != null) {
			// Draws the indicatorImage while Piece is selected
			if (getSelectedPiece().getMoveableTiles() != null) {
				getSelectedPiece().drawIndicators(graphics2d, getSelectedPiece().getMoveableTiles());
			}
			// Highlights captureableTile while Piece is selected
			if (getSelectedPiece().getCaptureableTiles() != null) {
				setHighlightedTiles(getSelectedPiece().getCaptureableTiles());
				getSelectedPiece().highlightCaptureableTiles(graphics2d,
						getSelectedPiece().getCaptureableTiles());
			}
			// Draws indicatorImage if King can castle
			if (getSelectedPiece().getPieceType() == PieceType.KING) {
				drawCastleIndicators(getSelectedPiece());
			}
			// Highlight enPassantTiles if Pawn can En Passant
			if (getSelectedPiece().getPieceType() == PieceType.PAWN) {
				highlightEnPassantTiles(getSelectedPiece());
			}
		}
		if (getSelectedPiece() == null) {
			// Restores original TileColor after a capture/move
			restoreTileColors();
		}
	}

	private void update() {
		// GAME LOOP - Manipulates the PLAYER_MOUSE / Board / Pieces
		if (PLAYER_MOUSE.isMousePressed()) {
			// Selects the Tile the PLAYER_MOUSE is on
			setSelectedTile(PLAYER_MOUSE.getHoveringTileChessBoard());
			// Selects Piece if selectedTile is not empty and contains Piece of same Color
			if (getSelectedPiece() == null) {
				if (getSelectedTile().isPieceOnTile()) {
					if (getSelectedTile().getPiece().isPieceColorTurnColor()) {
						setSelectedPiece(getSelectedTile().getPiece());
					}
				}
			}
			// Re-selects Piece if selectedTile contains Piece of same Color
			if (getSelectedPiece() != null && getSelectedTile().isPieceOnTile()) {
				if (getSelectedTile().getPiece().isPieceColorTurnColor()) {
					setSelectedPiece(getSelectedTile().getPiece());
					restoreTileColors();
				}
			}
			// Moves the Piece if selectedTile is in getMoveableTiles()
			if (getSelectedPiece() != null && getSelectedPiece().getMoveableTiles() != null) {
				if (getSelectedPiece().getMoveableTiles().contains(getSelectedTile())) {
					detectPawnTwoStep(getSelectedPiece(), getSelectedTile());
					movePiece(getSelectedTile(), getSelectedPiece());
					endTurn();
				}
			}
			// Captures the Piece if selectedTile is in getCaptureableTiles()
			if (getSelectedPiece() != null && getSelectedPiece().getCaptureableTiles() != null) {
				if (getSelectedPiece().getCaptureableTiles().contains(getSelectedTile())) {
					capturePiece(getSelectedTile(), getSelectedPiece());
					endTurn();
				}
			}
			// Checks if Castling is possible
			if (getSelectedPiece() != null && getSelectedPiece().getPieceType() == PieceType.KING) {
				tryCastling(getSelectedPiece(), getSelectedTile());
			}
			// Checks if EnPassant is possible
			if (getSelectedPiece() != null && getSelectedPiece().getPieceType() == PieceType.PAWN) {
				tryEnPassant(getSelectedPiece(), getSelectedTile());
			}
			setSelectedTile(null);
		}
	}

	// GETTERS
	public static PlayerColor getPlayerColor() {
		return playerColor;
	}

	public static PlayerColor getTurnColor() {
		return turnColor;
	}

	public static Mouse getPlayerMouse() {
		return PLAYER_MOUSE;
	}

	private Piece getSelectedPiece() {
		return selectedPiece;
	}

	private Pawn getEnPassantPawn() {
		return enPassantPawn;
	}

	private Tile getSelectedTile() {
		return selectedTile;
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

	private void setEnPassantPawn(Pawn pawn) {
		this.enPassantPawn = pawn;
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
		// Detects if a Pawn can to Promote before a turn ends.
		if (piece.getPieceType() == PieceType.PAWN) {
			Pawn pawn = (Pawn) piece;
			if (pawn.canPromote()) {
				promotePawn(pawn);
			}
		}
	}

	private void capturePiece(Tile tile, Piece piece) {
		Board.removePieceFromBoard(tile.getPiece());
		movePiece(tile, piece);
	}

	private void endTurn() {
		if (getTurnColor() == PlayerColor.WHITE) {
			setTurnColor(PlayerColor.BLACK);
			this.gameWindow.setTitle("Chess Game - Black to play!");
		} else {
			setTurnColor(PlayerColor.WHITE);
			this.gameWindow.setTitle("Chess Game - White to play!");
		}
		// Disables En Passant when a turn is ended
		if (getEnPassantPawn() != null) {
			if (getEnPassantPawn().isPieceColorTurnColor()) {
				getEnPassantPawn().setHasTwoStepped(false);
				setEnPassantPawn(null);
			}
		}
	}

	private void restoreTileColors() {
		if (getHighlightedTiles() != null) {
			for (Tile tile : getHighlightedTiles()) {
				tile.setTileColor(tile.getInitialTileColor());
			}
		}
		setHighlightedTiles(new ArrayList<Tile>());
	}

	private void promotePawn(Pawn promotionPawn) {
		JFrame promotionWindow = new JFrame("Choose Promotion Piece");
		PromotionPanel promotionPanel = new PromotionPanel(promotionWindow, promotionPawn);
		promotionWindow.add(promotionPanel);
		promotionWindow.pack();
	}

	// VOID - EN PASSANT
	private void tryEnPassant(Piece piece, Tile tile) {
		Pawn pawn = (Pawn) piece;
		if (pawn.getEnPassantTiles() != null) {
			if (pawn.getEnPassantTiles().contains(tile)) {
				Board.removePieceFromBoard(BOARD_TILES[tile.getFile()][piece.getRank()].getPiece());
				movePiece(tile, piece);
				endTurn();
			}
		}
	}

	private void detectPawnTwoStep(Piece piece, Tile tile) {
		if (piece.getPieceType() == PieceType.PAWN) {
			if (Math.abs(piece.getRank() - tile.getRank()) == 2) {
				// Sets hasTwoStepped to true if selectedPiece is a Pawn
				Pawn pawn = (Pawn) getSelectedPiece();
				pawn.setHasTwoStepped(true);
				setEnPassantPawn(pawn);
			}
		}
	}

	private void highlightEnPassantTiles(Piece piece) {
		Pawn pawn = (Pawn) piece;
		if (pawn.getEnPassantTiles() != null) {
			// Highlights enPassantTiles if Pawn can EnPassant
			setHighlightedTiles(pawn.getEnPassantTiles());
			pawn.highlightCaptureableTiles(graphics2d, pawn.getEnPassantTiles());
		}
	}

	// VOID - CASTLING
	private void tryCastling(Piece piece, Tile tile) {
		King king = (King) piece;
		if (king.getLeftCastleTile() == tile && king.canCastleLeft(king.getPieceColor())) {
			castleLeft(king.getPieceColor());
			endTurn();
		}
		if (king.getRightCastleTile() == tile && king.canCastleRight(king.getPieceColor())) {
			castleRight(king.getPieceColor());
			endTurn();
		}
	}

	private void castleLeft(PieceColor kingColor) {
		movePiece(Board.getBoardTiles()[Board.getKing(kingColor).getFile() - 2][Board.getKing(kingColor)
				.getRank()], Board.getKing(kingColor));
		if (playerColor == PlayerColor.WHITE) {
			if (kingColor == PieceColor.WHITE) {
				movePiece(Board.getBoardTiles()[3][7], Board.getBoardTiles()[0][7].getPiece());
			} else {
				movePiece(Board.getBoardTiles()[3][0], Board.getBoardTiles()[0][0].getPiece());
			}
		} else {
			if (kingColor == PieceColor.WHITE) {
				movePiece(Board.getBoardTiles()[2][0], Board.getBoardTiles()[0][0].getPiece());
			} else {
				movePiece(Board.getBoardTiles()[2][7], Board.getBoardTiles()[0][7].getPiece());
			}
		}

	}

	private void castleRight(PieceColor kingColor) {
		movePiece(Board.getBoardTiles()[Board.getKing(kingColor).getFile() + 2][Board.getKing(kingColor)
				.getRank()], Board.getKing(kingColor));
		if (playerColor == PlayerColor.WHITE) {
			if (kingColor == PieceColor.WHITE) {
				movePiece(Board.getBoardTiles()[5][7], Board.getBoardTiles()[7][7].getPiece());
			} else {
				movePiece(Board.getBoardTiles()[5][0], Board.getBoardTiles()[7][0].getPiece());
			}
		} else {
			if (kingColor == PieceColor.WHITE) {
				movePiece(Board.getBoardTiles()[4][0], Board.getBoardTiles()[7][0].getPiece());
			} else {
				movePiece(Board.getBoardTiles()[4][7], Board.getBoardTiles()[7][7].getPiece());
			}
		}
	}

	private void drawCastleIndicators(Piece piece) {
		King king = (King) piece;
		ArrayList<Tile> castleTiles = new ArrayList<Tile>();
		if (king.canCastleLeft(piece.getPieceColor())) {
			castleTiles.add(king.getLeftCastleTile());
		}
		if (king.canCastleRight(piece.getPieceColor())) {
			castleTiles.add(king.getRightCastleTile());
		}
		if (castleTiles != null) {
			king.drawIndicators(graphics2d, castleTiles);
		}
	}

}
