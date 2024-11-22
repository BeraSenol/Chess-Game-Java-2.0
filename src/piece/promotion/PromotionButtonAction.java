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
import piece.pieces.Queen;
import piece.pieces.Rook;

public class PromotionButtonAction implements ActionListener {

	private final Tile[][] BOARD_TILES = Board.getBoardTiles();
	private PieceColor pieceColor = null;
	private PieceType pieceType = null;
	private Piece promotionPiece = null;
	private int file, rank;

	public PromotionButtonAction(PieceType pieceType, PieceColor pieceColor, int file, int rank) {
		this.pieceColor = pieceColor;
		this.pieceType = pieceType;
		this.file = file;
		this.rank = rank;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (pieceType) {
		case QUEEN:
			promotionPiece = new Queen(pieceColor, BOARD_TILES[file][rank]);
			break;
		case BISHOP:
			promotionPiece = new Bishop(pieceColor, BOARD_TILES[file][rank]);
			break;
		case KNIGHT:
			promotionPiece = new Knight(pieceColor, BOARD_TILES[file][rank]);
			break;
		case ROOK:
			promotionPiece = new Rook(pieceColor, BOARD_TILES[file][rank]);
			break;
		default:
			break;
		}
		Board.addPieceToBoard(promotionPiece);
		BOARD_TILES[file][rank].setPiece(promotionPiece);
	}
}
