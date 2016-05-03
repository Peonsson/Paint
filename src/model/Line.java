package model;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Line extends Shape implements Serializable {

    public Line(int x1, int y1, int x2, int y2, Color color, int thickness) {
        super(x1, y1, x2, y2, color, thickness);
    }

}
