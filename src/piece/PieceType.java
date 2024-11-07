package piece;

public enum PieceType {
	PAWN("PAWN"),
        ROOK("ROOK"),
        KNIGHT("KNIGHT"),
        BISHOP("BISHOP"),
        QUEEN("QUEEN"),
        KING("KING");

        private final String name;
        private PieceType(String name) {
                this.name = name;
        }
        public String getPieceTypeName() {
                return name;
        }
}
