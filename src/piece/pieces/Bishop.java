package piece.pieces;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Bishop extends Piece {

	private final PieceType PIECE_TYPE = PieceType.BISHOP;

	public Bishop(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.BISHOP, tile);
		pieceImage = getImage(PIECE_TYPE, pieceColor);
	}
}
