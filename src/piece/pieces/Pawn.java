package piece.pieces;

import java.util.ArrayList;

import board.Board;
import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Pawn extends Piece {

	private final PieceType PIECE_TYPE = PieceType.PAWN;

	public Pawn(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.PAWN, tile);
		pieceImage = getPieceImage(PIECE_TYPE, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		if (this.getPieceColor().getPieceColorName() == PieceColor.WHITE.name()) {
			if (this.getRank() == 6) {
				moveableTiles.add(Board.chessBoard[this.getFile()][5]);
				moveableTiles.add(Board.chessBoard[this.getFile()][4]);
			} else {
				moveableTiles.add(Board.chessBoard[this.getFile()][this.getRank() - 1]);
			}
		}
		if (this.getPieceColor().getPieceColorName() == PieceColor.BLACK.name()) {
			if (this.getRank() == 1) {
				moveableTiles.add(Board.chessBoard[this.getFile()][2]);
				moveableTiles.add(Board.chessBoard[this.getFile()][3]);
			} else {
				moveableTiles.add(Board.chessBoard[this.getFile()][this.getRank() + 1]);
			}
		}
		return moveableTiles;
	}

}
