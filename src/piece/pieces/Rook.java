package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Rook extends Piece {
	public Rook(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.ROOK, tile);
		pieceImage = getPieceImage(PieceType.ROOK, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
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
		if (getOccupiedTileLeft(file, rank) != null
				&& !getOccupiedTileLeft(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileLeft(file, rank));
		}
		if (getOccupiedTileRight(file, rank) != null
				&& !getOccupiedTileRight(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileRight(file, rank));
		}
		if (getOccupiedTileAbove(file, rank) != null
				&& !getOccupiedTileAbove(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileAbove(file, rank));
		}
		if (getOccupiedTileBellow(file, rank) != null
				&& !getOccupiedTileBellow(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileBellow(file, rank));
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getDefendingTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		if (getOccupiedTileLeft(file, rank) != null
				&& getOccupiedTileLeft(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileLeft(file, rank));
		}
		if (getOccupiedTileRight(file, rank) != null
				&& getOccupiedTileRight(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileRight(file, rank));
		}
		if (getOccupiedTileAbove(file, rank) != null
				&& getOccupiedTileAbove(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileAbove(file, rank));
		}
		if (getOccupiedTileBellow(file, rank) != null
				&& getOccupiedTileBellow(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileBellow(file, rank));
		}
		return tiles;
	}

	private Tile getOccupiedTileLeft(int file, int rank) {
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

	private Tile getOccupiedTileRight(int file, int rank) {
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

	private Tile getOccupiedTileAbove(int file, int rank) {
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

	private Tile getOccupiedTileBellow(int file, int rank) {
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
}
