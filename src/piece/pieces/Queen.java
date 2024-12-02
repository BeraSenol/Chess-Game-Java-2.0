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
		if (getOccupiedTileRightAbove(file, rank) != null
				&& !getOccupiedTileRightAbove(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileRightAbove(file, rank));
		}
		if (getOccupiedTileLeftAbove(file, rank) != null
				&& !getOccupiedTileLeftAbove(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileLeftAbove(file, rank));
		}
		if (getOccupiedTileLeftBellow(file, rank) != null
				&& !getOccupiedTileLeftBellow(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileLeftBellow(file, rank));
		}
		if (getOccupiedTileRightBellow(file, rank) != null
				&& !getOccupiedTileRightBellow(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileRightBellow(file, rank));
		}
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
		if (getOccupiedTileRightAbove(file, rank) != null
				&& getOccupiedTileRightAbove(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileRightAbove(file, rank));
		}
		if (getOccupiedTileLeftAbove(file, rank) != null
				&& getOccupiedTileLeftAbove(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileLeftAbove(file, rank));
		}
		if (getOccupiedTileLeftBellow(file, rank) != null
				&& getOccupiedTileLeftBellow(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileLeftBellow(file, rank));
		}
		if (getOccupiedTileRightBellow(file, rank) != null
				&& getOccupiedTileRightBellow(file, rank).getPiece().isPieceColorTurnColor()) {
			tiles.add(getOccupiedTileRightBellow(file, rank));
		}
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

	private Tile getOccupiedTileLeft(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file - i)) {
			if (CHESS_BOARD[file - i][rank].isPieceOnTile()) {
				return CHESS_BOARD[file - i][rank];
			}
			i++;
		}
		return null;
	}

	private Tile getOccupiedTileRight(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file + i)) {
			if (CHESS_BOARD[file + i][rank].isPieceOnTile()) {
				return CHESS_BOARD[file + i][rank];
			}
			i++;
		}
		return null;

	}

	private Tile getOccupiedTileAbove(int file, int rank) {
		int i = 1;
		while (isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file][rank + i].isPieceOnTile()) {
				return CHESS_BOARD[file][rank + i];
			}
			i++;
		}
		return null;
	}

	private Tile getOccupiedTileBellow(int file, int rank) {
		int i = 1;
		while (isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file][rank - i].isPieceOnTile()) {
				return CHESS_BOARD[file][rank - i];
			}
			i++;
		}
		return null;
	}

	private Tile getOccupiedTileRightAbove(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file + i][rank - i].isPieceOnTile()) {
				return CHESS_BOARD[file + i][rank - i];
			}
			i++;
		}
		return null;
	}

	private Tile getOccupiedTileLeftAbove(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file - i][rank - i].isPieceOnTile()) {
				return CHESS_BOARD[file - i][rank - i];
			}
			i++;
		}
		return null;
	}

	private Tile getOccupiedTileLeftBellow(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file - i][rank + i].isPieceOnTile()) {
				return CHESS_BOARD[file - i][rank + i];
			}
			i++;
		}
		return null;
	}

	private Tile getOccupiedTileRightBellow(int file, int rank) {
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file + i][rank + i].isPieceOnTile()) {
				return CHESS_BOARD[file + i][rank + i];
			}
			i++;
		}
		return null;
	}
}
