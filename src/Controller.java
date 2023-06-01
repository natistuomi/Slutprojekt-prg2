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
        playGame();
    }

    public void playGame(){
        while(model.bothKingsAlive()){
            //player's turn loop
            while(view.isNoChange()){}
            view.resetNoChange();
            if(model.checkIfSquareIsViable(view.getSelected())){
                view.setOptions(model.getOptions(view.getSelected()));
            }
            //else if false... check if move option
                // piece moves (removed from square, moved to square and possible delete of other piece)
                // model.moveDone();
                // view.setMoves(m.getMoves);
        }
        //model.sendScore(view.get(players), model.getWinner());
    }

    public static void main(String[] args) {
        Model m = new Model();
        View v = new View(m.getSquare());
        Controller c = new Controller(m, v);
    }
}
