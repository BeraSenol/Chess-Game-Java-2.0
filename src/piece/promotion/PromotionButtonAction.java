package piece.promotion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public PromotionButtonAction(PieceType selectedPieceType, Pawn promotionPawn) {
		this.selectedPieceType = selectedPieceType;
		this.promotionPawn = promotionPawn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final int FILE = promotionPawn.getFile();
		final int RANK = promotionPawn.getRank();
		PieceColor selectedPieceColor = promotionPawn.getPieceColor();
		switch (selectedPieceType) {
		case QUEEN:
			promotionPiece = new Queen(selectedPieceColor, BOARD_TILES[FILE][RANK]);
			break;
		case BISHOP:
			promotionPiece = new Bishop(selectedPieceColor, BOARD_TILES[FILE][RANK]);
			break;
		case KNIGHT:
			promotionPiece = new Knight(selectedPieceColor, BOARD_TILES[FILE][RANK]);
			break;
		case ROOK:
			promotionPiece = new Rook(selectedPieceColor, BOARD_TILES[FILE][RANK]);
			break;
		default:
			break;
		}
		Board.removePieceFromBoard(promotionPawn);
		Board.addPieceToBoard(BOARD_TILES[FILE][RANK], promotionPiece);
		BOARD_TILES[FILE][RANK].setPiece(promotionPiece);
	}
}
