package hr.fer.zemris.optjava.dz13.gp.operator;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.solution.Solution;
import hr.fer.zemris.optjava.dz13.gp.tree.node.Node;

/**
 * Created by zac
 */
public class ReproductionOperator implements IReproductionOperator {

    public Solution reproduce(Solution solution, Ant fitnessFunction) {
        Node clone = solution.getTree().clone();
        return new Solution(clone, solution.getFoodCollected(), fitnessFunction);
    }

}