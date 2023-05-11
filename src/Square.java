public class Square {
    private String colour;
    private String occupiedBy = "empty";
    private String piece = "";

    public Square(String colour) {
        this.colour = colour;
    }

    public Square(String colour, String occupiedBy) {
        this.colour = colour;
        this.occupiedBy = occupiedBy;
    }

    public void setOccupiedBy(String occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public String getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(String occupiedBy, String p) {
        this.occupiedBy = occupiedBy;
        this.piece = p;
    }

    @Override
    public String toString() {
        return "Square{" +
                "colour='" + colour + '\'' +
                ", occupiedBy='" + occupiedBy + '\'' +
                '}';
    }

    public void leaveSquare(){
        occupiedBy = "empty";
    }
}
