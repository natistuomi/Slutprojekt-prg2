public class Model {
    private Square[][] square = new Square[8][8];
    private int moves = 0;

    //Create board and set colours to all squares (occupiedBy = empty):
    public void createBoard(){
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
    }

    //Adds to move count:
    public void moveDone(){
        moves += 1;
    }
}
