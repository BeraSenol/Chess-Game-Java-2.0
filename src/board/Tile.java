package board;

import java.awt.Color;

import piece.Piece;
import piece.PieceColor;

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

	public String getTilePieceColorName() {
		return tilePiece.getPieceColorName();
	}

	// SETTERS
	public void setFile(int file) {
		this.file = file;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setPiece(Piece tilePiece) {
		this.tilePiece = tilePiece;
	}

	// VOID
	public void removePiece() {
		this.tilePiece = null;
	}
}