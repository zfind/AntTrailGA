package hr.fer.zemris.optjava.dz13.gp.tree.node;

import hr.fer.zemris.optjava.dz13.gp.function.Action;

/**
 * Created by zac
 */
public class MoveNode extends TerminalNode {

    public MoveNode() {
        super(Action.getMoveActionInstance());
    }

    @Override
    public String getName() {
        return "Move";
    }

}
