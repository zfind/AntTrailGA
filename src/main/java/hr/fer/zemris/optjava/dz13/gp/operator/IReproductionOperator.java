package hr.fer.zemris.optjava.dz13.gp.operator;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.solution.Solution;

/**
 * Created by zac
 */
public interface IReproductionOperator {

    Solution reproduce(Solution solution, Ant fitnessFunction);

}