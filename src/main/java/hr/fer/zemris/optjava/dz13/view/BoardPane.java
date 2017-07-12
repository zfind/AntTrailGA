package hr.fer.zemris.optjava.dz13.view;

import hr.fer.zemris.optjava.dz13.gp.ant.Trace;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * Created by zac
 */
public class BoardPane extends GridPane {

    private boolean[][] originalBoard;
    private Tile[][] tiles;
    private Tile current;

    public BoardPane(boolean[][] board) {
        super();
        this.setAlignment(Pos.CENTER);

        this.originalBoard = board;
        tiles = new Tile[board.length][board[0].length];

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                Tile tile = new Tile(board[y][x], 20, 20);
                this.add(tile, x, y);
                tiles[y][x] = tile;
            }
        }
        current = tiles[0][0];
        current.visit(0);
    }

    public void visitTile(Trace trace) {
        current.leave();
        current = tiles[trace.y][trace.x];
        current.visit(trace.direction);
        System.out.println(trace);
    }

    public void reset() {
        current.leave();
        for (int y = 0; y < originalBoard.length; y++) {
            for (int x = 0; x < originalBoard[0].length; x++) {
                tiles[y][x].set(originalBoard[y][x]);
            }
        }
        current = tiles[0][0];
        current.visit(0);
    }

}
