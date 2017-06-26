package hr.fer.zemris.optjava.dz13.gp.ant;

import hr.fer.zemris.optjava.dz13.gp.fitness.IFitnessFunction;
import hr.fer.zemris.optjava.dz13.gp.tree.node.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac
 */
public class Ant implements IFitnessFunction {

    public static final int RIGHT = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int UP = 3;

    private final int mapWidth;
    private final int mapHeight;
    private final int foodCollectedThreshold;
    private final int maxActionsLimit;

    private Board board;

    private Position position;
    private boolean foodAhead;
    private int direction;
    private int actionsAvailable;
    private int foodCollected;

    public Ant(Board board, int foodCollectedThreshold, int maxActionsLimit) {
        this.mapWidth = board.width;
        this.mapHeight = board.height;
        this.foodCollectedThreshold = foodCollectedThreshold;
        this.maxActionsLimit = maxActionsLimit;

        this.direction = RIGHT;
        this.position = new Position(0, 0, mapWidth, mapHeight);
        this.foodAhead = false;

        this.board = board;
        this.foodCollected = 0;
        this.actionsAvailable = maxActionsLimit;
    }

    @Override
    public double evaluate(Node tree) {
        return evaluateTree(tree);
    }

    private int evaluateTree(Node tree) {
        reset();
        while (!isCollected() && hasActionsAvailable()) {
            tree.evaluate(this);
        }
        return foodCollected;
    }

    public List<Trace> trace(Node tree) {
        reset();
        List<Trace> traceList = new ArrayList<>();
        while (!isCollected() && hasActionsAvailable()) {
            tree.trace(this, traceList);
        }
        return traceList;
    }

    public boolean isFoodAhead() {
        return foodAhead;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getDirection() {
        return this.direction;
    }

    public int getFoodCollected() {
        return this.foodCollected;
    }

    public Trace getTrace() {
        return new Trace(this.position, this.direction, this.foodCollected, this.actionsAvailable);
    }

    public boolean hasActionsAvailable() {
        return actionsAvailable > 0;
    }

    private boolean isCollected() {
        return foodCollected >= foodCollectedThreshold;
    }

    private void reset() {
        board.reset();

        direction = RIGHT;
        position = new Position(0, 0, mapWidth, mapHeight);
        foodAhead = false;

        foodCollected = 0;
        actionsAvailable = maxActionsLimit;
        updateFood();
    }

    private void updateFood() {
        if (board.isFoodAt(position)) {
            board.pickUp(position);
            foodCollected++;
        }
        foodAhead = board.isFoodAt(position.getForward(direction));
    }

    public void moveForward() {
        position.moveForward(direction);
        actionsAvailable--;
        updateFood();
    }

    public void turnRight() {
        this.direction = (this.direction == UP) ? RIGHT : this.direction + 1;
        actionsAvailable--;
        updateFood();
    }

    public void turnLeft() {
        this.direction = (this.direction == RIGHT) ? UP : this.direction - 1;
        actionsAvailable--;
        updateFood();
    }

    private void moveForward(int steps) {
        for (int i = 0; i < steps; i++) {
            moveForward();
        }
    }

    private static void test1() {
        Board board = new Board("13-SantaFeAntTrail.txt");
//        board.print();

        Ant ant = new Ant(board, 89, 600);

        ant.moveForward(3);
        ant.turnRight();

        ant.moveForward(5);
        ant.turnLeft();

        ant.moveForward(9);
        ant.turnRight();

        ant.moveForward(19);
        ant.turnRight();

        ant.moveForward(11);
        ant.turnLeft();

        ant.moveForward(6);
        ant.turnLeft();

        ant.moveForward(6);
        ant.turnLeft();

        ant.moveForward(3);
        ant.turnRight();

        ant.moveForward(9);
        ant.turnLeft();

        ant.moveForward(12);
        ant.turnRight();

        ant.moveForward(4);
        ant.turnLeft();

        ant.moveForward(10);
        ant.turnRight();

        ant.moveForward(4);
        ant.turnLeft();

        ant.moveForward(3);
        ant.turnRight();

        ant.moveForward(5);
        ant.turnRight();

        ant.moveForward(12);
        ant.turnRight();

        ant.moveForward(6);
        ant.turnLeft();

        ant.moveForward(4);
        ant.turnLeft();

        ant.moveForward(4);
        ant.turnRight();

        ant.moveForward(4);
        ant.turnRight();

        ant.moveForward(4);
        ant.turnLeft();

        ant.moveForward();

        board.print();
        System.out.println(ant.position + " " + ant.direction);
        System.out.println(ant.foodCollected);
        System.out.println(ant.actionsAvailable);

    }

    private static void test2() {
        Board board = new Board("13-SantaFeAntTrail.txt");

        Ant ant = new Ant(board, 89, 600);

        ant.moveForward(12);
        ant.turnRight();

        ant.moveForward(33);

        board.print();
        System.out.println(ant.position + " " + ant.direction);
    }

    private static void test3() {
        Board board = new Board("13-SantaFeAntTrail.txt");

        Ant ant = new Ant(board, 89, 600);

        ant.turnRight();
        ant.moveForward(5);
        ant.turnLeft();
        ant.moveForward(33);

        board.print();
        System.out.println(ant.position + " " + ant.direction);
    }

    private static void test4() {
        Board board = new Board("13-SantaFeAntTrail.txt");

        Node node = new Prog3Node(new RightNode(), new IfFoodAheadNode(new Prog3Node(new LeftNode(), new RightNode(), new LeftNode()), new Prog3Node(new LeftNode(), new MoveNode(), new IfFoodAheadNode(new Prog2Node(new LeftNode(), new IfFoodAheadNode(new RightNode(), new RightNode())), new LeftNode()))), new IfFoodAheadNode(new IfFoodAheadNode(new MoveNode(), new RightNode()), new IfFoodAheadNode(new RightNode(), new RightNode())));

        Ant ant = new Ant(board, 89, 600);
//        System.out.println(ant.evaluate(node));

        for (Trace trace : ant.trace(node)) {
            System.out.println(trace);
        }
    }

    public static void main(String[] args) {
        test4();
    }

}
