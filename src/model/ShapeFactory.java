package model;

/**
 * Created by robin on 8/6/16.
 */
public class ShapeFactory extends AbstractShapeFactory {

    @Override
    public Shape createShape(Type type) {
        if (type == Type.line) {
            return ShapeCache.getShape(Type.line);
        }
        else if (type == Type.rectangle) {
            return ShapeCache.getShape(Type.rectangle);
        }
        else if (type == Type.oval) {
            return ShapeCache.getShape(Type.oval);
        }

        return null;
    }
}
