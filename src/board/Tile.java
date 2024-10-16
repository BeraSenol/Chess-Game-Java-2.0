package board;

import java.awt.Color;

import piece.Piece;

public class Tile {
	public int file, rank, tileSize;
	public Color tileColor;
	public String fileLabel, rankLabel;
	public Piece tilePiece;

	public Tile(int file, int rank, Color tileColor, String fileLabel, String rankLabel, Piece tilePiece) {
		this.file = file;
		this.rank = rank;
		this.tileColor = tileColor;
		this.fileLabel = fileLabel;
		this.rankLabel = rankLabel;
		this.tilePiece = tilePiece;
	}
}