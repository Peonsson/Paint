package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

/**
 * Created by Peonsson on 03/05/16.
 */
public class Model extends Observable {

    private ArrayList<Shape> shapes;
    private Stack<Command> commandStack;
    private Stack<Command> undoCommandStack;

    public Model() {
        this.shapes = new ArrayList<>();
        this.commandStack = new Stack<>();
        this.undoCommandStack = new Stack<>();
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
        setChanged();
        notifyObservers();
    }

    public Stack<Command> getCommandStack() {
        return commandStack;
    }

    public void setCommandStack(Stack<Command> commandStack) {
        this.commandStack = commandStack;
    }

    public Stack<Command> getUndoCommandStack() {
        return undoCommandStack;
    }

    public void setUndoCommandStack(Stack<Command> undoCommandStack) {
        this.undoCommandStack = undoCommandStack;
    }

    public void addShape(Shape shape) {
        // Vi ska skapa ett command -> sätta in i undo listan -> och köra command
        // storeAndExecute (rensar redoStack pushar undoStacken -> execute command)

        AddCommand command = new AddCommand(shapes, shape);
        undoCommandStack.push(command);
        command.doCommand();

        update();
    }

    public void modifyShape(Shape shape, int x, int y, int width, int height) {

        shape.setX1(x);
        shape.setY1(y);
        shape.setX2(width);
        shape.setY2(height);

        update();
    }

    public void modifyShape(Shape shape, int x, int y) {

        shape.setX1(x);
        shape.setY1(y);

        update();
    }

    public void modifyShape(Shape shape, Color color) {
        shape.setColor(color);
        update();
    }

    public void modifyShape(Shape shape, Boolean isFilled) {
        shape.setFilled(isFilled);
        update();
    }

    public void modifyShape(Shape selectedShape, int thickness) {
        selectedShape.setThickness(thickness);

        update();
    }

    public void update() {
        setChanged();
        notifyObservers();
    }
}