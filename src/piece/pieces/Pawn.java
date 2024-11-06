package piece.pieces;

import java.util.ArrayList;

import board.Board;
import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;
import player.PlayerColor;

public class Pawn extends Piece {

	private final PieceType PIECE_TYPE = PieceType.PAWN;
	private ArrayList<Tile> moveableTiles = new ArrayList<Tile>();

	public Pawn(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.PAWN, tile);
		pieceImage = getImage(PIECE_TYPE, pieceColor);
	}

	public ArrayList<Tile> getMoveableTiles() {
		if (this.getPieceColor().getPieceColorName() == PieceColor.WHITE.name()) {
			if (this.getRank() == 6) {
				moveableTiles.add(Board.chessBoard[this.getFile() - 1][6]);
				moveableTiles.add(Board.chessBoard[this.getFile() - 2][6]);
			} else {
				moveableTiles.add(Board.chessBoard[this.getFile() - 1][6]);
			}
		}
		if (this.getPieceColor().getPieceColorName() == PieceColor.BLACK.name()) {
			if (this.getRank() == 1) {
				moveableTiles.add(Board.chessBoard[this.getFile() + 1][1]);
				moveableTiles.add(Board.chessBoard[this.getFile() + 2][1]);
			} else {
				moveableTiles.add(Board.chessBoard[this.getFile() + 1][1]);
			}
		}
		return moveableTiles;
	}
}
