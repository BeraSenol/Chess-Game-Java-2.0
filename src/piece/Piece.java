package piece;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import board.Board;
import board.Tile;

public class Piece implements Moveable {
	private final int TILE_SIZE = Tile.getTileSize();
	private final int TILE_SIZE_HALF = TILE_SIZE / 2;
	private final int INDICATOR_SIZE = 30;
	private final int INDICATOR_OFFSET = TILE_SIZE_HALF - (INDICATOR_SIZE / 2);
	private final float ALPHA = 0.15f;
	
	private int file, rank, x, y = 0;
	private PieceColor pieceColor = null;
	private PieceType pieceType = null;
	private Tile tile = null;
	protected BufferedImage pieceImage = null;
	private BufferedImage indicatorImage = null;

	public Piece(PieceColor pieceColor, PieceType pieceType, Tile tile) {
		this.file = tile.getFile();
		this.rank = tile.getRank();
		this.x = tile.getFile() * TILE_SIZE;
		this.y = tile.getRank() * TILE_SIZE;
		this.pieceColor = pieceColor;
		this.pieceType = pieceType;
		this.tile = tile;
	}

	public BufferedImage getPieceImage(PieceType pieceType, PieceColor pieceColor) {
		// Loads the image for each PieceType and PieceColor
		pieceImage = null;
		try {
			pieceImage = ImageIO.read(new FileInputStream(
					"res/pieces/" + pieceType.getName() + "_" + pieceColor.getName() + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pieceImage;
	}

	private BufferedImage getMoveableTileImage() {
		// Loads the indicatorImage for moveableTiles
		indicatorImage = null;
		try {
			indicatorImage = ImageIO.read(new FileInputStream("res/board/circle.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return indicatorImage;
	}

	// BOOLEANS
	protected boolean isWithinBounds(int n) {
		if (n < 0) {
			return false;
		}
		if (n > 7) {
			return false;
		}
		return true;
	}

	// GETTERS
	public int getFile() {
		return file;
	}

	public int getRank() {
		return rank;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public Tile getTile() {
		return tile;
	}

	public Tile[][] getChessBoard() {
		return Board.chessBoard;
	}

	// GETTERS - MOVEABLE INTERFACE
	public ArrayList<Tile> getMoveableTiles() {
		return null;
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
		setX(tile.getX());
		setY(tile.getY());
	}

	// VOID
	public void drawPiece(Graphics2D g2) {
		g2.drawImage(pieceImage, x, y, TILE_SIZE, TILE_SIZE, null);
	}

	public void drawIndicators(Graphics2D g2, ArrayList<Tile> tiles) {
		for (Tile tile : tiles) {
			// Sets the transparancy of indicatorImage to ALPHA (0.15f)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ALPHA));
			g2.drawImage(getMoveableTileImage(), tile.getX() + INDICATOR_OFFSET,
					tile.getY() + INDICATOR_OFFSET, INDICATOR_SIZE, INDICATOR_SIZE, null);
		}
	}
}
