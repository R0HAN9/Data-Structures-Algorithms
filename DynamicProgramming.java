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
