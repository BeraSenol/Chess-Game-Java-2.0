package piece.pieces;

import java.util.ArrayList;

import board.Board;
import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Pawn extends Piece {

	private final PieceType PIECE_TYPE = PieceType.PAWN;
	private Tile[][] chessBoard = Board.chessBoard;

	public Pawn(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.PAWN, tile);
		pieceImage = getPieceImage(PIECE_TYPE, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		if (this.getPieceColor().getPieceColorName() == PieceColor.WHITE.name()) {
			if (chessBoard[file][rank - 1].isPieceOnTile()) {
				return new ArrayList<Tile>();
			}
			if (rank == 6) {
				moveableTiles.add(chessBoard[file][5]);
				if (!chessBoard[file][4].isPieceOnTile()) {
					moveableTiles.add(chessBoard[file][4]);
					return moveableTiles;
				}
			}
			moveableTiles.add(chessBoard[file][rank - 1]);
			return moveableTiles;
		}
		if (this.getPieceColor().getPieceColorName() == PieceColor.BLACK.name()) {
			if (chessBoard[file][rank + 1].isPieceOnTile()) {
				return new ArrayList<Tile>();
			}
			if (rank == 1) {
				moveableTiles.add(chessBoard[file][2]);
				if (!chessBoard[file][3].isPieceOnTile()) {
					moveableTiles.add(chessBoard[file][3]);
					return moveableTiles;
				}
			}
			moveableTiles.add(chessBoard[file][rank + 1]);
			return moveableTiles;
		}
		return moveableTiles;
	}
}
