package model;

import view.View;

import java.awt.*;

/**
 * Created by robin on 8/6/16.
 */
public abstract class AbstractShapeFactory {
    public abstract Shape createShape(Type type);
}
