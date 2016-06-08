package model;

import java.util.ArrayList;

/**
 * Created by robin on 8/6/16.
 */
public class RemoveCommand implements Command {

    private ArrayList<Shape> shapes;
    private Shape shape;

    public RemoveCommand(ArrayList<Shape> shapes, Shape shape) {
        this.shapes = shapes;
        this.shape = shape;
    }

    @Override
    public void doCommand() {
        shapes.remove(shape);
    }

    @Override
    public void undoCommand() {
        shapes.add(shape);
    }
}
