public class Main {
    public static void main(String[] args) {
        testIsLegalAndEnglingBoard();
    }

    private static void testIsLegalAndEnglingBoard() {
        //{row,column}
        int[] engling = {1, 6, 8, 3, 7, 4, 2, 5};
        int[] colBad = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] majorBad = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] minorBad = {8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("Engling's board is correct: " + Board.isLegalPosition(engling, 8));
        System.out.println("All in first column is correct: " + Board.isLegalPosition(colBad, 8));
        System.out.println("All in major diag is correct: " + Board.isLegalPosition(majorBad, 8));
        System.out.println("All in minor diag is correct: " + Board.isLegalPosition(minorBad, 8));
    }
}