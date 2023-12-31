package project6;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class SlidingPuzzleState extends State {

    private int width;
    private int height;
    private int row;
    private int col;
    private char[][] tiles;

    public SlidingPuzzleState(String boardString) {
        String[] rows = boardString.split("\\|");
        this.width = 0;
        for (String line : rows) {
            if (line.length() > width) {
                this.width = line.length();
            }
        }
        this.height = rows.length;
        this.tiles = new char[height][width];
        HashSet<Character> chars = new HashSet<Character>();
        for (int r = 0; r < this.height; r++) {
            if (rows[r].length() != this.width) {
                throw new IllegalArgumentException("board is not a rectangle");
            }
            for (int c = 0; c < this.width; c++) {
                char boardChar = rows[r].charAt(c);
                if (chars.contains(boardChar)) {
                    throw new IllegalArgumentException("character '" + boardChar + "' used more than once");
                }
                if (boardChar == ' ') {
                    this.row = r;
                    this.col = c;
                }
                this.tiles[r][c] = boardChar;
            }
        }
    }

    public List<Action> getActions() {
        List<Action> actions = new ArrayList<Action>();
        if (this.row > 0) {
            actions.add(new Action(
                "up", 1,
                new SlidingPuzzleState(this.toString(this.row - 1, this.col))
            ));
        }
        if (this.row < this.height - 1) {
            actions.add(new Action(
                "down", 1,
                new SlidingPuzzleState(this.toString(this.row + 1, this.col))
            ));
        }
        if (this.col > 0) {
            actions.add(new Action(
                "left", 1,
                new SlidingPuzzleState(this.toString(this.row, this.col - 1))
            ));
        }
        if (this.col < this.width - 1) {
            actions.add(new Action(
                "right", 1,
                new SlidingPuzzleState(this.toString(this.row, this.col + 1))
            ));
        }
        return actions;
    }

    private int findChar(char boardChar) {
        for (int r = 0; r < this.height; r++) {
            for (int c = 0; c < this.width; c++) {
                if (this.tiles[r][c] == boardChar) {
                    return r * this.width + c;
                }
            }
        }
        return -1;
    }

    public int heuristicTo(State state) {
        if (state == null || this.getClass() != state.getClass()) {
            throw new IllegalArgumentException("tried to get heuristic to state from another puzzle");
        }
        SlidingPuzzleState goal = (SlidingPuzzleState) state;
        int total = 0;
        for (int r = 0; r < this.height; r++) {
            for (int c = 0; c < this.width; c++) {
                char boardChar = this.tiles[r][c];
                int posCode = goal.findChar(this.tiles[r][c]);
                int goalRow = posCode / this.width;
                int goalCol = posCode % this.width;
                total += (
                    Math.abs(r - goalRow)
                    + Math.abs(c - goalCol)
                );
            }
        }
        return total;
    }

    public String toString() {
        return this.toString(this.row, this.col);
    }

    private String toString(int rowPos, int colPos) {
        String result = "";
        for (int r = 0; r < this.height; r++) {
            for (int c = 0; c < this.width; c++) {
                char boardChar = this.tiles[r][c];
                if (r == rowPos && c == colPos) {
                    result += ' ';
                } else if (boardChar == ' ') {
                    result += this.tiles[rowPos][colPos];
                } else {
                    result += this.tiles[r][c];
                }
            }
            result += "|";
        }
        return result.substring(0, result.length() - 1);
    }

    public void print() {
        System.out.print("+");
        for (int i = 0; i < this.width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        for (String line : this.toString().split("\\|")) {
            System.out.println("|" + line + "|");
        }
        System.out.print("+");
        for (int i = 0; i < this.width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

}