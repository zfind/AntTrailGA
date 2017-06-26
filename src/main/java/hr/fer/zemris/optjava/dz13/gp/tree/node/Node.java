package hr.fer.zemris.optjava.dz13.gp.tree.node;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.ant.Trace;

import java.util.List;

/**
 * Created by zac
 */
public abstract class Node {

    public abstract void evaluate(Ant ant);

    public abstract void trace(Ant ant, List<Trace> traceList);

    public abstract Node clone();

    public abstract int getDepth();

    public abstract int getNodeCount();

    public abstract String getName();

    public abstract String toIndentedString();

}
