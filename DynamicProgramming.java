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
