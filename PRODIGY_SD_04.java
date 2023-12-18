public class SudokuSolver {
    public static void main(String[] args) {
        int[][] unsolvedGrid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(unsolvedGrid)) {
            System.out.println("Sudoku solved successfully:");
            printSudokuGrid(unsolvedGrid);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }

    private static boolean solveSudoku(int[][] grid) {
        int N = grid.length;

        // Find an empty cell (with value 0)
        int[] emptyCell = findEmptyCell(grid);
        int row = emptyCell[0];
        int col = emptyCell[1];

        // If no empty cell is found, the Sudoku is solved
        if (row == -1 && col == -1) {
            return true;
        }

        // Try filling the empty cell with numbers 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                // Assign the number to the empty cell
                grid[row][col] = num;

                // Recursively try to solve the rest of the puzzle
                if (solveSudoku(grid)) {
                    return true; // If successful, the puzzle is solved
                }

                // If the current assignment does not lead to a solution, backtrack
                grid[row][col] = 0;
            }
        }

        // No number from 1 to 9 can be placed in the current cell
        return false;
    }

    private static int[] findEmptyCell(int[][] grid) {
        int[] result = {-1, -1};
        int N = grid.length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    result[0] = i;
                    result[1] = j;
                    return result; // Return the coordinates of the empty cell
                }
            }
        }

        return result; // Return {-1, -1} if no empty cell is found
    }

    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check if 'num' is not present in the current row and column
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        // Check if 'num' is not present in the 3x3 subgrid
        int subgridStartRow = row - row % 3;
        int subgridStartCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[subgridStartRow + i][subgridStartCol + j] == num) {
                    return false;
                }
            }
        }

        return true; // 'num' is safe to be placed in the current cell
    }

    private static void printSudokuGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
