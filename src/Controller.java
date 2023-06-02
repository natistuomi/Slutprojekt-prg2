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
                    System.out.println("1");
                }
                else if(model.checkIfSquareIsOption(view.getSelected(), view.getOptions())){
                    System.out.println("2");
                    System.out.println("From: " + view.getChosen()[0] + "," + view.getChosen()[1] + " To: " + view.getSelected()[0] + "," + view.getSelected()[1]);
                    view.setSquares(model.move(view.getChosen(), view.getSelected()));
                    i = 1;
                    view.setMoves("" + model.getMoves());
                    view.setOptions(model.emptyOptions());
                }
                else{
                    System.out.println("3");
                    view.setOptions(model.emptyOptions());
                }
            }
        }
        //model.sendScore(view.get(players));
    }

    public static void main(String[] args) {
        Model m = new Model();
        View v = new View(m.getSquare());
        Controller c = new Controller(m, v);
    }
}
