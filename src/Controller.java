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
        //while(m.bothKingsAlive()){
        while(v.isNoChange()){}
        v.resetNoChange();
        if(m.checkIfSquareIsViable(v.getSelected())){
            v.setOptions(m.getOptions(v.getSelected()));
        }
        //}
        //First player chooses own occupied square
        //First player sees options
        //First player chooses option
        //Piece moves
        //Repeat until a king is dead
    }
}
