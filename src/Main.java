public class Main {
    public static void main(String[] args) {
        testIsLegalAndEnglingBoard();

        testNumberOfSolutionsForN();
    }

    private static void testIsLegalAndEnglingBoard() {
        //arr[row] = {column}
        Board engling = new Board(new int[] {1, 6, 8, 3, 7, 4, 2, 5}, 7);
        Board colBad = new Board(new int[] {1, 1, 1, 1, 1, 1, 1, 1}, 7);
        Board majorBad = new Board(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 7);
        Board minorBad = new Board(new int[] {8, 7, 6, 5, 4, 3, 2, 1}, 7);
        Board partialGood = new Board(new int[] {1,6,8,3,7,0,0,0}, 4);
        Board partialBad = new Board(new int[] {1,6,8,3,5,0,0,0}, 4);
        System.out.println("Engling's board is correct: " + Board.isLegalPosition(engling, engling.n));
        System.out.println("All in first column is correct: " + Board.isLegalPosition(colBad, colBad.n));
        System.out.println("All in major diag is correct: " + Board.isLegalPosition(majorBad, majorBad.n));
        System.out.println("All in minor diag is correct: " + Board.isLegalPosition(minorBad, minorBad.n));
        System.out.println("All in partial good is correct: " + Board.isLegalPosition(partialGood, partialGood.n));
        System.out.println("All in partial bad is correct: " + Board.isLegalPosition(partialBad, partialBad.n));

        //The position returns false when the diagonals or columns collide
        //An elegant way to be to represent this with just bits, and shift into position
    }

    private static void testNumberOfSolutionsForN(){
        assert Board.solutionNumber(4) == 2;
        assert Board.solutionNumber(5) == 10;
        assert Board.solutionNumber(6) == 4;
        assert Board.solutionNumber(7) == 40;
        assert Board.solutionNumber(8) == 92;
        assert Board.solutionNumber(9) == 352;
        assert Board.solutionNumber(10) == 724;
        assert Board.solutionNumber(11) == 2680;
        assert Board.solutionNumber(12) == 14200;
        assert Board.solutionNumber(13) == 73712;
        assert Board.solutionNumber(14) == 365596;
        assert Board.solutionNumber(15) == 2279184;
        assert Board.solutionNumber(16) == 14772512;
        assert Board.solutionNumber(17) == 95815104;
    }
}