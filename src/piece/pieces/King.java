package piece.pieces;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class King extends Piece {

	private final PieceType PIECE_TYPE = PieceType.KING;

	public King(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.KING, tile);
		bufferedImage = getImage(PIECE_TYPE, pieceColor);
		
	}

}
