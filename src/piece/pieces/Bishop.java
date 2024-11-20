package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Bishop extends Piece {
	public Bishop(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.BISHOP, tile);
		pieceImage = getPieceImage(PieceType.BISHOP, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		for (Tile tile : getMoveableTilesLeftAbove(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getMoveableTilesRightAbove(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getMoveableTilesLeftBellow(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getMoveableTilesRightBellow(file, rank)) {
			tiles.add(tile);
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		if (getCapturableTileRightAbove(file, rank) != null) {
			tiles.add(getCapturableTileRightAbove(file, rank));
		}
		if (getCapturableTileLeftAbove(file, rank) != null) {
			tiles.add(getCapturableTileLeftAbove(file, rank));
		}
		if (getCapturableTileLeftBellow(file, rank) != null) {
			tiles.add(getCapturableTileLeftBellow(file, rank));
		}
		if (getCapturableTileRightBellow(file, rank) != null) {
			tiles.add(getCapturableTileRightBellow(file, rank));
		}
		return tiles;
	}

	private Tile getCapturableTileRightAbove(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file + i][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right-above the Bishop PieceColor is not TurnColor
					return CHESS_BOARD[file + i][rank - i];
				}
				break;
			}
			i++;
		}
		return null;
	}

	private Tile getCapturableTileLeftAbove(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file - i][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right-above the Bishop PieceColor is not TurnColor
					return CHESS_BOARD[file - i][rank - i];
				}
				break;
			}
			i++;
		}
		return null;
	}

	private Tile getCapturableTileLeftBellow(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file - i][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right-above the Bishop PieceColor is not TurnColor
					return CHESS_BOARD[file - i][rank + i];
				}
				break;
			}
			i++;
		}
		return null;
	}

	private Tile getCapturableTileRightBellow(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file + i][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right-above the Bishop PieceColor is not TurnColor
					return CHESS_BOARD[file + i][rank + i];
				}
				break;
			}
			i++;
		}
		return null;
	}

	private ArrayList<Tile> getMoveableTilesRightAbove(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)
				&& !CHESS_BOARD[file + i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right-above the Bishop
			tiles.add(CHESS_BOARD[file + i][rank - i]);
			i++;
		}
		return tiles;
	}

	private ArrayList<Tile> getMoveableTilesLeftAbove(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)
				&& !CHESS_BOARD[file - i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right-above the Bishop
			tiles.add(CHESS_BOARD[file - i][rank - i]);
			i++;
		}
		return tiles;
	}

	private ArrayList<Tile> getMoveableTilesLeftBellow(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)
				&& !CHESS_BOARD[file - i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles right-above the Bishop
			tiles.add(CHESS_BOARD[file - i][rank + i]);
			i++;
		}
		return tiles;
	}

	private ArrayList<Tile> getMoveableTilesRightBellow(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)
				&& !CHESS_BOARD[file + i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles right-above the Bishop
			tiles.add(CHESS_BOARD[file + i][rank + i]);
			i++;
		}
		return tiles;
	}
}
