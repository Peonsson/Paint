package controller;

import view.Paint;

import java.awt.event.*;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Listeners {

    private int mousePressedX, mousePressedY;
    private int width;
    private int height;
    private int x, y;

    public Listeners(Paint paint) {

        /*
            Canvas
         */
        paint.getCanvas().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int mouseDraggedX = e.getX();
                int mouseDraggedY = e.getY();

                if (paint.getShapeType() == paint.RECTANGLE || paint.getShapeType() == paint.OVAL) {
                    width = Math.abs(mouseDraggedX - mousePressedX);
                    height = Math.abs(mouseDraggedY - mousePressedY);

                    x = Math.min(mouseDraggedX, mousePressedX);
                    y = Math.min(mouseDraggedY, mousePressedY);

                    paint.setX1(x);
                    paint.setY1(y);
                    paint.setX2(width);
                    paint.setY2(height);
                    paint.repaint();
                } else {
                    paint.setX1(mousePressedX);
                    paint.setY1(mousePressedY);
                    paint.setX2(mouseDraggedX);
                    paint.setY2(mouseDraggedY);
                    paint.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        paint.getCanvas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        /*
            Radio buttons
         */
        paint.getBlackRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.BLACK);
            }
        });
        paint.getBlueRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.BLUE);
            }
        });
        paint.getRedRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.RED);
            }
        });
        paint.getYellowRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.YELLOW);
            }
        });

        /*
            Shapes
         */
        paint.getLineButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.LINE);
            }
        });
        paint.getOvalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.OVAL);
            }
        });
        paint.getRectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.RECTANGLE);
            }
        });

        /*
            Save / Load
         */
        paint.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO: implement save
            }
        });
        paint.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO: implement load
            }
        });

        /*
            Undo / Redo
         */
        paint.getUndoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO: implement undo
            }
        });
        paint.getRedoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO: implement redo
            }
        });

        /*
            Filled
         */
        paint.getFilledCheckBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setFilled(paint.getFilledCheckBox().isSelected());
            }
        });

        /*
            Thickness
         */
        paint.getThicknessComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO: implement thickness
            }
        });

        /*
            Select
         */
        paint.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO: implement select
            }
        });
    }
}
