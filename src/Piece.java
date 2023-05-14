public class Piece {
    private String colour;
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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void movePiece(int x, int y){setPosition(x, y);}

    public int[] stopper(){
        int[] s = new int[2];
        s[0] = 123;
        s[1] = 123;
        return s;
    }
}
