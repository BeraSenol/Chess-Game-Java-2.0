package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Bishop extends Piece  {
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
			// Tiles right-above the bishop
			moveableTiles.add(chessBoard[file + i][rank - i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)
				&& !chessBoard[file + i][rank + i].isPieceOnTile()) {
			// Tiles right-bellow the bishop
			moveableTiles.add(chessBoard[file + i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)
				&& !chessBoard[file - i][rank + i].isPieceOnTile()) {
			// Tiles left-above the bishop
			moveableTiles.add(chessBoard[file - i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)
				&& !chessBoard[file - i][rank - i].isPieceOnTile()) {
			// Tiles left-bellow the bishop
			moveableTiles.add(chessBoard[file - i][rank - i]);
			i++;
		}
		return moveableTiles;
	}
}
