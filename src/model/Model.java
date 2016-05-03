package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Peonsson on 03/05/16.
 */
public class Model extends Observable {

    private ArrayList<Shape> shapes;
    private ArrayList<Shape> undoShapes;

    public Model() {
        this.shapes = new ArrayList<>();
        this.undoShapes = new ArrayList<>();
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
