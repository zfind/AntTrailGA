package hr.fer.zemris.optjava.dz13.gp.operator;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.solution.Solution;
import hr.fer.zemris.optjava.dz13.gp.tree.TreeFactory;
import hr.fer.zemris.optjava.dz13.gp.tree.node.Node;
import hr.fer.zemris.optjava.rng.RNG;

import java.util.List;

/**
 * Created by zac
 */
public class ExperimentalCrossoverOperator implements ICrossoverOperator {

    private TreeFactory treeFactory;

    public ExperimentalCrossoverOperator(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public Solution cross(Solution p1, Solution p2, Ant costFunction) {
        Node p1Tree = p1.getTree().clone();
        Node p2Tree = p2.getTree().clone();
        List<Node> p1Branch = treeFactory.pickBranch(p1Tree);
        List<Node> p2Branch = treeFactory.pickBranch(p2Tree);

        int p = RNG.nextInt(0, p1Branch.size());
        int q = RNG.nextInt(0, p2Branch.size());

        double p1FoodCollected = p1.getFoodCollected();
        Node node = p2Branch.get(q);
        if (p == 0) {
            p1Tree = node;
            p1FoodCollected = p2.getFoodCollected();
        } else {
            TreeFactory.swapNode(p1Branch, p, node);
        }

        double p2FoodCollected = p2.getFoodCollected();
        node = p1Branch.get(p);
        if (q == 0) {
            p2Tree = node;
            p2FoodCollected = p1.getFoodCollected();
        } else {
            TreeFactory.swapNode(p2Branch, q, node);
        }

        treeFactory.fixNodesCount(p1Tree);
        treeFactory.fixNodesCount(p2Tree);
        Solution s1 = new Solution(p1Tree, p1FoodCollected, costFunction);
        Solution s2 = new Solution(p2Tree, p2FoodCollected, costFunction);

        return (s1.getFitness() >= s2.getFitness()) ? s1 : s2;
    }

}
