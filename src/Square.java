public class Square {
    private String colour;
    private String occupiedBy = "empty";
    private String piece = "empty";

    public Square(String colour) {
        this.colour = colour;
    }

    public Square(String colour, String occupiedBy) {
        this.colour = colour;
        this.occupiedBy = occupiedBy;
    }

    public String getPiece() {
        return piece;
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

    public void moveToSquare(String colour, String pc){
        occupiedBy = colour;
        piece = pc;
    }

    public void leaveSquare(){
        occupiedBy = "empty";
        piece = "empty";
    }
}
