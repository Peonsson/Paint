package view;

import controller.Listeners;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Peonsson on 2016-04-03.
 */
public class Paint extends JFrame {

    public static void main(String[] args) {
        Paint paint = new Paint();
        Listeners listeners = new Listeners(paint);
        paint.setTitle("Paint");
        paint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paint.setLocationRelativeTo(null);
        paint.pack();
        paint.setVisible(true);
    }

    public static final int LINE = 1;
    public static final int RECTANGLE = 2;
    public static final int OVAL = 3;
    private int type = 1;

    public static final int BLACK = 1;
    public static final int YELLOW = 2;
    public static final int RED = 3;
    public static final int BLUE = 4;
    private int color = 1;

    private boolean filled = false;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

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
    private String[] thickness = {"1", "2", "3", "4", "5"};
    private JComboBox thicknessComboBox = new JComboBox(thickness);
    private JCheckBox filledCheckBox = new JCheckBox("isFilled");

    public Paint() {
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
    }

    public int getShapeType() {
        return type;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
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

    public String[] getThickness() {
        return thickness;
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

    private Color getColor() {
        switch (color) {
            case BLACK:
                return Color.BLACK;
            case YELLOW:
                return Color.YELLOW;
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
        }
        return Color.GREEN;
    }

    private class myCanvas extends JPanel {

        public myCanvas() {

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            switch (type) {
                case LINE:
                    g.setColor(getColor());
                    g.drawLine(x1, y1, x2, y2);
                    break;
                case RECTANGLE:
                    g.setColor(getColor());
                    if (isFilled()) {
                        g.fillRect(x1, y1, x2, y2); //alpha
                    } else {
                        g.drawRect(x1, y1, x2, y2); //alpha
                    }
                    break;
                case OVAL:
                    g.setColor(getColor());
                    if (isFilled()) {
                        g.fillOval(x1, y1, x2, y2); //alpha
                    } else {
                        g.drawOval(x1, y1, x2, y2); //alpha
                    }
            }
        }
    }
}
