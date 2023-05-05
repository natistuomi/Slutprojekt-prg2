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




    /**
     * Rita ut alla saker. Ordningen är viktig eftersom vi kan rita saker på andra saker.
     *
     * @param g grafiken
     */
    private void draw(Graphics g) {
        drawChessBoard(g);
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
