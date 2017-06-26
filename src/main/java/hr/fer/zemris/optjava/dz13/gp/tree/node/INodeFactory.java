package hr.fer.zemris.optjava.dz13.gp.tree.node;

/**
 * Created by zac
 */
public interface INodeFactory {

    Node getRandomTermNodeInstance();

    Node getTermNodeInstance(int id);

    int chooseRandomFunction();

    int getArity(int funcId);

    Node getFuncNodeInstance(int choice, Node[] children);

    double getRatio();

    Node swapWithSameArityFunction(FunctionNode node);

}
