package hr.fer.zemris.optjava.dz13.gp.ant;

/**
 * Created by zac
 */
public class Position {

    private int x;
    private int y;
    private final int mapWidth;
    private final int mapHeight;

    public Position(int x, int y, int mapWidth, int mapHeight) {
        this.x = x;
        this.y = y;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        fixX();
        fixY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Position clone() {
        return new Position(x, y, mapWidth, mapHeight);
    }

    public void moveForward(int direction) {
        switch (direction) {
            case Ant.RIGHT:
                updateX(+1);
                break;
            case Ant.DOWN:
                updateY(+1);
                break;
            case Ant.LEFT:
                updateX(-1);
                break;
            case Ant.UP:
                updateY(-1);
                break;
        }
    }

    public Position getForward(int direction) {
        Position forward = this.clone();
        forward.moveForward(direction);
        return forward;
    }

    private void fixX() {
        if (x < 0 || x >= mapWidth) {
            x = (x + mapWidth) % mapWidth;
        }
    }

    private void fixY() {
        if (y < 0 || y >= mapHeight) {
            y = (y + mapHeight) % mapHeight;
        }
    }

    public void updateX(int dist) {
        x += dist;
        fixX();
    }

    public void updateY(int dist) {
        y += dist;
        fixY();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
