import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

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


    /**
     * Skapa ett fönster och lägg in grafiken i det.
     */
    public View() {
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


    public void setSquares(){
        //Square[][] s ^^^
        //square = s;
        square[0][0] = new Square("white", "rook");
    }




    /**
     * Rita ut alla saker. Ordningen är viktig eftersom vi kan rita saker på andra saker.
     *
     * @param g grafiken
     */
    private void draw(Graphics g) {
        //setSquares();
        drawChessBoard(g);
        //drawPieces(g);'
        drawQueen(g, firstSquareX, firstSquareY, "white");
        drawQueen(g, firstSquareX+80, firstSquareY, "black");
        drawQueen(g, firstSquareX, firstSquareY+80, "white");
        drawQueen(g, firstSquareX+80, firstSquareY+80, "black");
        drawKing(g, firstSquareX+160, firstSquareY, "white");
        drawKing(g, firstSquareX+240, firstSquareY, "black");
        drawKing(g, firstSquareX+160, firstSquareY+80, "white");
        drawKing(g, firstSquareX+240, firstSquareY+80, "black");
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
                String colour = square[i][h].getColour();
                if(square[i][h].getOccupiedBy().equals("empty")){}
                else if(square[i][h].getPiece().equals("pawn")){
                    drawPawn(g, x, y, colour);
                }
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
    public static void main(String[] args) {
        View exempel = new View();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                exempel.setVisible(true);
            }
        });
        exempel.start();
    }



    //Fylls i senare!
    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if(keyEvent.getKeyChar() == 'a'){
            }
            if(keyEvent.getKeyChar() == 'd'){
            }
            if(keyEvent.getKeyChar() == 'w'){
            }
            if(keyEvent.getKeyChar() == 's'){
            }
            if(keyEvent.getKeyChar() == '0'){
            }
        }
        @Override
        public void keyReleased(KeyEvent keyEvent) {
        }
    }
}
