package hr.fer.zemris.optjava.dz13.gp.solution;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.tree.node.Node;

/**
 * Created by zac
 */
public class Solution implements Comparable<Solution> {

    private static final double FOOD_AVAILABLE = 89;
    private static final double PLAGIARISM = 0.9;

    private Node tree;
    private double foodCollected;
    private final double parentCollected;
    private double fitness;

    public Solution(Node tree, Ant ant) {
        this.tree = tree;
        this.parentCollected = 0;
        this.fitness = -(FOOD_AVAILABLE - evaluate(ant));
    }

    public Solution(Node tree, double parentCollected, Ant ant) {
        this.tree = tree;
        this.parentCollected = parentCollected;
        this.fitness = -(FOOD_AVAILABLE - evaluate(ant));
    }

    private double evaluate(Ant ant) {
        this.foodCollected = ant.evaluate(tree);
        double plagCoef = foodCollected == parentCollected ? 1 : PLAGIARISM;
        return plagCoef * foodCollected;
    }

    public Node getTree() {
        return tree;
    }

    public double getFoodCollected() {
        return foodCollected;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Solution o) {
        return Double.compare(this.fitness, o.fitness);
    }

}
