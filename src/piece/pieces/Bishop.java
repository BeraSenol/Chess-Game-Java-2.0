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
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)
				&& !CHESS_BOARD[file + i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right-above the Bishop
			tiles.add(CHESS_BOARD[file + i][rank - i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)
				&& !CHESS_BOARD[file + i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles right-bellow the Bishop
			tiles.add(CHESS_BOARD[file + i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)
				&& !CHESS_BOARD[file - i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles left-above the Bishop
			tiles.add(CHESS_BOARD[file - i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)
				&& !CHESS_BOARD[file - i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles left-bellow the Bishop
			tiles.add(CHESS_BOARD[file - i][rank - i]);
			i++;
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		int i = 1;
		int j = 2;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)
				&& !CHESS_BOARD[file + i][rank - i].isPieceOnTile()) {
			if (isWithinBounds(file + j) && isWithinBounds(rank - j)
					&& CHESS_BOARD[file + j][rank - j].isPieceOnTile()) {
				// Adds right-above Tile if Tile behind last moveable Tile contains a Piece
				tiles.add(CHESS_BOARD[file + j][rank - j]);
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)
				&& !CHESS_BOARD[file + i][rank + i].isPieceOnTile()) {
			if (isWithinBounds(file + j) && isWithinBounds(rank + j)
					&& CHESS_BOARD[file + j][rank + j].isPieceOnTile()) {
				// Adds right-below Tile if Tile behind last moveable Tile contains a Piece
				tiles.add(CHESS_BOARD[file + j][rank + j]);
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)
				&& !CHESS_BOARD[file - i][rank + i].isPieceOnTile()) {
			if (isWithinBounds(file - j) && isWithinBounds(rank + j)
					&& CHESS_BOARD[file - j][rank + j].isPieceOnTile()) {
				// Adds left-above Tile if Tile behind last moveable Tile contains a Piece
				tiles.add(CHESS_BOARD[file - j][rank + j]);
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)
				&& !CHESS_BOARD[file - i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles left-bellow the Bishop
			if (isWithinBounds(file - j) && isWithinBounds(rank - j)
					&& CHESS_BOARD[file - j][rank - j].isPieceOnTile()) {
				// Adds left-above Tile if Tile behind last moveable Tile contains a Piece
				tiles.add(CHESS_BOARD[file - j][rank - j]);
			}
			i++;
		}
		return tiles;
	}
}
