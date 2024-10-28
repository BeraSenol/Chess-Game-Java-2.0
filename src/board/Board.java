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

	// VARIABLES - CONSTANTS
	private final int PIECE_RANK_BLACK = 0;
	private final int PAWN_RANK_BLACK = 1;
	private final int PAWN_RANK_WHITE = 6;
	private final int PIECE_RANK_WHITE = 7;
	public static final int BOARD_SIZE = 8;
	public static final int LABEL_MARGIN_X = 5;
	public static final int LABEL_MARGIN_Y = 13;
	public final String[] RANK_LABELS = { "8", "7", "6", "5", "4", "3", "2", "1" };
	public final String[] FILE_LABELS = { "a", "b", "c", "d", "e", "f", "g", "h" };
	
	public static int tileSize = 100;
	public static Color whiteTileColor = new Color(210, 165, 125);
	public static Color blackTileColor = new Color(175, 115, 70);

	public Player whitePlayer = new Player(PlayerColor.WHITE);
	public Player blackPlayer = new Player(PlayerColor.BLACK);

	public static final ArrayList<Piece> INITIAL_PIECES = new ArrayList<>();
	public static ArrayList<Piece> inGamePieces = new ArrayList<>();
	public Tile[][] board;

	public Board() {
		this.board = new Tile[BOARD_SIZE][BOARD_SIZE];
		for (int file = 0; file < BOARD_SIZE; file++) {
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				board[file][rank] = (file + rank) % 2 == 0
						? new Tile(rank, file, whiteTileColor, FILE_LABELS[file],
								RANK_LABELS[rank], null)
						: new Tile(rank, file, blackTileColor, FILE_LABELS[file],
								RANK_LABELS[rank], null);
			}
		}
	}

	// Draws the board
	public void drawChessBoard(Graphics2D g2) {
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		Color inverseColor = null;
		// Draws the tiles
		for (int file = 0; file < BOARD_SIZE; file++) {
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				g2.setColor(board[file][rank].tileColor);
				g2.fillRect(file * tileSize, rank * tileSize, tileSize, tileSize);
				inverseColor = (file + rank) % 2 == 0 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
			}
		}
		if (GameWindow.playerColor == PlayerColor.WHITE) {
			// Draws the file labels for white
			for (int file = 0; file < BOARD_SIZE; file++) {
				inverseColor = file % 2 == 0 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				g2.drawString(RANK_LABELS[file], LABEL_MARGIN_X,
						(file * tileSize) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
			}
			// Draws the rank labels for white
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				inverseColor = rank % 2 == 1 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				g2.drawString(FILE_LABELS[rank], (rank * tileSize) + (tileSize - LABEL_MARGIN_Y),
						(BOARD_SIZE - 1) * tileSize + (tileSize - LABEL_MARGIN_X));
			}
		} else {
			// Draws the rank labels for black
			for (int file = 0; file < BOARD_SIZE; file++) {
				inverseColor = file % 2 == 0 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				g2.drawString(RANK_LABELS[7 - file], LABEL_MARGIN_X,
						(file * tileSize) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
			}
			// Draws the rank labels for black
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				inverseColor = rank % 2 == 1 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				g2.drawString(FILE_LABELS[7 - rank], (rank * tileSize) + (100 - LABEL_MARGIN_Y),
						(BOARD_SIZE - 1) * tileSize + (100 - LABEL_MARGIN_X));
			}
		}
	}

	

	// Voids Functions
	public void initializePieces() {
		// Add pieces to the INITIAL_PIECES ArrayList
		for (int i = PIECE_RANK_BLACK; i <= PIECE_RANK_WHITE; i++) {
			INITIAL_PIECES.add(new Pawn(PieceColor.WHITE, board[PAWN_RANK_WHITE][i]));
			INITIAL_PIECES.add(new Pawn(PieceColor.BLACK, board[PAWN_RANK_BLACK][i]));
			switch (i) {
			case 0, 7 -> {
				INITIAL_PIECES.add(new Rook(PieceColor.WHITE, board[PIECE_RANK_WHITE][i]));
				INITIAL_PIECES.add(new Rook(PieceColor.BLACK, board[PIECE_RANK_BLACK][i]));
			}
			case 1, 6 -> {
				INITIAL_PIECES.add(new Knight(PieceColor.WHITE, board[PIECE_RANK_WHITE][i]));
				INITIAL_PIECES.add(new Knight(PieceColor.BLACK, board[PIECE_RANK_BLACK][i]));
			}
			case 2, 5 -> {
				INITIAL_PIECES.add(new Bishop(PieceColor.WHITE, board[PIECE_RANK_WHITE][i]));
				INITIAL_PIECES.add(new Bishop(PieceColor.BLACK, board[PIECE_RANK_BLACK][i]));
			}
			case 3 -> {
				INITIAL_PIECES.add(new Queen(PieceColor.WHITE, board[PIECE_RANK_WHITE][i]));
				INITIAL_PIECES.add(new Queen(PieceColor.BLACK, board[PIECE_RANK_BLACK][i]));
			}
			case 4 -> {
				INITIAL_PIECES.add(new King(PieceColor.WHITE, board[PIECE_RANK_WHITE][i]));
				INITIAL_PIECES.add(new King(PieceColor.BLACK, board[PIECE_RANK_BLACK][i]));
			}
			}
		}
	}

	public void initializeInGamePieces() {
		inGamePieces = INITIAL_PIECES;
	}

	// Getters
	public static int getTileSize() {
		return tileSize;
	}
}
