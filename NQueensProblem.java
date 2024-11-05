import java.util.Scanner;

public class NQueensProblem {
    // Function to check if it's safe to place a queen at board[row][col]
    public static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check this row on left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal on left side
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Utility function to solve the N-Queens problem
    public static boolean solveNQueensUtil(int[][] board, int col, int n) {
        // If all queens are placed, return true
        if (col >= n)
            return true;

        // Try placing a queen in each row in the current column
        for (int i = 0; i < n; i++) {
            if (isSafe(board, i, col, n)) {
                board[i][col] = 1;  // Place the queen

                // Recur to place queens in the rest of the columns
                if (solveNQueensUtil(board, col + 1, n))
                    return true;

                // If placing queen in board[i][col] doesn't lead to a solution, backtrack
                board[i][col] = 0;  // Backtrack
            }
        }
        return false;  // No solution found for this configuration
    }

    // Function to print the board
    public static void printBoard(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the board (n): ");
        int n = scanner.nextInt();

        System.out.print("Enter the row and column of the first queen (0-based index): ");
        int firstRow = scanner.nextInt();
        int firstCol = scanner.nextInt();

        int[][] board = new int[n][n];

        // Place the first queen at the specified position
        board[firstRow][firstCol] = 1;

        // Start solving from the next column
        if (solveNQueensUtil(board, firstCol + 1, n)) {
            System.out.println("Solution:");
            printBoard(board, n);
        } else {
            System.out.println("No solution exists for the given initial placement.");
        }

        scanner.close();
    }
}
