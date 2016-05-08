import controller.Controller;
import model.Model;
import view.CanvasObserver;
import view.View;

/**
 * Created by Peonsson on 03/05/16.
 */
public class Main {

    /*
        A simple main method
    */
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        CanvasObserver observer = new CanvasObserver();

        Controller controller = new Controller(view, model, observer);
    }
}