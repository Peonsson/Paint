package controller;

import model.*;
import model.Rectangle;
import model.Shape;
import view.View;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Controller {

    private AbstractShapeFactory shapeFactory;
    private int mousePressedX, mousePressedY;
    private int width;
    private int height;
    private int x, y;
    private int currentX;
    private int currentY;
    private View view;
    private Shape selectedShape;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        shapeFactory = new ShapeFactory();
        model.addObserver(view.getCanvas());

        /*
            Animating new shapes / moving existing shapes
         */
        view.getCanvas().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int mouseDraggedX = e.getX();
                int mouseDraggedY = e.getY();

                if (view.getShapeType() == Type.SELECT && selectedShape != null) {
                    if (selectedShape instanceof Rectangle || selectedShape instanceof Oval) {
                        selectedShape.setX1(currentX + mouseDraggedX - mousePressedX);
                        selectedShape.setY1(currentY + mouseDraggedY - mousePressedY);
//                        view.repaint();
                        return;
                    }
                }

                if (view.getShapeType() == Type.RECTANGLE || view.getShapeType() == Type.OVAL || view.getShapeType() == Type.LINE) {
                    if (view.getShapeType() == Type.RECTANGLE || view.getShapeType() == Type.OVAL) {
                        width = Math.abs(mouseDraggedX - mousePressedX);
                        height = Math.abs(mouseDraggedY - mousePressedY);
                        x = Math.min(mouseDraggedX, mousePressedX);
                        y = Math.min(mouseDraggedY, mousePressedY);
                    } else if (view.getShapeType() == Type.LINE) {
                        x = mousePressedX;
                        y = mousePressedY;
                        width = mouseDraggedX;
                        height = mouseDraggedY;
                    }
                    int size = model.getShapes().size();
                    if(size == 0)
                        return;
                    Shape temp = model.getShapes().get(size - 1);

                    model.modifyShape(temp, x, y, width, height);
//                    temp.setX1(x);
//                    temp.setY1(y);
//                    temp.setX2(width);
//                    temp.setY2(height);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        /*
            Logic for drawing on canvas
         */
        view.getCanvas().addMouseListener(new MouseListener() {
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
                if (view.getShapeType() == Type.SELECT) {
                    int listSize = model.getShapes().size();
                    for (int i = listSize - 1; i >= 0; i--) { // for all shapes
                        Shape shape = model.getShapes().get(i);
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
                                view.getFilledCheckBox().setSelected(isFilled);
                                int thickness = selectedShape.getThickness();
                                view.getThicknessComboBox().setSelectedIndex(thickness/2);
                                Color color = selectedShape.getColor();
                                if (color == Color.BLUE) {
                                    view.getBlueRadioButton().setSelected(true);
                                } else if (color == Color.BLACK) {
                                    view.getBlackRadioButton().setSelected(true);
                                } else if (color == Color.YELLOW) {
                                    view.getYellowRadioButton().setSelected(true);
                                } else if (color == Color.RED) {
                                    view.getRedRadioButton().setSelected(true);
                                }
                                return;
                            }
                        }
                    }
                    selectedShape = null; // Failed to select a shape
                    return;
                }

                int thickness = Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem());
                Color color = getColor();
                boolean isFilled = view.getFilledCheckBox().isSelected();

                Shape shape = shapeFactory.createShape(view.getShapeType());
                shape.setX1(x);
                shape.setY1(y);
                shape.setX2(width);
                shape.setY2(height);
                shape.setColor(color);
                shape.setFilled(isFilled);
                model.addShape(shape);


//                /*
//                    Create new shape
//                 */
//                int thickness = Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem());
//                Color color = getColor();
//                boolean isFilled = view.getFilledCheckBox().isSelected();
//
//                if (view.getShapeType() == view.LINE) {
//                    model.getShapes().add(new Line(x, y, width, height, color, thickness, view.getCanvas()));
//                }
//                else if (view.getShapeType() == view.RECTANGLE) {
//                    model.getShapes().add(new Rectangle(x, y, width, height, isFilled, color, thickness, view.getCanvas()));
//                }
//                else if (view.getShapeType() == view.OVAL) {
//                    model.getShapes().add(new Oval(x, y, width, height, isFilled, color, thickness, view.getCanvas()));
//                }
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
        view.getBlackRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setColor(view.BLACK);
                if (selectedShape != null) {
                    if (view.getShapeType() == Type.SELECT) {
                        selectedShape.setColor(Color.BLACK);
                    }
                }
            }
        });

        /*
            Blue radio button
         */
        view.getBlueRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setColor(view.BLUE);
                if (selectedShape != null) {
                    if (view.getShapeType() == Type.SELECT) {
                        selectedShape.setColor(Color.BLUE);
                    }
                }
            }
        });

        /*
            Red radio button
         */
        view.getRedRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setColor(view.RED);
                if (selectedShape != null) {
                    if (view.getShapeType() == Type.SELECT) {
                        selectedShape.setColor(Color.RED);
                    }
                }
            }
        });

        /*
            Yellow radio button
         */
        view.getYellowRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setColor(view.YELLOW);
                if (selectedShape != null) {
                    if (view.getShapeType() == Type.SELECT) {
                        selectedShape.setColor(Color.YELLOW);
                    }
                }
            }
        });

        /*
            Line button
         */
        view.getLineButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setType(Type.LINE);
            }
        });

        /*
            Oval button
         */
        view.getOvalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setType(Type.OVAL);
            }
        });

        /*
            Rect button
         */
        view.getRectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setType(Type.RECTANGLE);
            }
        });

        /*
            Select button
         */
        view.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setType(Type.SELECT);
            }
        });

        /*
            Save button
         */
        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Shape> undoShapes = model.getUndoShapes();
                    ArrayList<Shape> shapes = model.getShapes();
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
        view.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileInputStream fileInputStream = new FileInputStream("temp.dat");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    ArrayList<Shape> undoShapes = (ArrayList<Shape>) objectInputStream.readObject();
                    model.setUndoShapes(undoShapes);
                    ArrayList<Shape> shapes = (ArrayList<Shape>) objectInputStream.readObject();
                    model.setShapes(shapes);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        /*
            Undo button
         */
        view.getUndoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = model.getShapes().size();
                if (size > 0) {
                    model.Shape temp = model.getShapes().remove(size - 1);
                    model.getUndoShapes().add(temp);
                }
            }
        });

        /*
            Redo button
         */
        view.getRedoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = model.getUndoShapes().size();
                if (size > 0) {
                    model.Shape temp = model.getUndoShapes().remove(size - 1);
                    model.getUndoShapes().add(temp);
                }
            }
        });

        view.setController(this);
    }

    /*
            Converts a final static int to a Color object
     */
    private Color getColor() {
        int color = view.getColor();
        if (color == view.BLACK)
            return Color.BLACK;
        else if (color == view.BLUE)
            return Color.BLUE;
        else if (color == view.YELLOW)
            return Color.YELLOW;
        else if (color == view.RED)
            return Color.RED;
        else
            return null;
    }

    public ArrayList<Shape> getShapes() {
        return model.getShapes();
    }
}