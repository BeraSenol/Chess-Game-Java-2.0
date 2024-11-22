package piece.promotion;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import piece.PieceColor;
import piece.PieceType;

public class PromotionPanel extends JPanel {

	public PromotionPanel(PieceColor pieceColor, int file, int rank) {
		JButton queenButton = new JButton();
		JButton bishopButton = new JButton();
		JButton knightButton = new JButton();
		JButton rookButton = new JButton();
		this.add(queenButton);
		this.add(bishopButton);
		this.add(knightButton);
		this.add(rookButton);
		queenButton.setIcon(new ImageIcon(
				new ImageIcon("res/pieces/queen_" + pieceColor.getName().toLowerCase() + ".png")
						.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		bishopButton.setIcon(new ImageIcon(
				new ImageIcon("res/pieces/bishop_" + pieceColor.getName().toLowerCase() + ".png")
						.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		knightButton.setIcon(new ImageIcon(
				new ImageIcon("res/pieces/knight_" + pieceColor.getName().toLowerCase() + ".png")
						.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		rookButton.setIcon(new ImageIcon(
				new ImageIcon("res/pieces/rook_" + pieceColor.getName().toLowerCase() + ".png")
						.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		queenButton.addActionListener(new PromotionButtonAction(PieceType.QUEEN, pieceColor, file, rank));
		bishopButton.addActionListener(new PromotionButtonAction(PieceType.BISHOP, pieceColor, file, rank));
		knightButton.addActionListener(new PromotionButtonAction(PieceType.KNIGHT, pieceColor, file, rank));
		rookButton.addActionListener(new PromotionButtonAction(PieceType.ROOK, pieceColor, file, rank));
	}
}
