import java.util.ArrayList;

public class Model {
    private Square[][] square = new Square[8][8];
    private ArrayList<Pawn> whitePawn = new ArrayList<Pawn>();
    private ArrayList<Pawn> blackPawn = new ArrayList<Pawn>();
    private ArrayList<Rook> whiteRook = new ArrayList<Rook>();
    private ArrayList<Rook> blackRook = new ArrayList<Rook>();
    private ArrayList<Knight> whiteKnight = new ArrayList<Knight>();
    private ArrayList<Knight> blackKnight = new ArrayList<Knight>();
    private ArrayList<Bishop> whiteBishop = new ArrayList<Bishop>();
    private ArrayList<Bishop> blackBishop = new ArrayList<Bishop>();
    private ArrayList<Queen> whiteQueen = new ArrayList<Queen>();
    private ArrayList<Queen> blackQueen = new ArrayList<Queen>();
    private ArrayList<King> whiteKing = new ArrayList<King>();
    private ArrayList<King> blackKing = new ArrayList<King>();
    private int moves = 0;
    private String currentColour = "white";
    private int winner = -1;

    public Model() {
        createBoard();
        setPieces();
    }

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
                    whitePawn.add(new Pawn(colour, x, y));
                }
                else{
                    y = 6;
                    blackPawn.add(new Pawn(colour, x, y));
                }
                square[x][y].setOccupiedBy(colour, "pawn");
            }
            if (g == 0){
                y = 0;
                whiteRook.add(new Rook(colour, 0, y));
                square[0][y].setOccupiedBy(colour, "rook");
                whiteKnight.add(new Knight(colour, 1, y));
                square[1][y].setOccupiedBy(colour, "knight");
                whiteBishop.add(new Bishop(colour, 2, y));
                square[2][y].setOccupiedBy(colour, "bishop");
                whiteKing.add(new King(colour, 3, y));
                square[3][y].setOccupiedBy(colour, "king");
                whiteQueen.add(new Queen(colour, 4, y));
                square[4][y].setOccupiedBy(colour, "queen");
                whiteBishop.add(new Bishop(colour, 5, y));
                square[5][y].setOccupiedBy(colour, "bishop");
                whiteKnight.add(new Knight(colour, 6, y));
                square[6][y].setOccupiedBy(colour, "knight");
                whiteRook.add(new Rook(colour, 7, y));
                square[7][y].setOccupiedBy(colour, "rook");
            }
            else{
                y = 7;
                blackRook.add(new Rook(colour, 0, y));
                square[0][y].setOccupiedBy(colour, "rook");
                blackKnight.add(new Knight(colour, 1, y));
                square[1][y].setOccupiedBy(colour, "knight");
                blackBishop.add(new Bishop(colour, 2, y));
                square[2][y].setOccupiedBy(colour, "bishop");
                blackKing.add(new King(colour, 3, y));
                square[3][y].setOccupiedBy(colour, "king");
                blackQueen.add(new Queen(colour, 4, y));
                square[4][y].setOccupiedBy(colour, "queen");
                blackBishop.add(new Bishop(colour, 5, y));
                square[5][y].setOccupiedBy(colour, "bishop");
                blackKnight.add(new Knight(colour, 6, y));
                square[6][y].setOccupiedBy(colour, "knight");
                blackRook.add(new Rook(colour, 7, y));
                square[7][y].setOccupiedBy(colour, "rook");
            }
        }
    }

    public ArrayList<int[]> cleanOptions(ArrayList<int[]> options){
        ArrayList<int[]> clean = new ArrayList<int[]>();
        int[] s = new int[2];
        int q = 1;
        for(int i = 0; i < options.size(); i++){
            s[0] = options.get(i)[0];
            s[1] = options.get(i)[1];
            if(s[0] == 321){q = 0;}
            else if(s[0] == 123){q = 1;}
            else if(q == 0){
                if(square[s[0]][s[1]].getOccupiedBy().equals("empty")){}
                else if(square[s[0]][s[1]].getOccupiedBy().equals(currentColour)){}
                else{
                    clean.add(s);
                    s = new int[2];
                }
            }
            else if(q == 1 && square[s[0]][s[1]].getOccupiedBy().equals("empty")){
                clean.add(s);
                s = new int[2];
            }
            else{
                q = -1;
            }
        }
        return clean;
    }

    public ArrayList<int[]> getOptions(int[] selected){
        int a = selected[0];
        int b = selected[1];
        ArrayList<int[]> options = new ArrayList<int[]>();
        if(currentColour.equals("white")){
            if(square[a][b].getPiece().equals("pawn")){
                for(int i = 0; i < whitePawn.size(); i++){
                    if(whitePawn.get(i).getX()==a && whitePawn.get(i).getY()==b){
                        options = whitePawn.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("rook")){
                for(int i = 0; i < whiteRook.size(); i++){
                    if(whiteRook.get(i).getX()==a && whiteRook.get(i).getY()==b){
                        options = whiteRook.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("knight")){
                for(int i = 0; i < whiteKnight.size(); i++){
                    if(whiteKnight.get(i).getX()==a && whiteKnight.get(i).getY()==b){
                        options = whiteKnight.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("bishop")){
                for(int i = 0; i < whiteBishop.size(); i++){
                    if(whiteBishop.get(i).getX()==a && whiteBishop.get(i).getY()==b){
                        options = whiteBishop.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("king")){
                for(int i = 0; i < whiteKing.size(); i++){
                    if(whiteKing.get(i).getX()==a && whiteKing.get(i).getY()==b){
                        options = whiteKing.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("queen")){
                for(int i = 0; i < whiteQueen.size(); i++){
                    if(whiteQueen.get(i).getX()==a && whiteQueen.get(i).getY()==b){
                        options = whiteQueen.get(i).getOptions();
                    }
                }
            }
        }
        else{
            if(square[a][b].getPiece().equals("pawn")){
                for(int i = 0; i < blackPawn.size(); i++){
                    if(blackPawn.get(i).getX()==a && blackPawn.get(i).getY()==b){
                        options = blackPawn.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("rook")){
                for(int i = 0; i < blackRook.size(); i++){
                    if(blackRook.get(i).getX()==a && blackRook.get(i).getY()==b){
                        options = blackRook.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("knight")){
                for(int i = 0; i < blackKnight.size(); i++){
                    if(blackKnight.get(i).getX()==a && blackKnight.get(i).getY()==b){
                        options = blackKnight.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("bishop")){
                for(int i = 0; i < blackBishop.size(); i++){
                    if(blackBishop.get(i).getX()==a && blackBishop.get(i).getY()==b){
                        options = blackBishop.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("king")){
                for(int i = 0; i < blackKing.size(); i++){
                    if(blackKing.get(i).getX()==a && blackKing.get(i).getY()==b){
                        options = blackKing.get(i).getOptions();
                    }
                }
            }
            else if(square[a][b].getPiece().equals("queen")){
                for(int i = 0; i < blackQueen.size(); i++){
                    if(blackQueen.get(i).getX()==a && blackQueen.get(i).getY()==b){
                        options = blackQueen.get(i).getOptions();
                    }
                }
            }
        }
        options = cleanOptions(options);
        return options;
    }


    //Adds to move count and changes player:
    public void moveDone(){
        moves += 1;
        setCurrentColour();
    }

    public void setCurrentColour() {
        if(moves%2 == 0){
            currentColour = "white";
        }
        else{
            currentColour = "black";
        }
    }

    public boolean bothKingsAlive(){
        return whiteKing.size()!=0 && blackKing.size()!=0;
    }

    public Square[][] getSquare() {
        return square;
    }

    public boolean checkIfSquareIsViable(int[] selected){
        return square[selected[0]][selected[1]].getOccupiedBy().equals(currentColour);
    }

    public int getMoves() {
        return moves;
    }

    public int getWinner() {
        return winner;
    }

    public boolean checkIfSquareIsOption(int[] selected, ArrayList<int[]> options){
        for(int i = 0; i < options.size(); i++){
            if(options.get(i)[0] == selected[0] && options.get(i)[1] == selected[1]){
                return true;
            }
        }
        return false;
    }

    public ArrayList<int[]> emptyOptions(){
        ArrayList<int[]> options = new ArrayList<int[]>();
        return options;
    }

    public void move(int[] from, int[] to){



        moveDone();
    }
}
