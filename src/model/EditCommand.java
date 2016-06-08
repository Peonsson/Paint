package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by robin on 8/6/16.
 */
public class EditCommand implements Command {

    private Shape shape;
    private ArrayList<Shape> shapes;

    private int oldX;
    private int oldY;
    private int oldWidth;
    private int oldHeight;
    private boolean oldIsFilled;
    private Color oldColor;
    private int oldThickness;
    private boolean oldIsFirst;

    private int newX;
    private int newY;
    private int newWidth;
    private int newHeight;
    private boolean newIsFilled;
    private Color newColor;
    private int newThickness;
    private boolean newIsFirst;

    public EditCommand(ArrayList<Shape> shapes, Shape shape, int x, int y, int width, int height, boolean isFilled, Color color, int thickness, boolean first) {

        this.shapes = shapes;
        this.shape = shape;

        this.oldX = shape.getX1();
        this.oldY = shape.getY1();
        this.oldWidth = shape.getX2();
        this.oldHeight = shape.getY2();
        this.oldIsFilled = shape.isFilled;
        this.oldColor = shape.getColor();
        this.oldThickness = shape.getThickness();
        this.oldIsFirst = shape.isFirst();

        this.newX = x;
        this.newY = y;
        this.newWidth = width;
        this.newHeight = height;
        this.newIsFilled = shape.isFilled;
        this.newColor = color;
        this.newThickness = thickness;
        this.newIsFirst = first;
    }

    public EditCommand(ArrayList<Shape> shapes, Shape shape, int x, int y) {
        this.shapes = shapes;
        this.shape = shape;
        this.newX = x;
        this.newY = y;
    }

    @Override
    public void doCommand() {

        shape.setX1(newX);
        shape.setY1(newY);
        shape.setX2(newWidth);
        shape.setY2(newHeight);
        shape.setFilled(newIsFilled);
        shape.setColor(newColor);
        shape.setThickness(newThickness);
        shape.setFirst(newIsFirst);
    }

    @Override
    public void undoCommand() {

        shape.setX1(oldX);
        shape.setY1(oldY);
        shape.setX2(oldWidth);
        shape.setY2(oldHeight);
        shape.setFilled(oldIsFilled);
        shape.setColor(oldColor);
        shape.setThickness(oldThickness);
        shape.setFirst(oldIsFirst);
    }
}