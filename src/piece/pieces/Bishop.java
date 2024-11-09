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
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		Tile[][] chessBoard = getChessBoard();
		int file = this.getFile();
		int rank = this.getRank();
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)
				&& !chessBoard[file + i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right-above the Bishop
			moveableTiles.add(chessBoard[file + i][rank - i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)
				&& !chessBoard[file + i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles right-bellow the Bishop
			moveableTiles.add(chessBoard[file + i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)
				&& !chessBoard[file - i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles left-above the Bishop
			moveableTiles.add(chessBoard[file - i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)
				&& !chessBoard[file - i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles left-bellow the Bishop
			moveableTiles.add(chessBoard[file - i][rank - i]);
			i++;
		}
		return moveableTiles;
	}
}
