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
        while(model.bothKingsAlive()) {
            int i = 0;
            while (i == 0) {
                while (view.isNoChange()) {
                }
                view.resetNoChange();
                if (model.checkIfSquareIsViable(view.getSelected())) {
                    view.setOptions(model.getOptions(view.getSelected()));
                }
                else if(model.checkIfSquareIsOption(view.getSelected(), view.getOptions())){
                    model.move(view.getChosen(), view.getSelected());
                    i = 1;
                    view.setMoves("" + model.getMoves());
                    view.setOptions(model.emptyOptions());
                }
                else{
                    view.setOptions(model.emptyOptions());
                }
            }
        }
        //model.sendScore(view.get(players), model.getWinner());
    }

    public static void main(String[] args) {
        Model m = new Model();
        View v = new View(m.getSquare());
        Controller c = new Controller(m, v);
    }
}
