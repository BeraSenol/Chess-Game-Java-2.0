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
					// Adds empty Tiles around the King
					tile.add(CHESS_BOARD[file + i][rank + j]);
				}
			}
		}
		return tile;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		ArrayList<Tile> tile = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (isWithinBounds(file + i) && isWithinBounds(rank + j) && (i != 0 || j != 0)) {
					if (CHESS_BOARD[file + i][rank + j].isPieceOnTile()
							&& !CHESS_BOARD[file + i][rank + j].getPiece()
									.isPieceColorTurnColor()) {
						// Adds Tiles around the King PieceColor is not TurnColor
						tile.add(CHESS_BOARD[file + i][rank + j]);
					}
				}
			}
		}
		return tile;
	}
}
