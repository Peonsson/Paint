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
    private Shape selectedShape;

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

                //@TODO: implement select and drag shape
//                if (paint.getShapeType() == paint.SELECT) {
//                    int size = paint.getShapes().size();
//                    int mouseClickedX = e.getX();
//                    int mouseClickedY = e.getY();
//                    System.out.println("mouseClickedX: " + mouseClickedX);
//                    System.out.println("mouseClickedY: " + mouseClickedY);
//                    for (int i = size - 1; i >= 0; i--) { // for all shapes
//                        Shape shape = paint.getShapes().get(i);
//                        int x1 = shape.getX1();
//                        int x2 = shape.getX2();
//                        int y1 = shape.getY1();
//                        int y2 = shape.getY2();
//                        if (mouseClickedX < x1 + x2 && mouseClickedX > x1) { // we are within x
//                            if (mouseClickedY < y1 + y2 && mouseClickedY > y1) { // we are within y
//                                selectedShape = shape;
//                                shape.setColor(Color.LIGHT_GRAY);
//                                System.out.println("we selected shape " + i);
//                                paint.repaint();
//                                return;
//                            }
//                        }
//                    }
//                    return;
//            }

                if (paint.getShapeType() == paint.RECTANGLE || paint.getShapeType() == paint.OVAL) {
                    width = Math.abs(mouseDraggedX - mousePressedX);
                    height = Math.abs(mouseDraggedY - mousePressedY);
                    x = Math.min(mouseDraggedX, mousePressedX);
                    y = Math.min(mouseDraggedY, mousePressedY);
                } else if (paint.getShapeType() == paint.LINE) {
                    x = mousePressedX;
                    y = mousePressedY;
                    width = mouseDraggedX;
                    height = mouseDraggedY;
                }
                int size = paint.getShapes().size();
                model.Shape temp = paint.getShapes().get(size - 1);
                temp.setX1(x);
                temp.setY1(y);
                temp.setX2(width);
                temp.setY2(height);
                paint.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        paint.getCanvas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (paint.getShapeType() == paint.SELECT) {
                    int size = paint.getShapes().size();
                    int mouseClickedX = e.getX();
                    int mouseClickedY = e.getY();
                    for (int i = size - 1; i >= 0; i--) { // for all shapes
                        Shape shape = paint.getShapes().get(i);
                        int x1 = shape.getX1();
                        int x2 = shape.getX2();
                        int y1 = shape.getY1();
                        int y2 = shape.getY2();
                        if (mouseClickedX < x1 + x2 && mouseClickedX > x1) { // we are within x
                            if (mouseClickedY < y1 + y2 && mouseClickedY > y1) { // we are within y
                                if(shape instanceof Oval ||shape instanceof Rectangle) { // we don't support Lines
                                    selectedShape = shape;
                                    shape.setColor(Color.LIGHT_GRAY);
                                    paint.repaint();
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = e.getY();
                int thickness = Integer.parseInt((String) paint.getThicknessComboBox().getSelectedItem());
                Color color = getColor();
                boolean isFilled = paint.getFilledCheckBox().isSelected();

                if (paint.getShapeType() == paint.LINE)
                    paint.getShapes().add(new Line(x, y, width, height, color, thickness));
                else if (paint.getShapeType() == paint.RECTANGLE)
                    paint.getShapes().add(new Rectangle(x, y, width, height, isFilled, color, thickness));
                else if (paint.getShapeType() == paint.OVAL)
                    paint.getShapes().add(new Oval(x, y, width, height, isFilled, color, thickness));
                paint.repaint();
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
                System.out.println("set color: " + paint.getColor());
                if (selectedShape != null) {
                    if (paint.getShapeType() == paint.SELECT) {
                        selectedShape.setColor(Color.BLACK);
                        paint.repaint();
                    }
                }
            }
        });
        paint.getBlueRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.BLUE);
                System.out.println("set color: " + paint.getColor());
                if (selectedShape != null) {
                    if (paint.getShapeType() == paint.SELECT) {
                        selectedShape.setColor(Color.BLUE);
                        paint.repaint();
                    }
                }
            }
        });
        paint.getRedRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.RED);
                System.out.println("set color: " + paint.getColor());
                if (selectedShape != null) {
                    if (paint.getShapeType() == paint.SELECT) {
                        selectedShape.setColor(Color.RED);
                        paint.repaint();
                    }
                }
            }
        });
        paint.getYellowRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.YELLOW);
                System.out.println("set color: " + paint.getColor());
                if (selectedShape != null) {
                    if (paint.getShapeType() == paint.SELECT) {
                        selectedShape.setColor(Color.YELLOW);
                        paint.repaint();
                    }
                }
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
            Select
         */
        paint.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.SELECT);
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
