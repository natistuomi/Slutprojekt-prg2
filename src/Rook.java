import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<int[]> getOptions(){
        ArrayList<int[]> options = new ArrayList<int[]>();
        int[] s = new int[2];
        // checks left, up, right and then down
        for(int i = getX()-1; i != -1; i--){
            s[0] = i;
            s[1] = getY();
            options.add(s);
        }
        s = stopper();
        options.add(s);
        for(int i = getY()-1; i != -1; i--){
            s[0] = getX();
            s[1] = i;
            options.add(s);
        }
        s = stopper();
        options.add(s);
        for(int i = getX()+1; i != 8; i++){
            s[0] = i;
            s[1] = getY();
            options.add(s);
        }
        s = stopper();
        options.add(s);
        for(int i = getY()+1; i != 8; i++){
            s[0] = getX();
            s[1] = i;
            options.add(s);
        }
        s = stopper();
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
