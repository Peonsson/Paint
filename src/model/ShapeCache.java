package model;

import java.util.Hashtable;

/**
 * Created by robin on 8/6/16.
 */
public class ShapeCache {

    private static Hashtable<Type, Shape> map = new Hashtable<>();

    public static Shape getShape(Type shapeId) {
        Shape cachedShape = map.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    public static void loadCache() {
        Line line = new Line();
        line.setId(Type.LINE);
        map.put(line.getId(), line);

        Oval oval = new Oval();
        oval.setId(Type.OVAL);
        map.put(oval.getId(), oval);

        Rectangle rectangle = new Rectangle();
        rectangle.setId(Type.RECTANGLE);
        map.put(rectangle.getId(), rectangle);
    }
}
