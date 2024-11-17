package board;
import piece.Piece;

public class Tile {
	public static int tileSize = 100;
	private int file, rank;
	private TileColor tileColor;
	private String fileLabel, rankLabel;
	private Piece piece;

	// CONSTRUCTOR
	public Tile(int file, int rank, TileColor tileColor, String fileLabel, String rankLabel, Piece piece) {
		this.file = file;
		this.rank = rank;
		this.tileColor = tileColor;
		this.fileLabel = fileLabel;
		this.rankLabel = rankLabel;
		this.piece = piece;
	}

	// BOOLEANS
	public boolean isPieceOnTile() {
		if (piece == null) {
			return false;
		}
		return true;
	}

	// GETTERS
	public static int getTileSize() {
		return tileSize;
	}

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

	public TileColor getTileColor() {
		return tileColor;
	}

	public String getFileLabel() {
		return fileLabel;
	}

	public String getRankLabel() {
		return rankLabel;
	}

	public String getTileLabel() {
		return fileLabel + rankLabel;
	}

	public Piece getPiece() {
		return piece;
	}

	// SETTERS
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public void setTileColor(TileColor tileColor) {
		this.tileColor = tileColor;
	}

	// VOID
	public void removePiece() {
		this.piece = null;
	}
}