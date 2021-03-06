import controller.Controller;
import model.Model;
import model.ShapeCache;
import view.View;

/**
 * Created by Peonsson on 03/05/16.
 */
public class Main {

    /*
        A simple main method
    */
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view, model);
    }
}