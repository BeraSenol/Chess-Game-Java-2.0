package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Queen extends Piece {
	public Queen(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.QUEEN, tile);
		pieceImage = getPieceImage(PieceType.QUEEN, pieceColor);
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
		for (Tile tile : getMoveableTilesLeft(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getMoveableTilesRight(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getMoveableTilesAbove(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getMoveableTilesBellow(file, rank)) {
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
		if (getCapturableTileLeft(file, rank) != null) {
			tiles.add(getCapturableTileLeft(file, rank));
		}
		if (getCapturableTileRight(file, rank) != null) {
			tiles.add(getCapturableTileRight(file, rank));
		}
		if (getCapturableTileAbove(file, rank) != null) {
			tiles.add(getCapturableTileAbove(file, rank));
		}
		if (getCapturableTileBellow(file, rank) != null) {
			tiles.add(getCapturableTileBellow(file, rank));
		}
		return tiles;
	}

	private ArrayList<Tile> getMoveableTilesLeft(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int i = 1;
		while (isWithinBounds(file - i) && !CHESS_BOARD[file - i][rank].isPieceOnTile()) {
			// Adds empty Tiles right of THE ROOK!!!
			tiles.add(CHESS_BOARD[file - i][rank]);
			i++;
		}
		return tiles;
	}

	private ArrayList<Tile> getMoveableTilesRight(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int i = 1;
		while (isWithinBounds(file + i) && !CHESS_BOARD[file + i][rank].isPieceOnTile()) {
			// Adds empty Tiles right of THE ROOK!!!
			tiles.add(CHESS_BOARD[file + i][rank]);
			i++;
		}
		return tiles;
	}

	private ArrayList<Tile> getMoveableTilesAbove(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int i = 1;
		while (isWithinBounds(rank - i) && !CHESS_BOARD[file][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right of THE ROOK!!!
			tiles.add(CHESS_BOARD[file][rank - i]);
			i++;
		}
		return tiles;
	}

	private ArrayList<Tile> getMoveableTilesBellow(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int i = 1;
		while (isWithinBounds(rank + i) && !CHESS_BOARD[file][rank + i].isPieceOnTile()) {
			// Adds empty Tiles right of THE ROOK!!!
			tiles.add(CHESS_BOARD[file][rank + i]);
			i++;
		}
		return tiles;
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

	private Tile getCapturableTileLeft(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file - i)) {
			if (CHESS_BOARD[file - i][rank].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile left of THE ROOK if PieceColor is not TurnColor
					return CHESS_BOARD[file - i][rank];
				}
				break;
			}
			i++;
		}
		return null;
	}

	private Tile getCapturableTileRight(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file + i)) {
			if (CHESS_BOARD[file + i][rank].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right of THE ROOK if PieceColor is not TurnColor
					return CHESS_BOARD[file + i][rank];
				}
				break;
			}
			i++;
		}
		return null;

	}

	private Tile getCapturableTileAbove(int file, int rank) {
		int i = 1;
		while (isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile above of THE ROOK if PieceColor is not TurnColor
					return CHESS_BOARD[file][rank + i];
				}
				break;
			}
			i++;
		}
		return null;
	}

	private Tile getCapturableTileBellow(int file, int rank) {
		int i = 1;
		while (isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile above of THE ROOK if PieceColor is not TurnColor
					return CHESS_BOARD[file][rank - i];
				}
				break;
			}
			i++;
		}
		return null;
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
}
