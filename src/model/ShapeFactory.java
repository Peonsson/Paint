package model;

import view.View;

import java.awt.*;

/**
 * Created by robin on 8/6/16.
 */
public class ShapeFactory extends AbstractShapeFactory {

    @Override
    public Shape createShape(Type type) {
        /*
            Create new shape
         */
//        int thickness = Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem());
//        Color color = getColor();
//        boolean isFilled = view.getFilledCheckBox().isSelected();

        if (type == Type.LINE) {
            return new Line();
        }
        else if (type == Type.RECTANGLE) {
            return new Rectangle();
        }
        else if (type == Type.OVAL) {
            return new Oval();
        }

        return null;
    }
}
