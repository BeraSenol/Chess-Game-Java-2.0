package main;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        // Creates a window
        JFrame jFrame = new JFrame("Chess Game");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        GameWindow gameWindow = new GameWindow();
        jFrame.add(gameWindow);
        jFrame.pack();
        gameWindow.launchGame();
        // Centers the window inside screen
        jFrame.setLocationRelativeTo(null);
    }
}
