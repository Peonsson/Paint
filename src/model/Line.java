package model;

import controller.MyObserver;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Line extends Shape implements Serializable {

    public Line(int x1, int y1, int x2, int y2, Color color, int thickness, MyObserver observer) {
        super(x1, y1, x2, y2, color, thickness, observer);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}