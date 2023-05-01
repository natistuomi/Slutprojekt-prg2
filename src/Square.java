public class Square {
    private String colour;
    private String occupiedBy = "empty";

    public Square(String colour) {
        this.colour = colour;
    }

    public Square(String colour, String occupiedBy) {
        this.colour = colour;
        this.occupiedBy = occupiedBy;
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

    public void setOccupiedBy(String occupiedBy) {
        this.occupiedBy = occupiedBy;
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
