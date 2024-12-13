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
        int rob = 0;
        int norob = 0;
        for (int i = 0; i < nums.length; i ++) {
            int newRob = norob + nums[i];
            int newNoRob = Math.max(norob, rob);
            rob = newRob;
            norob = newNoRob;
        }
        return Math.max(rob, norob);
    }
}


// Maximum Product Subarray

class Solution {
    int max = 1; int min = 1;
    public int maxProduct(int[] nums) {
        return dfs(0, nums);
    }
    public int dfs(int i, int[] nums) {
        if (i >= nums.length - 1) {
            max = nums[nums.length - 1]; min = max;
            if (nums[nums.length - 1] == 0) {
                min = 1; max = 1;
            }
            return nums[nums.length - 1] != 0 ? max : 0;
        }
        int res = dfs(i + 1, nums);
        
        if (nums[i] == 0) { min = 1; max = 1; 
                 return res > 0 ? res : 0;
        }
        int temp = max;
        max = Math.max(max * nums[i], min * nums[i]);
        max = Math.max(max, nums[i]);
        min = Math.min(temp * nums[i], min * nums[i]);
        min = Math.min(min, nums[i]);
        
        res = Math.max(res, max);
        return res;
    }
}


// Unique Paths

class Solution {
    public int uniquePaths(int m, int n) {
        
        if (m == 1 && n == 1) return 1;
        int[] prev = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    curr[j] = 1;
                }
                else {
                    int up = prev[j];
                    int left = 0;

                    if (j > 0) left = curr[j - 1];
                    curr[j] = left + up;
                }
            }
            prev = curr;
        }

        return prev[n - 1];
    }
}


// Word Break


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {

                int start = i - word.length();
                if (start >= 0 && dp[start] && s.substring(start, i).equals(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}


// Count Square Submatrices with All Ones

class Solution {
    public int countSquares(int[][] matrix) {
        
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        int totalCount = 0;

        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0];
            totalCount += dp[i][0];
        }

        for (int j = 1; j < cols; j++) {
            dp[0][j] = matrix[0][j];
            totalCount += dp[0][j];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {

                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                }
                totalCount += dp[i][j];
            }
        }

        return totalCount;
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
