package piece.pieces;

import java.util.ArrayList;

import board.Board;
import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;
import piece.PlayerColor;

public class King extends Piece {
	public King(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.KING, tile);
		pieceImage = getPieceImage(PieceType.KING, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (isMoveable(file, rank, i, j)) {
					tiles.add(CHESS_BOARD[file + i][rank + j]);
				}
			}
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		ArrayList<Tile> tile = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (isCaptureable(file, rank, i, j)) {
					tile.add(CHESS_BOARD[file + i][rank + j]);
				}
			}
		}
		return tile;
	}

	// GETTERS
	public Tile getLeftCastleTile() {
		if (canCastleLeft(getPieceColor())) {
			return CHESS_BOARD[this.getFile() - 2][this.getRank()];
		}
		return null;
	}

	public Tile getRightCastleTile() {
		if (canCastleRight(getPieceColor())) {
			return CHESS_BOARD[this.getFile() + 2][this.getRank()];
		}
		return null;
	}

	// BOOLEANS
	private boolean isMoveable(int file, int rank, int i, int j) {
		if (!isWithinBounds(file + i) || !isWithinBounds(rank + j)) {
			return false;
		}
		if (i == 0 && j == 0) {
			return false;
		}
		if (CHESS_BOARD[file + i][rank + j].isPieceOnTile()) {
			return false;
		}
		return true;
	}

	private boolean isCaptureable(int file, int rank, int i, int j) {
		if (!isWithinBounds(file + i) || !isWithinBounds(rank + j)) {
			return false;
		}
		if (i == 0 && j == 0) {
			return false;
		}
		if (!CHESS_BOARD[file + i][rank + j].isPieceOnTile()
				|| CHESS_BOARD[file + i][rank + j].getPiece().isPieceColorTurnColor()) {
			return false;
		}
		return true;
	}

	// BOOLEANS - CASTLING
	public boolean canCastleLeft(PieceColor kingColor) {
		King king = Board.getKing(kingColor);
		if (hasMoved(king)) {
			return false;
		}
		if (PLAYER_COLOR == PlayerColor.WHITE) {
			if (kingColor == PieceColor.WHITE) {
				if (canRookCastleRight(Board.getBoardTiles()[0][7], 3)) {
					return true;
				}
			} else {
				if (canRookCastleRight(Board.getBoardTiles()[0][0], 3)) {
					return true;
				}
			}
		} else {
			if (kingColor == PieceColor.WHITE) {
				if (canRookCastleRight(Board.getBoardTiles()[0][0], 2)) {
					return true;
				}
			} else {
				if (canRookCastleRight(Board.getBoardTiles()[0][7], 2)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean canCastleRight(PieceColor kingColor) {
		King king = Board.getKing(kingColor);
		if (hasMoved(king)) {
			return false;
		}
		if (PLAYER_COLOR == PlayerColor.WHITE) {
			if (kingColor == PieceColor.WHITE) {
				if (canRookCastleLeft(Board.getBoardTiles()[7][7], 2)) {
					return true;
				}
			} else {
				if (canRookCastleLeft(Board.getBoardTiles()[7][0], 2)) {
					return true;
				}
			}
		} else {
			if (kingColor == PieceColor.WHITE) {
				if (canRookCastleLeft(Board.getBoardTiles()[7][0], 3)) {
					return true;
				}
			} else {
				if (canRookCastleLeft(Board.getBoardTiles()[7][7], 3)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canRookCastleLeft(Tile tile, int count) {
		if (!isRookOnTile(tile)) {
			return false;
		}
		if (hasMoved(tile.getPiece())) {
			return false;
		}
		if (!areTilesLeftEmpty(tile, count)) {
			return false;
		}
		return true;
	}

	private boolean canRookCastleRight(Tile tile, int count) {
		if (!isRookOnTile(tile)) {
			return false;
		}
		if (hasMoved(tile.getPiece())) {
			return false;
		}
		if (!areTilesRightEmpty(tile, count)) {
			return false;
		}
		return true;
	}

	private boolean isRookOnTile(Tile tile) {
		if (tile.getPiece().getPieceType() == PieceType.ROOK) {
			return true;
		}
		return false;
	}

	private boolean areTilesLeftEmpty(Tile tile, int count) {
		for (int i = 1; i <= count; i++) {
			if (CHESS_BOARD[tile.getFile() - i][tile.getRank()].isPieceOnTile()) {
				return false;
			}
		}
		return true;
	}

	private boolean areTilesRightEmpty(Tile tile, int count) {
		for (int i = 1; i <= count; i++) {
			if (CHESS_BOARD[tile.getFile() + i][tile.getRank()].isPieceOnTile()) {
				return false;
			}
		}
		return true;
	}

	private boolean hasMoved(Piece piece) {
		if (piece.getMoveCount() != 0) {
			return true;
		}
		return false;
	}
}
