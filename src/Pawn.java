import java.util.ArrayList;

public class Pawn extends Piece{
    public Pawn(String colour, int x, int y){
        setColour(colour);
        setPosition(x, y);
    }

    public ArrayList<int[]> getOptions(){
        ArrayList<int[]> options = new ArrayList<int[]>();
        int[] s = new int[2];
        if(getColour().equals("white") && getY() != 0 || getColour().equals("black") && getY() != 7){
            s[0] = getX();
            s[1] = getY()+getK();
            options.add(s);
            s[0] = 321;
            s[1] = 321;
            options.add(s);
            if(getX() != 0){
                s[0] = getX()-1;
                s[1] = getY()+getK();
                options.add(s);
                s[0] = 321;
                s[1] = 321;
                options.add(s);
            }
            if(getX() != 7){
                s[0] = getX()+1;
                s[1] = getY()+getK();
                System.out.println("::: " + s[0] + " " + s[1]);
                options.add(s);
                System.out.println(options.get(4)[0]);
                s = stopper();
                System.out.println("::: " + s[0] + " " + s[1]);
                options.add(s);
                System.out.println(options.get(5)[0]);
            }
        }
        for(int i = 0; i < options.size(); i++){
            System.out.println("Pawn option " + i + ": " + options.get(i)[0] + " , " + options.get(i)[1]);
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
