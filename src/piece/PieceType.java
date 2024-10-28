package piece;

public enum PieceType {
	PAWN("pawn"),
        ROOK("rook"),
        KNIGHT("knight"),
        BISHOP("bishop"),
        QUEEN("queen"),
        KING("king");

        private final String name;
        private PieceType(String name) {
                this.name = name;
        }
        public String getPieceTypeName() {
                return name;
        }
}
