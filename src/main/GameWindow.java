package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import board.Board;

public class GameWindow extends JPanel implements Runnable {

	public final int INTERVAL = 100000000;
	public static int windowWidth = 800;
	public static int windowHeight = 800;
	public int fps = 60;
	public double drawInterval = INTERVAL / fps;
	public Color windowBackgroundColor = Color.DARK_GRAY;
	public Dimension windowDimension = new Dimension(windowWidth, windowHeight);
	public Thread gameThread;
	public Board board = new Board();


	public GameWindow() {
		setPreferredSize(windowDimension);
		setBackground(windowBackgroundColor);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		board.draw(g2);
	}

	

	// Runnable interface
	@Override
	public void run() {
		// Game loop
		double deltaTime = 0;
		long previousTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			deltaTime += (currentTime - previousTime) / drawInterval;
			previousTime = currentTime;
			if (deltaTime >= 1) {
				update();
				repaint();
				deltaTime--;
			}
		} 
	}

	protected void launchGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	private void update() {
		
	}
}
