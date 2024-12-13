// BurstBallons

public class BurstBallons {
    public int maxCoins(int[] nums) {

        int n = nums.length;

        // Create a new array to include virtual balloons at the boundaries with value 1.
        int[] balloons = new int[n + 2];
        balloons[0] = 1; // Virtual balloon at the left boundary.
        balloons[n + 1] = 1; // Virtual balloon at the right boundary.

        // Copy the original balloon values into the new array.
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }

        // Create a 2D dp array to store the maximum coins that can be obtained
        // by bursting balloons in a given range.
        int[][] dp = new int[n + 2][n + 2];

        // Iterate over all possible lengths of subarrays of balloons to consider.
        for (int length = 2; length <= n + 1; length++) {

            // Iterate over all possible starting indices for the subarray.
            for (int start = 0; start <= n + 1 - length; start++) {

                int end = start + length; // Determine the end index of the subarray.

                // Iterate over all possible last balloons to burst in the current range.
                for (int k = start + 1; k < end; k++) {

                    // Calculate the coins obtained by bursting balloon 'k' last in the range.
                    int coins = balloons[start] * balloons[k] * balloons[end];

                    // Add the coins obtained from previously solved subproblems (left and right of 'k').
                    int totalCoins = coins + dp[start][k] + dp[k][end];

                    // Update the dp table with the maximum coins for the current range.
                    dp[start][end] = Math.max(dp[start][end], totalCoins);
                }
            }
        }

        // Return the maximum coins obtainable for bursting all balloons (0 to n+1 range).
        return dp[0][n + 1];
    }
}




// Minimum Path Sum

class Solution {
    public int minPathSum(int[][] grid) {
        
        // Get the number of rows and columns in the grid
        int rows = grid.length;
        int cols = grid[0].length;

        // Create a DP table to store the minimum path sum at each cell
        int[][] dp = new int[rows][cols];

        // Iterate through each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // Base case: starting cell (top-left corner)
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue; // Move to the next iteration
                }

                // Initialize variables to store path sums from left and above
                int leftPathSum = (int) (1e9); // Representing infinity initially
                int upPathSum = (int) (1e9); // Representing infinity initially

                // Calculate the minimum path sum from the left cell, if it exists
                if (j > 0) leftPathSum = grid[i][j] + dp[i][j - 1];

                // Calculate the minimum path sum from the above cell, if it exists
                if (i > 0) upPathSum = grid[i][j] + dp[i - 1][j];

                // Store the minimum of the two possible path sums in the DP table
                dp[i][j] = Math.min(leftPathSum, upPathSum);
            }
        }

        // Return the minimum path sum to reach the bottom-right corner
        return dp[rows - 1][cols - 1];
    }
}




// House Robber

class Solution {
    public int rob(int[] nums) {
        // `rob` represents the maximum amount we can rob by including the current house
        int rob = 0;
        // `norob` represents the maximum amount we can rob by excluding the current house
        int norob = 0;

        // Iterate through each house in the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the new amount if we decide to rob the current house
            int newRob = norob + nums[i];

            // Calculate the new amount if we decide NOT to rob the current house
            int newNoRob = Math.max(norob, rob);

            // Update `rob` and `norob` for the next iteration
            rob = newRob;      // Now, `rob` represents the new maximum amount by robbing the current house
            norob = newNoRob;  // Now, `norob` represents the new maximum amount by not robbing the current house
        }

        // The final result is the maximum of robbing or not robbing the last house
        return Math.max(rob, norob);
    }
}



// Maximum Product Subarray

class Solution {
    // Global variables to store the current maximum and minimum product
    int max = 1; 
    int min = 1;

    public int maxProduct(int[] nums) {
        // Start the recursive DFS from index 0
        return dfs(0, nums);
    }

