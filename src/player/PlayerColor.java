package player;

public enum PlayerColor {
	    WHITE("WHITE"),
        BLACK("BLACK");
        
        private final String name;
        private PlayerColor(String name) {
            this.name = name;
        }
        public String getPlayerColorName() {
            return name;
        }
}
