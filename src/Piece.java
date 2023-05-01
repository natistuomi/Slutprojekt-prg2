public class Piece {
    private String colour;
    private Square position;
    private int x;
    private int y;
    private int k = 1;
    // k = riktningskoefficient

    public void setColour(String colour) {
        this.colour = colour;
        if(colour.equals("white")){
            k = -1;
        }
    }

    public int getK() {
        return k;
    }

    public String getColour() {
        return colour;
    }

    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position, int x, int y) {
        this.position = position;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void movePiece(Square a, int x, int y){
        setPosition(a, x, y);
    }


}
