package model;

import java.awt.*;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Shape {

    private int x = 0;
    private int y = 0;
    private Color color = Color.BLACK;
    private int thickness = 1;

    public Shape() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
}
