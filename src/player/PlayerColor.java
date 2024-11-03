package player;

public enum PlayerColor {
	    WHITE("white"),
        BLACK("black");
        
        private final String name;
        private PlayerColor(String name) {
            this.name = name;
        }
        public String getPlayerColorName() {
            return name;
        }
}
