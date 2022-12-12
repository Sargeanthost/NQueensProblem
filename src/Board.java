import java.util.HashSet;

public class Board {
    /**
     * index is 0 to n-1, while elements are 1 to n
     */
    int[] queenPositions;
    /**
     * The row index on which the last queen is found. for a full board where n=8, this would be 7;
     */
    private int lastEntryIndex;
    final int n; //nxn

    public Board(int[] board, int lastEntryIndex) {
        this.queenPositions = board;
        this.lastEntryIndex = lastEntryIndex;
        n = board.length;
    }

    public Board() {
        n = 8;
        queenPositions = new int[n];
        lastEntryIndex = -1;
    }

    /**
     * Checks board legality with no assumptions, and should be used when checking an arbitrary board. Works in O(n^2)
     *
     * @param board the board
     * @param n     the size of the board
     * @return returns whether the board is in a legal position
     */
    public static boolean isLegalPosition(Board board, int n) {
        //col check
        HashSet<Integer> seen = new HashSet<>();
        for(int column = 0; column <= board.lastEntryIndex; column++){
            if (!seen.add(board.queenPositions[column])) {
                return false;
            }
        }
        //maj min diag check.
        //offsets should be equal if they're on the same diagonal. trivial to see if on main diag where (0,0),(1,1)
        // interfere: 0-1 = -1, 0-1 = -1.
        for (int currentRow = 0; currentRow <= board.lastEntryIndex; currentRow++) {
            for (int targetRow = currentRow + 1; targetRow <= board.lastEntryIndex; targetRow++) { //if we interfere with a previous
                // row, then that previous row interferes with us, so start at +1 previous.
                if (targetRow == currentRow) {
                    continue;
                }
                int rowDif = Math.abs(currentRow - targetRow);
                int colDif = Math.abs(board.queenPositions[currentRow] - board.queenPositions[targetRow]);
                if (colDif == rowDif) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int solutionNumber(int n) {
        return 0;
    }

    /**
     * Appends a queen into the next row on a non-full board. Must check legality of board after calling this function.
     *
     * @param col the column to insert a queen into
     * @throws Exception throws an exception if the board is already full and a queen tries to be added to the board.
     */
    private void addQueen(int col) throws Exception {
        if (lastEntryIndex == queenPositions.length - 1) {
            throw new Exception("Cannot insert a queen into an already full board.");
        } else if (col < 1 || col > 8) {
            throw new Exception("Cannot place a queen off of the board.");
        }
        queenPositions[lastEntryIndex++] = col;
    }
}
