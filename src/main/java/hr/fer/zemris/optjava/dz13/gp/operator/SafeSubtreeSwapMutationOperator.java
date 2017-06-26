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
public class SafeSubtreeSwapMutationOperator implements IMutationOperator {

    private TreeFactory treeFactory;
    private final int maxDepth;

    public SafeSubtreeSwapMutationOperator(TreeFactory treeFactory, int maxDepth) {
        this.treeFactory = treeFactory;
        this.maxDepth = maxDepth;
    }

    public Solution mutate(Solution s, Ant costFunction) {
        Node tree = s.getTree().clone();
        List<Node> branch = treeFactory.pickBranch(tree);

        int picked = RNG.nextInt(0, branch.size());
        int newBranchMaxDepth = Math.max(1, maxDepth - picked);
        int newBranchDepth = RNG.nextInt(0, newBranchMaxDepth);
        Node newBranch = treeFactory.growTree(newBranchDepth, true);
        if (picked == 0) {
            tree = newBranch;
        } else {
            TreeFactory.swapNode(branch, picked, newBranch);
        }

        if (tree.getNodeCount() > treeFactory.maxNodeCount) {
            return new Solution(s.getTree().clone(), s.getFoodCollected(), costFunction);
        }
//        treeFactory.fixNodesCount(tree);

        return new Solution(tree, s.getFoodCollected(), costFunction);
    }

}
