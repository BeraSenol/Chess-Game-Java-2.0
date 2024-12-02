package piece;

import java.util.ArrayList;

import board.Tile;

public interface Moveable {
	public ArrayList<Tile> getMoveableTiles();
	public ArrayList<Tile> getCaptureableTiles();
	public ArrayList<Tile> getDefendingTiles();
}
