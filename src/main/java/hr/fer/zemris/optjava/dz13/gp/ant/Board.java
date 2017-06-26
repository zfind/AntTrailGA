package hr.fer.zemris.optjava.dz13.gp.ant;

import java.io.*;

/**
 * Created by zac
 */
public class Board {

    public int height;
    public int width;

    private boolean[][] board;
    private boolean[][] originalBoard;


    public Board(String path) {
        parse(path);
    }

    public boolean[][] getOriginalBoard() {
        boolean[][] clone = new boolean[originalBoard.length][];
        for (int i = 0; i < originalBoard.length; i++) {
            clone[i] = originalBoard[i].clone();
        }
        return clone;
    }

    public void reset() {
        board = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(originalBoard[i], 0, board[i], 0, width);
        }
    }

    private boolean isFoodAt(int i, int j) {
        return board[i][j];
    }

    public boolean isFoodAt(Position position) {
        return isFoodAt(position.getY(), position.getX());
    }

    private void pickUp(int i, int j) {
        board[i][j] = false;
    }

    public void pickUp(Position position) {
        pickUp(position.getY(), position.getX());
    }


    private void parse(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String line;

            line = reader.readLine();
            String[] xx = line.trim().split("x");
            height = Integer.parseInt(xx[0]);
            width = Integer.parseInt(xx[1]);

            board = new boolean[height][width];
            originalBoard = new boolean[height][width];

            for (int i = 0; i < height; i++) {
                line = reader.readLine();
                xx = line.trim().split("");
                for (int j = 0; j < width; j++) {
                    board[i][j] = originalBoard[i][j] = !(xx[j].equals(".") || xx[j].equals("0"));
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Greska. Nije moguce parsirati mapu");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print((board[i][j]) ? "1" : ".");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board board = new Board("13-SantaFeAntTrail.txt");
        board.print();

        Position position = new Position(8, 5, board.width, board.height);

        boolean test = board.isFoodAt(position);
        System.out.println(test);

        board.pickUp(position);
        test = board.isFoodAt(position);
        System.out.println(test);

        board.reset();
        test = board.isFoodAt(position);
        System.out.println(test);
    }

}
