package piece.pieces;

import java.util.ArrayList;

import board.Board;
import board.Tile;
import main.GameWindow;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;
import player.PlayerColor;

public class Pawn extends Piece {
	private PlayerColor playerColor = GameWindow.getPlayerColor();

	public Pawn(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.PAWN, tile);
		pieceImage = getPieceImage(PieceType.PAWN, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> moveableTiles = new ArrayList<Tile>();
		Tile[][] chessBoard = getChessBoard();
		int file = this.getFile();
		int rank = this.getRank();
		final int PAWN_RANK_BLACK = Board.getPawnRankBlack();
		final int PAWN_RANK_WHITE = Board.getPawnRankWhite();
		String pieceColorName = this.getPieceColor().getName();
		if (playerColor == PlayerColor.WHITE) {
			if (pieceColorName == PieceColor.WHITE.name()) {
				// Pawn movement when playerColor is White for the White Pieces
				if (chessBoard[file][rank - 1].isPieceOnTile()) {
					// if Piece is in front Pawn, it can't move
					return new ArrayList<Tile>();
				}
				if (rank == PAWN_RANK_WHITE && !chessBoard[file][rank - 2].isPieceOnTile()) {
					// if two Tiles in front of Pawn is empty, it can move one or two Tiles
					moveableTiles.add(chessBoard[file][rank - 1]);
					moveableTiles.add(chessBoard[file][rank - 2]);
				} else {
					// Otherwise it can only move one Tile
					moveableTiles.add(chessBoard[file][rank - 1]);
				}
			} else {
				// Pawn movement when playerColor is White for the Black Pieces
				if (chessBoard[file][rank + 1].isPieceOnTile()) {
					return new ArrayList<Tile>();
				}
				if (rank == PAWN_RANK_BLACK && !chessBoard[file][rank + 2].isPieceOnTile()) {
					moveableTiles.add(chessBoard[file][rank + 1]);
					moveableTiles.add(chessBoard[file][rank + 2]);
				} else {
					moveableTiles.add(chessBoard[file][rank + 1]);
				}
			}
		} else {
			if (pieceColorName == PieceColor.WHITE.name()) {
				// Pawn movement when playerColor is Black for the White Pieces
				if (chessBoard[file][rank + 1].isPieceOnTile()) {
					return new ArrayList<Tile>();
				}
				if (rank == PAWN_RANK_BLACK && !chessBoard[file][rank + 2].isPieceOnTile()) {
					moveableTiles.add(chessBoard[file][rank + 1]);
					moveableTiles.add(chessBoard[file][rank + 2]);
				} else {
					moveableTiles.add(chessBoard[file][rank + 1]);
				}
			} else {
				// Pawn movement when playerColor is Black for the Black Pieces
				if (chessBoard[file][rank - 1].isPieceOnTile()) {
					return new ArrayList<Tile>();
				}
				if (rank == PAWN_RANK_WHITE && !chessBoard[file][rank - 2].isPieceOnTile()) {
					moveableTiles.add(chessBoard[file][rank - 1]);
					moveableTiles.add(chessBoard[file][rank - 2]);
				} else {
					moveableTiles.add(chessBoard[file][rank - 1]);
				}
			}
		}
		return moveableTiles;
	}
}
