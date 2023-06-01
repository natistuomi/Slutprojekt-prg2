import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<int[]> getOptions(){
        ArrayList<int[]> options = new ArrayList<int[]>();
        int[] s = new int[2];
        int x = getX();
        int y = getY();
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
        return "Bishop{" +
                "colour='" + getColour() + '\'' +
                ", x=" + getX() +
                ", y=" + getY() +
                ", k=" + getK() +
                '}';
    }
}
