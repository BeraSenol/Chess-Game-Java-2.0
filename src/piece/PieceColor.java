package piece;

public enum PieceColor {
	    WHITE("white"),
        BLACK("black");
        
        private final String name;
        private PieceColor(String name) {
            this.name = name;
        }
        public String getPieceColorName() {
            return name;
        }
}
