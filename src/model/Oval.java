package model;

import view.CanvasObserver;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Oval extends Shape implements Serializable {

    public Oval(int x1, int y1, int x2, int y2, boolean isFilled, Color color, int thickness, CanvasObserver observer) {
        super(x1, y1, x2, y2, isFilled, color, thickness, observer);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (isFilled) {
            g.fillOval(x1, y1, x2, y2);
        } else {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(thickness));
            g.drawOval(x1, y1, x2, y2);
        }
    }
}
