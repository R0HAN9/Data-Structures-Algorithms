// Surrounded Regions


class Solution {
    public void solve(char[][] board) {
        // Get dimensions of the board.
        int n = board.length;      // Number of rows
        int m = board[0].length;   // Number of columns

        // Arrays to represent directions (up, right, down, left).
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        // Create a visited array to keep track of cells that are already processed.
        boolean[][] vis = new boolean[n][m];

        // Traverse the first and last columns for every row.
        for (int i = 0; i < n; i++) {
            // Check the first column of the current row.
            if (!vis[i][0] && board[i][0] == 'O') {
                dfs(i, 0, dRow, dCol, vis, board); // Mark all connected 'O' cells as visited.
            }
            // Check the last column of the current row.
            if (!vis[i][m - 1] && board[i][m - 1] == 'O') {
                dfs(i, m - 1, dRow, dCol, vis, board); // Mark all connected 'O' cells as visited.
            }
        }

        // Traverse the first and last rows for every column.
        for (int i = 0; i < m; i++) {
            // Check the first row of the current column.
            if (!vis[0][i] && board[0][i] == 'O') {
                dfs(0, i, dRow, dCol, vis, board); // Mark all connected 'O' cells as visited.
            }
            // Check the last row of the current column.
            if (!vis[n - 1][i] && board[n - 1][i] == 'O') {
                dfs(n - 1, i, dRow, dCol, vis, board); // Mark all connected 'O' cells as visited.
            }
        }

        // Convert all remaining unvisited 'O' cells to 'X'.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && board[i][j] == 'O') {
                    board[i][j] = 'X'; // Flip isolated 'O' cells to 'X'.
                }
            }
        }
    }

    // Depth First Search to mark all connected 'O' cells.
    public void dfs(int row, int col, int[] dRow, int[] dCol, boolean[][] vis, char[][] board) {
        // Mark the current cell as visited.
        vis[row][col] = true;

        // Get dimensions of the board.
        int n = board.length;      // Number of rows
        int m = board[0].length;   // Number of columns

        // Explore all 4 possible directions (up, right, down, left).
        for (int i = 0; i < 4; i++) {
            int newRow = row + dRow[i]; // Calculate the new row index.
            int newCol = col + dCol[i]; // Calculate the new column index.

            // Check if the new cell is within bounds, not visited, and contains 'O'.
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m &&
                !vis[newRow][newCol] && board[newRow][newCol] == 'O') {
                dfs(newRow, newCol, dRow, dCol, vis, board); // Recur for the new cell.
            }
        }
    }
}




// Number of Islands

class Solution {
    public int numIslands(char[][] grid) {
        // Check for edge cases: If the grid is null or empty, there are no islands.
        if (grid == null || grid.length == 0) {
            return 0; // Return 0 as there are no islands.
        }
        
        int numIslands = 0; // Counter to keep track of the number of islands.
        
        // Iterate through every cell in the grid.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // If the current cell is '1' (land), it indicates the start of a new island.
                if (grid[i][j] == '1') {
                    numIslands++; // Increment the island count.
                    // Use DFS to mark all the cells in this island as visited.
                    dfs(grid, i, j);
                }
            }
        }
        
        return numIslands; // Return the total number of islands.
    }
    
    private void dfs(char[][] grid, int i, int j) {
        // Check if the current cell is out of bounds or is not part of the island.
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return; // Stop further exploration.
        }
        
        // Mark the current cell as visited by setting it to '0'.
        grid[i][j] = '0';
        
        // Explore all four directions: down, up, right, and left.
        dfs(grid, i + 1, j); // Move down.
        dfs(grid, i - 1, j); // Move up.
        dfs(grid, i, j + 1); // Move right.
        dfs(grid, i, j - 1); // Move left.
    }
}
  

// If we can update grid directly, we can write solutions below.

class Solution {
    public int numIslands(char[][] grid) {
        
        int island = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (grid[r][c] == '1') {
                    island++;
                    bfs(grid, r, c, rows, cols);
                }
            }
        }
        return island;
    }

    private void bfs(char[][] grid, int r, int c, int rows, int cols) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        grid[r][c] = '0';

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0], col = point[1];

            for (int[] direction : directions) {

                int nr = row + direction[0];
                int nc = col + direction[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1') {
                    q.add(new int[] {nr, nc});
                    grid[nr][nc] = '0';
                }
            }
        }
    }
}
---------------------------------------------------------------------------------------------------------------------------------------------------


// 2. The Number of the Smallest Unoccupied Chair


class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        
        int n = times.length;
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(times[a][0], times[b][0]));

        PriorityQueue<Integer> emptySeats = new PriorityQueue<>();
        PriorityQueue<int[]> takenSeats = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n; i++) emptySeats.offer(i);
        for (int i : order) {

            int arrival = times[i][0], leave = times[i][1];
            while (!takenSeats.isEmpty() && takenSeats.peek()[0] <= arrival) {
                emptySeats.offer(takenSeats.poll()[1]);
            }

            int seat = emptySeats.poll();
            if (i == targetFriend) return seat;
            takenSeats.offer(new int[] {leave, seat});
        }

        return -1;
    }
}


// 3. Maximal Square


class Solution {
    public int maximalSquare(char[][] matrix) {
        
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int maxSide = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {

                        dp[i][j] = 1;
                    }
                    else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }
}




// Search a 2D Matrix



class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {

            int mid = (left + right) / 2;
            int row = mid / cols;
            int col = mid % cols;

            int guess = matrix[row][col];
            if (guess == target) return true;

            else if (guess < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return false;
    }
}



// Rotate Image


class Solution {
    public void rotate(int[][] matrix) {
        
        int edgeLength = matrix.length;
        int top = 0;
        int bottom = edgeLength - 1;

        while (top < bottom) {
            for (int col = 0; col < edgeLength; col++) {

                int temp = matrix[top][col];
                matrix[top][col] = matrix[bottom][col];
                matrix[bottom][col] = temp;
            }
            top++;
            bottom--;
        }

        for (int row = 0; row < edgeLength; row++) {
            for (int col = row + 1; col < edgeLength; col++) {

                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
    }
}



// Valid Sudoku


class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] subBoxes = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';

                    int boxIndex = (i/3) * 3 + (j/3);
                    if (rows[i][num] || columns[j][num] || subBoxes[boxIndex][num]) {
                        return false;
                    }

                    rows[i][num] = true;
                    columns[j][num] = true;
                    subBoxes[boxIndex][num] = true;
                }
            }
        }

        return true;
    }
}
