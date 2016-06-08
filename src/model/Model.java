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

        AddCommand command = new AddCommand(shapes, shape);
        undoCommandStack.push(command);
        command.doCommand();

        update();
    }

    public void modifyShape(Shape shape, int x, int y, int width, int height, Color color, boolean isFilled, int thickness, boolean isFirst) {
        System.out.println("adding to undo stack");
        EditCommand command = new EditCommand(shapes, shape, x, y, width, height, isFilled, color, thickness, isFirst);
        undoCommandStack.push(command);
        command.doCommand();
        update();
    }


    public void modifyShapeAnimation(Shape animationShape, int x, int y, int width, int height, Color color, boolean isFilled, int thickness, boolean isFirst) {

        EditCommand command = new EditCommand(shapes, animationShape, x, y, width, height, isFilled, color, thickness, isFirst);
        command.doCommand();
        update();
    }

//    public void modifyShape(Shape shape, int x, int y, int width, int height) {
//
//        EditCommand command = new EditCommand(shapes, shape, x, y, width, height);
//        undoCommandStack.push(command);
//        command.doCommand();
//        update();
//    }
//
//    public void modifyShape(Shape shape, int x, int y) {
//
//        EditCommand command = new EditCommand(shapes, shape, x, y);
//        undoCommandStack.push(command);
//        command.doCommand();
//
//        update();
//    }

//    public void modifyShape(Shape shape, Color color) {
//
//        shape.setColor(color);
//        update();
//    }
//
//    public void modifyShape(Shape shape, Boolean isFilled) {
//        shape.setFilled(isFilled);
//        update();
//    }
//
//    public void modifyShape(Shape selectedShape, int thickness) {
//        selectedShape.setThickness(thickness);
//
//        update();
//    }

    public void update() {
        setChanged();
        notifyObservers();
    }

    public void removeShape(Shape selectedShape) {
        RemoveCommand command = new RemoveCommand(shapes, selectedShape);
        undoCommandStack.push(command);
        command.doCommand();
        update();
    }
}