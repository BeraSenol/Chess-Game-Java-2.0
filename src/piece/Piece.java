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
		this.file = tile.getFile();
		this.rank = tile.getRank();
		this.x = getX(tile.getFile());
		this.y = getY(tile.getRank());
	}

	public BufferedImage getImage(PieceType pieceType, PieceColor pieceColor) {
		bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new FileInputStream("res/pieces/" + pieceType.getPieceTypeName()
					+ "_" + pieceColor.getPieceColorName() + ".png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}

	// GETTERS

	public int getX(int file) {
		return file * Tile.getTileSize();
	}

	public int getY(int rank) {
		return rank * Tile.getTileSize();
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(bufferedImage, x, y, Board.tileSize, Board.tileSize, null);
	}
}
