package piece.promotion;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Tile;
import piece.PieceColor;
import piece.PieceType;
import piece.pieces.Pawn;

public class PromotionPanel extends JPanel {
	private final JButton QUEEN_BUTTON = new JButton();
	private final JButton BISHOP_BUTTON = new JButton();
	private final JButton KNIGHT_BUTTON = new JButton();
	private final JButton ROOK_BUTTON = new JButton();
	private JFrame promotionWindow = null;
	private Pawn promotionPawn = null;

	public PromotionPanel(JFrame promotionWindow, Pawn promotionPawn) {
		this.promotionPawn = promotionPawn;
		this.promotionWindow = promotionWindow;
		initializeFrameSettings();
		initializeButtons();
		initializeIcons(promotionPawn.getPieceColor());
		initializeActionListeners(promotionPawn.getPieceColor(), promotionPawn.getFile(),
				promotionPawn.getRank());
	}

	// GETTERS
	private JFrame getPromotionWindow() {
		return promotionWindow;
	}

	private Pawn getPromotionPawn() {
		return promotionPawn;
	}

	// VOID
	private void initializeFrameSettings() {
		final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
		final int WIDTH_OFFSET = 240;
		final int HEIGHT_OFFSET = 60;
		final int SCREEN_WIDTH_CENTER = (int) SCREEN_SIZE.getWidth() / 2 - WIDTH_OFFSET;
		final int SCREEN_HEIGHT_CENTER = (int) SCREEN_SIZE.getHeight() / 2 - HEIGHT_OFFSET;
		getPromotionWindow().setLocation(SCREEN_WIDTH_CENTER, SCREEN_HEIGHT_CENTER);
		getPromotionWindow().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getPromotionWindow().setVisible(true);
		getPromotionWindow().setResizable(false);
	}

	private void initializeButtons() {
		this.add(QUEEN_BUTTON);
		this.add(BISHOP_BUTTON);
		this.add(KNIGHT_BUTTON);
		this.add(ROOK_BUTTON);
	}

	private void initializeIcons(PieceColor pawnColor) {
		final int TILE_SIZE = Tile.getTileSize();
		final int IMAGE_SCALE = Image.SCALE_SMOOTH;
		final String PIECE_COLOR_NAME = pawnColor.getName().toLowerCase();
		// ImageIcon has to be double nested since getScaledInstance() returns a regular
		// Image object
		QUEEN_BUTTON.setIcon(
				new ImageIcon(new ImageIcon(String.format("res/pieces/queen_%s.png", PIECE_COLOR_NAME))
						.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, IMAGE_SCALE)));
		BISHOP_BUTTON.setIcon(
				new ImageIcon(new ImageIcon(String.format("res/pieces/bishop_%s.png", PIECE_COLOR_NAME))
						.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, IMAGE_SCALE)));
		KNIGHT_BUTTON.setIcon(
				new ImageIcon(new ImageIcon(String.format("res/pieces/knight_%s.png", PIECE_COLOR_NAME))
						.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, IMAGE_SCALE)));
		ROOK_BUTTON.setIcon(
				new ImageIcon(new ImageIcon(String.format("res/pieces/rook_%s.png", PIECE_COLOR_NAME))
						.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, IMAGE_SCALE)));
	}

	private void initializeActionListeners(PieceColor pawnColor, int file, int rank) {
		QUEEN_BUTTON.addActionListener(
				new PromotionButtonAction(getPromotionWindow(), PieceType.QUEEN, getPromotionPawn()));
		BISHOP_BUTTON.addActionListener(
				new PromotionButtonAction(getPromotionWindow(), PieceType.BISHOP, getPromotionPawn()));
		KNIGHT_BUTTON.addActionListener(
				new PromotionButtonAction(getPromotionWindow(), PieceType.KNIGHT, getPromotionPawn()));
		ROOK_BUTTON.addActionListener(
				new PromotionButtonAction(getPromotionWindow(), PieceType.ROOK, getPromotionPawn()));
	}

}
