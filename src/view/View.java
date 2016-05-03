package view;

import controller.Controller;
import model.Line;
import model.Oval;
import model.Rectangle;
import model.Shape;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class View extends JFrame {

    public static final int LINE = 1;
    public static final int RECTANGLE = 2;
    public static final int OVAL = 3;
    public static final int SELECT = 4;

    public static final int BLACK = 1;
    public static final int YELLOW = 2;
    public static final int RED = 3;
    public static final int BLUE = 4;

    /*
        Member variables
     */
    private Controller controller;

    private int type = 1;
    private int color = 1;
    private myCanvas canvas = new myCanvas();
    private JPanel menuPanel = new JPanel();
    private JButton saveButton = new JButton("save");
    private JButton loadButton = new JButton("load");
    private JButton selectButton = new JButton("select");
    private JButton ovalButton = new JButton("oval");
    private JButton rectButton = new JButton("rect");
    private JButton lineButton = new JButton("line");
    private JButton undoButton = new JButton("undo");
    private JButton redoButton = new JButton("redo");
    private JRadioButton blackRadioButton = new JRadioButton("black");
    private JRadioButton yellowRadioButton = new JRadioButton("yellow");
    private JRadioButton redRadioButton = new JRadioButton("red");
    private JRadioButton blueRadioButton = new JRadioButton("blue");
    private JLabel jLabel = new JLabel("thickness");
    private String[] thickness = {"1", "3", "5", "7", "9", "11", "13", "15"};
    private JComboBox thicknessComboBox = new JComboBox(thickness);
    private JCheckBox filledCheckBox = new JCheckBox("isFilled");

    /*
        Constructor
     */
    public View() {

        this.setTitle("View");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        menuPanel.add(saveButton);
        menuPanel.add(loadButton);
        menuPanel.add(selectButton);
        menuPanel.add(ovalButton);
        menuPanel.add(rectButton);
        menuPanel.add(lineButton);
        menuPanel.add(undoButton);
        menuPanel.add(redoButton);
        menuPanel.add(blackRadioButton);
        menuPanel.add(yellowRadioButton);
        menuPanel.add(redRadioButton);
        menuPanel.add(blueRadioButton);
        menuPanel.add(jLabel);
        menuPanel.add(thicknessComboBox);
        menuPanel.add(filledCheckBox);
        filledCheckBox.setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        blackRadioButton.setSelected(true);
        buttonGroup.add(blackRadioButton);
        buttonGroup.add(yellowRadioButton);
        buttonGroup.add(redRadioButton);
        buttonGroup.add(blueRadioButton);
        thicknessComboBox.setSelectedIndex(0);
        menuPanel.setBorder(new LineBorder(Color.BLACK));
        add(menuPanel, BorderLayout.NORTH);
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(0, 600));
        canvas.setBorder(new LineBorder(Color.BLACK));
        add(canvas, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    /*
        Getters and setters
     */

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getShapeType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public JButton getRedoButton() {
        return redoButton;
    }

    public JComboBox getThicknessComboBox() {
        return thicknessComboBox;
    }

    public JCheckBox getFilledCheckBox() {
        return filledCheckBox;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getOvalButton() {
        return ovalButton;
    }

    public JButton getRectButton() {
        return rectButton;
    }

    public JButton getLineButton() {
        return lineButton;
    }

    public JRadioButton getBlackRadioButton() {
        return blackRadioButton;
    }

    public JRadioButton getYellowRadioButton() {
        return yellowRadioButton;
    }

    public JRadioButton getRedRadioButton() {
        return redRadioButton;
    }

    public JRadioButton getBlueRadioButton() {
        return blueRadioButton;
    }

    public JPanel getCanvas() {
        return canvas;
    }

    private class myCanvas extends JPanel {

        public myCanvas() {
        }

        /*
            View logic
        */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if(controller == null) //check if controller have been initiated yet.
                return;

            for (Shape shape : controller.getShapes()) {
                int x1 = shape.getX1();
                int x2 = shape.getX2();
                int y1 = shape.getY1();
                int y2 = shape.getY2();
                boolean isFilled = shape.isFilled();
                int thickness = shape.getThickness();
                Color color = shape.getColor();

                if (shape instanceof Line) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(thickness));
                    g.setColor(color);
                    g.drawLine(x1, y1, x2, y2);
                } else if (shape instanceof Rectangle) {
                    g.setColor(color);
                    if (isFilled) {
                        g.fillRect(x1, y1, x2, y2);
                    } else {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(thickness));
                        g.drawRect(x1, y1, x2, y2);
                    }
                } else if (shape instanceof Oval) {
                    g.setColor(color);
                    if (isFilled) {
                        g.fillOval(x1, y1, x2, y2);
                    } else {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(thickness));
                        g.drawOval(x1, y1, x2, y2);
                    }
                }
            }
        }
    }
}
