package board;

import java.awt.Color;

import piece.Piece;
import piece.PieceColor;
import piece.PieceType;

public class Tile {
	private int file, rank;
	public static int tileSize = 100;
	private Color tileColor;
	private String fileLabel, rankLabel;
	private Piece tilePiece;

	// CONSTRUCTOR
	public Tile(int file, int rank, Color tileColor, String fileLabel, String rankLabel, Piece tilePiece) {
		this.file = file;
		this.rank = rank;
		this.tileColor = tileColor;
		this.fileLabel = fileLabel;
		this.rankLabel = rankLabel;
		this.tilePiece = tilePiece;
	}

	// BOOLEANS
	public boolean isPieceOnTile() {
		if (tilePiece == null) {
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
		return file * tileSize;
	}

	public int getY() {
		return rank * tileSize;
	}

	public static int getTileSize() {
		return tileSize;
	}

	public Color getTileColor() {
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

	public Piece getTilePiece() {
		return tilePiece;
	}

	public PieceColor getTilePieceColor() {
		return tilePiece.getPieceColor();
	}

	public PieceType getTilePieceType() {
		return tilePiece.getPieceType();
	}

	public String getTilePieceColorName() {
		return tilePiece.getPieceColor().getPieceColorName();
	}

	public String getTilePieceTypeName() {
		return tilePiece.getPieceType().getPieceTypeName();
	}

	

	// SETTERS
	public void setFile(int file) {
		this.file = file;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setPiece(Piece piece) {
		this.tilePiece = piece;
	}

	// VOID
	public void removePiece() {
		this.tilePiece = null;
	}
}