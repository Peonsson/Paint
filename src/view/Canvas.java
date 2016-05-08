package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Xsnud on 2016-05-08.
 */
public class Canvas extends JPanel {

    private Controller controller;

    public Canvas(Controller controller) {
        this.controller = controller;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("size: " + controller.getShapes().size());

        for (model.Shape shape : controller.getShapes())
            shape.draw(g);
    }
}
