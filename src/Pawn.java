import java.util.ArrayList;

public class Pawn extends Piece{
    public Pawn(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
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
        return "Pawn{" +
                "colour='" + getColour() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", k=" + getK() +
                '}';
    }
}
