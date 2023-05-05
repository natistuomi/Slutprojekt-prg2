import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<String> getOptions(){
        ArrayList<String> options = new ArrayList<String>();
        String s = "";
        // checks left, up, right and then down
        for(int i = getX()-1; i != -1; i--){
            s = i + ":" + getY();
            options.add(s);
        }
        s = "/";
        options.add(s);
        for(int i = getY()-1; i != -1; i--){
            s = getX() + ":" + i;
            options.add(s);
        }
        s = "/";
        options.add(s);
        for(int i = getX()+1; i != 8; i++){
            s = i + ":" + getY();
            options.add(s);
        }
        s = "/";
        options.add(s);
        for(int i = getY()+1; i != 8; i++){
            s = getX() + ":" + i;
            options.add(s);
        }
        s = "/";
        options.add(s);
        return options;
    }

    @Override
    public String toString() {
        return "Rook{" +
                "colour='" + getColour() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", k=" + getK() +
                '}';
    }
}
