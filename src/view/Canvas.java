package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Xsnud on 2016-05-08.
 */
public class Canvas extends JPanel implements Observer {

    private Controller controller;

    public Canvas() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("painted component");
        if (controller != null) {
            System.out.println("controller not null");
            for (model.Shape shape : controller.getShapes())
                shape.draw(g);
            System.out.println("size: " + controller.getShapes().size());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
