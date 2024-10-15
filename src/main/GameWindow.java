package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class GameWindow extends JPanel {

	static int windowWidth = 800;
	static int windowHeight = 800;
	static Dimension windowDimension = new Dimension(windowWidth, windowHeight);
	static Color windowBackgroundColor = Color.DARK_GRAY;

	public GameWindow() {
		setPreferredSize(windowDimension);
		setBackground(windowBackgroundColor);
	}
}
