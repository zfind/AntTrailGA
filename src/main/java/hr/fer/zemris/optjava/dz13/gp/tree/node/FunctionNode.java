package hr.fer.zemris.optjava.dz13.gp.tree.node;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.ant.Trace;
import hr.fer.zemris.optjava.rng.RNG;

import java.util.List;

/**
 * Created by zac
 */
public abstract class FunctionNode extends Node {

    protected Node[] children;

    public FunctionNode(Node[] children) {
        this.children = children;
    }

    @Override
    public void evaluate(Ant ant) {
        for (Node node : children) {
            if (ant.hasActionsAvailable()) {
                node.evaluate(ant);
            }
        }
    }

    @Override
    public void trace(Ant ant, List<Trace> traceList) {
        for (Node node : children) {
            if (ant.hasActionsAvailable()) {
                node.trace(ant, traceList);
            }
        }
    }

    @Override
    public int getDepth() {
        int childrenDepth = 0;
        for (Node child : children) {
            childrenDepth = Math.max(childrenDepth, child.getDepth());
        }
        return 1 + childrenDepth;
    }

    @Override
    public int getNodeCount() {
        int childrenSize = 0;
        for (Node child : children) {
            childrenSize += child.getNodeCount();
        }
        return 1 + childrenSize;
    }

    public Node[] getChildren() {
        return children;
    }

    public Node getRandomChild() {
        return children[RNG.nextInt(0, children.length)];
    }

    public abstract int getArity();

    protected static Node[] cloneChildren(Node[] children) {
        Node[] cloned = new Node[children.length];
        for (int i = 0; i < children.length; i++) {
            cloned[i] = children[i].clone();
        }
        return cloned;
    }

    @Override
    public String toIndentedString() {
        StringBuilder sb = new StringBuilder(getName());
        for (Node child : children) {
            sb.append(System.lineSeparator());
            sb.append(child.toIndentedString().replaceAll("(?m)^", "|  "));
        }
        return sb.toString();
    }

}
