package piece.pieces;

import java.util.ArrayList;

import board.Board;
import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;
import player.PlayerColor;

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

	public boolean canCastleLeft(PieceColor kingColor) {
		Piece king = Board.getKing(kingColor);
		if (king.getMoveCount() != 0) {
			// Cannot castle if the King has moved
			return false;
		}
		if (PLAYER_COLOR == PlayerColor.WHITE) {
			if (kingColor == PieceColor.WHITE) {
				if (!doesTileContainRook(Board.getChessBoard()[0][7])) {
					// Cannot castle if Piece in the corner is not A ROOK!!!
					return false;
				}
				if (Board.getChessBoard()[0][7].getPiece().getMoveCount() != 0) {
					// Cannot castle if THE ROOK!!! has moved
					return false;
				}
				if (Board.getChessBoard()[1][7].isPieceOnTile()
						|| Board.getChessBoard()[2][7].isPieceOnTile()
						|| Board.getChessBoard()[3][7].isPieceOnTile()) {
					// Cannot castle if there are Pieces between the King and THE ROOK!!!
					return false;
				}
				return true;
			} else {
				if (!doesTileContainRook(Board.getChessBoard()[0][0])) {
					return false;
				}
				if (Board.getChessBoard()[0][0].getPiece().getMoveCount() != 0) {
					return false;
				}
				if (Board.getChessBoard()[1][0].isPieceOnTile()
						|| Board.getChessBoard()[2][0].isPieceOnTile()
						|| Board.getChessBoard()[3][0].isPieceOnTile()) {
					return false;
				}
				return true;
			}
		} else {
			if (kingColor == PieceColor.WHITE) {
				if (!doesTileContainRook(Board.getChessBoard()[0][0])) {
					return false;
				}
				if (Board.getChessBoard()[0][0].getPiece().getMoveCount() != 0) {
					return false;
				}
				if (Board.getChessBoard()[1][0].isPieceOnTile()
						|| Board.getChessBoard()[2][0].isPieceOnTile()) {
					return false;
				}
				return true;
			} else {
				if (!doesTileContainRook(Board.getChessBoard()[0][7])) {
					return false;
				}
				if (Board.getChessBoard()[0][7].getPiece().getMoveCount() != 0) {
					return false;
				}
				if (Board.getChessBoard()[1][7].isPieceOnTile()
						|| Board.getChessBoard()[2][7].isPieceOnTile()) {
					return false;
				}
				return true;
			}
		}
	}

	public boolean canCastleRight(PieceColor kingColor) {
		Piece king = Board.getKing(kingColor);
		if (king.getMoveCount() != 0) {
			// Cannot castle if the King has moved
			return false;
		}
		if (PLAYER_COLOR == PlayerColor.WHITE) {
			if (kingColor == PieceColor.WHITE) {
				if (!doesTileContainRook(Board.getChessBoard()[7][7])) {
					// Cannot castle if Piece in the corner is not A ROOK!!!
					return false;
				}
				if (Board.getChessBoard()[7][7].getPiece().getMoveCount() != 0) {
					// Cannot castle THE ROOK!!! has moved
					return false;
				}
				if (Board.getChessBoard()[5][7].isPieceOnTile()
						|| Board.getChessBoard()[6][7].isPieceOnTile()) {
					// Cannot castle if there are Pieces between the King and THE ROOK!!!
					return false;
				}
				return true;
			} else {
				if (!doesTileContainRook(Board.getChessBoard()[7][0])) {
					return false;
				}
				if (Board.getChessBoard()[7][0].getPiece().getMoveCount() != 0) {
					return false;
				}
				if (Board.getChessBoard()[5][0].isPieceOnTile()
						|| Board.getChessBoard()[6][0].isPieceOnTile()) {
					return false;
				}
				return true;
			}
		} else {
			if (kingColor == PieceColor.WHITE) {
				if (!doesTileContainRook(Board.getChessBoard()[7][0])) {
					return false;
				}
				if (Board.getChessBoard()[7][0].getPiece().getMoveCount() != 0) {
					return false;
				}
				if (Board.getChessBoard()[4][0].isPieceOnTile()
						|| Board.getChessBoard()[5][0].isPieceOnTile()
						|| Board.getChessBoard()[6][0].isPieceOnTile()) {
					return false;
				}
				return true;
			} else {
				if (!doesTileContainRook(Board.getChessBoard()[7][7])) {
					return false;
				}
				if (Board.getChessBoard()[7][7].getPiece().getMoveCount() != 0) {
					return false;
				}
				if (Board.getChessBoard()[4][7].isPieceOnTile()
						|| Board.getChessBoard()[5][7].isPieceOnTile()
						|| Board.getChessBoard()[6][7].isPieceOnTile()) {
					return false;
				}
				return true;
			}
		}
	}

	private boolean isMoveable(int file, int rank, int i, int j) {
		if (!isWithinBounds(file + i) || !isWithinBounds(rank + j)) {
			return false;
		}
		if (CHESS_BOARD[file + i][rank + j].isPieceOnTile()) {
			return false;
		}
		if (!(i != 0 || j != 0)) {
			return false;
		}
		return true;
	}

	private boolean isCaptureable(int file, int rank, int i, int j) {
		if (!isWithinBounds(file + i) || !isWithinBounds(rank + j)) {
			return false;
		}
		if (!(i != 0 || j != 0)) {
			return false;
		}
		if (!CHESS_BOARD[file + i][rank + j].isPieceOnTile()
				|| CHESS_BOARD[file + i][rank + j].getPiece().isPieceColorTurnColor()) {
			return false;
		}
		return true;
	}

	private boolean doesTileContainRook(Tile tile) {
		if (tile.getPiece().getPieceType() == PieceType.ROOK) {
			return true;
		}
		return false;
	}
}
