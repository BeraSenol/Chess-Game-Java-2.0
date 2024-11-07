package piece.pieces;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Rook extends Piece {

	private final PieceType PIECE_TYPE = PieceType.ROOK;

	public Rook(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.ROOK, tile);
		pieceImage = getPieceImage(PIECE_TYPE, pieceColor);
	}
}
