package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import main.GameWindow;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;
import player.PlayerColor;

public class Pawn extends Piece {

	private final PieceType PIECE_TYPE = PieceType.PAWN;
	private PlayerColor playerColor = GameWindow.playerColor;

	public Pawn(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.PAWN, tile);
		pieceImage = getPieceImage(PIECE_TYPE, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		Tile[][] chessBoard = getChessBoard();
		int file = this.getFile();
		int rank = this.getRank();
		if (playerColor == PlayerColor.WHITE) {
			if (this.getPieceColor().getPieceColorName() == PieceColor.WHITE.name()) {
				// Pawn movement when playerColor is white for the white pieces
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
				// Pawn movement when playerColor is white for the black pieces
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
		} else {
			if (this.getPieceColor().getPieceColorName() == PieceColor.WHITE.name()) {
				// Pawn movement when playerColor is black for the white pieces
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
			if (this.getPieceColor().getPieceColorName() == PieceColor.BLACK.name()) {
				// Pawn movement when playerColor is black for the black pieces
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
		}
		return moveableTiles;
	}

	@Override
	public ArrayList<Tile> getIndicatedTiles() {
		return getMoveableTiles();
	}
}
