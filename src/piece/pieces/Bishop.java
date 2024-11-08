package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Bishop extends Piece {

	private final PieceType PIECE_TYPE = PieceType.BISHOP;

	public Bishop(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.BISHOP, tile);
		pieceImage = getPieceImage(PIECE_TYPE, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		Tile[][] chessBoard = getChessBoard();
		int file = this.getFile();
		int rank = this.getRank();
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)) {
			moveableTiles.add(chessBoard[file + i][rank - i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)) {
			moveableTiles.add(chessBoard[file + i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)) {
			moveableTiles.add(chessBoard[file - i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)) {
			moveableTiles.add(chessBoard[file - i][rank - i]);
			i++;
		}
		return moveableTiles;
	}

	@Override
	public ArrayList<Tile> getIndicatedTiles() {
		ArrayList<Tile> moveableTiles = getMoveableTiles();
		ArrayList<Tile> indicatedTiles = new ArrayList<Tile>();
		for (Tile tile : moveableTiles) {
			if (!tile.isPieceOnTile()) {
				indicatedTiles.add(tile);
			}
		}
		return indicatedTiles;
	}
}
