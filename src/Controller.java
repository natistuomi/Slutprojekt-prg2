import java.awt.*;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setVisible(true);
            }
        });
        view.start();
    }

    public static void main(String[] args) {
        Model m = new Model();
        View v = new View(m.getSquare());
        Controller c = new Controller(m, v);
        while(m.bothKingsAlive()){
            //player's turn loop
            while(v.isNoChange()){}
            v.resetNoChange();
            if(m.checkIfSquareIsViable(v.getSelected())){
                v.setOptions(m.getOptions(v.getSelected()));
            }
            //else if false... check if move option
                // piece moves (removed from square, moved to square and possible delete of other piece)
                // m.moveDone();
                // v.setMoves(m.getMoves);
        }
        //m.sendScore(v.get(players), m.getWinner());
    }
}
