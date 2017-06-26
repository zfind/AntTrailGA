package hr.fer.zemris.optjava.dz13.gp.tree.node;

/**
 * Created by zac
 */
public class Prog3Node extends FunctionNode {

    private final int ARITY = 3;

    public Prog3Node(Node[] children) {
        super(children);
    }

    public Prog3Node(Node a, Node b, Node c) {
        super(new Node[]{a, b, c});
    }

    @Override
    public Node clone() {
        Node[] cloned = cloneChildren(children);
        return new Prog3Node(cloned);
    }


    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    public String getName() {
        return "Prog3";
    }

    @Override
    public String toString() {
        return "Prog3(" + children[0] + ", " + children[1] + ", " + children[2] + ")";
    }

}
