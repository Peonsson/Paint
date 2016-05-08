package controller;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Xsnud on 2016-05-08.
 */
public class MyObserver implements java.util.Observer {

    /*
        Members
     */
    private Controller controller;

    /*
        Constructor
     */
    public MyObserver() {
        System.out.println("created MyObserver");
    }

    /*
        Methods
     */
    @Override
    public void update(Observable o, Object arg) {
        //TODO: repaint view
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