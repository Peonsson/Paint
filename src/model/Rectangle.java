package model;

import java.awt.*;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Rectangle extends Shape {

    public Rectangle() {
        super();
    }

    public Rectangle(int x1, int y1, int x2, int y2, boolean isFilled, Color color, int thickness) {
        super(x1, y1, x2, y2, isFilled, color, thickness);
    }
}