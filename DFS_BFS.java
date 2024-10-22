// Kth Largest Sum in a Binary Tree

class Solution {
    public long kthLargestLevelSum(TreeNode root, int k) {
        
        List<Long> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int num = queue.size();
            long sum = 0;

            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(sum);
        }

        if (k > result.size()) return -1;
        result.sort(Collections.reverseOrder());

        return result.get(k - 1);
    }
}




// Coin Change

class Solution {
    int[][] dp;

    public int coinChange(int[] coins, int amount) {
        
        dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                if (i == 0) dp[i][j] = Integer.MAX_VALUE - 1;
                if (j == 0) dp[i][j] = 0;

                if (i == 1 && j != 0) {
                    if (j % coins[0] == 0) {
                        dp[i][j] = j / coins[0];
                    }
                    else {
                        dp[i][j] = Integer.MAX_VALUE - 1;
                    }
                }
            }
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (coins[i-1] <= j) {
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i-1]], dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        if (dp[coins.length][amount] >= Integer.MAX_VALUE - 1) return -1;

        return dp[coins.length][amount];
    }
}
