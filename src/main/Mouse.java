package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import board.*;

public class Mouse extends MouseAdapter {
	private boolean mousePressed = false;
	private boolean mouseClicked = false;
	private int x, y;
	private int tileSize = Tile.getTileSize();

	@Override
	public void mouseClicked(MouseEvent e) {
		setMouseClicked(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		setMousePressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setMousePressed(false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
	}

	// GETTERS
	public boolean isMousePressed() {
		return mousePressed;
	}

	public boolean isMouseClicked() {
		return mouseClicked;
	}

	public int getMouseX() {
		return x;
	}

	public int getMouseY() {
		return y;
	}

	public int getMouseFile() {
		return (int) Math.floor(x / tileSize);
	}

	public int getMouseRank() {
		return (int) Math.floor(y / tileSize);
	}

	public Tile getHoveringTile() {
		return Board.chessBoard[getMouseFile()][getMouseRank()];
	}

	// SETTERS
	private void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	private void setMouseClicked(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	private void setMouseX(int x) {
		this.x = x;
	}

	private void setMouseY(int y) {
		this.y = y;
	}
}
