public class Test {
    static Square[][] square = new Square[8][8];

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

        // create pawn and show pawn and square's qualities as well as show pawn's moving pattern
        Pawn pawn = new Pawn("white", square[5][6], 5, 6);
        square[5][6].setOccupiedBy("white");
        System.out.println(pawn.toString());
        System.out.println(pawn.getOptions());


    }
}
