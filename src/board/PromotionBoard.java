package board;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import main.GameWindow;
import main.Mouse;
import piece.PieceColor;
import piece.pieces.Bishop;
import piece.pieces.Knight;
import piece.pieces.Queen;
import piece.pieces.Rook;

public class PromotionBoard extends JPanel {
	private final int TILE_SIZE = Tile.getTileSize();
	private final int WINDOW_SIZE = 200;
	private final Dimension WINDOW_DIMENSION = new Dimension(WINDOW_SIZE, WINDOW_SIZE);
	private final static Mouse PLAYER_MOUSE = GameWindow.PLAYER_MOUSE;
	private Tile[][] board = new Tile[2][2];
	private PieceColor pawnColor = null;
	private Graphics2D graphics2d = null;

	public PromotionBoard(PieceColor pawnColor) {
		this.pawnColor = pawnColor;
		setPreferredSize(WINDOW_DIMENSION);
		addMouseListener(PLAYER_MOUSE);
		addMouseMotionListener(PLAYER_MOUSE);
		initializeTiles();
		initializePromotionPieces();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics2d = (Graphics2D) g;
		drawPromotionTiles();
		drawPromotionPieces();
	}

	private void initializeTiles() {
		TileColor inversColor = null;
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 1; j++) {
				inversColor = i - j % 2 == 0 ? TileColor.LIGHT : TileColor.DARK;
				board[i][j] = new Tile(i, j, inversColor, null, null, null);
			}
		}
	}

	private void initializePromotionPieces() {
		board[0][0].setPiece(new Queen(pawnColor, board[0][0]));
		board[1][0].setPiece(new Bishop(pawnColor, board[1][0]));
		board[0][1].setPiece(new Knight(pawnColor, board[0][1]));
		board[1][1].setPiece(new Rook(pawnColor, board[1][1]));
	}

	private void drawPromotionTiles() {
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 1; j++) {
				graphics2d.setColor(board[i][j].getTileColor().getColor());
				graphics2d.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
	}

	private void drawPromotionPieces() {
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 1; j++) {
				graphics2d.drawImage(
						board[i][j].getPiece().getPieceImage(
								board[i][j].getPiece().getPieceType(),
								board[i][j].getPiece().getPieceColor()),
						board[i][j].getFile() * TILE_SIZE, board[i][j].getRank() * TILE_SIZE,
						TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
}
