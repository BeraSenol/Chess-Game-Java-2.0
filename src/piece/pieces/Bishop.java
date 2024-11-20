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
		for (Tile tile : getTilesLeftAbove(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getTilesRightAbove(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getTilesLeftBellow(file, rank)) {
			tiles.add(tile);
		}
		for (Tile tile : getTilesRightBellow(file, rank)) {
			tiles.add(tile);
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
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
		return tiles;
	}

	private ArrayList<Tile> getTilesRightAbove(int file, int rank) {
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

	private ArrayList<Tile> getTilesLeftAbove(int file, int rank) {
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

	private ArrayList<Tile> getTilesLeftBellow(int file, int rank) {
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

	private ArrayList<Tile> getTilesRightBellow(int file, int rank) {
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