    public int dfs(int i, int[] nums) {
        // Base case: If we reach the last element in the array
        if (i >= nums.length - 1) {
            max = nums[nums.length - 1]; // Initialize max to the last element
            min = max;                  // Initialize min to the last element

            // If the last element is 0, reset max and min to 1
            if (nums[nums.length - 1] == 0) {
                min = 1; 
                max = 1;
            }

            // Return the last element as the result, unless it is 0
            return nums[nums.length - 1] != 0 ? max : 0;
        }

        // Recursive call for the next index
        int res = dfs(i + 1, nums);

        // If the current element is 0, reset min and max to 1
        if (nums[i] == 0) { 
            min = 1; 
            max = 1; 
            return res > 0 ? res : 0; // Return the maximum of result and 0
        }

        // Store the current max temporarily before updating it
        int temp = max;

        // Update max by considering:
        // 1. Product of current max and current element
        // 2. Product of current min and current element
        // 3. Current element itself
        max = Math.max(max * nums[i], min * nums[i]);
        max = Math.max(max, nums[i]);

        // Update min by considering:
        // 1. Product of current max (before update) and current element
        // 2. Product of current min and current element
        // 3. Current element itself
        min = Math.min(temp * nums[i], min * nums[i]);
        min = Math.min(min, nums[i]);

        // Update the result to the maximum of itself or the current max
        res = Math.max(res, max);

        return res;
    }
}



// Unique Paths

class Solution {
    public int uniquePaths(int m, int n) {
        
        // Base case: If the grid is 1x1, there is only one unique path
        if (m == 1 && n == 1) return 1;

        // Create a 1D array to store the results of the previous row
        int[] prev = new int[n];

        // Iterate through all rows of the grid
        for (int i = 0; i < m; i++) {
            // Create a temporary array to calculate the current row
            int[] curr = new int[n];

            // Iterate through all columns of the grid
            for (int j = 0; j < n; j++) {
                // If we are at the top-left corner, initialize it to 1
                if (i == 0 && j == 0) {
                    curr[j] = 1;
                } else {
                    // Retrieve the number of paths from the cell above
                    int up = prev[j];
                    // Retrieve the number of paths from the cell to the left
                    int left = 0;
                    if (j > 0) left = curr[j - 1];

                    // Total unique paths to the current cell
                    curr[j] = left + up;
                }
            }
            // Update the previous row to be the current row
            prev = curr;
        }

        // The last element in the previous row array contains the result
        return prev[n - 1];
    }
}


// Word Break


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        // Create a DP array where dp[i] indicates whether the substring s[0...i-1] can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        
        // Base case: An empty string can always be segmented
        dp[0] = true;

        // Iterate through the length of the string
        for (int i = 1; i <= s.length(); i++) {
            // Iterate through all words in the dictionary
            for (String word : wordDict) {
                // Calculate the start index of the word in the substring
                int start = i - word.length();

                // Check if the word can fit within the current substring
                // and if the part before this word can be segmented
                if (start >= 0 && dp[start] && s.substring(start, i).equals(word)) {
                    dp[i] = true; // Mark dp[i] as true since segmentation is possible
                    break;        // No need to check further words for this index
                }
            }
        }

        // Return whether the entire string can be segmented
        return dp[s.length()];
    }
}



// Count Square Submatrices with All Ones

class Solution {
    public int countSquares(int[][] matrix) {
        
        int rows = matrix.length;  // Number of rows in the matrix
        int cols = matrix[0].length;  // Number of columns in the matrix

        // Create a DP table where dp[i][j] represents the size of the largest square ending at cell (i, j)
        int[][] dp = new int[rows][cols];
        int totalCount = 0;  // Initialize total count of square submatrices

        // Fill the first column of the DP table
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0];  // If the matrix cell is 1, there's a 1x1 square
            totalCount += dp[i][0];  // Add to the total count
        }

        // Fill the first row of the DP table
        for (int j = 1; j < cols; j++) {
            dp[0][j] = matrix[0][j];  // If the matrix cell is 1, there's a 1x1 square
            totalCount += dp[0][j];  // Add to the total count
        }

        // Process the rest of the matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                // If the current cell is 1, calculate the size of the largest square ending at (i, j)
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i][j - 1], dp[i - 1][j]),  // Minimum of left and top cells
                        dp[i - 1][j - 1]  // Top-left diagonal cell
                    );
                }
                totalCount += dp[i][j];  // Add the size of the square to the total count
            }
        }

        return totalCount;  // Return the total count of square submatrices
    }
}



