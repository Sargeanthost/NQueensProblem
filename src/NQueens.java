import java.util.BitSet;

public class NQueens {
    //Guiding definitions:
    //default value is board[0] - 0
    //bit set: col = n, main = col-row - n-1+defaultValue, minor = col + row - defaultValue
    //lastQueenRowIndex max value is n
    //solution is defined as the n-1st row being filled and a call to isLegal with this board being true.

    public static boolean isLegalPosition(int[] board, int n) {
        //last row to accept partial solutions
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
        for (int column = 0; column < n; column++) {
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
        int lastQueenRowIndex = 0;
        while (lastQueenRowIndex < n && board[lastQueenRowIndex] != 0) {
            lastQueenRowIndex++;
        }
        //if legal, we can move to the next row
        if (isLegalPosition(board, n)) {
            //need to have check that not on last row
            int original = board[lastQueenRowIndex - 1];
            for (int i = board[lastQueenRowIndex - 1] + 1; i <= n + 1; i++) {//<= because i is 1 indexed
                if (i > n) {
                    if (lastQueenRowIndex == n) {
                        //if given solved board and cant move queen over, erase last row
                        board[lastQueenRowIndex - 1] = 0;
                        //and increment previous row, if in bound. if not set to 0, so we dont get back to where we
                        // started
                        if (board[lastQueenRowIndex - 2] == n) {
                            board[lastQueenRowIndex - 2] = 0;
                        } else {
                            board[lastQueenRowIndex - 2] = board[lastQueenRowIndex - 2] + 1;
                        }
                        nextLegalPosition(board, n);
                        break;
                    }
                    board[lastQueenRowIndex - 1] = original;
                    board[lastQueenRowIndex] = 1;
                    nextLegalPosition(board, n);
                    break;
                } else {
                    board[lastQueenRowIndex - 1] = i;
                    if (isLegalPosition(board, n)) {
                        break;
                    }
                }
            }
        } else {
            // if not legal, we have to assume we havent reached a legal position at all on
            // this row, and as such need to back track
            //lastQueenRowIndex - 1 to get last row value;
            //n+1 so that it goes into backtrack
            for (int i = board[lastQueenRowIndex - 1] + 1; i <= n + 1; i++) {
                if (i > n) {
                    //dont need same check since it backtracks automatically
                    board[lastQueenRowIndex - 1] = 0;//backtrack. coming from solution needs to incerment lastqueen-2
                    nextLegalPosition(board, n);
                } else {
                    board[lastQueenRowIndex - 1] = i;
                    if (isLegalPosition(board, n)) {
                        break;
                    }
                }
            }
        }
    }

    //    public static int[] firstSolution(int n) {
    //        int[] board = new int[n];
    //        //seed board;
    //        board[0] = 1;//queen in first row and first column
    //        while (isLegalPosition(board, n) && lastEntry) {
    //            nextLegalPosition(board, n);
    //        }
    //        //solve
    //
    //        return board;
    //    }
}
