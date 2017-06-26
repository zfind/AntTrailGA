package hr.fer.zemris.optjava.dz13.gp.function;

import hr.fer.zemris.optjava.dz13.gp.ant.Ant;

/**
 * Created by zac
 */
public class MoveAction extends Action {
    @Override
    public void execute(Ant ant) {
        ant.moveForward();
    }
}