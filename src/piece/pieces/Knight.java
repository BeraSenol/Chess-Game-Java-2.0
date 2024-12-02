package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Knight extends Piece {
	public Knight(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.KNIGHT, tile);
		pieceImage = getPieceImage(PieceType.KNIGHT, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		for (Tile tile : getTilesInSight(getFile(), getRank())) {
			if (!tile.isPieceOnTile()) {
				tiles.add(tile);
			}
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		for (Tile tile : getTilesInSight(getFile(), getRank())) {
			if (tile.isPieceOnTile()) {
				if (!tile.getPiece().isPieceColorTurnColor()) {
					tiles.add(tile);
				}
			}
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getDefendingTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		for (Tile tile : getTilesInSight(getFile(), getRank())) {
			if (tile.isPieceOnTile()) {
				if (tile.getPiece().isPieceColorTurnColor()) {
					tiles.add(tile);
				}
			}
		}
		return tiles;
	}

	private ArrayList<Tile> getTilesInSight(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		for (Tile tile : getTilesInSightLongLeft(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getTilesInSightShortLeft(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getTilesInSightShortRight(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getTilesInSightLongRight(file, rank)) {
			tiles.add(tile);
		}
		return tiles;
	}

	private ArrayList<Tile> getTilesInSightLongLeft(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		if (isWithinBounds(file - 2)) {
			// Two Tiles left
			if (isWithinBounds(rank + 1)) {
				// One Tiles down if empty
				tiles.add(CHESS_BOARD[file - 2][rank + 1]);
			}
			if (isWithinBounds(rank - 1)) {
				// One Tiles up if empty
				tiles.add(CHESS_BOARD[file - 2][rank - 1]);
			}
		}
		return tiles;
	}

	private ArrayList<Tile> getTilesInSightShortLeft(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		if (isWithinBounds(file - 1)) {
			// One Tile left
			if (isWithinBounds(rank + 2)) {
				// Two Tiles down if empty
				tiles.add(CHESS_BOARD[file - 1][rank + 2]);
			}
			if (isWithinBounds(rank - 2)) {
				// Two Tiles up if empty
				tiles.add(CHESS_BOARD[file - 1][rank - 2]);
			}
		}
		return tiles;
	}

	private ArrayList<Tile> getTilesInSightShortRight(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		if (isWithinBounds(file + 1)) {
			// One Tile right
			if (isWithinBounds(rank + 2)) {
				// Two Tiles down if empty
				tiles.add(CHESS_BOARD[file + 1][rank + 2]);
			}
			if (isWithinBounds(rank - 2)) {
				// Two Tiles up if empty
				tiles.add(CHESS_BOARD[file + 1][rank - 2]);
			}
		}
		return tiles;
	}

	private ArrayList<Tile> getTilesInSightLongRight(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		if (isWithinBounds(file + 2)) {
			// Two Tiles right
			if (isWithinBounds(rank + 1)) {
				// One Tiles down if empty
				tiles.add(CHESS_BOARD[file + 2][rank + 1]);
			}
			if (isWithinBounds(rank - 1)) {
				// One Tiles up if empty
				tiles.add(CHESS_BOARD[file + 2][rank - 1]);
			}
		}
		return tiles;
	}
}
