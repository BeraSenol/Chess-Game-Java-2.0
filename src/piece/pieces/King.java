package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class King extends Piece {
	public King(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.KING, tile);
		pieceImage = getPieceImage(PieceType.KING, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> tile = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (isWithinBounds(file + i) && isWithinBounds(rank + j)
						&& !CHESS_BOARD[file + i][rank + j].isPieceOnTile()
						&& (i != 0 || j != 0)) {
					// Adds empty Tiles around the King except King's Tile
					tile.add(CHESS_BOARD[file + i][rank + j]);
				}
			}
		}
		return tile;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		throw new UnsupportedOperationException("Unimplemented method 'getCaptureableTiles'");
	}
}
