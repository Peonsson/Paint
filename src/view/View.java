package view;

import controller.Controller;
import model.Model;
import model.ShapeCache;
import model.ShapeFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

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
    private Canvas canvas = new Canvas();
    private model.Type type;
    private int color = 1;
    private JPanel menuPanel = new JPanel();
    private JButton saveButton = new JButton("save");
    private JButton loadButton = new JButton("load");
    private JButton selectButton = new JButton("select");

    private ArrayList<JButton> shapeButtons = new ArrayList<>();

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

        addShapeButtons();

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
        canvas.setController(controller);
    }

    public ArrayList<JButton> getShapeButtons() {
        return shapeButtons;
    }

    public void setShapeButtons(ArrayList<JButton> shapeButtons) {
        this.shapeButtons = shapeButtons;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public model.Type getShapeType() {
        return type;
    }

    public void setType(model.Type type) {
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

    public view.Canvas getCanvas() {
        return canvas;
    }

    private void addShapeButtons() {
        Set<model.Type> types = ShapeCache.getCacheTypes();

        for (model.Type type : types) {
            JButton button = new JButton(type.toString());
            shapeButtons.add(button);
            menuPanel.add(button);
        }

    }
}