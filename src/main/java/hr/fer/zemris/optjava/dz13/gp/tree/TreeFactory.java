package hr.fer.zemris.optjava.dz13.gp.tree;

import hr.fer.zemris.optjava.dz13.gp.tree.node.FunctionNode;
import hr.fer.zemris.optjava.dz13.gp.tree.node.INodeFactory;
import hr.fer.zemris.optjava.dz13.gp.tree.node.Node;
import hr.fer.zemris.optjava.rng.RNG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac
 */
public class TreeFactory {

    private INodeFactory nodeFactory;
    public final int maxDepth;
    public final int maxNodeCount;

    public TreeFactory(INodeFactory nodeFactory, int maxDepth, int maxNodeCount) {
        this.nodeFactory = nodeFactory;
        this.maxDepth = maxDepth;
        this.maxNodeCount = maxNodeCount;
    }

    public Node growTree(int depth, boolean grow) {
        Node expr;
        if (depth <= 1 || (grow && RNG.nextDouble() < nodeFactory.getRatio())) {
            expr = nodeFactory.getRandomTermNodeInstance();
        } else {
            int func = nodeFactory.chooseRandomFunction();
            Node[] children = new Node[nodeFactory.getArity(func)];
            for (int i = 0; i < children.length; i++) {
                children[i] = growTree(depth - 1, grow);
            }
            expr = nodeFactory.getFuncNodeInstance(func, children);
        }
        return expr;
    }

    public static void swapNode(List<Node> branch, int picked, Node subtree) {
        Node pickedNode = branch.get(picked);
        Node parentNode = branch.get(picked - 1);
        Node[] parentChildren = ((FunctionNode) parentNode).getChildren();
        int pickedIndex;
        for (pickedIndex = 0; pickedIndex < parentChildren.length; pickedIndex++) {
            if (pickedNode.equals(parentChildren[pickedIndex])) {
                break;
            }
        }
        parentChildren[pickedIndex] = subtree;
    }

    public List<Node> pickBranch(Node tree) {
        List<Node> branch = new ArrayList<>();
        Node node = tree;
        branch.add(node);
        while (true) {
            if (node instanceof FunctionNode) {
                node = ((FunctionNode) node).getRandomChild();
                branch.add(node);
            } else {
                break;
            }
        }
        return branch;
    }

    private void cutPartOfBranch(Node tree) {
        int currentDepth = tree.getDepth();
        List<Node> branch;
        while (true) {
            branch = pickBranch(tree);
            if (branch.size() > currentDepth / 2) {
                break;
            }
        }
        Node leaf = growTree(1, true);
        int picked = RNG.nextInt(branch.size() / 2, branch.size());
        Node pickedNode = branch.get(picked);
        Node parentNode = branch.get(picked - 1);
        Node[] parentChildren = ((FunctionNode) parentNode).getChildren();
        int pickedIndex;
        for (pickedIndex = 0; pickedIndex < parentChildren.length; pickedIndex++) {
            if (pickedNode == parentChildren[pickedIndex]) {
                break;
            }
        }
        parentChildren[pickedIndex] = leaf;
    }

    public void fixNodesCount(Node tree) {
        while (tree.getDepth() > maxDepth || tree.getNodeCount() > maxNodeCount) {
            cutPartOfBranch(tree);
        }
    }

}
