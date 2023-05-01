public class Piece {
    private String colour;
    private String position;

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void movePiece(int a, int b){
        String s = a + ":" + b;
        setPosition(s);
    }
}
