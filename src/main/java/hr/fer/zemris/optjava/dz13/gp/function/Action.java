package hr.fer.zemris.optjava.dz13.gp.function;

/**
 * Created by zac
 */
public abstract class Action implements IFunction {

    private static final Action left;
    private static final Action right;
    private static final Action move;

    static {
        left = new LeftAction();
        right = new RightAction();
        move = new MoveAction();
    }

    public static Action getLeftActionInstance() {
        return left;
    }

    public static Action getRightActionInstance() {
        return right;
    }

    public static Action getMoveActionInstance() {
        return move;
    }

}