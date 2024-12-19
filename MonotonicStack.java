// Trapping Rain Water

class Solution {
    public int trap(int[] height) {
        
        int left = 0; // Pointer for the left side of the array
        int right = height.length - 1; // Pointer for the right side of the array

        int leftMax = height[left]; // Initialize the maximum height from the left
        int rightMax = height[right]; // Initialize the maximum height from the right
        int water = 0; // Variable to store the total trapped water

        // Process the array until the two pointers meet
        while (left < right) {
            if (leftMax < rightMax) {
                // Move the left pointer one step to the right
                left++;
                // Update the maximum height encountered from the left
                leftMax = Math.max(leftMax, height[left]);
                // Calculate water trapped at the current position
                water += leftMax - height[left];
            } else {
                // Move the right pointer one step to the left
                right--;
                // Update the maximum height encountered from the right
                rightMax = Math.max(rightMax, height[right]);
                // Calculate water trapped at the current position
                water += rightMax - height[right];
            }
        }

        return water; // Return the total amount of trapped water
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
