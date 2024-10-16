package main;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        // Create a window
        JFrame window = new JFrame("Chess Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        GameWindow gameWindow = new GameWindow();
        window.add(gameWindow);
        window.pack();
        gameWindow.launchGame();
        // Center window inside screen
        window.setLocationRelativeTo(null);
    }
}
