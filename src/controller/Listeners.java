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
public class Listeners {

    private int mousePressedX, mousePressedY;
    private int width;
    private int height;
    private int x, y;
    private int currentX;
    private int currentY;
    private Paint paint;
    private Shape selectedShape;

    public Listeners(Paint paint) {
        this.paint = paint;

        /*
            Animating new shapes / moving existing shapes
         */
        paint.getCanvas().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int mouseDraggedX = e.getX();
                int mouseDraggedY = e.getY();

                if (paint.getShapeType() == paint.SELECT && selectedShape != null) {
                    if (selectedShape instanceof Rectangle || selectedShape instanceof Oval) {
                        selectedShape.setX1(currentX + mouseDraggedX - mousePressedX);
                        selectedShape.setY1(currentY + mouseDraggedY - mousePressedY);
                        paint.repaint();
                        return;
                    }
                }

                if (paint.getShapeType() == paint.RECTANGLE || paint.getShapeType() == paint.OVAL || paint.getShapeType() == paint.LINE) {
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
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        /*
            Logic for drawing on canvas
         */
        paint.getCanvas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            /*
                Selecting  / creating new shapes
             */
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedX = e.getX();
                mousePressedY = e.getY();

                /*
                    Selecting a shape
                 */
                if (paint.getShapeType() == paint.SELECT) {
                    int listSize = paint.getShapes().size();
                    for (int i = listSize - 1; i >= 0; i--) { // for all shapes
                        Shape shape = paint.getShapes().get(i);
                        int x1 = shape.getX1();
                        int x2 = shape.getX2();
                        int y1 = shape.getY1();
                        int y2 = shape.getY2();
                        if (mousePressedX < x1 + x2 && mousePressedX > x1) { // we are within x
                            if (mousePressedY < y1 + y2 && mousePressedY > y1) { // we are within y
                                selectedShape = shape; // A shape was selected
                                currentX = selectedShape.getX1();
                                currentY = selectedShape.getY1();

                                /*
                                    Update view to match selection
                                 */
                                boolean isFilled = selectedShape.isFilled();
                                paint.getFilledCheckBox().setSelected(isFilled);
                                int thickness = selectedShape.getThickness();
                                paint.getThicknessComboBox().setSelectedIndex(thickness/2);
                                Color color = selectedShape.getColor();
                                if (color == Color.BLUE) {
                                    paint.getBlueRadioButton().setSelected(true);
                                } else if (color == Color.BLACK) {
                                    paint.getBlackRadioButton().setSelected(true);
                                } else if (color == Color.YELLOW) {
                                    paint.getYellowRadioButton().setSelected(true);
                                } else if (color == Color.RED) {
                                    paint.getRedRadioButton().setSelected(true);
                                }
                                return;
                            }
                        }
                    }
                    selectedShape = null; // Failed to select a shape
                    return;
                }

                /*
                    Create new shape
                 */
                int thickness = Integer.parseInt((String) paint.getThicknessComboBox().getSelectedItem());
                Color color = getColor();
                boolean isFilled = paint.getFilledCheckBox().isSelected();

                if (paint.getShapeType() == paint.LINE)
                    paint.getShapes().add(new Line(x, y, width, height, color, thickness));
                else if (paint.getShapeType() == paint.RECTANGLE)
                    paint.getShapes().add(new Rectangle(x, y, width, height, isFilled, color, thickness));
                else if (paint.getShapeType() == paint.OVAL)
                    paint.getShapes().add(new Oval(x, y, width, height, isFilled, color, thickness));
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
            Black radio button
         */
        paint.getBlackRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.BLACK);
                if (selectedShape != null) {
                    if (paint.getShapeType() == paint.SELECT) {
                        selectedShape.setColor(Color.BLACK);
                        paint.repaint();
                    }
                }
            }
        });

        /*
            Blue radio button
         */
        paint.getBlueRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.BLUE);
                if (selectedShape != null) {
                    if (paint.getShapeType() == paint.SELECT) {
                        selectedShape.setColor(Color.BLUE);
                        paint.repaint();
                    }
                }
            }
        });

        /*
            Red radio button
         */
        paint.getRedRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.RED);
                if (selectedShape != null) {
                    if (paint.getShapeType() == paint.SELECT) {
                        selectedShape.setColor(Color.RED);
                        paint.repaint();
                    }
                }
            }
        });

        /*
            Yellow radio button
         */
        paint.getYellowRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setColor(paint.YELLOW);
                if (selectedShape != null) {
                    if (paint.getShapeType() == paint.SELECT) {
                        selectedShape.setColor(Color.YELLOW);
                        paint.repaint();
                    }
                }
            }
        });

        /*
            Line button
         */
        paint.getLineButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.LINE);
            }
        });

        /*
            Oval button
         */
        paint.getOvalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.OVAL);
            }
        });

        /*
            Rect button
         */
        paint.getRectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.RECTANGLE);
            }
        });

        /*
            Select button
         */
        paint.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.setType(paint.SELECT);
            }
        });

        /*
            Save button
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

        /*
            Load button
         */
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
            Undo button
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

        /*
            Redo button
         */
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
    }

    /*
        Converts a final static int to a Color object
     */
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