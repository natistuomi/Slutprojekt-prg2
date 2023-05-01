public class Square {
    private String colour;
    private String occupiedBy = "";

    public Square(String colour, String occupiedBy) {
        this.colour = colour;
        this.occupiedBy = occupiedBy;
    }

    public String getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(String occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public void leaveSquare(){
        occupiedBy = "";
    }
}
