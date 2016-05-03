import controller.Listeners;
import view.Paint;

import javax.swing.*;

/**
 * Created by Peonsson on 03/05/16.
 */
public class Main {

    /*
        A simple main method
    */
    public static void main(String[] args) {
        Paint paint = new Paint();
        Listeners listeners = new Listeners(paint);
        paint.setTitle("Paint");
        paint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paint.setLocationRelativeTo(null);
        paint.pack();
        paint.setVisible(true);
    }
}