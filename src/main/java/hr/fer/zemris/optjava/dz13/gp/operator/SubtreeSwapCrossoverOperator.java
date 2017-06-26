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
public class SubtreeSwapCrossoverOperator implements ICrossoverOperator {

    private TreeFactory treeFactory;

    public SubtreeSwapCrossoverOperator(TreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public Solution cross(Solution p1, Solution p2, Ant costFunction) {
        Node p1Tree = p1.getTree().clone();
        Node p2Tree = p2.getTree().clone();
        List<Node> p1Branch = treeFactory.pickBranch(p1Tree);
        List<Node> p2Branch = treeFactory.pickBranch(p2Tree);

        int p = RNG.nextInt(0, p1Branch.size());
        int q = RNG.nextInt(0, p2Branch.size());

        double parentFoodCollected = p1.getFoodCollected();

        Node branch = p2Branch.get(q);
        if (p == 0) {
            p1Tree = branch;
            parentFoodCollected = p2.getFoodCollected();
        } else {
            TreeFactory.swapNode(p1Branch, p, branch);
        }

//        if (p1Tree.getDepth() > treeFactory.maxDepth || p1Tree.getNodeCount() > treeFactory.maxNodeCount){
//            Solution solution = p1.getFitness() > p2.getFitness() ? p1 : p2;
//            return new Solution(solution.getTree().clone(), solution.getFoodCollected(), costFunction);
//        }
        treeFactory.fixNodesCount(p1Tree);

        return new Solution(p1Tree, parentFoodCollected, costFunction);
    }

}
