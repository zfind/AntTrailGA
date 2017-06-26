package hr.fer.zemris.optjava.dz13;

import hr.fer.zemris.optjava.dz13.config.Configuration;
import hr.fer.zemris.optjava.dz13.gp.GeneticProgramming;
import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.ant.Board;
import hr.fer.zemris.optjava.dz13.gp.initializer.RampedHalfAndHalfInitializer;
import hr.fer.zemris.optjava.dz13.gp.operator.*;
import hr.fer.zemris.optjava.dz13.gp.solution.Solution;
import hr.fer.zemris.optjava.dz13.gp.tree.TreeFactory;
import hr.fer.zemris.optjava.dz13.gp.tree.node.AntNodeFactory;
import hr.fer.zemris.optjava.dz13.gp.tree.node.INodeFactory;
import hr.fer.zemris.optjava.dz13.gp.tree.node.Node;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by zac
 */
public class AntTrailGA {

    public static void main(String args[]) {
        Configuration config = new Configuration();

        Board board = new Board("13-SantaFeAntTrail.txt");
        Ant ant = new Ant(board, config.getFoodAvailable(), config.getMaxActions());

        INodeFactory nodeFactory = new AntNodeFactory();
        TreeFactory treeFactory = new TreeFactory(nodeFactory, config.getMaxDepth(), config.getMaxNodeCount());

        ICrossoverOperator crossoverOperator = new ExperimentalCrossoverOperator(treeFactory);
        IMutationOperator mutationOperator = new SafeSubtreeSwapMutationOperator(treeFactory, config.getMaxDepth());
        IReproductionOperator reproductionOperator = new ReproductionOperator();
        ISelection tournamentSelection = new TournamentSelection(config.getTournamentSize());

        List<Solution> initialPopulation = RampedHalfAndHalfInitializer.getInitPopulation(
                config.getPopulationSize(), 2, 6,
                ant, treeFactory
        );

        GeneticProgramming alg = new GeneticProgramming(
                ant,
                crossoverOperator,
                mutationOperator,
                reproductionOperator,
                tournamentSelection,
                initialPopulation,
                config.getCrossoverProbability(),
                config.getMutationProbability(),
                config.getReproductionProbability(),
                config.getPopulationSize(),
                config.getMaxIterations(),
                0.
        );

        Solution solution = alg.run();

        Node solutionTree = solution.getTree();
        System.out.println("Node count:" + solutionTree.getNodeCount());
        System.out.println("Depth: " + solutionTree.getDepth());
        System.out.println("Result: " + solution.getFoodCollected());

        if (args.length == 1) {
            try (PrintWriter out = new PrintWriter(args[0])) {
                out.println(solution.getTree().toIndentedString());
            } catch (FileNotFoundException e) {
                System.out.println("Nije moguce zapisati u datoteku: " + args[0]);
            }
        } else if (args.length > 1) {
            System.out.println("Nije moguce zapisati u datoteku; previse parametara naredbenog retka");
            System.out.println(args);
        }

    }

}
