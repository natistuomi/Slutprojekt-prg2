import java.util.ArrayList;

public class Model {
    private Square[][] square = new Square[8][8];
    private ArrayList<Piece> white = new ArrayList<Piece>();
    private ArrayList<Piece> black = new ArrayList<Piece>();
    private int moves = 0;
    private String currentColour = "white";

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

    public void setPieces(){
        String colour;
        int x;
        int y;
        for(int g = 0; g < 2; g++){
            if(g == 0){colour = "white";}
            else{colour = "black";}
            for(int i = 0; i < 8; i++){
                x = i;
                if(g == 0){
                    y = 1;
                    white.add(new Pawn(colour, x, y));
                }
                else{
                    y = 6;
                    black.add(new Pawn(colour, x, y));
                }
                square[x][y].setOccupiedBy(colour, "pawn");
            }
            if (g == 0){
                y = 0;
                white.add(new Rook(colour, 0, y));
                square[0][y].setOccupiedBy(colour, "rook");
                white.add(new Knight(colour, 1, y));
                square[1][y].setOccupiedBy(colour, "knight");
                white.add(new Bishop(colour, 2, y));
                square[2][y].setOccupiedBy(colour, "bishop");
                white.add(new King(colour, 3, y));
                square[3][y].setOccupiedBy(colour, "king");
                white.add(new Queen(colour, 4, y));
                square[4][y].setOccupiedBy(colour, "queen");
                white.add(new Bishop(colour, 5, y));
                square[5][y].setOccupiedBy(colour, "bishop");
                white.add(new Knight(colour, 6, y));
                square[6][y].setOccupiedBy(colour, "knight");
                white.add(new Rook(colour, 7, y));
                square[7][y].setOccupiedBy(colour, "rook");
            }
            else{
                y = 7;
                black.add(new Rook(colour, 0, y));
                square[0][y].setOccupiedBy(colour, "rook");
                black.add(new Knight(colour, 1, y));
                square[1][y].setOccupiedBy(colour, "knight");
                black.add(new Bishop(colour, 2, y));
                square[2][y].setOccupiedBy(colour, "bishop");
                black.add(new King(colour, 3, y));
                square[3][y].setOccupiedBy(colour, "king");
                black.add(new Queen(colour, 4, y));
                square[4][y].setOccupiedBy(colour, "queen");
                black.add(new Bishop(colour, 5, y));
                square[5][y].setOccupiedBy(colour, "bishop");
                black.add(new Knight(colour, 6, y));
                square[6][y].setOccupiedBy(colour, "knight");
                black.add(new Rook(colour, 7, y));
                square[7][y].setOccupiedBy(colour, "rook");
            }
        }
    }

    public ArrayList<int[]> cleanOptions(ArrayList<int[]> options){
        ArrayList<int[]> clean = new ArrayList<int[]>();
        int[] s = new int[2];
        int q = 1;
        for(int i = 0; i < options.size()-1; i++){
            s[0] = options.get(i)[0];
            s[1] = options.get(i)[1];
            if(s[0] == 321){q = 0;}
            else if(s[0] == 123){q = 1;}
            else if(q == 0){
                if(square[s[0]][s[1]].getOccupiedBy().equals("empty")){}
                else if(square[s[0]][s[1]].getOccupiedBy().equals(currentColour)){}
                else{clean.add(s);}
            }
            else if(q == 1 && square[s[0]][s[1]].getOccupiedBy().equals("empty")){
                clean.add(s);
            }
            else{
                q = -1;
            }
        }
        return clean;
    }

    //Adds to move count:
    public void moveDone(){
        moves += 1;
    }

    public void setCurrentColour(String currentColour) {
        this.currentColour = currentColour;
    }

    public Square[][] getSquare() {
        return square;
    }
}
