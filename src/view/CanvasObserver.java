package view;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Xsnud on 2016-05-08.
 */
public class CanvasObserver extends JPanel implements Observer {

    /*
        Members
     */
    private Controller controller;

    /*
        Constructor
     */
    public CanvasObserver() {
        System.out.println("created CanvasObserver");
    }

    /*
        Methods
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("are we here? is controller null?");

        if(controller == null)
            return;

        System.out.println("size: " + controller.getShapes().size());

        for (model.Shape shape : controller.getShapes())
            shape.draw(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.validate();
        this.repaint();
    }

    /*
        Getters and setters
     */
    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}