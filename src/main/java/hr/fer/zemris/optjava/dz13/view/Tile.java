package hr.fer.zemris.optjava.dz13.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


/**
 * Created by zac
 */
public class Tile extends StackPane {

    private static final int[] rotationMap = new int[]{0, 90, 180, 270};

    private boolean hasFood;
    private final Rectangle r;
    private final Polygon ant;

    public Tile(boolean hasFood, double width, double height) {
        this.hasFood = hasFood;
        this.r = new Rectangle(0, 0, width, height);
        r.setStroke(Color.BLACK);
        if (hasFood) {
            r.setFill(Color.GREEN);
        } else {
            r.setFill(Color.YELLOW);
        }
        this.getChildren().add(r);

        this.ant = new Polygon();
        ant.getPoints().addAll(
                0.0, 5.0,
                20.0, 10.0,
                0.0, 15.0);
        ant.setStroke(Color.BLACK);
        ant.setFill(Color.RED);
    }

    public void visit(int direction) {
        ant.setRotate(rotationMap[direction]);
        if (hasFood) {
            this.hasFood = false;
            r.setFill(Color.YELLOW);
        }
        this.getChildren().add(ant);
    }

    public void leave() {
        this.getChildren().remove(ant);
    }

    public void set(boolean hasFood) {
        if (hasFood) {
            this.hasFood = true;
            r.setFill(Color.GREEN);
        }
    }

}
