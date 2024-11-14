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
		int file = this.getFile();
		int rank = this.getRank();
		if (isWithinBounds(file - 2)) {
			// Two Tiles left
			if (isWithinBounds(rank + 1) && !CHESS_BOARD[file - 2][rank + 1].isPieceOnTile()) {
				// One Tiles down if empty
				tiles.add(CHESS_BOARD[file - 2][rank + 1]);
			}
			if (isWithinBounds(rank - 1) && !CHESS_BOARD[file - 2][rank - 1].isPieceOnTile()) {
				// One Tiles up if empty
				tiles.add(CHESS_BOARD[file - 2][rank - 1]);
			}
		}
		if (isWithinBounds(file - 1)) {
			// One Tile left
			if (isWithinBounds(rank + 2) && !CHESS_BOARD[file - 1][rank + 2].isPieceOnTile()) {
				// Two Tiles down if empty
				tiles.add(CHESS_BOARD[file - 1][rank + 2]);
			}
			if (isWithinBounds(rank - 2) && !CHESS_BOARD[file - 1][rank - 2].isPieceOnTile()) {
				// Two Tiles up if empty
				tiles.add(CHESS_BOARD[file - 1][rank - 2]);
			}
		}

		if (isWithinBounds(file + 1)) {
			// One Tile right
			if (isWithinBounds(rank + 2) && !CHESS_BOARD[file + 1][rank + 2].isPieceOnTile()) {
				// Two Tiles down if empty
				tiles.add(CHESS_BOARD[file + 1][rank + 2]);
			}
			if (isWithinBounds(rank - 2) && !CHESS_BOARD[file + 1][rank - 2].isPieceOnTile()) {
				// Two Tiles up if empty
				tiles.add(CHESS_BOARD[file + 1][rank - 2]);
			}
		}
		if (isWithinBounds(file + 2)) {
			// Two Tiles right
			if (isWithinBounds(rank + 1) && !CHESS_BOARD[file + 2][rank + 1].isPieceOnTile()) {
				// One Tiles down if empty
				tiles.add(CHESS_BOARD[file + 2][rank + 1]);
			}
			if (isWithinBounds(rank - 1) && !CHESS_BOARD[file + 2][rank - 1].isPieceOnTile()) {
				// One Tiles up if empty
				tiles.add(CHESS_BOARD[file + 2][rank - 1]);
			}
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		throw new UnsupportedOperationException("Unimplemented method 'getCaptureableTiles'");
	}
}
