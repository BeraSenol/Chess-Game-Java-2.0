package piece.pieces;

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
}
