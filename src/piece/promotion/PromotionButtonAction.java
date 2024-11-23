package piece.promotion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import board.Board;
import board.Tile;
import piece.Piece;
import piece.PieceColor;
import piece.PieceType;
import piece.pieces.Bishop;
import piece.pieces.Knight;
import piece.pieces.Pawn;
import piece.pieces.Queen;
import piece.pieces.Rook;

public class PromotionButtonAction implements ActionListener {

	private final Tile[][] BOARD_TILES = Board.getBoardTiles();
	private Piece promotionPiece = null;
	private PieceType selectedPieceType = null;
	private Pawn promotionPawn = null;
	private JFrame promotionWindow = null;

	public PromotionButtonAction(JFrame promotionWindow, PieceType selectedPieceType, Pawn promotionPawn) {
		this.promotionWindow = promotionWindow;
		this.selectedPieceType = selectedPieceType;
		this.promotionPawn = promotionPawn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final int FILE = getPromotionPawn().getFile();
		final int RANK = getPromotionPawn().getRank();
		createPromotionPiece(FILE, RANK);
		Board.removePieceFromBoard(getPromotionPawn());
		Board.addPieceToBoard(BOARD_TILES[FILE][RANK], getPromotionPiece());
		BOARD_TILES[FILE][RANK].setPiece(getPromotionPiece());
		getPromotionWindow().dispose();
	}

	// GETTERS
	private JFrame getPromotionWindow() {
		return promotionWindow;
	}

	private Pawn getPromotionPawn() {
		return promotionPawn;
	}

	private Piece getPromotionPiece() {
		return promotionPiece;
	}

	private PieceType getSelectedPieceType() {
		return selectedPieceType;
	}

	// SETTERS
	private void setPromotionPiece(Piece piece) {
		this.promotionPiece = piece;
	}

	// VOID
	private void createPromotionPiece(final int FILE, final int RANK) {
		final PieceColor PROMOTION_PAWN_COLOR = getPromotionPawn().getPieceColor();
		switch (getSelectedPieceType()) {
		case QUEEN:
			setPromotionPiece(new Queen(PROMOTION_PAWN_COLOR, BOARD_TILES[FILE][RANK]));
			break;
		case BISHOP:
			setPromotionPiece(new Bishop(PROMOTION_PAWN_COLOR, BOARD_TILES[FILE][RANK]));
			break;
		case KNIGHT:
			setPromotionPiece(new Knight(PROMOTION_PAWN_COLOR, BOARD_TILES[FILE][RANK]));
			break;
		case ROOK:
			setPromotionPiece(new Rook(PROMOTION_PAWN_COLOR, BOARD_TILES[FILE][RANK]));
			break;
		default:
			break;
		}
	}
}
