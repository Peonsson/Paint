package model;

import java.util.ArrayList;

/**
 * Created by Peonsson on 03/05/16.
 */
public class CanvasModel {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Shape> undoShapes = new ArrayList<>();

    public CanvasModel() {

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
