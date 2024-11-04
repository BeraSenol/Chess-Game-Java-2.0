package piece;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.awt.Graphics2D;

import board.Tile;

public class Piece {
	private int file, rank, x, y;
	private int tileSize = Tile.getTileSize();
	private PieceColor pieceColor;
	private PieceType pieceType;
	private Tile tile;
	public BufferedImage bufferedImage;

	public Piece(PieceColor pieceColor, PieceType pieceType, Tile tile) {
		this.file = tile.getFile();
		this.rank = tile.getRank();
		this.x = tile.getFile() * tileSize;
		this.y = tile.getRank() * tileSize;
		this.pieceColor = pieceColor;
		this.pieceType = pieceType;
		this.tile = tile;
	}

	// Loads the image for each PieceType and PieceColor
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
	public int getFile() {
		return file;
	}

	public int getRank() {
		return rank;
	}

	public int getX() {
		return file * tileSize;
	}

	public int getY() {
		return rank * tileSize;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public String getPieceColorName() {
		return pieceColor.getPieceColorName();
	}

	public Tile getTile() {
		return tile;
	}

	// SETTERS
	public void setFile(int file) {
		this.file = file;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
		setFile(tile.getFile());
		setRank(tile.getRank());
		setX(tile.getRank() * tileSize);
		setY(tile.getFile() * tileSize);
	}

	// VOID
	public void drawPiece(Graphics2D g2) {
		g2.drawImage(bufferedImage, x, y, tileSize, tileSize, null);
	}
}
