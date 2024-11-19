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
	private boolean hasTwoStepped = false;
	final int PAWN_RANK_BLACK = Board.getPawnRankBlack();
	final int PAWN_RANK_WHITE = Board.getPawnRankWhite();

	public Pawn(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.PAWN, tile);
		pieceImage = getPieceImage(PieceType.PAWN, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		String pieceColorName = this.getPieceColor().getName();
		int file = this.getFile();
		int rank = this.getRank();
		if ((playerColor == PlayerColor.WHITE && pieceColorName == PieceColor.WHITE.name())
				|| (playerColor == PlayerColor.BLACK && pieceColorName == PieceColor.BLACK.name())) {
			for (Tile tile : getMoveableTilesPlayer(file, rank)) {
				tiles.add(tile);
			}
		} else {
			for (Tile tile : getMoveableTilesOpponent(file, rank)) {
				tiles.add(tile);
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

	public ArrayList<Tile> getEnPassantTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		PieceColor pawnColor = this.getPieceColor();
		int file = this.getFile();
		int rank = this.getRank();
		if (canEnPassantLeft()) {
			if (pawnColor.name() == PLAYER_COLOR.name()) {
				tiles.add(CHESS_BOARD[file - 1][rank - 1]);
			} else {
				tiles.add(CHESS_BOARD[file - 1][rank + 1]);
			}
		}
		if (canEnPassantRight()) {
			if (pawnColor.name() == PLAYER_COLOR.name()) {
				tiles.add(CHESS_BOARD[file + 1][rank - 1]);
			} else {
				tiles.add(CHESS_BOARD[file + 1][rank + 1]);
			}
		}
		return tiles;
	}

	// BOOLEAN
	private boolean canEnPassantLeft() {
		PieceColor pawnColor = this.getPieceColor();
		int file = this.getFile();
		int rank = this.getRank();
		if (!isWithinBounds(file - 1)) {
			return false;
		}
		if (!CHESS_BOARD[file - 1][rank].isPieceOnTile()) {
			return false;
		}
		if (CHESS_BOARD[file - 1][rank].getPiece().getPieceType() != PieceType.PAWN
				&& isPieceColorTurnColor()) {
			return false;
		}
		if (pawnColor.name() == PLAYER_COLOR.name()) {
			if (rank != 3) {
				return false;
			}
		} else {
			if (rank != 4) {
				return false;
			}
		}
		Pawn pawn = (Pawn) CHESS_BOARD[file - 1][rank].getPiece();
		if (pawn.hasTwoStepped()) {
			return true;
		}
		return false;
	}

	private boolean canEnPassantRight() {
		PieceColor pawnColor = this.getPieceColor();
		int file = this.getFile();
		int rank = this.getRank();
		if (!isWithinBounds(file + 1)) {
			return false;
		}
		if (!CHESS_BOARD[file + 1][rank].isPieceOnTile()) {
			return false;
		}
		if (CHESS_BOARD[file + 1][rank].getPiece().getPieceType() != PieceType.PAWN
				&& isPieceColorTurnColor()) {
			return false;
		}
		if (pawnColor.name() == PLAYER_COLOR.name()) {
			if (rank != 3) {
				return false;
			}
		} else {
			if (rank != 4) {
				return false;
			}
		}
		Pawn pawn = (Pawn) CHESS_BOARD[file + 1][rank].getPiece();
		if (pawn.hasTwoStepped()) {
			return true;
		}
		return false;
	}

	// GETTERS
	public boolean hasTwoStepped() {
		return hasTwoStepped;
	}

	private ArrayList<Tile> getMoveableTilesPlayer(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
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
		return tiles;
	}

	private ArrayList<Tile> getMoveableTilesOpponent(int file, int rank) {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		if (CHESS_BOARD[file][rank + 1].isPieceOnTile()) {
			// if Piece is in front Pawn, it can't move
			return new ArrayList<Tile>();
		}
		if (rank == PAWN_RANK_BLACK && !CHESS_BOARD[file][rank + 2].isPieceOnTile()) {
			// if two Tiles in front of Pawn is empty, it can move one or two Tiles
			tiles.add(CHESS_BOARD[file][rank + 1]);
			tiles.add(CHESS_BOARD[file][rank + 2]);
		} else {
			// Otherwise it can only move one Tile
			tiles.add(CHESS_BOARD[file][rank + 1]);
		}
		return tiles;
	}

	// SETTERS
	public void setHasTwoStepped(boolean hasTwoStepped) {
		this.hasTwoStepped = hasTwoStepped;
	}
}
