package hr.fer.zemris.optjava.dz13.gp.tree.node;

import hr.fer.zemris.optjava.rng.RNG;

/**
 * Created by zac
 */
public class AntNodeFactory implements INodeFactory {

    private static final int MOVE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int IFFOODAHEAD = 0;
    private static final int PROG2 = 1;
    private static final int PROG3 = 2;

    private TerminalNode[] terminals;
    private int[] arity;
    private final int terminalsCount;
    private final int functionsCount;
    private final double ratio;

    public AntNodeFactory() {
        this.terminals = new TerminalNode[3];
        terminals[MOVE] = new MoveNode();
        terminals[LEFT] = new LeftNode();
        terminals[RIGHT] = new RightNode();
        this.terminalsCount = 3;
        this.functionsCount = 3;
        this.ratio = 0.5;

        this.arity = new int[3];
        arity[IFFOODAHEAD] = 2;
        arity[PROG2] = 2;
        arity[PROG3] = 3;
    }

    @Override
    public Node getRandomTermNodeInstance() {
        int choice = RNG.nextInt(0, terminalsCount);
        return terminals[choice].clone();
    }

    @Override
    public Node getTermNodeInstance(int id) {
        return terminals[id].clone();
    }

    @Override
    public int chooseRandomFunction() {
        return RNG.nextInt(0, functionsCount);
    }

    @Override
    public int getArity(int funcId) {
        return arity[funcId];
    }

    @Override
    public Node getFuncNodeInstance(int choice, Node[] children) {
        switch (choice) {
            case IFFOODAHEAD:
                return new IfFoodAheadNode(children);
            case PROG2:
                return new Prog2Node(children);
            case PROG3:
                return new Prog3Node(children);
            default:
                System.err.println("Greska. Nepostojeci razred");
                return null;
        }
    }

    @Override
    public double getRatio() {
        return ratio;
    }

    @Override
    public Node swapWithSameArityFunction(FunctionNode node) {
        return null; //TODO
    }

}
