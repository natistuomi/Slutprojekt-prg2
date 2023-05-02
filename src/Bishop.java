import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<String> getOptions(){
        ArrayList<String> options = new ArrayList<String>();
        String s = "";
        int x = getX();
        int y = getY();
        int g = y;
        for(int i = x-1; i > -1; i--){
            g -= 1;
            if(g != -1){
                s = i + ":" + g;
                options.add(s);
            }
            else{
                i = -1;
            }
        }
        g = y;
        for(int i = x+1; i < 8; i++){
            g -= 1;
            if(g != -1){
                s = i + ":" + g;
                options.add(s);
            }
            else{
                i = 8;
            }
        }
        g = y;
        for(int i = x+1; i < 8; i++){
            g += 1;
            if(g != 8){
                s = i + ":" + g;
                options.add(s);
            }
            else{
                i = 8;
            }
        }
        g = y;
        for(int i = x-1; i > -1; i--){
            g += 1;
            if(g != 8){
                s = i + ":" + g;
                options.add(s);
            }
            else{
                i = -1;
            }
        }
        return options;
    }

    @Override
    public String toString() {
        return "Bishop{" +
                "colour='" + getColour() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", k=" + getK() +
                '}';
    }
}
