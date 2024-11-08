package board;

import java.awt.Graphics2D;

import main.GameWindow;
import piece.Piece;
import piece.PieceColor;
import piece.pieces.*;
import player.*;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class Board {
	private final int PIECE_RANK_BLACK = 0;
	private final int PAWN_RANK_BLACK = 1;
	private final int PAWN_RANK_WHITE = 6;
	private final int PIECE_RANK_WHITE = 7;
	private final int BOARD_SIZE = 8;
	private final int LABEL_MARGIN_X = 5;
	private final int LABEL_MARGIN_Y = 13;
	private final int TILE_SIZE = Tile.getTileSize();
	private final String[] RANK_LABELS = { "8", "7", "6", "5", "4", "3", "2", "1" };
	private final String[] FILE_LABELS = { "a", "b", "c", "d", "e", "f", "g", "h" };
	private final ArrayList<Piece> INITIAL_PIECES = new ArrayList<>();
	private final Color WHITE_TILE_COLOR = new Color(210, 165, 125);
	private final Color BLACK_TILE_COLOR = new Color(175, 115, 70);
	private final PlayerColor PLAYER_COLOR = GameWindow.getPlayerColor();

	private ArrayList<Piece> inGamePieces = new ArrayList<>();
	public static Tile[][] chessBoard = null;

	// CONSTRUCTOR
	public Board() {
		chessBoard = new Tile[BOARD_SIZE][BOARD_SIZE];
		// Initializes chessBoard object with corresponding tiles
		for (int i = PIECE_RANK_BLACK; i <= PIECE_RANK_WHITE; i++) {
			for (int j = PIECE_RANK_BLACK; j <= PIECE_RANK_WHITE; j++) {
				chessBoard[i][j] = (i + j) % 2 == 0
						? new Tile(i, j, WHITE_TILE_COLOR, FILE_LABELS[i], RANK_LABELS[j], null)
						: new Tile(i, j, BLACK_TILE_COLOR, FILE_LABELS[i], RANK_LABELS[j],
								null);
			}
		}
		if (PLAYER_COLOR == PlayerColor.WHITE) {
			for (int i = PIECE_RANK_BLACK; i <= PIECE_RANK_WHITE; i++) {
				// Add pieces to the INITIAL_PIECES ArrayList when playerColor is white
				INITIAL_PIECES.add(new Pawn(PieceColor.WHITE, chessBoard[i][PAWN_RANK_WHITE]));
				INITIAL_PIECES.add(new Pawn(PieceColor.BLACK, chessBoard[i][PAWN_RANK_BLACK]));
				switch (i) {
				case 0, 7 -> {
					INITIAL_PIECES.add(new Rook(PieceColor.WHITE, chessBoard[i][PIECE_RANK_WHITE]));
					INITIAL_PIECES.add(new Rook(PieceColor.BLACK, chessBoard[i][PIECE_RANK_BLACK]));
				}
				case 1, 6 -> {
					INITIAL_PIECES.add(
							new Knight(PieceColor.WHITE, chessBoard[i][PIECE_RANK_WHITE]));
					INITIAL_PIECES.add(
							new Knight(PieceColor.BLACK, chessBoard[i][PIECE_RANK_BLACK]));
				}
				case 2, 5 -> {
					INITIAL_PIECES.add(
							new Bishop(PieceColor.WHITE, chessBoard[i][PIECE_RANK_WHITE]));
					INITIAL_PIECES.add(
							new Bishop(PieceColor.BLACK, chessBoard[i][PIECE_RANK_BLACK]));
				}
				case 3 -> {
					INITIAL_PIECES.add(
							new Queen(PieceColor.WHITE, chessBoard[i][PIECE_RANK_WHITE]));
					INITIAL_PIECES.add(
							new Queen(PieceColor.BLACK, chessBoard[i][PIECE_RANK_BLACK]));
				}
				case 4 -> {
					INITIAL_PIECES.add(new King(PieceColor.WHITE, chessBoard[i][PIECE_RANK_WHITE]));
					INITIAL_PIECES.add(new King(PieceColor.BLACK, chessBoard[i][PIECE_RANK_BLACK]));
				}
				}
			}
		} else {
			for (int i = PIECE_RANK_BLACK; i <= PIECE_RANK_WHITE; i++) {
				// Add pieces to the INITIAL_PIECES ArrayList when playerColor is black
				INITIAL_PIECES.add(new Pawn(PieceColor.WHITE, chessBoard[i][PAWN_RANK_BLACK]));
				INITIAL_PIECES.add(new Pawn(PieceColor.BLACK, chessBoard[i][PAWN_RANK_WHITE]));
				switch (i) {
				case 0, 7 -> {
					INITIAL_PIECES.add(new Rook(PieceColor.WHITE, chessBoard[i][PIECE_RANK_BLACK]));
					INITIAL_PIECES.add(new Rook(PieceColor.BLACK, chessBoard[i][PIECE_RANK_WHITE]));
				}
				case 1, 6 -> {
					INITIAL_PIECES.add(
							new Knight(PieceColor.WHITE, chessBoard[i][PIECE_RANK_BLACK]));
					INITIAL_PIECES.add(
							new Knight(PieceColor.BLACK, chessBoard[i][PIECE_RANK_WHITE]));
				}
				case 2, 5 -> {
					INITIAL_PIECES.add(
							new Bishop(PieceColor.WHITE, chessBoard[i][PIECE_RANK_BLACK]));
					INITIAL_PIECES.add(
							new Bishop(PieceColor.BLACK, chessBoard[i][PIECE_RANK_WHITE]));
				}
				case 3 -> {
					INITIAL_PIECES.add(
							new Queen(PieceColor.WHITE, chessBoard[i][PIECE_RANK_BLACK]));
					INITIAL_PIECES.add(
							new Queen(PieceColor.BLACK, chessBoard[i][PIECE_RANK_WHITE]));
				}
				case 4 -> {
					INITIAL_PIECES.add(new King(PieceColor.WHITE, chessBoard[i][PIECE_RANK_BLACK]));
					INITIAL_PIECES.add(new King(PieceColor.BLACK, chessBoard[i][PIECE_RANK_WHITE]));
				}
				}
			}
		}

		for (Piece piece : INITIAL_PIECES) {
			// Add pieces of INITIAL_PIECES to chessBoard object
			chessBoard[piece.getFile()][piece.getRank()].setPiece(piece);
		}
		inGamePieces = INITIAL_PIECES;
	}

	// GETTERS
	public static Tile[][] getChessBoard() {
		return chessBoard;
	}

	// VOID
	public void drawChessBoard(Graphics2D g2) {
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		Color inverseColor = null;
		// Draws the tiles
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				g2.setColor(chessBoard[i][j].getTileColor());
				g2.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				inverseColor = (i + j) % 2 == 0 ? BLACK_TILE_COLOR : WHITE_TILE_COLOR;
				g2.setColor(inverseColor);
			}
		}
		if (PLAYER_COLOR == PlayerColor.WHITE) {
			// Draws the file labels for white
			for (int file = 0; file < BOARD_SIZE; file++) {
				inverseColor = file % 2 == 0 ? BLACK_TILE_COLOR : WHITE_TILE_COLOR;
				g2.setColor(inverseColor);
				g2.drawString(RANK_LABELS[file], LABEL_MARGIN_X,
						(file * TILE_SIZE) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
			}
			// Draws the rank labels for white
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				inverseColor = rank % 2 == 1 ? BLACK_TILE_COLOR : WHITE_TILE_COLOR;
				g2.setColor(inverseColor);
				g2.drawString(FILE_LABELS[rank], (rank * TILE_SIZE) + (TILE_SIZE - LABEL_MARGIN_Y),
						(BOARD_SIZE - 1) * TILE_SIZE + (TILE_SIZE - LABEL_MARGIN_X));
			}
		} else {
			// Draws the rank labels for black
			for (int file = 0; file < BOARD_SIZE; file++) {
				inverseColor = file % 2 == 0 ? BLACK_TILE_COLOR : WHITE_TILE_COLOR;
				g2.setColor(inverseColor);
				g2.drawString(RANK_LABELS[7 - file], LABEL_MARGIN_X,
						(file * TILE_SIZE) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
			}
			// Draws the rank labels for black
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				inverseColor = rank % 2 == 1 ? BLACK_TILE_COLOR : WHITE_TILE_COLOR;
				g2.setColor(inverseColor);
				g2.drawString(FILE_LABELS[7 - rank], (rank * TILE_SIZE) + (100 - LABEL_MARGIN_Y),
						(BOARD_SIZE - 1) * TILE_SIZE + (100 - LABEL_MARGIN_X));
			}
		}
	}

	public void drawInitialChessPieces(Graphics2D g2) {
		for (Piece p : inGamePieces) {
			p.drawPiece(g2);
		}
	}
}
