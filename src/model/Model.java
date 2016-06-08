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

    public void addShape(Shape shape) {
        // Vi ska skapa ett command -> sätta in i undo listan -> och köra command
        // storeAndExecute (rensar redoStack pushar undoStacken -> execute command)
        shapes.add(shape);

        setChanged();
        notifyObservers();
    }

    public void modifyShape(Shape shape, int x, int y, int width, int height) {

        shape.setX1(x);
        shape.setY1(y);
        shape.setX2(width);
        shape.setY2(height);

        setChanged();
        notifyObservers();
    }
}