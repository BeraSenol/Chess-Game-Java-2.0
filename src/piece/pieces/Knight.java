package piece.pieces;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Knight extends Piece {

	private final PieceType PIECE_TYPE = PieceType.KNIGHT;

	public Knight(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.KNIGHT, tile);
		bufferedImage = getImage(PIECE_TYPE, pieceColor);
	}

}
