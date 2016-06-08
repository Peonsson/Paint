package controller;

import model.*;
import model.Rectangle;
import model.Shape;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;

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

    private Shape previousShapeState;

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

                if (view.getShapeType() == Type.select && selectedShape != null) {
                    if (selectedShape instanceof Rectangle || selectedShape instanceof Oval) {
                        int x = currentX + mouseDraggedX - mousePressedX;
                        int y = currentY + mouseDraggedY - mousePressedY;
//                        model.modifyShape(selectedShape, x, y);
                        // USED in wtf.mov VIDEO
//                        model.modifyShapeAnimation(selectedShape, x, y, selectedShape.getX2(), selectedShape.getY2(), selectedShape.getColor(), selectedShape.isFilled(), selectedShape.getThickness(), selectedShape.isFirst());
                        model.modifyShapeAnimation(selectedShape, x, y, selectedShape.getX2(), selectedShape.getY2(), selectedShape.getColor(), selectedShape.isFilled(), selectedShape.getThickness(), selectedShape.isFirst());
                        return;
                    }
                }

                if (view.getShapeType() == Type.rectangle || view.getShapeType() == Type.oval || view.getShapeType() == Type.line) {
                    if (view.getShapeType() == Type.rectangle || view.getShapeType() == Type.oval) {
                        width = Math.abs(mouseDraggedX - mousePressedX);
                        height = Math.abs(mouseDraggedY - mousePressedY);
                        x = Math.min(mouseDraggedX, mousePressedX);
                        y = Math.min(mouseDraggedY, mousePressedY);
                    } else if (view.getShapeType() == Type.line) {
                        x = mousePressedX;
                        y = mousePressedY;
                        width = mouseDraggedX;
                        height = mouseDraggedY;
                    }

                    int size = model.getShapes().size();
                    if (size == 0)
                        return;

                    Shape temp = model.getShapes().get(size - 1);
                    model.modifyShapeAnimation(temp, x, y, width, height, getColor(), view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
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
                if (view.getShapeType() == Type.select) {
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

                                previousShapeState = (Shape) selectedShape.clone();

                                currentX = selectedShape.getX1();
                                currentY = selectedShape.getY1();

                                /*
                                    Update view to match selection
                                 */
                                boolean isFilled = selectedShape.isFilled();
                                view.getFilledCheckBox().setSelected(isFilled);
                                int thickness = selectedShape.getThickness();
                                view.getThicknessComboBox().setSelectedIndex(thickness / 2);
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
                shape.setX1(mousePressedX);
                shape.setY1(mousePressedY);
                shape.setX2(0);
                shape.setY2(0);
                shape.setColor(color);
                shape.setThickness(thickness);
                shape.setFilled(isFilled);
                model.addShape(shape);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("shape type: " + view.getShapeType());
                if (view.getShapeType() == Type.select) {
                    System.out.println("mouse released event");
                    // USED IN wtf.mov VIDEO
//                    model.modifyShape(selectedShape, x, y, width, height, getColor(), view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
                    model.modifyShape(previousShapeState, x, y, width, height, getColor(), view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        view.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedShape != null) {
                    model.removeShape(selectedShape);
                }
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
                    if (view.getShapeType() == Type.select) {
                        model.modifyShape(previousShapeState, x, y, width, height, Color.BLACK, view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
//                        model.modifyShape(selectedShape, Color.BLACK);
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
                    if (view.getShapeType() == Type.select) {
                        model.modifyShape(previousShapeState, x, y, width, height, Color.BLUE, view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
//                        model.modifyShape(selectedShape, Color.BLUE);
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
                    if (view.getShapeType() == Type.select) {
                        model.modifyShape(previousShapeState, x, y, width, height, Color.RED, view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
//                        model.modifyShape(selectedShape, Color.RED);
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
                    if (view.getShapeType() == Type.select) {
                        model.modifyShape(previousShapeState, x, y, width, height, Color.YELLOW, view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
//                        model.modifyShape(selectedShape, Color.YELLOW);
                    }
                }
            }
        });


        ArrayList<JButton> buttons = view.getShapeButtons();

        for (int i = 0; i < buttons.size(); i++) {
            final JButton button = buttons.get(i);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.setType(Type.valueOf(button.getText()));
                }
            });
        }

        /*
            Select button
         */
        view.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setType(Type.select);
            }
        });

        /*
            isFilled checkbox
         */
        view.getFilledCheckBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedShape != null) {
                    if (view.getShapeType() == Type.select) {
                        Boolean isFilled = view.getFilledCheckBox().isSelected();
                        model.modifyShape(previousShapeState, x, y, width, height, getColor(), view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
//                        model.modifyShape(selectedShape, isFilled);
                    }
                }
            }
        });
        /*
            Thickness combobox
         */
        view.getThicknessComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getShapeType() == Type.select) {
                    int thickness = Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem());
                    model.modifyShape(previousShapeState, x, y, width, height, getColor(), view.getFilledCheckBox().isSelected(), Integer.parseInt((String) view.getThicknessComboBox().getSelectedItem()), false);
//                    model.modifyShape(selectedShape, thickness);
                }
            }
        });

        /*
            Save button
         */
        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Shape> shapes = model.getShapes();
                    Stack<Command> commands = model.getCommandStack();
                    Stack<Command> undoCommands = model.getUndoCommandStack();
                    FileOutputStream fileOutputStream = new FileOutputStream("temp.dat");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(shapes);
                    objectOutputStream.writeObject(undoCommands);
                    objectOutputStream.writeObject(commands);
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
                    ArrayList<Shape> shapes = (ArrayList<Shape>) objectInputStream.readObject();
                    Stack<Command> undoCommands = (Stack<Command>) objectInputStream.readObject();
                    Stack<Command> commands = (Stack<Command>) objectInputStream.readObject();
                    model.setShapes(shapes);
                    model.setCommandStack(commands);
                    model.setUndoCommandStack(undoCommands);
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
                int size = model.getUndoCommandStack().size();
                if (size > 0) {
                    Command command = model.getUndoCommandStack().pop();
                    command.undoCommand();
                    model.getCommandStack().push(command);
                    model.update();
                }
            }
        });

        /*
            Redo button
         */
        view.getRedoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = model.getCommandStack().size();
                if (size > 0) {
                    Command command = model.getCommandStack().pop();
                    command.doCommand();
                    model.getUndoCommandStack().push(command);
                    model.update();
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