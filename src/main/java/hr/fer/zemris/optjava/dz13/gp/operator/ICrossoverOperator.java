package hr.fer.zemris.optjava.dz13.gp.operator;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.solution.Solution;

/**
 * Created by zac
 */
public interface ICrossoverOperator {

    Solution cross(Solution p1, Solution p2, Ant function);

}