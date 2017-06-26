package hr.fer.zemris.optjava.dz13.gp.tree.node;

import hr.fer.zemris.optjava.dz13.gp.function.Action;

/**
 * Created by zac
 */
public class RightNode extends TerminalNode {

    public RightNode() {
        super(Action.getRightActionInstance());
    }

    @Override
    public String getName() {
        return "Right";
    }

}