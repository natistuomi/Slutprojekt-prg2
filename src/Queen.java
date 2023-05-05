import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<String> getOptions(){
        ArrayList<String> options = new ArrayList<String>();
        String s = "";
        int x = getX();
        int y = getY();
        for(int i = x-1; i != -1; i--){
            s = i + ":" + y;
            options.add(s);
        }
        s = "/";
        options.add(s);
        for(int i = y-1; i != -1; i--){
            s = x + ":" + i;
            options.add(s);
        }
        s = "/";
        options.add(s);
        for(int i = x+1; i != 8; i++){
            s = i + ":" + y;
            options.add(s);
        }
        s = "/";
        options.add(s);
        for(int i = y+1; i != 8; i++){
            s = x + ":" + i;
            options.add(s);
        }
        s = "/";
        options.add(s);
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
        s = "/";
        options.add(s);
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
        s = "/";
        options.add(s);
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
        s = "/";
        options.add(s);
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
        s = "/";
        options.add(s);
        return options;
    }

    @Override
    public String toString() {
        return "Queen{" +
                "colour='" + getColour() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", k=" + getK() +
                '}';
    }
}
