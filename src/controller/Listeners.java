package controller;

import model.*;
import model.Rectangle;
import model.Shape;
import view.Paint;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Listeners implements Serializable {

    private int mousePressedX, mousePressedY;
    private int width;
    private int height;
    private int x, y;
    private Paint paint;

    public Listeners(Paint paint) {
        this.paint = paint;

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
                } else if (paint.getShapeType() == paint.LINE) {
                    x = mousePressedX;
                    y = mousePressedY;
                    width = mouseDraggedX;
                    height = mouseDraggedY;
                } else {
                    System.err.println("FATAL ERROR UNRECOGNISED SHAPE!");
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
                int thickness = Integer.parseInt((String) paint.getThicknessComboBox().getSelectedItem());
                Color color = getColor();
                boolean isFilled = paint.getFilledCheckBox().isSelected();

                if (paint.getShapeType() == paint.LINE)
                    paint.getShapes().add(new Line(x, y, width, height, color, thickness));
                else if (paint.getShapeType() == paint.RECTANGLE)
                    paint.getShapes().add(new Rectangle(x, y, width, height, isFilled, color, thickness));
                else if (paint.getShapeType() == paint.OVAL)
                    paint.getShapes().add(new Oval(x, y, width, height, isFilled, color, thickness));
                else
                    System.err.println("FATAL ERROR WHEN ADDING SHAPES!");
                paint.repaint();
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
                System.out.println("set color: " + paint.getColor());
            }
        });
        paint.getBlueRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.BLUE);
                System.out.println("set color: " + paint.getColor());
            }
        });
        paint.getRedRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.RED);
                System.out.println("set color: " + paint.getColor());
            }
        });
        paint.getYellowRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.YELLOW);
                System.out.println("set color: " + paint.getColor());
            }
        });

        /*
            Shapes
         */
        paint.getLineButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.LINE);
                System.out.println("set shape: " + paint.getShapeType());
            }
        });
        paint.getOvalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.OVAL);
                System.out.println("set shape: " + paint.getShapeType());
            }
        });
        paint.getRectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.RECTANGLE);
                System.out.println("set shape: " + paint.getShapeType());
            }
        });

        /*
            Save / Load
         */
        paint.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Shape> undoShapes = paint.getUndoShapes();
                    ArrayList<Shape> shapes = paint.getShapes();
                    FileOutputStream fileOutputStream = new FileOutputStream("temp.dat");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(undoShapes);
                    objectOutputStream.writeObject(shapes);
                    objectOutputStream.close();
                    System.out.println("saved file");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        paint.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream fileInputStream = new FileInputStream("temp.dat");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    ArrayList<Shape> undoShapes = (ArrayList<Shape>) objectInputStream.readObject();
                    paint.setUndoShapes(undoShapes);
                    ArrayList<Shape> shapes = (ArrayList<Shape>) objectInputStream.readObject();
                    paint.setShapes(shapes);
                    paint.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        /*
            Undo / Redo
         */
        paint.getUndoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = paint.getShapes().size();
                if (size > 0) {
                    model.Shape temp = paint.getShapes().remove(size - 1);
                    paint.getUndoShapes().add(temp);
                    paint.repaint();
                }
            }
        });
        paint.getRedoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = paint.getUndoShapes().size();
                if (size > 0) {
                    model.Shape temp = paint.getUndoShapes().remove(size - 1);
                    paint.getShapes().add(temp);
                    paint.repaint();
                }
            }
        });

        /*
            Filled
         */
        paint.getFilledCheckBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setFilled(paint.getFilledCheckBox().isSelected());
                System.out.println("set filled: " + paint.getFilledCheckBox().isSelected());
            }
        });

        /*
            Thickness
         */
        paint.getThicknessComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO: implement thickness
                System.out.println("set thickness: " + paint.getThicknessComboBox().getSelectedItem().toString());
            }
        });

        /*
            Select
         */
        paint.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //@TODO: implement select
                System.out.println("clicked select button");
            }
        });
    }

    private Color getColor() {
        int color = paint.getColor();
        if (color == paint.BLACK)
            return Color.BLACK;
        else if (color == paint.BLUE)
            return Color.BLUE;
        else if (color == paint.YELLOW)
            return Color.YELLOW;
        else if (color == paint.RED)
            return Color.RED;
        else
            return null;
    }
}
