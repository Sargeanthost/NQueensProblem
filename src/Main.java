import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        testNextLegalPosition();
//        testIsLegalAndEnglingBoard();
        testFirstSolution();
        //TODO uncomment when turning in        testNumberOfSolutionsForN();
    }

    private static void testFirstSolution() {
//        NQueens.firstSolution(4);
//        NQueens.firstSolution(5);
//        NQueens.firstSolution(6);
//        NQueens.firstSolution(7);
        NQueens.firstSolution(8);
//        NQueens.firstSolution(9);
//        NQueens.firstSolution(11);
//        NQueens.firstSolution(12);
//        NQueens.firstSolution(13);
//        NQueens.firstSolution(14);
//        NQueens.firstSolution(15);
//        NQueens.firstSolution(16);
//        NQueens.firstSolution(17);
//        NQueens.firstSolution(18);
//        NQueens.firstSolution(19);
//        NQueens.firstSolution(21);
//        NQueens.firstSolution(22);
//        NQueens.firstSolution(23);
//        NQueens.firstSolution(24);
//        NQueens.firstSolution(25);
//        NQueens.firstSolution(26);
//        NQueens.firstSolution(27);
//        NQueens.firstSolution(28);
//        NQueens.firstSolution(29);
//        NQueens.firstSolution(31);
//        NQueens.firstSolution(32);
//        NQueens.firstSolution(33);
//        NQueens.firstSolution(34);
//        NQueens.firstSolution(35);
    }

    private static void testNextLegalPosition() {
        //the next move after a legal position will be on the next row unless the last row is off the board.
        // then this will trigger a backtrack, which will increiment the previous row by one if thats on the board.
        // this may end up putting that in an illegal position, wich will back track some arbiturary amount of times,
        // and the final placement may be a few rows behind the current row.
        int[] illegal = new int[] {1, 6, 8, 3, 5, 0, 0, 0};
        int[] legal = new int[] {1, 6, 8, 3, 7, 0, 0, 0};
        int[] nextLegal = new int[] {1, 6, 8, 3, 7, 4, 0, 0};
        int[] firstEightSolution = new int[] {1, 6, 8, 3, 7, 4, 2, 5};
        int[] afterFirstSolution = new int[] {1, 6, 8, 5, 0, 0, 0, 0};

        NQueens.nextLegalPosition(illegal, illegal.length);
        assert Arrays.equals(illegal, legal);
        NQueens.nextLegalPosition(legal, legal.length);
        assert Arrays.equals(legal, nextLegal);
        NQueens.nextLegalPosition(nextLegal, nextLegal.length);
        assert Arrays.equals(nextLegal, new int[] {1, 6, 8, 3, 7, 4, 2, 0});
        NQueens.nextLegalPosition(firstEightSolution, firstEightSolution.length);
        assert Arrays.equals(firstEightSolution, afterFirstSolution);

    }

    private static void testIsLegalAndEnglingBoard() {
        //arr[row] = {column}

        assert NQueens.isLegalPosition(new int[] {1, 6, 8, 3, 7, 4, 2, 5}, 8);
        assert !NQueens.isLegalPosition(new int[] {1, 1, 1, 1, 1, 1, 1, 1}, 8);
        assert !NQueens.isLegalPosition(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 8);
        assert !NQueens.isLegalPosition(new int[] {8, 7, 6, 5, 4, 3, 2, 1}, 8);
        assert NQueens.isLegalPosition(new int[] {1, 6, 8, 3, 7, 0, 0, 0}, 8);
        assert !NQueens.isLegalPosition(new int[] {1, 6, 8, 3, 5, 0, 0, 0}, 8);
        assert NQueens.isLegalPosition(new int[] {1, 6, 8, 3, 7, 4, 0, 0}, 8);
        assert !NQueens.isLegalPosition(new int[] {1, 6, 8, 3, 7, 4, 2, 6}, 8);

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