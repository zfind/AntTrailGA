package hr.fer.zemris.optjava.dz13.gp.ant;

/**
 * Created by zac
 */
public class Trace {
    public final int x;
    public final int y;
    public final int direction;
    public final int foodCollected;
    public final int actionsAvailable;

    public Trace(Position position, int direction, int foodCollected, int actionsAvailable) {
        this.x = position.getX();
        this.y = position.getY();
        this.direction = direction;
        this.foodCollected = foodCollected;
        this.actionsAvailable = actionsAvailable;
    }

    @Override
    public String toString() {
        String position = "(" + x + "," + y + ")";
        return String.format("%.20s\t%4d\t%4d\t%4d", position, direction, foodCollected, actionsAvailable);
    }

}
