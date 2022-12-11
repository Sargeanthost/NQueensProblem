public class Main {
    public static void main(String[] args) {
        testIsLegalWithEnglingBoard();
    }

    private static void testIsLegalWithEnglingBoard() {
        //{row,column}
        int[][] engling = {{0, 1}, {1, 7}, {2, 8}, {3, 2}, {4, 6}, {5, 3}, {6, 1}, {7, 4}};
        System.out.println("Engling's board is correct: " + Board.isLegalPositionFactorial(engling, 8));
    }
}