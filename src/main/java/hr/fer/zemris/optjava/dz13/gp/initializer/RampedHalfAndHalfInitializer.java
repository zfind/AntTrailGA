package hr.fer.zemris.optjava.dz13.gp.initializer;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.solution.Solution;
import hr.fer.zemris.optjava.dz13.gp.tree.TreeFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zac
 */
public class RampedHalfAndHalfInitializer {

    public static List<Solution> getInitPopulation(int populationSize, int minDepth, int maxDepth,
                                                   Ant function, TreeFactory treeFactory) {
        List<Solution> population = new ArrayList<>();
        for (int i = minDepth; i <= maxDepth; i++) {
            for (int j = 0; j < (populationSize / (maxDepth - minDepth + 1) / 2); j++) {
                population.add(new Solution(treeFactory.growTree(i, true), function));
                population.add(new Solution(treeFactory.growTree(i, false), function));
            }
        }

        return population;
    }

}