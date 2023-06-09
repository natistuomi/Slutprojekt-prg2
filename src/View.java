import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class View extends Canvas implements Runnable{
    private Thread thread;
    int fps = 30;
    private boolean isRunning;
    // Skapa en buffrad grafik så att vi kan rita bilder i förväg, bättre än dbg från tidigare
    private BufferStrategy bs;
    // Storleken på bilden
    private final int height = 900;
    private final int width = 640;
    // Variabler gör det lättare att placera saker
    private int chessStartY = 60;
    private int chessEndY = 700;
    private int firstSquareX = 40;
    private int firstSquareY = 70;
    private Square[][] square = new Square[8][8];
    private String[] player = new String[2];
    private int stage = 0;
    private String currentPlayer = "white";
    private String moves = "0";
    private int currentX = 2;
    private int currentY = 62;
    private ArrayList<int[]> options = new ArrayList<int[]>();
    private int[] selected = new int[2];
    private int[] chosen = new int[2];
    private boolean noChange = true;


    /**
     * Skapa ett fönster och lägg in grafiken i det.
     */
    public View(Square[][] sq) {
        player[0] = "";
        player[1] = "";
        chosen[0] = 8;
        chosen[1] = 8;
        square = sq;
        JFrame frame = new JFrame("Chess");
        this.setSize(width, height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // Börja med animationen avslagen
        isRunning = false;
        // Lägg till en keyListener
        this.addKeyListener(new KL());
        this.requestFocus();
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double deltaT = 1000.0 / fps;
        long lastTime = System.currentTimeMillis();
        while (isRunning) {
            long now = System.currentTimeMillis();
            if (now - lastTime > deltaT) {
                lastTime = now;
            }
            paint();
        }
        stop();
    }

    /**
     * Nu gör vi en egen paint. Skapa en bufferStrategy så att vi får flera skärmar att jobba på, Java sköter det åt oss
     */
    public void paint() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        // Om vi inte suddar allt ritar vi över det som redan fanns. Ibland kan det vara bättre att bara sudda en bit
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        draw(g);
        // Det här byter skärm
        g.dispose();
        bs.show();
    }


    public void setSquares(Square[][] sq){
        square = sq;
    }

    public void setCurrentPlayer() {
        if(Integer.parseInt(moves)%2 == 0){
            currentPlayer = "white";
        }
        else{
            currentPlayer = "black";
        }
    }

    public void setMoves(String moves) {
        this.moves = moves;
        setCurrentPlayer();
    }

    /**
     * Rita ut alla saker. Ordningen är viktig eftersom vi kan rita saker på andra saker.
     *
     * @param g grafiken
     */
    private void draw(Graphics g) {
        if(stage < 2){
            drawQuestion(g);
        }
        else{
            drawChessBoard(g);
            drawPieces(g);
            drawText(g);
            drawOptions(g);
            drawCurrent(g);
            drawChosen(g);
        }
    }

    public void drawQuestion(Graphics g){
        Font stringFont = new Font( "SansSerif", Font.PLAIN, 24 );
        g.setFont( stringFont );
        g.setColor(Color.black);
        g.drawString("What is player " + (stage+1) + "'s name?", 20, 200);
        g.drawString(player[stage], 20, 300);
    }

    public void drawChessBoard(Graphics g){
        g.setColor(Color.black);
        int x = 0;
        int y = chessStartY;
        g.drawRect(x, y-1, 640, 641);
        for(int i = 0; i < 8; i++){
            int n = 0;
            for( int h = 0; h < 8; h++){
                if(i % 2 == 0 && n % 2 == 0){
                    g.setColor(Color.white);
                    g.fillRect(x, y, 80, 80);
                }
                else if(i % 2 == 1 && n % 2 == 1){
                    g.setColor(Color.white);
                    g.fillRect(x, y, 80, 80);
                }
                else{
                    g.setColor(Color.black);
                    g.fillRect(x, y, 80, 80);
                }
                n += 1;
                x += 80;
            }
            x = 0;
            y += 80;
        }

    }

    public void drawPieces(Graphics g){
        for(int i = 0; i < 8; i++){
            for(int h = 0; h < 8; h++){
                int x = firstSquareX + (i * 80);
                int y = firstSquareY + (h * 80);
                String colour = square[i][h].getOccupiedBy();
                if(square[i][h].getOccupiedBy().equals("empty")){}
                else if(square[i][h].getPiece().equals("pawn")){drawPawn(g, x, y, colour);}
                else if(square[i][h].getPiece().equals("rook")){drawRook(g, x, y, colour);}
                else if(square[i][h].getPiece().equals("knight")){drawKnight(g, x, y, colour);}
                else if(square[i][h].getPiece().equals("bishop")){drawBishop(g, x, y, colour);}
                else if(square[i][h].getPiece().equals("queen")){drawQueen(g, x, y, colour);}
                else if(square[i][h].getPiece().equals("king")){drawKing(g, x, y, colour);}
            }
        }
    }

    public void drawPawn(Graphics g, int x, int y, String colour){
        setColour(g, colour, "");
        g.fillPolygon(new int[] {x-10, x, x+10}, new int[] {y+60, y+15, y+60}, 3);
        setColour(g, colour, "inverse");
        g.drawPolygon(new int[] {x-10, x, x+10}, new int[] {y+60, y+15, y+60}, 3);
        setColour(g, colour, "");
        g.fillOval(x-10, y+15, 20, 20);
        setColour(g, colour, "inverse");
        g.drawOval(x-10, y+15, 20, 20);

    }

    public void drawRook(Graphics g, int x, int y, String colour){
        setColour(g, colour, "");
        g.fillRect(x-10, y+30, 20, 30);
        setColour(g, colour, "inverse");
        g.drawRect(x-10, y+20, 20, 40);
        setColour(g, colour, "");
        g.fillRect(x-20, y+10, 40, 20);
        setColour(g, colour, "inverse");
        g.drawRect(x-20, y+10, 40, 20);
        setColour(g, colour, "");
        g.fillRect(x-5, y, 10, 10);
        g.fillRect(x-20, y, 10, 10);
        g.fillRect(x+10, y, 10, 10);
        setColour(g, colour, "inverse");
        g.drawRect(x-5, y, 10, 10);
        g.drawRect(x-20, y, 10, 10);
        g.drawRect(x+10, y, 10, 10);
    }

    public void drawBishop(Graphics g, int x, int y, String colour){
        setColour(g, colour, "");
        g.fillPolygon(new int[] {x-10, x, x+10}, new int[] {y+60, y+30, y+60}, 3);
        setColour(g, colour, "inverse");
        g.drawPolygon(new int[] {x-10, x, x+10}, new int[] {y+60, y+30, y+60}, 3);
        setColour(g, colour, "");
        g.fillPolygon(new int[] {x-10, x, x+10}, new int[] {y+50, y+20, y+50}, 3);
        setColour(g, colour, "inverse");
        g.drawPolygon(new int[] {x-10, x, x+10}, new int[] {y+50, y+20, y+50}, 3);
        setColour(g, colour, "");
        g.fillOval(x-10, y, 20, 20);
        setColour(g, colour, "inverse");
        g.drawOval(x-10, y, 20, 20);
    }

    public void drawKnight(Graphics g, int x, int y, String colour){
        setColour(g, colour, "");
        g.fillPolygon(new int[] {x-15, x, x+15}, new int[] {y+60, y+10, y+60}, 3);
        setColour(g, colour, "inverse");
        g.drawPolygon(new int[] {x-15, x, x+15}, new int[] {y+60, y+10, y+60}, 3);
        setColour(g, colour, "");
        g.fillPolygon(new int[] {x-5, x+3, x+10}, new int[] {y+20, y, y+20}, 3);
        setColour(g, colour, "inverse");
        g.drawPolygon(new int[] {x-5, x+3, x+10}, new int[] {y+20, y, y+20}, 3);
        setColour(g, colour, "");
        g.fillRect(x-15, y+20, 15, 40);
        setColour(g, colour, "inverse");
        g.drawRect(x-15, y+20, 15, 40);
        setColour(g, colour, "");
        g.fillPolygon(new int[] {x-15, x+15, x+25}, new int[] {y+20, y+5, y+30}, 3);
        setColour(g, colour, "inverse");
        g.drawPolygon(new int[] {x-15, x+15, x+25}, new int[] {y+20, y+5, y+30}, 3);
    }

    public void drawQueen(Graphics g, int x, int y, String colour){
        setColour(g, colour, "");
        g.fillPolygon(new int[] {x-10, x, x+10}, new int[] {y+60, y+20, y+60}, 3);
        setColour(g, colour, "inverse");
        g.drawPolygon(new int[] {x-10, x, x+10}, new int[] {y+60, y+20, y+60}, 3);
        setColour(g, colour, "");
        g.fillOval(x-4, y, 8, 10);
        setColour(g, colour, "inverse");
        g.drawOval(x-4, y, 8, 10);
        setColour(g, colour, "");
        g.fillOval(x-10, y+5, 20, 30);
        setColour(g, colour, "inverse");
        g.drawOval(x-10, y+5, 20, 30);
        setColour(g, colour, "");
        g.fillRect(x-10, y+30, 20, 5);
        setColour(g, colour, "inverse");
        g.drawRect(x-10, y+30, 20, 5);
    }

    public void drawKing(Graphics g, int x, int y, String colour){
        setColour(g, colour, "");
        g.fillPolygon(new int[] {x-13, x, x+13}, new int[] {y+60, y+10, y+60}, 3);
        setColour(g, colour, "inverse");
        g.drawPolygon(new int[] {x-13, x, x+13}, new int[] {y+60, y+10, y+60}, 3);
        setColour(g, colour, "");
        g.fillRect(x-1, y, 2, 20);
        setColour(g, colour, "inverse");
        g.drawRect(x-1, y, 2, 20);
        setColour(g, colour, "");
        g.fillRect(x-6, y+4, 12, 2);
        setColour(g, colour, "inverse");
        g.drawRect(x-6, y+4, 12, 2);
        setColour(g, colour, "");
        g.fillRect(x-10, y+20, 20, 5);
        setColour(g, colour, "inverse");
        g.drawRect(x-10, y+20, 20, 5);
        setColour(g, colour, "");
        g.fillRect(x-8, y+10, 16, 10);
        setColour(g, colour, "inverse");
        g.drawRect(x-8, y+10, 16, 10);
    }

    public void drawText(Graphics g){
        Font stringFont = new Font( "SansSerif", Font.PLAIN, 24 );
        g.setFont( stringFont );
        g.setColor(Color.black);
        int i;
        if(currentPlayer.equals("white")){
            i = 0;
        }else{
            i = 1;
        }
        g.drawString("Currently " + player[i] + "'s turn (" + currentPlayer + ")", 10, 40);
        g.drawString("Moves: " + moves, 475, 40);
    }

    public void drawCurrent(Graphics g){
        g.setColor(Color.blue);
        g.drawRect(currentX, currentY, 76, 76);
        g.drawRect(currentX+1, currentY+1, 74, 74);
        g.drawRect(currentX+2, currentY+2, 72, 72);
    }

    public void drawOptions(Graphics g){
        g.setColor(Color.red);
        for(int i = 0; i < options.size(); i++){
            int newX = firstSquareX + (options.get(i)[0]*80) - 30;
            int newY = firstSquareY + (options.get(i)[1]*80);
            g.drawOval(newX, newY, 60, 60);
            g.drawOval(newX+1, newY+1, 58, 58);
            g.drawOval(newX+2, newY+2, 56, 56);
        }
    }

    public void drawChosen(Graphics g){
        g.setColor(Color.red);
        g.drawRect(2 + (80*chosen[0]), 62 + (80*chosen[1]), 76, 76);
        g.drawRect(3 + (80*chosen[0]), 63 + (80*chosen[1]), 74, 74);
        g.drawRect(4 + (80*chosen[0]), 64 + (80*chosen[1]), 72, 72);
    }


















    private void setColour(Graphics g, String colour, String inv){
        if(colour.equals("white") && inv.equals("")){g.setColor(Color.white);}
        else if(colour.equals("white")){g.setColor(Color.black);}
        else if(colour.equals("black") && inv.equals("inverse")){g.setColor(Color.white);}
        else{g.setColor(Color.black);}
    }





    /**
     * Nu kan vi starta vårt program
     * Skapa först en JFrame och en canvas, starta sedan tråden som sköter animationen.
     */
    //Tas bort senare och flyttas till controller





    //Fylls i senare!
    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if(keyEvent.getKeyChar() == 'a'){
                if(stage == 2 && currentX != 2){currentX -= 80;}
                else{player[stage] += "a";}
            }if(keyEvent.getKeyChar() == 'b'){
                if(stage == 0 || stage == 1){player[stage] += "b";}
            }if(keyEvent.getKeyChar() == 'c'){
                if(stage == 0 || stage == 1){player[stage] += "c";}
            }if(keyEvent.getKeyChar() == 'd'){
                if(stage == 2 && currentX != 562){currentX += 80;}
                else{player[stage] += "d";}
            }if(keyEvent.getKeyChar() == 'e'){
                if(stage == 0 || stage == 1){player[stage] += "e";}
            }if(keyEvent.getKeyChar() == 'f'){
                if(stage == 0 || stage == 1){player[stage] += "f";}
            }if(keyEvent.getKeyChar() == 'g'){
                if(stage == 0 || stage == 1){player[stage] += "g";}
            }if(keyEvent.getKeyChar() == 'h'){
                if(stage == 0 || stage == 1){player[stage] += "h";}
            }if(keyEvent.getKeyChar() == 'i'){
                if(stage == 0 || stage == 1){player[stage] += "i";}
            }if(keyEvent.getKeyChar() == 'j'){
                if(stage == 0 || stage == 1){player[stage] += "j";}
            }if(keyEvent.getKeyChar() == 'k'){
                if(stage == 0 || stage == 1){player[stage] += "k";}
            }if(keyEvent.getKeyChar() == 'l'){
                if(stage == 0 || stage == 1){player[stage] += "l";}
            }if(keyEvent.getKeyChar() == 'm'){
                if(stage == 0 || stage == 1){player[stage] += "m";}
            }if(keyEvent.getKeyChar() == 'n'){
                if(stage == 0 || stage == 1){player[stage] += "n";}
            }if(keyEvent.getKeyChar() == 'o'){
                if(stage == 0 || stage == 1){player[stage] += "o";}
            }if(keyEvent.getKeyChar() == 'p'){
                if(stage == 0 || stage == 1){player[stage] += "p";}
            }if(keyEvent.getKeyChar() == 'q'){
                if(stage == 0 || stage == 1){player[stage] += "q";}
            }if(keyEvent.getKeyChar() == 'r'){
                if(stage == 0 || stage == 1){player[stage] += "r";}
            }if(keyEvent.getKeyChar() == 's'){
                if(stage == 2 && currentY != 622){currentY += 80;}
                else{player[stage] += "s";}
            }if(keyEvent.getKeyChar() == 't'){
                if(stage == 0 || stage == 1){player[stage] += "t";}
            }if(keyEvent.getKeyChar() == 'u'){
                if(stage == 0 || stage == 1){player[stage] += "u";}
            }if(keyEvent.getKeyChar() == 'v'){
                if(stage == 0 || stage == 1){player[stage] += "v";}
            }if(keyEvent.getKeyChar() == 'w'){
                if(stage == 2 && currentY != 62){currentY -= 80;}
                else{player[stage] += "w";}
            }if(keyEvent.getKeyChar() == 'x'){
                if(stage == 0 || stage == 1){player[stage] += "x";}
            }if(keyEvent.getKeyChar() == 'y'){
                if(stage == 0 || stage == 1){player[stage] += "y";}
            }if(keyEvent.getKeyChar() == 'z'){
                if(stage == 0 || stage == 1){player[stage] += "z";}
            }if(keyEvent.getKeyChar() == ' '){
                if(stage == 0 || stage == 1){stage += 1;}
                else{
                    makeSelected();
                    noChange = false;
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent keyEvent) {
        }
    }

    public void setStage(int a){
        stage = a;
    }

    public String[] getPlayer() {
        return player;
    }

    public void setOptions(ArrayList<int[]> options) {
        this.options = options;
        if(options.size() == 0){
            chosen = new int[]{8, 8};
        }
        else{
            chosen = selected;
            System.out.println("Chosen: " + chosen[0] + "," + chosen[1]);
        }
    }

    public int[] getSelected() {
        return selected;
    }

    public void resetNoChange(){noChange = true;}

    public void makeSelected(){
        int a = currentX/80;
        int b = currentY/80;
        selected[0] = a;
        selected[1] = b;
    }

    public boolean isNoChange() {
        return noChange;
    }

    public ArrayList<int[]> getOptions() {
        return options;
    }

    public int[] getChosen() {
        System.out.println("Get chosen: " + chosen[0] + "," + chosen[1]);
        return chosen;
    }
}
