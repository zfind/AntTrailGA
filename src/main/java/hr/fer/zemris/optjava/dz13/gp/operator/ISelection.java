package hr.fer.zemris.optjava.dz13.gp.operator;

import hr.fer.zemris.optjava.dz13.gp.solution.Solution;

import java.util.List;

/**
 * Created by zac
 */
public interface ISelection {

    Solution select(List<Solution> population);

}