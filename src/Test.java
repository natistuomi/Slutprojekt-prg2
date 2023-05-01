import java.util.ArrayList;

public class Test {
    static Square[][] square = new Square[8][8];
    static String q = "";

    public static void main (String[] args){

        //Create board and set colours to all squares:
        for(int i = 0; i < 8; i++){
            int n = 0;
            for( int g = 0; g < 8; g++){
                if(i % 2 == 0 && n % 2 == 0){
                    square[i][g] = new Square("white");
                }
                else if(i % 2 == 1 && n % 2 == 1){
                    square[i][g] = new Square("white");
                }
                else{
                    square[i][g] = new Square("black");
                }
                n += 1;
                System.out.println("X: " + i + " Y: " + g + " = " + square[i][g].getColour());
            }
        }

        // create pawn and show qualities as well as pawn's moving pattern
        Pawn pawn = new Pawn("white", 5, 6);
        square[5][6].setOccupiedBy("white");
        System.out.println(pawn.toString());
        ArrayList<String> options = pawn.getOptions();
        System.out.println(options);
        checkSquareStatus(options.get(0));
        System.out.println(q);
        checkSquareStatus("5:6");
        System.out.println(q);

        Rook rook = new Rook("white", 0, 7);
        square[0][7].setOccupiedBy("white");
        System.out.println(rook.toString());
        options = rook.getOptions();
        System.out.println(options);

        // find a way to check viable options

    }



    public static void checkSquareStatus(String s){
        int x = Integer.parseInt(String.valueOf(s.charAt(0)));
        int y = Integer.parseInt(String.valueOf(s.charAt(2)));
        q = square[x][y].getOccupiedBy();
    }
}
