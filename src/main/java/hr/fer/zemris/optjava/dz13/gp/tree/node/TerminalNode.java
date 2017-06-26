package hr.fer.zemris.optjava.dz13.gp.tree.node;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.ant.Trace;
import hr.fer.zemris.optjava.dz13.gp.function.Action;

import java.util.List;

/**
 * Created by zac
 */
public abstract class TerminalNode extends Node {

    private Action action;

    public TerminalNode(Action action) {
        this.action = action;
    }

    @Override
    public void evaluate(Ant ant) {
        action.execute(ant);
    }

    @Override
    public void trace(Ant ant, List<Trace> traceList) {
        action.execute(ant);
        traceList.add(ant.getTrace());
    }

    @Override
    public Node clone() {
        return this;
    }

    @Override
    public int getDepth() {
        return 1;
    }

    @Override
    public int getNodeCount() {
        return 1;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String toIndentedString() {
        return getName();
    }

}
