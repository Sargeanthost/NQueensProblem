import java.util.BitSet;

public class NQueens {
    //Guiding definitions:
    //default value is board[0] - 0
    //bit set: col = n, main = col-row - n-1+defaultValue, minor = col + row - defaultValue
    //lastQueenRowIndex max value is n
    //solution is defined as the n-1st row being filled and a call to isLegal with this board being true.

    public static boolean isLegalPosition(int[] board, int n) {
        //last row to accept partial solutions
        //bitset to keep memory low
        int lastQueenRowIndex = 0;
        while (lastQueenRowIndex < n && board[lastQueenRowIndex] != 0) {
            lastQueenRowIndex++;
        }
        BitSet col = new BitSet(n);
        BitSet major = new BitSet(2 * n - 1);
        BitSet minor = new BitSet(2 * n - 1);
        for (int row = 0; row < lastQueenRowIndex; row++) {
            if (!col.get(board[row]) && !major.get(board[row] - row + n - 2) && !minor.get(board[row] + row - 1)) {
                col.set(board[row], true);
                major.set(board[row] - row + n - 2, true);
                minor.set(board[row] + row - 1, true);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Optimised way of finding number of solutions that doesn't depend on a matrix being generated. Simply checks
     * that if a queen were to be placed in a column, that it does not have interference. It then creates this
     * interference. <br>
     * Goes through n columns and could be though of as seeding columns of first row and finding the solutions to
     * those, and add this to a continuous total.
     *
     * @param n the size of the board to find total solutions for
     * @return the number of solutions
     */
    public static long numberOfSolutions(int n) {
        long[] numberOfSolutions = {0};//trick to pass by reference
        BitSet col = new BitSet(n);
        BitSet major = new BitSet(2 * n - 1);
        BitSet minor = new BitSet(2 * n - 1);
        numberOfSolutions(0, n, numberOfSolutions, col, major, minor);
        return numberOfSolutions[0];
    }

    private static void numberOfSolutions(int lastQueenRowIndex, int n, long[] solutions, BitSet col, BitSet major,
                                          BitSet minor) {
        if (lastQueenRowIndex == n) {
            solutions[0] += 1;
            return;
        }
        for (int column = 0; column < n; column++) {//and row
            //dont use defaultValue subtraction because were not working on array, just the truth values that would
            // be at a certain index;
            //bring isLegal function inline to have better program flow
            if (!col.get(column) && !major.get(column - lastQueenRowIndex + n - 1) && !minor.get(
                lastQueenRowIndex + column)) {
                col.set(column, true);
                major.set(column - lastQueenRowIndex + n - 1, true);
                minor.set(lastQueenRowIndex + column, true);
                //checks each column on current row.
                numberOfSolutions(lastQueenRowIndex + 1, n, solutions, col, major, minor);
                //have to unset the failed positions since we now move past the column
                col.set(column, false);
                major.set(column - lastQueenRowIndex + n - 1, false);
                minor.set(lastQueenRowIndex + column, false);
            }
        }
    }

    /**
     * Modifies board to have the next legal move.
     *
     * @param board the board to modify
     * @param n     size of board
     */
    public static void nextLegalPosition(int[] board, int n) {
        int lastQueenRowIndex = 0;//mutates at capacity
        while (lastQueenRowIndex < n && board[lastQueenRowIndex] != 0) {
            lastQueenRowIndex++;
        }
        if (lastQueenRowIndex == n || !isLegalPosition(board, n)) {
            lastQueenRowIndex--;
        }
        out:
        for (int row = lastQueenRowIndex; row >= 0; row--) {
            for (int col = board[row]+1; col <= n+1; col++) {
                if (col==n+1){
                    board[row] = 0;
                    break;
                }
                board[row] = (col);
                if (isLegalPosition(board, n)) {
                    break out;
                }
            }
        }
    }

    public static int[] firstSolution(int n) {
        int[] board = new int[n];
        BitSet col = new BitSet(n);
        BitSet major = new BitSet(2 * n - 1);
        BitSet minor = new BitSet(2 * n - 1);
        int[] solutions = new int[] {0};
        findFirstSolution(0, board, n, solutions, col, major, minor);
        return board;
    }

    private static void findFirstSolution(int lastQueenRowIndex, int[] board, int n, int[] solutions, BitSet col,
                                          BitSet major, BitSet minor) {
        if (lastQueenRowIndex == n) {
            solutions[0] += 1;
            return;
        }
        for (int column = 0; column < n; column++) {
            if (solutions[0] > 0) {
                break;
            }
            if (!col.get(column) && !major.get(column - lastQueenRowIndex + n - 1) && !minor.get(
                lastQueenRowIndex + column)) {
                setBoard(board, lastQueenRowIndex, column + 1);
                col.set(column, true);
                major.set(column - lastQueenRowIndex + n - 1, true);
                minor.set(lastQueenRowIndex + column, true);
                //checks each column on current row.
                findFirstSolution(lastQueenRowIndex + 1, board, n, solutions, col, major, minor);
                //have to unset the failed positions since we now move past the column
                col.set(column, false);
                major.set(column - lastQueenRowIndex + n - 1, false);
                minor.set(lastQueenRowIndex + column, false);
            }
        }
    }

    private static void setBoard(int[] board, int row, int col) {
        board[row] = col;
    }
}
