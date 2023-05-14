import java.util.ArrayList;

public class King extends Piece{
    public King(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<int[]> getOptions(){
        ArrayList<int[]> options = new ArrayList<int[]>();
        int[] s = new int[2];
        if(getX() > 0){
            s[0] = getX()-1;
            s[1] = getY();
            options.add(s);
            s = stopper();
            options.add(s);
            if(getY() > 0){
                s[0] = getX()-1;
                s[1] = getY()-1;
                options.add(s);
                s = stopper();
                options.add(s);
            }
            if(getY() < 7){
                s[0] = getX()-1;
                s[1] = getY()+1;
                options.add(s);
                s = stopper();
                options.add(s);
            }
        }
        if(getX() < 7){
            s[0] = getX()+1;
            s[1] = getY();
            options.add(s);
            s = stopper();
            options.add(s);
            if(getY() > 0){
                s[0] = getX()+1;
                s[1] = getY()-1;
                options.add(s);
                s = stopper();
                options.add(s);
            }
            if(getY() < 7){
                s[0] = getX()+1;
                s[1] = getY()+1;
                options.add(s);
                s = stopper();
                options.add(s);
            }
        }
        if(getY() > 0){
            s[0] = getX();
            s[1] = getY()-1;
            options.add(s);
            s = stopper();
            options.add(s);
        }
        if(getY() < 7){
            s[0] = getX();
            s[1] = getY()+1;
            options.add(s);
            s = stopper();
            options.add(s);
        }
        return options;
    }

    @Override
    public String toString() {
        return "King{" +
                "colour='" + getColour() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", k=" + getK() +
                '}';
    }
}
