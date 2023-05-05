import java.util.ArrayList;

public class King extends Piece{
    public King(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<String> getOptions(){
        ArrayList<String> options = new ArrayList<String>();
        String s = "";
        if(getX() > 0){
            s = (getX()-1) + ":" + getY();
            options.add(s);
            if(getY() > 0){
                s = (getX()-1) + ":" + (getY()-1);
                options.add(s);
            }
            if(getY() < 7){
                s = (getX()-1) + ":" + (getY()+1);
                options.add(s);
            }
        }
        if(getX() < 7){
            s = (getX()+1) + ":" + getY();
            options.add(s);
            if(getY() > 0){
                s = (getX()+1) + ":" + (getY()-1);
                options.add(s);
            }
            if(getY() < 7){
                s = (getX()+1) + ":" + (getY()+1);
                options.add(s);
            }
        }
        if(getY() > 0){
            s = getX() + ":" + (getY()-1);
            options.add(s);
        }
        if(getY() < 7){
            s = getX() + ":" + (getY()+1);
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
