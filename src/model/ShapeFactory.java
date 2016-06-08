package model;

import view.View;

import java.awt.*;

/**
 * Created by robin on 8/6/16.
 */
public class ShapeFactory extends AbstractShapeFactory {

    @Override
    public Shape createShape(Type type) {
        if (type == Type.LINE) {
            return ShapeCache.getShape(Type.LINE);
        }
        else if (type == Type.RECTANGLE) {
            return ShapeCache.getShape(Type.RECTANGLE);
        }
        else if (type == Type.OVAL) {
            return ShapeCache.getShape(Type.OVAL);
        }

        return null;
    }
}
