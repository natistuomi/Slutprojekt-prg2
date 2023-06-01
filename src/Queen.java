import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<int[]> getOptions(){
        ArrayList<int[]> options = new ArrayList<int[]>();
        int[] s = new int[2];
        int x = getX();
        int y = getY();
        for(int i = x-1; i != -1; i--){
            s[0] = i;
            s[1] = y;
            options.add(s);
            s = new int[2];
        }
        s = stopper();
        options.add(s);
        s = new int[2];
        for(int i = y-1; i != -1; i--){
            s[0] = x;
            s[1] = i;
            options.add(s);
            s = new int[2];
        }
        s = stopper();
        options.add(s);
        s = new int[2];
        for(int i = x+1; i != 8; i++){
            s[0] = i;
            s[1] = y;
            options.add(s);
            s = new int[2];
        }
        s = stopper();
        options.add(s);
        s = new int[2];
        for(int i = y+1; i != 8; i++){
            s[0] = x;
            s[1] = i;
            options.add(s);
            s = new int[2];
        }
        s = stopper();
        options.add(s);
        s = new int[2];
        int g = y;
        for(int i = x-1; i > -1; i--){
            g -= 1;
            if(g != -1){
                s[0] = i;
                s[1] = g;
                options.add(s);
                s = new int[2];
            }
            else{
                i = -1;
            }
        }
        s = stopper();
        options.add(s);
        s = new int[2];
        g = y;
        for(int i = x+1; i < 8; i++){
            g -= 1;
            if(g != -1){
                s[0] = i;
                s[1] = g;
                options.add(s);
                s = new int[2];
            }
            else{
                i = 8;
            }
        }
        s = stopper();
        options.add(s);
        s = new int[2];
        g = y;
        for(int i = x+1; i < 8; i++){
            g += 1;
            if(g != 8){
                s[0] = i;
                s[1] = g;
                options.add(s);
                s = new int[2];
            }
            else{
                i = 8;
            }
        }
        s = stopper();
        options.add(s);
        s = new int[2];
        g = y;
        for(int i = x-1; i > -1; i--){
            g += 1;
            if(g != 8){
                s[0] = i;
                s[1] = g;
                options.add(s);
                s = new int[2];
            }
            else{
                i = -1;
            }
        }
        s = stopper();
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
