package piece.pieces;

import java.util.ArrayList;

import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Queen extends Piece {
	public Queen(PieceColor pieceColor, Tile tile) {
		super(pieceColor, PieceType.QUEEN, tile);
		pieceImage = getPieceImage(PieceType.QUEEN, pieceColor);
	}

	@Override
	public ArrayList<Tile> getMoveableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		int i = 1;
		while (isWithinBounds(file + i) && !CHESS_BOARD[file + i][rank].isPieceOnTile()) {
			// Adds empty Tiles right of the Queen
			tiles.add(CHESS_BOARD[file + i][rank]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && !CHESS_BOARD[file - i][rank].isPieceOnTile()) {
			// Adds empty Tiles left of the Queen
			tiles.add(CHESS_BOARD[file - i][rank]);
			i++;
		}
		i = 1;
		while (isWithinBounds(rank + i) && !CHESS_BOARD[file][rank + i].isPieceOnTile()) {
			// Adds empty Tiles above the Queen
			tiles.add(CHESS_BOARD[file][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(rank - i) && !CHESS_BOARD[file][rank - i].isPieceOnTile()) {
			// Adds empty Tiles bellow the Queen
			tiles.add(CHESS_BOARD[file][rank - i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)
				&& !CHESS_BOARD[file + i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right-above the Queen
			tiles.add(CHESS_BOARD[file + i][rank - i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)
				&& !CHESS_BOARD[file + i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles right-bellow the Queen
			tiles.add(CHESS_BOARD[file + i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)
				&& !CHESS_BOARD[file - i][rank + i].isPieceOnTile()) {
			// Adds empty Tiles left-above the Queen
			tiles.add(CHESS_BOARD[file - i][rank + i]);
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)
				&& !CHESS_BOARD[file - i][rank - i].isPieceOnTile()) {
			// Adds empty Tiles right-bellow the Queen
			tiles.add(CHESS_BOARD[file - i][rank - i]);
			i++;
		}
		return tiles;
	}

	@Override
	public ArrayList<Tile> getCaptureableTiles() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int file = this.getFile();
		int rank = this.getRank();
		// DIAGONAL
		int i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file + i][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right-above the Bishop PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file + i][rank - i]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file + i) && isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file + i][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right-above the Bishop PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file + i][rank + i]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file - i][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile left-above the Bishop PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file - i][rank + i]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i) && isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file - i][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile left-bellow the Bishop PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file - i][rank - i]);
				}
				break;
			}
			i++;
		}
		// HORIZONTAL
		i = 1;
		while (isWithinBounds(file + i)) {
			if (CHESS_BOARD[file + i][rank].isPieceOnTile()) {
				if (!CHESS_BOARD[file + i][rank].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile right of THE ROOK if PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file + i][rank]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(file - i)) {
			if (CHESS_BOARD[file - i][rank].isPieceOnTile()) {
				if (!CHESS_BOARD[file - i][rank].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile left of THE ROOK if PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file - i][rank]);
				}
				break;
			}
			i++;
		}
		// VERTICAL
		i = 1;
		while (isWithinBounds(rank + i)) {
			if (CHESS_BOARD[file][rank + i].isPieceOnTile()) {
				if (!CHESS_BOARD[file][rank + i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile above of THE ROOK if PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file][rank + i]);
				}
				break;
			}
			i++;
		}
		i = 1;
		while (isWithinBounds(rank - i)) {
			if (CHESS_BOARD[file][rank - i].isPieceOnTile()) {
				if (!CHESS_BOARD[file][rank - i].getPiece().isPieceColorTurnColor()) {
					// Adds unobstructed Tile bellow of THE ROOK if PieceColor is not TurnColor
					tiles.add(CHESS_BOARD[file][rank - i]);
				}
				break;
			}
			i++;
		}
		return tiles;
	}
}
