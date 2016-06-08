package model;

import java.awt.*;
import java.io.Serializable;
import java.util.Observable;

/**
 * Created by Peonsson on 2016-04-03.
 */
public abstract class Shape implements Serializable, Cloneable {

    protected Type id;
    protected int x1 = 0;
    protected int y1 = 0;
    protected int x2 = 0;
    protected int y2 = 0;
    protected boolean isFilled = false;
    protected Color color = Color.BLACK;
    protected int thickness = 1;

    public Shape() {

    }

    /*
        Creating rectagle or oval
     */
//    public Shape(int x1, int y1, int x2, int y2, boolean isFilled, Color color, int thickness, view.Canvas canvas) {
//        this.x1 = x1;
//        this.y1 = y1;
//        this.x2 = x2;
//        this.y2 = y2;
//        this.isFilled = isFilled;
//        this.color = color;
//        this.thickness = thickness;
//        addObserver(canvas);
//        setChanged();
//        notifyObservers("added a shape");
//        System.out.println("observer count:" + countObservers());
//    }

    /*
        Creating a line
     */
//    public Shape(int x1, int y1, int x2, int y2, Color color, int thickness, view.Canvas canvas) {
//        this.x1 = x1;
//        this.y1 = y1;
//        this.x2 = x2;
//        this.y2 = y2;
//        this.color = color;
//        this.thickness = thickness;
//        addObserver(canvas);
//        setChanged();
//        notifyObservers("added a shape");
//        System.out.println("observer count:" + countObservers());
//    }


    public Type getId() {
        return id;
    }

    public void setId(Type id) {
        this.id = id;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
//        setChanged();
//        notifyObservers("set filled");
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
//        setChanged();
//        notifyObservers();
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
//        setChanged();
//        notifyObservers();
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", isFilled=" + isFilled +
                ", color=" + color +
                ", thickness=" + thickness +
                '}';
    }

    public abstract void draw(Graphics g);

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
