package board;

import java.awt.Graphics2D;

import main.GameWindow;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;
import piece.pieces.*;
import player.*;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class Board {
	private static final int PIECE_RANK_BLACK = 0;
	private static final int PAWN_RANK_BLACK = 1;
	private static final int PAWN_RANK_WHITE = 6;
	private static final int PIECE_RANK_WHITE = 7;
	private final int BOARD_SIZE = 8;
	private final int LABEL_MARGIN_X = 5;
	private final int LABEL_MARGIN_Y = 13;
	private final int TILE_SIZE = Tile.getTileSize();
	private final int FONT_SIZE = 14;
	private final String FONT_FAMILY = "Arial";
	private final String[] RANK_LABELS = { "8", "7", "6", "5", "4", "3", "2", "1" };
	private final String[] FILE_LABELS = { "a", "b", "c", "d", "e", "f", "g", "h" };
	private final ArrayList<Piece> INITIAL_PIECES = new ArrayList<>();
	private final TileColor LIGHT_TILE_COLOR = TileColor.LIGHT;
	private final TileColor DARK_TILE_COLOR = TileColor.DARK;
	private final PlayerColor PLAYER_COLOR = GameWindow.getPlayerColor();

	private static ArrayList<Piece> onBoardPieces = new ArrayList<>();
	public static Tile[][] chessBoard = null;

	// CONSTRUCTOR
	public Board() {
		chessBoard = new Tile[BOARD_SIZE][BOARD_SIZE];
		int pawnRankBlack = PAWN_RANK_BLACK;
		int pieceRankBlack = PIECE_RANK_BLACK;
		int pieceRankWhite = PIECE_RANK_WHITE;
		int pawnRankWhite = PAWN_RANK_WHITE;
		if (PLAYER_COLOR == PlayerColor.BLACK) {
			// Inverses ranks if playing as Black
			pawnRankBlack = PAWN_RANK_WHITE;
			pieceRankBlack = PIECE_RANK_WHITE;
			pieceRankWhite = PIECE_RANK_BLACK;
			pawnRankWhite = PAWN_RANK_BLACK;
		}
		for (int i = PIECE_RANK_BLACK; i <= PIECE_RANK_WHITE; i++) {
			for (int j = PIECE_RANK_BLACK; j <= PIECE_RANK_WHITE; j++) {
				// Creates Tiles object for the chessBoard
				chessBoard[i][j] = (i + j) % 2 == 0
						? new Tile(i, j, LIGHT_TILE_COLOR, FILE_LABELS[i], RANK_LABELS[j], null)
						: new Tile(i, j, DARK_TILE_COLOR, FILE_LABELS[i], RANK_LABELS[j], null);
			}
		}
		for (int i = PIECE_RANK_BLACK; i <= PIECE_RANK_WHITE; i++) {
			// Creates Pieces
			INITIAL_PIECES.add(new Pawn(PieceColor.WHITE, chessBoard[i][pawnRankWhite]));
			INITIAL_PIECES.add(new Pawn(PieceColor.BLACK, chessBoard[i][pawnRankBlack]));
			switch (i) {
			case 0, 7 -> {
				INITIAL_PIECES.add(new Rook(PieceColor.WHITE, chessBoard[i][pieceRankWhite]));
				INITIAL_PIECES.add(new Rook(PieceColor.BLACK, chessBoard[i][pieceRankBlack]));
			}
			case 1, 6 -> {
				INITIAL_PIECES.add(new Knight(PieceColor.WHITE, chessBoard[i][pieceRankWhite]));
				INITIAL_PIECES.add(new Knight(PieceColor.BLACK, chessBoard[i][pieceRankBlack]));
			}
			case 2, 5 -> {
				INITIAL_PIECES.add(new Bishop(PieceColor.WHITE, chessBoard[i][pieceRankWhite]));
				INITIAL_PIECES.add(new Bishop(PieceColor.BLACK, chessBoard[i][pieceRankBlack]));
			}
			case 3 -> {
				// Queen-King if playing as White
				if (PLAYER_COLOR == PlayerColor.WHITE) {
					INITIAL_PIECES.add(new Queen(PieceColor.WHITE, chessBoard[i][pieceRankWhite]));
					INITIAL_PIECES.add(new Queen(PieceColor.BLACK, chessBoard[i][pieceRankBlack]));
				} else {
					INITIAL_PIECES.add(new King(PieceColor.WHITE, chessBoard[i][pieceRankWhite]));
					INITIAL_PIECES.add(new King(PieceColor.BLACK, chessBoard[i][pieceRankBlack]));
				}
			}
			case 4 -> {
				// King-Queen if playing as Black
				if (PLAYER_COLOR == PlayerColor.WHITE) {
					INITIAL_PIECES.add(new King(PieceColor.WHITE, chessBoard[i][pieceRankWhite]));
					INITIAL_PIECES.add(new King(PieceColor.BLACK, chessBoard[i][pieceRankBlack]));
				} else {
					INITIAL_PIECES.add(new Queen(PieceColor.WHITE, chessBoard[i][pieceRankWhite]));
					INITIAL_PIECES.add(new Queen(PieceColor.BLACK, chessBoard[i][pieceRankBlack]));
				}
			}
			}
		}
		for (Piece piece : INITIAL_PIECES) {
			// Adds created Pieces to chessBoard
			chessBoard[piece.getFile()][piece.getRank()].setPiece(piece);
		}
		onBoardPieces = INITIAL_PIECES;
	}

	// GETTERS
	public static Tile[][] getChessBoard() {
		return chessBoard;
	}

	public static int getPawnRankBlack() {
		return PAWN_RANK_BLACK;
	}

	public static int getPieceRankBlack() {
		return PIECE_RANK_BLACK;
	}

	public static int getPieceRankWhite() {
		return PIECE_RANK_WHITE;
	}

	public static int getPawnRankWhite() {
		return PAWN_RANK_WHITE;
	}

	public static Piece getKing(PieceColor kingColor) {
		for (Piece piece : onBoardPieces) {
			if (piece.getPieceType() == PieceType.KING) {
				if (piece.getPieceColor() == kingColor) {
					return piece;
				}
			}
		}
		return null;
	}

	public static ArrayList<Piece> getOnBoardPieces() {
		return onBoardPieces;
	}

	// VOID
	public void drawChessBoard(Graphics2D g2) {
		g2.setFont(new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE));
		final Color DARK = DARK_TILE_COLOR.getColor();
		final Color LIGHT = LIGHT_TILE_COLOR.getColor();
		Color inverseColor = null;
		// Draws the tiles (same for white and black)
		for (int i = PIECE_RANK_BLACK; i <= PIECE_RANK_WHITE; i++) {
			for (int j = PIECE_RANK_BLACK; j <= PIECE_RANK_WHITE; j++) {
				g2.setColor(chessBoard[i][j].getTileColor().getColor());
				g2.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				inverseColor = (i + j) % 2 == 0 ? DARK : LIGHT;
				g2.setColor(inverseColor);
			}
		}
		if (PLAYER_COLOR == PlayerColor.WHITE) {
			// Draws the file labels for white
			for (int file = PIECE_RANK_BLACK; file <= PIECE_RANK_WHITE; file++) {
				inverseColor = file % 2 == 0 ? DARK : LIGHT;
				g2.setColor(inverseColor);
				g2.drawString(RANK_LABELS[file], LABEL_MARGIN_X,
						(file * TILE_SIZE) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
			}
			// Draws the rank labels for white
			for (int rank = PIECE_RANK_BLACK; rank <= PIECE_RANK_WHITE; rank++) {
				inverseColor = rank % 2 == 1 ? DARK : LIGHT;
				g2.setColor(inverseColor);
				g2.drawString(FILE_LABELS[rank], (rank * TILE_SIZE) + (TILE_SIZE - LABEL_MARGIN_Y),
						(BOARD_SIZE - 1) * TILE_SIZE + (TILE_SIZE - LABEL_MARGIN_X));
			}
		} else {
			// Draws the rank labels for black
			for (int file = PIECE_RANK_BLACK; file <= PIECE_RANK_WHITE; file++) {
				inverseColor = file % 2 == 0 ? DARK : LIGHT;
				g2.setColor(inverseColor);
				g2.drawString(RANK_LABELS[7 - file], LABEL_MARGIN_X,
						(file * TILE_SIZE) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
			}
			// Draws the rank labels for black
			for (int rank = PIECE_RANK_BLACK; rank <= PIECE_RANK_WHITE; rank++) {
				inverseColor = rank % 2 == 1 ? DARK : LIGHT;
				g2.setColor(inverseColor);
				g2.drawString(FILE_LABELS[7 - rank], (rank * TILE_SIZE) + (100 - LABEL_MARGIN_Y),
						(BOARD_SIZE - 1) * TILE_SIZE + (100 - LABEL_MARGIN_X));
			}
		}
	}

	public void drawInitialChessPieces(Graphics2D g2) {
		for (Piece p : onBoardPieces) {
			p.drawPiece(g2);
		}
	}

	public void removePieceFromBoard(Piece piece) {
		onBoardPieces.remove(piece);
		piece.getTile().removePiece();
	}
}
