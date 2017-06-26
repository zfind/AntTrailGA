package hr.fer.zemris.optjava.dz13.gp.tree.node;

import hr.fer.zemris.optjava.dz13.gp.function.Action;

/**
 * Created by zac
 */
public class LeftNode extends TerminalNode {

    public LeftNode() {
        super(Action.getLeftActionInstance());
    }

    @Override
    public String getName() {
        return "Left";
    }

}
