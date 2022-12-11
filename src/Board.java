import java.util.Arrays;
import java.util.HashSet;

public class Board {
    /**
     * index is 0 to n-1, while elements are 1 to n
     */
    int[] board;
    /**
     * The row on which the last queen is found. for a full board where n=8, this would be 7;
     */
    private int lastEntry;
    private final int n; //nxn

    public Board(int[] board, int lastEntry) {
        this.board = board;
        this.lastEntry = lastEntry;
        n = board.length;
    }

    public Board() {
        n = 8;
        board = new int[n];
        lastEntry = -1;
    }

    /**
     * Checks board legality with no assumptions, and should be used when checking an arbitrary board. Works in O(n^2)
     *
     * @param board the board
     * @param n     the size of the board
     * @return returns whether the board is in a legal position
     */
    public static boolean isLegalPosition(int[] board, int n) {
        //col check
        HashSet<Integer> seen = new HashSet<>();
        for (int position : board) {
            if (!seen.add(position)) {
                return false;
            }
        }
        //maj min diag check.
        //offsets should be equal if they're on the same diagonal. trivial to see if on main diag where (0,0),(1,1)
        // interfere: 0-1 = -1, 0-1 = -1.
        for (int currentRow = 0; currentRow < n; currentRow++) {
            for (int targetRow = currentRow + 1; targetRow < n; targetRow++) { //if we interfere with a previous
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

    /**
     * Appends a queen into the next row on a non-full board. Must check legality of board after calling this function.
     *
     * @param col the column to insert a queen into
     * @throws Exception throws an exception if the board is already full and a queen tries to be added to the board.
     */
    private void addQueen(int col) throws Exception {
        if (lastEntry == board.length - 1) {
            throw new Exception("Cannot insert a queen into an already full board.");
        } else if (col < 1 || col > 8) {
            throw new Exception("Cannot place a queen off of the board.");
        }
        board[lastEntry++] = col;
    }
}