// Longest Square Streak in an Array

class Solution {
    public int longestSquareStreak(int[] nums) {
        
        Map<Integer, Integer> mp = new HashMap<>();
        Arrays.sort(nums);

        int result = -1;
        for (int num : nums) {

            int sqrt = (int) Math.sqrt(num);
            if (sqrt * sqrt == num && mp.containsKey(sqrt)) {
                mp.put(num, mp.get(sqrt) + 1);
                result = Math.max(result, mp.get(num));
            }
            else {
                mp.put(num, 1);
            }
        }

        return result;
    }
}



// Maximum Number of Moves in a Grid


class Solution {
    public int maxMoves(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        int maxMoves = 0;

        for (int col = n - 2; col >= 0; col--) {
            for (int row = 0; row < m; row++) {

                if (row > 0 && grid[row][col] < grid[row - 1][col + 1]) {
                    dp[row][col] = Math.max(dp[row][col], dp[row - 1][col + 1] + 1);
                }

                if (grid[row][col] < grid[row][col + 1]) {
                    dp[row][col] = Math.max(dp[row][col], dp[row][col + 1] + 1);
                }

                if (row < m - 1 && grid[row][col] < grid[row + 1][col + 1]) {
                    dp[row][col] = Math.max(dp[row][col], dp[row + 1][col + 1] + 1);
                }
            }
        }

        for (int row = 0; row < m; row++) {
            maxMoves = Math.max(maxMoves, dp[row][0]);
        }

        return maxMoves;
    }
}



// Minimum Total Distance Traveled


class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Sort robots and factories by position for optimal assignment
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        
        int m = robot.size();
        int n = factory.length; 
        
        // dp[i][j] represents min total distance for robots[i:] using factories[j:]
        long[][] dp = new long[m + 1][n + 1];
        
        // Set last column to MAX_VALUE as boundary condition
        for (int i = 0; i < m; i++) {
            dp[i][n] = Long.MAX_VALUE;
        }
        
        // Process each factory from right to left
        for (int j = n - 1; j >= 0; j--) {
            // Track cumulative distance from current factory to robots
            long prefix = 0;
            // Use deque to maintain potential optimal assignments
            Deque<Pair<Integer, Long>> qq = new ArrayDeque<>();
            // Initialize with boundary condition
            qq.offer(new Pair<>(m, 0L));
            
            // Process each robot from right to left
            for (int i = m - 1; i >= 0; i--) {
                // Add distance from current robot to current factory
                prefix += Math.abs(robot.get(i) - factory[j][0]);
                
                // Remove assignments that exceed factory capacity
                while (!qq.isEmpty() && qq.peekFirst().getKey() > i + factory[j][1]) {
                    qq.pollFirst();
                }
                
                // Remove suboptimal assignments
                while (!qq.isEmpty() && qq.peekLast().getValue() >= dp[i][j + 1] - prefix) {
                    qq.pollLast();
                }
                
                // Add current state to deque
                qq.offerLast(new Pair<>(i, dp[i][j + 1] - prefix));
                // Update dp with optimal assignment
                dp[i][j] = qq.peekFirst().getValue() + prefix;
            }
        }
        
        // Return minimum total distance for all robots
        return dp[0][0];
    }
    
    // Helper class to store key-value pairs
    private static class Pair<K, V> {
        private K key;
        private V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
    }
}
