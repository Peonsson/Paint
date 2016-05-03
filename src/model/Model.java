package model;

import java.util.ArrayList;

/**
 * Created by Peonsson on 03/05/16.
 */
public class Model {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Shape> undoShapes = new ArrayList<>();

    public Model() {

    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<Shape> getUndoShapes() {
        return undoShapes;
    }

    public void setUndoShapes(ArrayList<Shape> undoShapes) {
        this.undoShapes = undoShapes;
    }
}
