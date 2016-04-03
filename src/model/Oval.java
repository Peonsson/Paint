package model;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Oval extends Shape {

    private int width = 20;
    private int height = 20;

    public Oval() {
        super();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
