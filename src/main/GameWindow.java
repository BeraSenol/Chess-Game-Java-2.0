package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import board.*;
import player.PlayerColor;

public class GameWindow extends JPanel implements Runnable {

	// VARIABLES - CONSTANTS
	private final int INTERVAL = 100000000;

	// VARIABLES - PRIMITIVE
	protected static int windowWidth = 800;
	protected static int windowHeight = 800;
	private int fps = 120;
	private double drawInterval = INTERVAL / fps;

	// VARIABLES - NON-PRIMITIVE
	public static PlayerColor playerColor = PlayerColor.WHITE;
	private Color windowBackgroundColor = Color.DARK_GRAY;
	private Dimension windowDimension = new Dimension(windowWidth, windowHeight);
	private Thread gameThread;
	private Board chessBoard = new Board();

	// CONSTRUCTOR
	protected GameWindow() {
		setPreferredSize(windowDimension);
		setBackground(windowBackgroundColor);
		chessBoard.initializePieces();
		chessBoard.initializeInGamePieces();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		chessBoard.drawChessBoard(g2);
		chessBoard.drawInitialChessPieces(g2);
	}

	// Runnable interface implementation
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
