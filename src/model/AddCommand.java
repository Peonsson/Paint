package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by robin on 8/6/16.
 */
public class AddCommand implements Command, Serializable {

    private ArrayList<Shape> shapes;
    private Shape shape;

    public AddCommand(ArrayList<Shape> shapes, Shape shape) {
        this.shapes = shapes;
        this.shape = shape;
    }

    @Override
    public void doCommand() {
        shapes.add(shape);
    }

    @Override
    public void undoCommand() {
        shapes.remove(shape);
    }
}
