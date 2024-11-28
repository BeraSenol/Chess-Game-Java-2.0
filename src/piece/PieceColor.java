package piece;

public enum PieceColor {
    WHITE("WHITE"), BLACK("BLACK");

    private final String name;

    private PieceColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PieceColor getOppositeColor(PieceColor pieceColor) {
        return pieceColor == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
    }
}
