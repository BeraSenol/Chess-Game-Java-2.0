package piece;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.awt.Graphics2D;

import board.Board;
import board.Tile;

public class Piece {
	private int file, rank, x, y;
	private PieceColor pieceColor;
	private PieceType pieceType;
	private Tile tile;
	public BufferedImage bufferedImage;

	public Piece(PieceColor pieceColor, PieceType pieceType, Tile tile) {
		this.pieceColor = pieceColor;
		this.pieceType = pieceType;
		this.tile = tile;
		this.file = tile.file;
		this.rank = tile.rank;
		this.x = getX(tile.file);
		this.y = getY(tile.rank);
	}

	public BufferedImage getImage(PieceType pieceType, PieceColor pieceColor) {
		bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(
					new FileInputStream("res/pieces/" + pieceType.getPieceTypeName()
							+ "_" + pieceColor.getPieceColorName() + ".png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	// Getters
	public int getX(int file) {
		return file * Board.getTileSize();
	}

	public int getY(int rank) {
		return rank * Board.getTileSize();
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(bufferedImage, x, y, Board.tileSize, Board.tileSize, null);
	}
}
