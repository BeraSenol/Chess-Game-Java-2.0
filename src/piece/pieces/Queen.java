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
		// DIAGONAL
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file + i][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right-above the Bishop PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file + i][rank - i]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file + i][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right-above the Bishop PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file + i][rank + i]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file - i][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile left-above the Bishop PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file - i][rank + i]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file - i][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile left-bellow the Bishop PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file - i][rank - i]);
				}
				break;
			}
			i++;
		}
		// HORIZONTAL
		i = 1;
		while (isWithinBounds(file + i)) {
			if (CHESS_BOARD[file + i][rank].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right of THE ROOK if PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file + i][rank]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i)) {
			if (CHESS_BOARD[file - i][rank].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile left of THE ROOK if PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file - i][rank]);
				}
				break;
			}
			i++;
		}
		// VERTICAL
		i = 1;
		while (isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile above of THE ROOK if PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file][rank + i]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile bellow of THE ROOK if PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file][rank - i]);
				}
				break;
			}
			i++;
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
}
