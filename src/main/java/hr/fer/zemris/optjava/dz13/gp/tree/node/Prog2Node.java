package hr.fer.zemris.optjava.dz13.gp.tree.node;

/**
 * Created by zac
 */
public class Prog2Node extends FunctionNode {

    private final int ARITY = 2;

    public Prog2Node(Node[] children) {
        super(children);
    }

    public Prog2Node(Node a, Node b) {
        super(new Node[]{a, b});
    }

    @Override
    public Node clone() {
        Node[] cloned = cloneChildren(children);
        return new Prog2Node(cloned);
    }


    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    public String getName() {
        return "Prog2";
    }

    @Override
    public String toString() {
        return "Prog2(" + children[0] + ", " + children[1] + ")";
    }

}
