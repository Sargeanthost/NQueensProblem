public class Main {
    public static void main(String[] args) {
        testIsLegalAndEnglingBoard();
        testNumberOfSolutionsForN();
    }

    private static void testIsLegalAndEnglingBoard() {
        //arr[row] = {column}

        assert NQueens.isLegalPosition(new int[] {1, 6, 8, 3, 7, 4, 2, 5}, 8) : "FAIL FAIL U FAIL";
        assert !NQueens.isLegalPosition(new int[] {1, 1, 1, 1, 1, 1, 1, 1}, 8) : "FAIL FAIL U FAIL";
        assert !NQueens.isLegalPosition(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 8) : "FAIL FAIL U FAIL";
        assert !NQueens.isLegalPosition(new int[] {8, 7, 6, 5, 4, 3, 2, 1}, 8) : "FAIL FAIL U FAIL";
        assert NQueens.isLegalPosition(new int[] {1, 6, 8, 3, 7, 0, 0, 0}, 8) : "FAIL FAIL U FAIL";
        assert !NQueens.isLegalPosition(new int[] {1, 6, 8, 3, 5, 0, 0, 0}, 8) : "FAIL FAIL U FAIL";

        //The position returns false when the diagonals or columns collide
        //An elegant way to be to represent this is with just bits for each main/minor diag and column, and preform a
        // constant time lookup before inserting a queen. This only works on building boards, though. The way to
        // optimise for an arbitrary board is to calculate a row col offset, and check if two queens have the same
        // offset. this is like the slope of a line, and if the have the same slope, they intersect and are not a
        // vaild position/board
    }

    /**
     * Checks the validity of solver. Make sure VM flag -enableassertions is on
     */
    private static void testNumberOfSolutionsForN() {
                assert NQueens.numberOfSolutions(4) == 2;
                assert NQueens.numberOfSolutions(5) == 10;
                assert NQueens.numberOfSolutions(6) == 4;
                assert NQueens.numberOfSolutions(7) == 40;
                assert NQueens.numberOfSolutions(8) == 92;
                assert NQueens.numberOfSolutions(9) == 352;
                assert NQueens.numberOfSolutions(10) == 724;
                assert NQueens.numberOfSolutions(11) == 2680;
                assert NQueens.numberOfSolutions(12) == 14200;
                assert NQueens.numberOfSolutions(13) == 73712;
                assert NQueens.numberOfSolutions(14) == 365596;
                assert NQueens.numberOfSolutions(15) == 2279184;
                //TODO **GRADER**, Running all tests will take about 10 minutes
                assert NQueens.numberOfSolutions(16) == 14772512;
                assert NQueens.numberOfSolutions(17) == 95815104;
    }
}