package model;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Oval extends Shape implements Serializable {

    public Oval(int x1, int y1, int x2, int y2, boolean isFilled, Color color, int thickness) {
        super(x1, y1, x2, y2, isFilled, color, thickness);
    }
}
