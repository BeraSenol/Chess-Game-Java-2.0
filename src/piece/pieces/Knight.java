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
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		Tile[][] chessBoard = getChessBoard();
		int file = this.getFile();
		int rank = this.getRank();
		if (isWithinBounds(file - 2)) {
			// Two Tiles left
			if (isWithinBounds(rank + 1) && !chessBoard[file - 2][rank + 1].isPieceOnTile()) {
				// One Tiles down if empty
				moveableTiles.add(chessBoard[file - 2][rank + 1]);
			}
			if (isWithinBounds(rank - 1) && !chessBoard[file - 2][rank - 1].isPieceOnTile()) {
				// One Tiles up if empty
				moveableTiles.add(chessBoard[file - 2][rank - 1]);
			}
		}
		if (isWithinBounds(file - 1)) {
			// One Tile left
			if (isWithinBounds(rank + 2) && !chessBoard[file - 1][rank + 2].isPieceOnTile()) {
				// Two Tiles down if empty
				moveableTiles.add(chessBoard[file - 1][rank + 2]);
			}
			if (isWithinBounds(rank - 2) && !chessBoard[file - 1][rank - 2].isPieceOnTile()) {
				// Two Tiles up if empty
				moveableTiles.add(chessBoard[file - 1][rank - 2]);
			}
		}

		if (isWithinBounds(file + 1)) {
			// One Tile right
			if (isWithinBounds(rank + 2) && !chessBoard[file + 1][rank + 2].isPieceOnTile()) {
				// Two Tiles down if empty
				moveableTiles.add(chessBoard[file + 1][rank + 2]);
			}
			if (isWithinBounds(rank - 2) && !chessBoard[file + 1][rank - 2].isPieceOnTile()) {
				// Two Tiles up if empty
				moveableTiles.add(chessBoard[file + 1][rank - 2]);
			}
		}
		if (isWithinBounds(file + 2)) {
			// Two Tiles right
			if (isWithinBounds(rank + 1) && !chessBoard[file + 2][rank + 1].isPieceOnTile()) {
				// One Tiles down if empty
				moveableTiles.add(chessBoard[file + 2][rank + 1]);
			}
			if (isWithinBounds(rank - 1) && !chessBoard[file + 2][rank - 1].isPieceOnTile()) {
				// One Tiles up if empty
				moveableTiles.add(chessBoard[file + 2][rank - 1]);
			}
		}
		return moveableTiles;
	}
}
