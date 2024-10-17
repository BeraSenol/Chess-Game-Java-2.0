package board;

import java.awt.Graphics2D;

import main.GameWindow;
import player.PlayerColor;

import java.awt.Color;
import java.awt.Font;

public class Board {
	public static final int BOARD_SIZE = 8;
	public final String[] RANK_LABELS = { "8", "7", "6", "5", "4", "3", "2", "1" };
	public final String[] FILE_LABELS = { "a", "b", "c", "d", "e", "f", "g", "h" };
	public static int tileSize = 100;
	public static final int LABEL_MARGIN_X = 5;
	public static final int LABEL_MARGIN_Y = 13;
	public static Color whiteTileColor = new Color(210, 165, 125);
	public static Color blackTileColor = new Color(175, 115, 70);
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
	public void draw(Graphics2D g2) {
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		Color inverseColor;
		// Draw the tiles
		for (int file = 0; file < BOARD_SIZE; file++) {
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				g2.setColor(board[file][rank].tileColor);
				g2.fillRect(file * tileSize, rank * tileSize, tileSize, tileSize);
				inverseColor = (file + rank) % 2 == 0 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
			}
		}

		if (GameWindow.playerColor == PlayerColor.WHITE) {
			// Draw the rank and file label for white
			for (int file = 0; file < BOARD_SIZE; file++) {
				inverseColor = file % 2 == 0 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				g2.drawString(RANK_LABELS[file], LABEL_MARGIN_X,
						(file * tileSize) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
			}
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				inverseColor = rank % 2 == 1 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				g2.drawString(FILE_LABELS[rank], (rank * tileSize) + (100 - LABEL_MARGIN_Y),
						(BOARD_SIZE - 1) * tileSize + (100 - LABEL_MARGIN_X));
			}
		} else {
			// Draw the rank and file label for black
			for (int file = 0; file < BOARD_SIZE; file++) {
				inverseColor = file % 2 == 0 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				g2.drawString(RANK_LABELS[7 - file], LABEL_MARGIN_X,
						(file * tileSize) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
			}
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				inverseColor = rank % 2 == 1 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				g2.drawString(FILE_LABELS[7 - rank], (rank * tileSize) + (100 - LABEL_MARGIN_Y),
						(BOARD_SIZE - 1) * tileSize + (100 - LABEL_MARGIN_X));
			}
		}
	}
}
