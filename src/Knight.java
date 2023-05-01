import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<String> getOptions(){
        ArrayList<String> options = new ArrayList<String>();
        String s = "";
        if(getX() > 1){
            if(getY() != 0){
                s = (getX()-2) + ":" + (getY()-1);
                options.add(s);
            }
            if(getY() != 7){
                s = (getX()-2) + ":" + (getY()+1);
                options.add(s);
            }
        }
        if(getY() > 1){
            if(getX() != 0){
                s = (getX()-1) + ":" + (getY()-2);
                options.add(s);
            }
            if(getX() != 7){
                s = (getX()+1) + ":" + (getY()-2);
                options.add(s);
            }
        }
        if(getX() < 6){
            if(getY() != 0){
                s = (getX()+2) + ":" + (getY()-1);
                options.add(s);
            }
            if(getY() != 7){
                s = (getX()+2) + ":" + (getY()+1);
                options.add(s);
            }
        }
        if(getY() < 6){
            if(getX() != 0){
                s = (getX()-1) + ":" + (getY()+2);
                options.add(s);
            }
            if(getX() != 7){
                s = (getX()+1) + ":" + (getY()+2);
                options.add(s);
            }
        }
        return options;
    }

    @Override
    public String toString() {
        return "Knight{" +
                "colour='" + getColour() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", k=" + getK() +
                '}';
    }
}
