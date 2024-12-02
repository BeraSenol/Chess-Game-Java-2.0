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
		return tiles;
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
