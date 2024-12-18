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
        // Variable to count the number of islands
        int island = 0;
        // Get the number of rows and columns in the grid
        int rows = grid.length;
        int cols = grid[0].length;

        // Traverse every cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // If the cell contains '1', it's part of an island
                if (grid[r][c] == '1') {
                    // Increment the island count
                    island++;
                    // Perform BFS to mark the entire island as visited
                    bfs(grid, r, c, rows, cols);
                }
            }
        }
        // Return the total count of islands
        return island;
    }

    private void bfs(char[][] grid, int r, int c, int rows, int cols) {
        // Initialize a queue for BFS, starting with the current cell
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        // Mark the current cell as visited by setting it to '0'
        grid[r][c] = '0';

        // Define directions for traversing up, down, left, and right
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Perform BFS until the queue is empty
        while (!q.isEmpty()) {
            // Get the next cell to process from the queue
            int[] point = q.poll();
            int row = point[0], col = point[1];

            // Explore all possible directions from the current cell
            for (int[] direction : directions) {

                int nr = row + direction[0]; // New row
                int nc = col + direction[1]; // New column

                // Check if the neighboring cell is valid and contains '1'
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1') {
                    // Add the neighboring cell to the queue
                    q.add(new int[] {nr, nc});
                    // Mark the neighboring cell as visited
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
        
        int n = times.length; // Total number of friends
        Integer[] order = new Integer[n]; // Array to track the order of friends by arrival time

        // Initialize the order array with indices 0 to n-1
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        // Sort the friends by their arrival times using the order array
        Arrays.sort(order, (a, b) -> Integer.compare(times[a][0], times[b][0]));

        // Min-heap to manage available (empty) seats by their indices
        PriorityQueue<Integer> emptySeats = new PriorityQueue<>();

        // Min-heap to manage occupied seats, sorted by leave time
        PriorityQueue<int[]> takenSeats = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Initially, all chairs are available
        for (int i = 0; i < n; i++) {
            emptySeats.offer(i);
        }

        // Iterate over the sorted order of friends
        for (int i : order) {

            int arrival = times[i][0]; // Current friend's arrival time
            int leave = times[i][1];  // Current friend's leaving time

            // Release chairs that become available by the current arrival time
            while (!takenSeats.isEmpty() && takenSeats.peek()[0] <= arrival) {
                emptySeats.offer(takenSeats.poll()[1]);
            }

            // Assign the smallest available seat to the current friend
            int seat = emptySeats.poll();

            // If this is the target friend, return the assigned seat
            if (i == targetFriend) {
                return seat;
            }

            // Add the current friend's leave time and assigned seat to the takenSeats heap
            takenSeats.offer(new int[] {leave, seat});
        }

        return -1; // Default return value (shouldn't be reached in this problem)
    }
}



// 3. Maximal Square


class Solution {
    public int maximalSquare(char[][] matrix) {
        
        // If the matrix is empty, return 0 (no squares possible)
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;  // Number of rows in the matrix
        int cols = matrix[0].length;  // Number of columns in the matrix
        
        // Create a DP table to store the side length of the largest square ending at each cell
        int[][] dp = new int[rows][cols];
        
        int maxSide = 0;  // Variable to track the maximum side length of a square

        // Iterate through the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // Process only cells with '1'
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        // If the cell is in the first row or first column, it can only form a square of size 1
                        dp[i][j] = 1;
                    } else {
                        // Otherwise, the square side length depends on the minimum side lengths of squares
                        // ending at the top, left, and top-left diagonal cells
                        dp[i][j] = Math.min(
                            Math.min(dp[i-1][j], dp[i][j-1]),  // Top and left
                            dp[i-1][j-1]  // Top-left diagonal
                        ) + 1;
                    }
                    // Update the maximum side length found so far
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        // The area of the largest square is the square of its side length
        return maxSide * maxSide;
    }
}





// Search a 2D Matrix



class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        // Get the number of rows and columns in the matrix
        int rows = matrix.length; 
        int cols = matrix[0].length; 

        // Treat the 2D matrix as a 1D array for binary search
        int left = 0; 
        int right = rows * cols - 1; 

        // Perform binary search
        while (left <= right) {

            // Find the middle index of the virtual 1D array
            int mid = (left + right) / 2; 

            // Map the middle index to a row and column in the 2D matrix
            int row = mid / cols; 
            int col = mid % cols; 

            // Get the value at the calculated position
            int guess = matrix[row][col]; 

            // If the value matches the target, return true
            if (guess == target) return true;

            // If the value is less than the target, move to the right half
            else if (guess < target) {
                left = mid + 1; 
            }
            // If the value is greater than the target, move to the left half
            else {
                right = mid - 1; 
            }
        }

        // If the target is not found, return false
        return false; 
    }
}




// Rotate Image


class Solution {
    public void rotate(int[][] matrix) {
        
        int edgeLength = matrix.length; // Length of the matrix (assuming square matrix)
        int top = 0; // Pointer to the top row
        int bottom = edgeLength - 1; // Pointer to the bottom row

        // Step 1: Reverse the rows of the matrix
        while (top < bottom) {
            for (int col = 0; col < edgeLength; col++) {

                // Swap elements in the top and bottom rows
                int temp = matrix[top][col];
                matrix[top][col] = matrix[bottom][col];
                matrix[bottom][col] = temp;
            }
            top++; // Move top pointer down
            bottom--; // Move bottom pointer up
        }

        // Step 2: Transpose the matrix
        for (int row = 0; row < edgeLength; row++) {
            for (int col = row + 1; col < edgeLength; col++) {

                // Swap elements to transpose the matrix
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
        
        // Boolean arrays to track the presence of numbers in rows, columns, and sub-boxes
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] subBoxes = new boolean[9][9];

        // Iterate through each cell in the board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] != '.') { // Check only non-empty cells
                    int num = board[i][j] - '1'; // Convert char to integer (0-based index)

                    // Calculate sub-box index using row and column
                    int boxIndex = (i / 3) * 3 + (j / 3);

                    // Check if the number already exists in the current row, column, or sub-box
                    if (rows[i][num] || columns[j][num] || subBoxes[boxIndex][num]) {
                        return false; // Sudoku rule violation
                    }

                    // Mark the number as seen in the current row, column, and sub-box
                    rows[i][num] = true;
                    columns[j][num] = true;
                    subBoxes[boxIndex][num] = true;
                }
            }
        }

        return true; // All checks passed, the Sudoku is valid
    }
}
