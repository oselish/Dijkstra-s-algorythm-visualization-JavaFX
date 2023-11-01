package com.example.lab4visualization;

import com.oselish.dijkstra.Dijkstra;
import com.oselish.dijkstra.DijkstraResult;
import com.oselish.dijkstra.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.HashMap;
import java.util.Vector;

public class HelloController {

    @FXML
    private Pane pane;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Label statusLabel;

    @FXML
    private Line from1to2;

    @FXML
    private Line from1to3;

    @FXML
    private Line from1to4;

    @FXML
    private Line from2to5;

    @FXML
    private Line from2to6;

    @FXML
    private Line from3to2;

    @FXML
    private Line from3to5;

    @FXML
    private Line from4to3;

    @FXML
    private Line from4to6;

    @FXML
    private Line from5to6;

    @FXML
    private Line from5to7;

    @FXML
    private Line from6to7;

    private Integer start;
    private Integer finish;
    private Boolean startSetted = false;
    private Boolean finishSetted = false;

    Vector<Arrow> arrows = new Vector<>();
    Vector<Button> buttons = new Vector<>();


    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);
    Dijkstra dijkstra;

    @FXML
    private void initialize() {
        buildArrows();

        n1.setNeighbours(n2, n3, n4);
        n1.setDistances(5, 4, 2);
        n2.setNeighbours(n5, n6);
        n2.setDistances(3, 5);
        n3.setNeighbours(n2, n5);
        n3.setDistances(3, 6);
        n4.setNeighbours(n3, n6);
        n4.setDistances(2, 8);
        n5.setNeighbours(n6, n7);
        n5.setDistances(2, 5);
        n6.setNeighbours(n7);
        n6.setDistances(2);
        dijkstra = new Dijkstra(n1, n2, n3, n4, n5, n6, n7);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);

        from1to2.setVisible(false);
        from1to3.setVisible(false);
        from1to4.setVisible(false);
        from2to5.setVisible(false);
        from2to6.setVisible(false);
        from3to2.setVisible(false);
        from3to5.setVisible(false);
        from4to3.setVisible(false);
        from4to6.setVisible(false);
        from5to6.setVisible(false);
        from5to7.setVisible(false);
        from6to7.setVisible(false);
    }

    private void addPoint(int index) {

        if (!startSetted) {
            startSetted = true;
            start = index;
        }
        else if (!finishSetted) {
            finishSetted = true;
            finish = index;
        }
        if (startSetted) {
            if (finishSetted) {
                try {
                    for (var arrow : arrows)
                        arrow.setStroke(Color.BLACK);
                    var way = dijkstra.calculateWay(start, finish);
                    showWay(way.getWay());
                    String wayLengthString = way.getWayLength().toString();
                    if (wayLengthString.endsWith(".0")) wayLengthString = wayLengthString.substring(0, wayLengthString.length() - 2);
                    
                    statusLabel.setText(String.format("Длина пути от %d до %d равна %s", start, finish, wayLengthString));
                }
                catch (Exception ex) {
                    statusLabel.setText(String.format("Нет пути от %d до %d", start, finish));
                }

                startSetted = false;
                finishSetted = false;
                start = null;
                finish = null;
            }
        }
    }

    public void onButton1Click(ActionEvent actionEvent) {
        addPoint(Integer.valueOf(button1.getText()));
    }

    public void onButton2Click(ActionEvent actionEvent) {
        addPoint(Integer.valueOf(button2.getText()));
    }

    public void onButton3Click(ActionEvent actionEvent) {
        addPoint(Integer.valueOf(button3.getText()));
    }

    public void onButton4Click(ActionEvent actionEvent) {
        addPoint(Integer.valueOf(button4.getText()));
    }

    public void onButton5Click(ActionEvent actionEvent) {
        addPoint(Integer.valueOf(button5.getText()));
    }

    public void onButton6Click(ActionEvent actionEvent) {
        addPoint(Integer.valueOf(button6.getText()));
    }

    public void onButton7Click(ActionEvent actionEvent) {
       addPoint(Integer.valueOf(button7.getText()));
    }

    public void buildArrows() {
        buildArrow(1, 2);
        buildArrow(1, 3);
        buildArrow(1, 4);
        buildArrow(2, 5);
        buildArrow(2, 6);
        buildArrow(3, 2);
        buildArrow(3, 5);
        buildArrow(4, 3);
        buildArrow(4, 6);
        buildArrow(5, 6);
        buildArrow(5, 7);
        buildArrow(6, 7);

        Integer i = 0;
        for (var arrow : arrows) {
            i++;
            arrow.idProperty().set("arrow" + i.toString());
            pane.getChildren().add(arrow);
        }
    }

    private Arrow buildArrow(int index1, int index2) {
        Arrow arrow = new Arrow();
        arrow.setStroke(Color.BLACK);

        var coords = getArrowCoordinates(index1, index2);
        arrow.setStartX(coords[0].getX());
        arrow.setStartY(coords[0].getY());
        arrow.setEndX(coords[1].getX());
        arrow.setEndY(coords[1].getY());
        arrows.add(arrow);
        return arrow;
    }

    private void setArrowColor(int index1, int index2, Color color) {
        Button b1 = null, b2 = null;
        for (var button : buttons) {
            var buttonText = button.getText();
            if (Integer.valueOf(buttonText) == index1)
                b1 = button;
            if (Integer.valueOf(buttonText) == index2)
                b2 = button;
        }


        var coords = getArrowCoordinates(index1, index2);

        for (var arrow : arrows) {
            if (
                    arrow.getStartX() == coords[0].getX() &&
                    arrow.getStartY() == coords[0].getY() &&
                    arrow.getEndX() == coords[1].getX() &&
                    arrow.getEndY() == coords[1].getY()
            ){
                arrow.setStroke(color);
            }
        }
    }

    private void setArrowColor(Arrow arrow, Color color) {
        arrow.setStroke(color);
    }

    private void showWay(Vector<Node> nodes) {
        
//        Color color = Color.rgb(22,165,214);
        Color color = Color.rgb(6,160,211);
        for (int i = 0; i < nodes.size() - 1; i++) {
            var current = nodes.get(i);
            var next = nodes.get(i + 1);
            setArrowColor(current.getIndex(), next.getIndex(), color);
        }
    }

    private Point[] getArrowCoordinates(int index1, int index2) {
        Point[] coords = new Point[2];
        Line line = null;
        if (index1 == 1) {
            if (index2 == 2) line = from1to2;
            if (index2 == 3) line = from1to3;
            if (index2 == 4) line = from1to4;
        }
        else if (index1 == 2) {
            if (index2 == 5) line = from2to5;
            if (index2 == 6) line = from2to6;
        }
        else if (index1 == 3) {
            if (index2 == 2) line = from3to2;
            if (index2 == 5) line = from3to5;
        }
        else if (index1 == 4) {
            if (index2 == 3) line = from4to3;
            if (index2 == 6) line = from4to6;
        }
        else if (index1 == 5) {
            if (index2 == 6) line = from5to6;
            if (index2 == 7) line = from5to7;
        }
        else if (index1 == 6) {
            if (index2 == 7) line = from6to7;
        }

        if (line == null)
            return null;
        coords[0] = new Point(line.getLayoutX() + line.getStartX(), line.getLayoutY() + line.getStartY());
        coords[1] = new Point(line.getLayoutX() + line.getEndX(), line.getLayoutY() + line.getEndY());

        return coords;
    }

}

