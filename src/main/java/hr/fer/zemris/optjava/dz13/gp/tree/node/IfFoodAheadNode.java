package hr.fer.zemris.optjava.dz13.gp.tree.node;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.ant.Trace;

import java.util.List;

/**
 * Created by zac
 */
public class IfFoodAheadNode extends FunctionNode {

    private final int ARITY = 2;

    public IfFoodAheadNode(Node[] children) {
        super(children);
    }

    public IfFoodAheadNode(Node a, Node b) {
        super(new Node[]{a, b});
    }

    @Override
    public void evaluate(Ant ant) {
        if (ant.hasActionsAvailable()) {
            if (ant.isFoodAhead()) {
                children[0].evaluate(ant);
            } else {
                children[1].evaluate(ant);
            }
        }
    }

    @Override
    public void trace(Ant ant, List<Trace> traceList) {
        if (ant.hasActionsAvailable()) {
            if (ant.isFoodAhead()) {
                children[0].trace(ant, traceList);
            } else {
                children[1].trace(ant, traceList);
            }
        }
    }

    @Override
    public Node clone() {
        Node[] cloned = cloneChildren(children);
        return new IfFoodAheadNode(cloned);
    }


    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    public String getName() {
        return "IfFoodAhead";
    }

    @Override
    public String toString() {
        return "IfFoodAhead(" + children[0] + ", " + children[1] + ")";
    }

}
