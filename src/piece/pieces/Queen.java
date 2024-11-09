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
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		Tile[][] chessBoard = getChessBoard();
		int file = this.getFile();
		int rank = this.getRank();
		int i = 1;
		while (isWithinBounds(file + i) && !chessBoard[file + i][rank].isPieceOnTile()) {
			// Adds empty Tiles right of the Queen
			moveableTiles.add(chessBoard[file + i][rank]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && !chessBoard[file - i][rank].isPieceOnTile()) {
			// Adds empty Tiles left of the Queen
			moveableTiles.add(chessBoard[file - i][rank]);
			i++;
		}
		i = 1;
		while (isWithinBounds(rank + i) && !chessBoard[file][rank + i].isPieceOnTile()) {
			// Adds empty Tiles above the Queen
			moveableTiles.add(chessBoard[file][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(rank - i) && !chessBoard[file][rank - i].isPieceOnTile()) {
			// Adds empty Tiles bellow the Queen
			moveableTiles.add(chessBoard[file][rank - i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)
				&& !chessBoard[file + i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right-above the Queen
			moveableTiles.add(chessBoard[file + i][rank - i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)
				&& !chessBoard[file + i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles right-bellow the Queen
			moveableTiles.add(chessBoard[file + i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)
				&& !chessBoard[file - i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles left-above the Queen
			moveableTiles.add(chessBoard[file - i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)
				&& !chessBoard[file - i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right-bellow the Queen
			moveableTiles.add(chessBoard[file - i][rank - i]);
			i++;
		}
		return moveableTiles;
	}
}
