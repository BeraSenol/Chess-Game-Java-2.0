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
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		final int PAWN_RANK_BLACK = Board.getPawnRankBlack();
		final int PAWN_RANK_WHITE = Board.getPawnRankWhite();
		String pieceColorName = this.getPieceColor().getName();
		if (playerColor == PlayerColor.WHITE) {
			if (pieceColorName == PieceColor.WHITE.name()) {
				// Pawn movement when playerColor is White for the White Pieces
				if (CHESS_BOARD[file][rank - 1].isPieceOnTile()) {
					// if Piece is in front Pawn, it can't move
					return new ArrayList<Tile>();
				}
				if (rank == PAWN_RANK_WHITE && !CHESS_BOARD[file][rank - 2].isPieceOnTile()) {
					// if two Tiles in front of Pawn is empty, it can move one or two Tiles
					tiles.add(CHESS_BOARD[file][rank - 1]);
					tiles.add(CHESS_BOARD[file][rank - 2]);
				} else {
					// Otherwise it can only move one Tile
					tiles.add(CHESS_BOARD[file][rank - 1]);
				}
			} else {
				// Pawn movement when playerColor is White for the Black Pieces
				if (CHESS_BOARD[file][rank + 1].isPieceOnTile()) {
					return new ArrayList<Tile>();
				}
				if (rank == PAWN_RANK_BLACK && !CHESS_BOARD[file][rank + 2].isPieceOnTile()) {
					tiles.add(CHESS_BOARD[file][rank + 1]);
					tiles.add(CHESS_BOARD[file][rank + 2]);
				} else {
					tiles.add(CHESS_BOARD[file][rank + 1]);
				}
			}
		} else {
			if (pieceColorName == PieceColor.WHITE.name()) {
				// Pawn movement when playerColor is Black for the White Pieces
				if (CHESS_BOARD[file][rank + 1].isPieceOnTile()) {
					return new ArrayList<Tile>();
				}
				if (rank == PAWN_RANK_BLACK && !CHESS_BOARD[file][rank + 2].isPieceOnTile()) {
					tiles.add(CHESS_BOARD[file][rank + 1]);
					tiles.add(CHESS_BOARD[file][rank + 2]);
				} else {
					tiles.add(CHESS_BOARD[file][rank + 1]);
				}
			} else {
				// Pawn movement when playerColor is Black for the Black Pieces
				if (CHESS_BOARD[file][rank - 1].isPieceOnTile()) {
					return new ArrayList<Tile>();
				}
				if (rank == PAWN_RANK_WHITE && !CHESS_BOARD[file][rank - 2].isPieceOnTile()) {
					tiles.add(CHESS_BOARD[file][rank - 1]);
					tiles.add(CHESS_BOARD[file][rank - 2]);
				} else {
					tiles.add(CHESS_BOARD[file][rank - 1]);
				}
			}
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		String pieceColorName = this.getPieceColor().getName();
		if (playerColor == PlayerColor.WHITE) {
			if (pieceColorName == PieceColor.WHITE.name()) {
				if (isWithinBounds(file - 1) && isWithinBounds(rank - 1)) {
					if (CHESS_BOARD[file - 1][rank - 1].isPieceOnTile()) {
						if (CHESS_BOARD[file - 1][rank - 1].getPiece()
								.getPieceColor() == PieceColor.BLACK) {
							// Adds Tile left above Pawn if a Black Piece is on it
							tiles.add(CHESS_BOARD[file - 1][rank - 1]);
						}
					}
				}
				if (isWithinBounds(file + 1) && isWithinBounds(rank - 1)) {
					if (CHESS_BOARD[file + 1][rank - 1].isPieceOnTile()) {
						if (CHESS_BOARD[file + 1][rank - 1].getPiece()
								.getPieceColor() == PieceColor.BLACK) {
							// Adds Tile right above Pawn if a Black Piece is on it
							tiles.add(CHESS_BOARD[file + 1][rank - 1]);
						}
					}
				}
			} else {
				if (isWithinBounds(file - 1) && isWithinBounds(rank + 1)) {
					if (CHESS_BOARD[file - 1][rank + 1].isPieceOnTile()) {
						if (CHESS_BOARD[file - 1][rank + 1].getPiece()
								.getPieceColor() == PieceColor.WHITE) {
							// Adds Tile left bellow Pawn if a White Piece is on it
							tiles.add(CHESS_BOARD[file - 1][rank + 1]);
						}
					}
				}
				if (isWithinBounds(file + 1) && isWithinBounds(rank + 1)) {
					if (CHESS_BOARD[file + 1][rank + 1].isPieceOnTile()) {
						if (CHESS_BOARD[file + 1][rank + 1].getPiece()
								.getPieceColor() == PieceColor.WHITE) {
							// Adds Tile left bellow Pawn if a White Piece is on it
							tiles.add(CHESS_BOARD[file + 1][rank + 1]);
						}
					}
				}
			}
		} else {
			if (pieceColorName == PieceColor.WHITE.name()) {
				if (isWithinBounds(file - 1) && isWithinBounds(rank + 1)) {
					if (CHESS_BOARD[file - 1][rank + 1].isPieceOnTile()) {
						if (CHESS_BOARD[file - 1][rank + 1].getPiece()
								.getPieceColor() == PieceColor.BLACK) {
							// Adds Tile left above Pawn if a Black Piece is on it
							tiles.add(CHESS_BOARD[file - 1][rank + 1]);
						}
					}
				}
				if (isWithinBounds(file + 1) && isWithinBounds(rank + 1)) {
					if (CHESS_BOARD[file + 1][rank + 1].isPieceOnTile()) {
						if (CHESS_BOARD[file + 1][rank + 1].getPiece()
								.getPieceColor() == PieceColor.BLACK) {
							// Adds Tile right above Pawn if a Black Piece is on it
							tiles.add(CHESS_BOARD[file + 1][rank + 1]);
						}
					}
				}
			} else {
				if (isWithinBounds(file - 1) && isWithinBounds(rank - 1)) {
					if (CHESS_BOARD[file - 1][rank - 1].isPieceOnTile()) {
						if (CHESS_BOARD[file - 1][rank - 1].getPiece()
								.getPieceColor() == PieceColor.WHITE) {
							// Adds Tile left bellow Pawn if a White Piece is on it
							tiles.add(CHESS_BOARD[file - 1][rank - 1]);
						}
					}
				}
				if (isWithinBounds(file + 1) && isWithinBounds(rank - 1)) {
					if (CHESS_BOARD[file + 1][rank - 1].isPieceOnTile()) {
						if (CHESS_BOARD[file + 1][rank - 1].getPiece()
								.getPieceColor() == PieceColor.WHITE) {
							// Adds Tile left bellow Pawn if a White Piece is on it
							tiles.add(CHESS_BOARD[file + 1][rank - 1]);
						}
					}
				}
			}
		}
		return tiles;
	}
}
