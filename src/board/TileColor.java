package board;

import java.awt.Color;

public enum TileColor {
	LIGHT(new Color(210, 165, 125)), DARK(new Color(175, 115, 70)),
	RED(new Color(255, 77, 77));

	private final Color color;

	private TileColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
