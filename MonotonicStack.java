// Trapping Rain Water

class Solution {
    public int trap(int[] height) {
        
        int left = 0;
        int right = height.length - 1;

        int leftMax = height[left];
        int rightMax = height[right];
        int water = 0;

        while (left < right) {
            if (leftMax < rightMax) {

                left++;
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
            }
            else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
            }
        }

        return water;
    }
}




// Daily Temperatures

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Deque<Integer> stk = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && temperatures[stk.peek()] <= temperatures[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                ans[i] = stk.peek() - i;
            }
            stk.push(i);
        }
        return ans;
    }
}
