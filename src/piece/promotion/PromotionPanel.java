package piece.promotion;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private Pawn promotionPawn = null;

	public PromotionPanel(Pawn promotionPawn) {
		this.promotionPawn = promotionPawn;
		addButtons();
		setIcons(promotionPawn.getPieceColor());
		addActionListeners(promotionPawn.getPieceColor(), promotionPawn.getFile(), promotionPawn.getRank());
	}

	private void addButtons() {
		this.add(QUEEN_BUTTON);
		this.add(BISHOP_BUTTON);
		this.add(KNIGHT_BUTTON);
		this.add(ROOK_BUTTON);
	}

	private void setIcons(PieceColor pawnColor) {
		final int TILE_SIZE = Tile.getTileSize();
		final int IMAGE_SCALE = Image.SCALE_SMOOTH;
		final String PIECE_COLOR_NAME = pawnColor.getName().toLowerCase();
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

	private void addActionListeners(PieceColor pawnColor, int file, int rank) {
		QUEEN_BUTTON.addActionListener(new PromotionButtonAction(PieceType.QUEEN, promotionPawn));
		BISHOP_BUTTON.addActionListener(new PromotionButtonAction(PieceType.BISHOP, promotionPawn));
		KNIGHT_BUTTON.addActionListener(new PromotionButtonAction(PieceType.KNIGHT, promotionPawn));
		ROOK_BUTTON.addActionListener(new PromotionButtonAction(PieceType.ROOK, promotionPawn));
	}
}
