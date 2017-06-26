package hr.fer.zemris.optjava.dz13.config;

/**
 * Created by zac
 */
public class Configuration {

    public static final int MAX_ACTIONS = 600;
    public static final int FOOD_AVAILABLE = 89;

    public static final int MAX_ITER = 100;
    public static final int POP_SIZE = 3000;
    public static final int MAX_NODE_COUNT = 100;
    public static final int MAX_DEPTH = 15;

    public static final double CROSSING = 0.85;
    public static final double MUTATION = 0.14;
    public static final double REPRODUCTION = 0.01;
    public static final int TOURNAMENT = 5;

    public int getFoodAvailable() {
        return FOOD_AVAILABLE;
    }

    public double getReproductionProbability() {
        return REPRODUCTION;
    }

    public double getMutationProbability() {
        return MUTATION;
    }

    public double getCrossoverProbability() {
        return CROSSING;
    }

    public int getTournamentSize() {
        return TOURNAMENT;
    }

    public int getMaxActions() {
        return MAX_ACTIONS;
    }

    public int getMaxNodeCount() {
        return MAX_NODE_COUNT;
    }

    public int getMaxDepth() {
        return MAX_DEPTH;
    }

    public int getMaxIterations() {
        return MAX_ITER;
    }

    public int getPopulationSize() {
        return POP_SIZE;
    }

}
