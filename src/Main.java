import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        testNextLegalPosition();
        testIsLegalAndEnglingBoard();
        testFirstSolution();
        testNumberOfSolutionsForN();
    }

    private static void testFirstSolution() {
        System.out.println("Printing first solutions up to n=33:");
        System.out.println("First solution=4 :" + Arrays.toString(NQueens.firstSolution(4)));
        System.out.println("First solution=5 :" + Arrays.toString(NQueens.firstSolution(5)));
        System.out.println("First solution=6 " + Arrays.toString(NQueens.firstSolution(6)));
        System.out.println("First solution=7 :" + Arrays.toString(NQueens.firstSolution(7)));
        System.out.println("First solution=8 :" + Arrays.toString(NQueens.firstSolution(8)));
        System.out.println("First solution=9 :" + Arrays.toString(NQueens.firstSolution(9)));
        System.out.println("First solution=10 :" + Arrays.toString(NQueens.firstSolution(10)));
        System.out.println("First solution=11 :" + Arrays.toString(NQueens.firstSolution(11)));
        System.out.println("First solution=12 :" + Arrays.toString(NQueens.firstSolution(12)));
        System.out.println("First solution=13 :" + Arrays.toString(NQueens.firstSolution(13)));
        System.out.println("First solution=14 :" + Arrays.toString(NQueens.firstSolution(14)));
        System.out.println("First solution=15 :" + Arrays.toString(NQueens.firstSolution(15)));
        System.out.println("First solution=16 :" + Arrays.toString(NQueens.firstSolution(16)));
        System.out.println("First solution=17 :" + Arrays.toString(NQueens.firstSolution(17)));
        System.out.println("First solution=18 :" + Arrays.toString(NQueens.firstSolution(18)));
        System.out.println("First solution=19 :" + Arrays.toString(NQueens.firstSolution(19)));
        System.out.println("First solution=20 :" + Arrays.toString(NQueens.firstSolution(20)));
        System.out.println("First solution=21 :" + Arrays.toString(NQueens.firstSolution(21)));
        System.out.println("First solution=22 :" + Arrays.toString(NQueens.firstSolution(22)));
        System.out.println("First solution=23 :" + Arrays.toString(NQueens.firstSolution(23)));
        System.out.println("First solution=24 :" + Arrays.toString(NQueens.firstSolution(24)));
        System.out.println("First solution=25 :" + Arrays.toString(NQueens.firstSolution(25)));
        System.out.println("First solution=26 :" + Arrays.toString(NQueens.firstSolution(26)));
        System.out.println("First solution=27 :" + Arrays.toString(NQueens.firstSolution(27)));
        System.out.println("First solution=28 :" + Arrays.toString(NQueens.firstSolution(28)));
        System.out.println("First solution=29 :" + Arrays.toString(NQueens.firstSolution(29)));
        System.out.println("First solution=30 :" + Arrays.toString(NQueens.firstSolution(30)));
        System.out.println("First solution=31 :" + Arrays.toString(NQueens.firstSolution(31)));
        System.out.println("First solution=32 :" + Arrays.toString(NQueens.firstSolution(32)));
        System.out.println("First solution=33 :" + Arrays.toString(NQueens.firstSolution(33)));
        //takes about an hour to run the bottom ones
        //        System.out.println("First solution=34 :" + Arrays.toString(NQueens.firstSolution(34)));
        //        System.out.println("First solution=35 :" + Arrays.toString(NQueens.firstSolution(35)));
        //        System.out.println("First solution=36 :" + Arrays.toString(NQueens.firstSolution(36)));
        System.out.println("Done printing solutions.");
    }

    private static void testNextLegalPosition() {
        //the next move after a legal position will be on the next row unless the last row is off the board.
        // then this will trigger a backtrack, which will increment the previous row by one if thats on the board.
        // this may end up putting that in an illegal position, which will back track some arbitrary amount of times,
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
        // valid position/board
    }

    private static void testNumberOfSolutionsForN() {
        System.out.println("Printing out number of solutions for n=17:");
        System.out.println("There are " + NQueens.numberOfSolutions(4) + " solutions to the 4-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(5) + " solutions to the 5-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(6) + " solutions to the 6-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(7) + " solutions to the 7-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(8) + " solutions to the 8-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(9) + " solutions to the 9-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(10) + " solutions to the 10-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(11) + " solutions to the 11-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(12) + " solutions to the 12-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(13) + " solutions to the 13-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(14) + " solutions to the 14-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(15) + " solutions to the 15-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(16) + " solutions to the 16-Queens problem.");
        System.out.println("There are " + NQueens.numberOfSolutions(17) + " solutions to the 17-Queens problem.");
        System.out.println("Done printing solutions.");
    }
}
