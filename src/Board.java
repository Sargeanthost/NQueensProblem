public class Board {
    int[][] board;
    /**
     * The row on which the last queen is found. for a full board where n=8, this would be 7;
     */
    private int lastEntry;
    private final int n; //nxn

    public Board(int[][] board, int lastEntry) {
        this.board = board;
        this.lastEntry = lastEntry;
        n = board.length;
    }

    public Board() {
        board = new int[][] {{}, {}, {}, {}, {}, {}, {}, {}};
        n = 8;
        lastEntry = -1;
    }

    /**
     * Checks board legality with no assumptions, and should be used when checking an arbitrary board. Works in !!O(n!)
     * time.
     *
     * @param board the board
     * @param n the size of the board
     * @return returns whether the board is in a legal position
     */
    public static boolean isLegalPositionFactorial(int[][] board, int n) {
        int currentRowIndex = 0;

        while (board[currentRowIndex].length != 0) {
            int colToCheck = board[currentRowIndex][1];
            int offset = 1;
            int rightOffset = Math.min(n - 1, colToCheck + offset);
            int leftOffset = Math.max(0, colToCheck - offset);
//            int prevRowCol = board[i][1];
            for (int rowToCheckIndex = ++currentRowIndex; board[rowToCheckIndex].length != 0; rowToCheckIndex++) {
                int rowToCheckCol = board[rowToCheckIndex][1];
                if (rowToCheckCol == colToCheck || rowToCheckCol == leftOffset || rowToCheckCol == rightOffset) {//col, col +
                    // rowoffset, col -
                    // rowoffset
                    return false;
                }
            }
            //            currentRow++;
        } return true;
    }

    public static boolean isLegalPositionQuad(int[][] board, int n){
        //check columns
        
        for(int col = 0; col < n; col++){
            f
        }
    }

    /**
     * Checks if move is legal position, given that this function has been called on each previous position. Should
     * be used when finding a board solution. Works in O(n) time.
     * <br>
     * A legal position is defined as no more than one queen in any given column or row, or the major and minor diagonal
     * of another queen.
     *
     * @return returns whether the board is in a legal position
     */
    public boolean isLegalPositionFast() {
        if (lastEntry == -1 || lastEntry == 0) {
            return true;
        }
        int colToCheck = board[lastEntry][1]; //gets col part of last filled rows' queen
        int offset = 1;
        for (int i = lastEntry - 1; i >= 0; i--) {
            int prevRowCol = board[i][1];
            int rightOffset = Math.min(n - 1, colToCheck + offset);
            int leftOffset = Math.max(0, colToCheck - offset);
            if (prevRowCol == colToCheck || prevRowCol == rightOffset || prevRowCol == leftOffset) {
                return false;
            }
            offset++;
        }
        return true;
    }

    /**
     * Adds queen into a non-full board. Must check legality of board after calling this function.
     *
     * @param row the row to insert a queen into
     * @param col the column to insert a queen into
     * @throws Exception throws an exception if the board is already full and a queen tries to be added to the board.
     */
    private void addQueen(int row, int col) throws Exception {
        if (lastEntry == board.length - 1) {
            throw new Exception("Cannot insert a queen into an already full board.");
        }
        board[lastEntry++] = new int[] {row, col};
    }
}
