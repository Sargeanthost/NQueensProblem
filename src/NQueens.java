import java.util.BitSet;
import java.util.HashSet;

public class NQueens {
    //bit set: col = n, main = col-row - n-1+1, minor = col + row - 1

    public static boolean isLegalPosition(int[] board, int n) {
        //last row
        int lastRow = 0;
        while (lastRow < n - 1 && board[lastRow] != 0) {
            lastRow++;
        }
        BitSet col = new BitSet(n);
        BitSet major = new BitSet(2 * n - 1);
        BitSet minor = new BitSet(2 * n - 1);

        for (int row = 0; row <= lastRow; row++) {
            if (!col.get(board[row]) && !major.get(board[row] - row + n - 2) && !minor.get(board[row] + row - 1)) {
                col.set(board[row], true);
                major.set(board[row] - row + n - 2, true);
                minor.set(board[row] + row - 1, true);
            } else {
                //if theres already something in the col, either diag
                return false;
            }
        }
        return true;
    }

    //TODO allowed to have another method thats optimized?
    private static boolean isLegalPositionForBuilding(int[] board, int lastEntry, BitSet main, BitSet minor,
                                                      BitSet col) {

        return false;
    }

    public static long numberOfSolutions(int n) {
        long[] answer = {0};//trick to pass by reference
        BitSet col = new BitSet(n);
        BitSet major = new BitSet(2 * n - 1);
        BitSet minor = new BitSet(2 * n - 1);
        numberOfSolutions(0, n, answer, col, major, minor);
        return answer[0];
    }

    private static void numberOfSolutions(int lastRow, int n, long[] solutions, BitSet col, BitSet major,
                                          BitSet minor) {
        if (lastRow == n) {
            solutions[0] += 1;
            return;
        }
        for (int column = 0; column < n; column++) {
            //dont use defaultValue subtraction because were not working on array, just the truth values that would
            // be at a certain index;
            if (!col.get(column) && !major.get(column - lastRow + n - 1) && !minor.get(lastRow + column)) {
                col.set(column, true);
                major.set(column - lastRow + n - 1, true);
                minor.set(lastRow + column, true);
                numberOfSolutions(lastRow + 1, n, solutions, col, major, minor);//checks each column on current
                // row.
                //have to unset the failed positions
                col.set(column, false);
                major.set(column - lastRow + n - 1, false);
                minor.set(lastRow + column, false);
            }
        }
    }

    /**
     * Modifies board to have the next legal move. Will override solved boards
     *
     * @param board the board to modify
     * @param n
     */
    private static void nextLegalPosition(int[] board, int n) {

    }

    public static int[] firstSolution(int n) {
        int[] board = new int[n];
        BitSet col = new BitSet(n);
        //pos slopes
        //indexing offset. col - row + n - 1 + defaultOffset
        //default offset is 1-0 = 1, so "n-2"
        final int OFFSET = n - 2;//major
        BitSet major = new BitSet(2 * n - 1);
        //neg slopes
        //col+row - defaultOffset := 1
        BitSet minor = new BitSet(2 * n - 1);
        //seed queen
        board[0] = 1;
        col.set(0, true);
        major.set(1 + OFFSET, true);
        //TODO minor math
        minor.set(0, true);
        //solve

        return board;
    }
}
