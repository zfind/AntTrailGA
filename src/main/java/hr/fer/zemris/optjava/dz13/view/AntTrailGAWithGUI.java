package hr.fer.zemris.optjava.dz13.view;

import hr.fer.zemris.optjava.dz13.config.Configuration;
import hr.fer.zemris.optjava.dz13.gp.GeneticProgramming;
import hr.fer.zemris.optjava.dz13.gp.ant.Ant;
import hr.fer.zemris.optjava.dz13.gp.ant.Board;
import hr.fer.zemris.optjava.dz13.gp.ant.Trace;
import hr.fer.zemris.optjava.dz13.gp.initializer.RampedHalfAndHalfInitializer;
import hr.fer.zemris.optjava.dz13.gp.operator.*;
import hr.fer.zemris.optjava.dz13.gp.solution.Solution;
import hr.fer.zemris.optjava.dz13.gp.tree.TreeFactory;
import hr.fer.zemris.optjava.dz13.gp.tree.node.AntNodeFactory;
import hr.fer.zemris.optjava.dz13.gp.tree.node.INodeFactory;
import hr.fer.zemris.optjava.dz13.gp.tree.node.Node;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by zac
 */
public class AntTrailGAWithGUI extends Application {

    private Solution solution;
    private final boolean[][] board;
    private final List<Trace> traceList;

    private BooleanProperty finishedProperty;
    private IntegerProperty iteratorProperty;
    private StringProperty resultProperty;
    private BoardPane boardPane;

    public AntTrailGAWithGUI() {
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

        solution = alg.run();

        Node solutionTree = solution.getTree();
        System.out.println("Node count:" + solutionTree.getNodeCount());
        System.out.println("Depth: " + solutionTree.getDepth());
        System.out.println("Result: " + solution.getFoodCollected());

        this.board = board.getOriginalBoard();
        this.traceList = ant.trace(solution.getTree());

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        List<String> args = getParameters().getRaw();
        if (args.size() == 1) {
            try (PrintWriter out = new PrintWriter(args.get(0))) {
                out.println(solution.getTree().toIndentedString());
            } catch (FileNotFoundException e) {
                System.out.println("Nije moguce zapisati u datoteku: " + args.get(0));
            }
        } else if (args.size() > 1) {
            System.out.println("Nije moguce zapisati u datoteku; previse parametara naredbenog retka");
            System.out.println(args.toString());
        }

        finishedProperty = new SimpleBooleanProperty(false);
        iteratorProperty = new SimpleIntegerProperty(0);
        resultProperty = new SimpleStringProperty("0");

        boardPane = new BoardPane(board);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);

        Label lbSpeed = new Label("Brzina [ms]:");
        TextField tfSpeed = new TextField(Integer.toString(100));
        tfSpeed.setPrefWidth(50);
        tfSpeed.setAlignment(Pos.CENTER);

        Button btNext = new Button("Sljedeci korak");
        btNext.setOnAction(e -> {
            if (finishedProperty.get()) {
                reset();
            }

            int index = iteratorProperty.getValue();
            if (index < traceList.size()) {
                Trace next = traceList.get(index);
                boardPane.visitTile(next);
                resultProperty.setValue(Integer.toString(next.foodCollected));
                iteratorProperty.setValue(index + 1);
            } else {
                finishedProperty.setValue(true);
            }
        });
        hBox.getChildren().add(btNext);

        Button btAuto = new Button("Automatski");
        Button btStop = new Button("Zaustavi");
        btAuto.setOnAction(e -> {
                    if (finishedProperty.get()) {
                        reset();
                    }

                    Task<Void> task = new Task<Void>() {
                        @Override
                        public Void call() {
                            for (int index = iteratorProperty.get(); index < traceList.size(); index++) {
                                Trace trace = traceList.get(index);
                                try {
                                    Thread.sleep(Integer.parseInt(tfSpeed.getText()));
                                } catch (InterruptedException e1) {
                                    iteratorProperty.setValue(index);
                                    return null;
                                }
                                if (isCancelled()) {
                                    iteratorProperty.setValue(index);
                                } else {
                                    Platform.runLater(() -> {
                                        boardPane.visitTile(trace);
                                        resultProperty.setValue(Integer.toString(trace.foodCollected));
                                    });
                                }
                            }
                            finishedProperty.setValue(true);
                            return null;
                        }
                    };
                    Thread thread = new Thread(task);
//                    btStop.setOnAction((e1) -> thread.interrupt());
                    btStop.setOnAction((e1) -> task.cancel());
                    thread.start();
                }
        );
        hBox.getChildren().addAll(btAuto, btStop);

        Button btReset = new Button("Reset");
        btReset.setOnAction(e -> reset());
        hBox.getChildren().add(btReset);

        hBox.getChildren().addAll(lbSpeed, tfSpeed);


        HBox res = new HBox();
        res.setSpacing(10);
        res.setAlignment(Pos.CENTER_RIGHT);
        Label lbResultText = new Label("Rezultat:");
        Label lbResult = new Label();
        lbResult.textProperty().bind(resultProperty);
        res.getChildren().addAll(lbResultText, lbResult);


        HBox controls = new HBox();
        Region reg = new Region();
        HBox.setHgrow(reg, Priority.ALWAYS);
        controls.getChildren().addAll(hBox, reg, res);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(boardPane);
        borderPane.setBottom(controls);
        BorderPane.setMargin(controls, new Insets(10, 10, 10, 10));

        System.out.println("position direction food actions_left");

        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("AntTrailGA");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void reset() {
        boardPane.reset();
        finishedProperty.setValue(false);
        iteratorProperty.setValue(0);
        resultProperty.setValue(Integer.toString(0));
    }

}
