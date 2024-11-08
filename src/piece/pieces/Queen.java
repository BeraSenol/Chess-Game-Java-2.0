package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Queen extends Piece {

	private final PieceType PIECE_TYPE = PieceType.QUEEN;

	public Queen(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.QUEEN, tile);
		pieceImage = getPieceImage(PIECE_TYPE, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		Tile[][] chessBoard = getChessBoard();
		int file = this.getFile();
		int rank = this.getRank();
		int i = 1;
		while (isWithinBounds(file + i) && !chessBoard[file + i][rank].isPieceOnTile()) {
			// Tiles right from the queen
			moveableTiles.add(chessBoard[file + i][rank]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && !chessBoard[file - i][rank].isPieceOnTile()) {
			// Tiles left from the queen
			moveableTiles.add(chessBoard[file - i][rank]);
			i++;
		}
		i = 1;
		while (isWithinBounds(rank + i) && !chessBoard[file][rank + i].isPieceOnTile()) {
			// Tiles above the queen
			moveableTiles.add(chessBoard[file][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(rank - i) && !chessBoard[file][rank - i].isPieceOnTile()) {
			// Tiles bellow the queen
			moveableTiles.add(chessBoard[file][rank - i]);
			i++;
		}
		i = 1;
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

	@Override
	public ArrayList<Tile> getIndicatedTiles() {
		return getMoveableTiles();
	}
}
