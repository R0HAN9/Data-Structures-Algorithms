// Minimum Path Sum


class Solution {
    public int minPathSum(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                int leftPathSum = (int) (1e9);
                int upPathSum = (int) (1e9);

                if (j > 0) leftPathSum = grid[i][j] + dp[i][j - 1];
                if (i > 0) upPathSum = grid[i][j] + dp[i - 1][j];
                dp[i][j] = Math.min(leftPathSum, upPathSum);
            }
        }

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
        
        Collections.sort(robot);
        Arrays.sort(factory, (a,b) -> Integer.compare(a[0], b[0]));

        int m = robot.size();
        int n = factory.length;
        long[][] dp = new long[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            dp[i][n] = Long.MAX_VALUE;
        }

        for (int j = n-1; j >= 0; j--) {

            long prefix = 0;
            Deque<Pair<Integer, Long>> qq = new ArrayDeque<>();

            qq.offer(new Pair<>(m, 0L));
            for (int i = m-1; i >= 0; i--) {
                
                prefix += Math.abs(robot.get(i) - factory[j][0]);
                while (!qq.isEmpty() && qq.peekLast().getValue() >= dp[i][j+1] - prefix) {
                    qq.pollLast();
                }

                qq.offerLast(new Pair<>(i, dp[i][j+1] - prefix));
                dp[i][j] = qq.peekFirst().getValue() + prefix;
            }
        }

        return dp[0][0];
    }

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
