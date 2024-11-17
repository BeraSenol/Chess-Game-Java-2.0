package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import board.*;

public class Mouse extends MouseAdapter {
	private boolean mousePressed = false;
	private int x, y;
	private final int TILE_SIZE = Tile.getTileSize();
	private final Tile[][] CHESS_BOARD = Board.chessBoard;

	@Override
	public void mousePressed(MouseEvent e) {
		setMousePressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setMousePressed(false);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
	}

	// BOOLEANS
	public boolean isMousePressed() {
		return mousePressed;
	}

	// GETTERS
	public int getMouseX() {
		return x;
	}

	public int getMouseY() {
		return y;
	}

	public int getMouseFile() {
		return (int) Math.floor(x / TILE_SIZE);
	}

	public int getMouseRank() {
		return (int) Math.floor(y / TILE_SIZE);
	}

	public Tile getHoveringTile() {
		return CHESS_BOARD[getMouseFile()][getMouseRank()];
	}

	// SETTERS
	protected void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	private void setMouseX(int x) {
		this.x = x;
	}

	private void setMouseY(int y) {
		this.y = y;
	}
}
