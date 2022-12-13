import java.util.BitSet;
import java.util.HashSet;

public class NQueens {

    public static boolean isLegalPosition(int[] board, int n) {
        int lastRow = n;
        HashSet<Integer> seen = new HashSet<>();
        for (int column = 0; column < lastRow; column++) {
            if (board[column] == 0) {
                lastRow = column;
                break;
            }
            if (!seen.add(board[column])) {
                return false;
            }
        }
        //maj min diag check.
        //offsets should be equal if they're on the same diagonal. trivial to see if on main diag where (0,0),(1,1)
        // interfere: 0-1 = -1, 0-1 = -1.
        for (int currentRow = 0; currentRow < lastRow; currentRow++) {
            for (int targetRow = currentRow + 1; targetRow < lastRow; targetRow++) { //if we interfere with a previous
                // row, then that previous row interferes with us, so start at +1 previous.
                if (targetRow == currentRow) {
                    continue;
                }
                int rowDif = Math.abs(currentRow - targetRow);
                int colDif = Math.abs(board[currentRow] - board[targetRow]);
                if (colDif == rowDif) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO allowed to have another method thats optimized?
    private static boolean isLegalPositionForBuilding(int[] board, int lastEntry, BitSet main, BitSet minor,
                                                      BitSet col) {
        //col
        return false;
    }

    public static long numberOfSolutions(int n) {
        long[] answer = {0};//trick to pass by reference
        BitSet col = new BitSet(n);
        BitSet major = new BitSet(2 * n - 1);
        BitSet minor = new BitSet(2 * n - 1);
        numberOfSolutions(0, n, answer, col, major, minor);
        return answer[0];
        //        int[] board = new int[n];
        //        BitSet col = new BitSet(n);
        //        //pos offset
        //        BitSet major = new BitSet(2 * n - 1);
        //        //minor offset
        //        BitSet minor = new BitSet(2 * n - 1);
        //        //each queen will add one entry to each bitset
        //        int solutions = 0;
        //        for (int column = 0; column < n; column++) {
        //            //TODO go through all columns for n sized board.
        //        }
        //        //definition of solution
        //        //if isLegalPosition == true && lastEntry == n-1 (last row has queen)
        //        return solutions;

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
