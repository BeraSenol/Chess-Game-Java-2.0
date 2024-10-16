package board;

import java.awt.Graphics2D;
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
		for (int file = 0; file < BOARD_SIZE; file++) {
			for (int rank = 0; rank < BOARD_SIZE; rank++) {
				g2.setColor(board[file][rank].tileColor);
				g2.fillRect(file * tileSize, rank * tileSize, tileSize, tileSize);
				inverseColor = (file + rank) % 2 == 0 ? blackTileColor : whiteTileColor;
				g2.setColor(inverseColor);
				if (file == 0) {
					g2.drawString(RANK_LABELS[rank], (file * tileSize) + LABEL_MARGIN_X,
							(rank * tileSize) + LABEL_MARGIN_X + LABEL_MARGIN_Y);
				}
				if (rank == 7) {
					g2.drawString(FILE_LABELS[file], (file * tileSize) + (100 - LABEL_MARGIN_Y),
							(rank * tileSize) + (100 - LABEL_MARGIN_X));
				}
			}
		}
	}
}
