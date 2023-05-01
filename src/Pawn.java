import java.util.ArrayList;

public class Pawn extends Piece{
    public Pawn(String colour, Square position, int x, int y){
        setColour(colour);
        setPosition(position, x, y);
    }

    public ArrayList<String> getOptions(){
        ArrayList<String> options = new ArrayList<String>();
        String s = "";
        if(getColour().equals("white") && getY() != 0 || getColour().equals("black") && getY() != 7){
            s = getX() + ":" + (getY()+getK());
            options.add(s);
            if(getX() != 0){
                s = getX()-1 + ":" + (getY()+getK());
                options.add(s);
            }
            if(getX() != 7){
                s = getX()+1 + ":" + (getY()+getK());
                options.add(s);
            }
        }
        return options;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "colour='" + getColour() + '\'' +
                ", position=" + getPosition() +
                ", x=" + getX() +
                ", y=" + getY() +
                ", k=" + getK() +
                '}';
    }
}
