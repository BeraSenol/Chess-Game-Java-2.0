package piece.pieces;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Pawn extends Piece {

	private final PieceType PIECE_TYPE = PieceType.PAWN;

	public Pawn(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.PAWN, tile);
		bufferedImage = getImage(PIECE_TYPE, pieceColor);
	}
}
