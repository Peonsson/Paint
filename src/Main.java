import controller.Controller;
import model.Model;
import view.View;

/**
 * Created by Peonsson on 03/05/16.
 */
public class Main {

    /*
        A simple main method
    */
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        new Controller(view, model);
    }
}