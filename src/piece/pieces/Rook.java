package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Rook extends Piece {
	public Rook(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.ROOK, tile);
		pieceImage = getPieceImage(PieceType.ROOK, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		Tile[][] chessBoard = getChessBoard();
		int file = this.getFile();
		int rank = this.getRank();
		int i = 1;
		while (isWithinBounds(file + i) && !chessBoard[file + i][rank].isPieceOnTile()) {
			// Adds empty Tiles right of THE ROOK
			moveableTiles.add(chessBoard[file + i][rank]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && !chessBoard[file - i][rank].isPieceOnTile()) {
			// Adds empty Tiles left of THE ROOK
			moveableTiles.add(chessBoard[file - i][rank]);
			i++;
		}
		i = 1;
		while (isWithinBounds(rank + i) && !chessBoard[file][rank + i].isPieceOnTile()) {
			// Adds empty Tiles above of THE ROOK
			moveableTiles.add(chessBoard[file][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(rank - i) && !chessBoard[file][rank - i].isPieceOnTile()) {
			// Adds empty Tiles bellow of THE ROOK
			moveableTiles.add(chessBoard[file][rank - i]);
			i++;
		}
		return moveableTiles;
	}
}
