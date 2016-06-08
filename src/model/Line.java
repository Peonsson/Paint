package model;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Line extends Shape implements Serializable {

    public Line() {}

    public Line(int x1, int y1, int x2, int y2, Color color, int thickness, view.Canvas canvas) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.thickness = thickness;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}