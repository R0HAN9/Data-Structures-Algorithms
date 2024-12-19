// Height of Binary Tree After Subtree Removal Queries


class Solution {

    // Arrays to store depth, levels, and maximum heights at each level
    int[] depth; 
    int[] levelArray; 
    int[] max1; 
    int[] max2; 

    public int height(TreeNode root, int level) {

        if (root == null) return 0; // Base case: null node has height 0

        // Store the level of the current node
        levelArray[root.val] = level;

        // Compute the depth of the current node recursively
        depth[root.val] = 1 + Math.max(height(root.left, level + 1), height(root.right, level + 1));

        // Update the maximum and second maximum depths at the current level
        if (max1[level] < depth[root.val]) {
            max2[level] = max1[level];
            max1[level] = depth[root.val];
        } else if (max2[level] < depth[root.val]) {
            max2[level] = depth[root.val];
        }

        return depth[root.val]; // Return the depth of the current node
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        
        // Initialize arrays for up to 100001 nodes
        depth = new int[100001];
        levelArray = new int[100001];
        max1 = new int[100001];
        max2 = new int[100001];

        // Calculate depths and levels of all nodes
        height(root, 0);

        for (int i = 0; i < queries.length; i++) {

            int q = queries[i]; // Current query node
            int level = levelArray[q]; // Level of the query node

            // Compute the new height of the tree excluding the query node
            queries[i] = (max1[level] == depth[q] ? max2[level] : max1[level]) + level - 1;
        }

        return queries; // Return the results for all queries
    }
}





// Search in Rotated Sorted Array ***


class Solution {
    public int search(int[] nums, int target) {
        int left = 0; // Initialize the left pointer
        int right = nums.length - 1; // Initialize the right pointer

        while (left <= right) {
            int mid = (left + right) / 2; // Calculate the middle index

            if (nums[mid] == target) {
                return mid; // If the middle element is the target, return its index
            } 
            // Check if the left half is sorted
            else if (nums[mid] >= nums[left]) {
                // If the target lies in the sorted left half
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1; // Adjust the right pointer
                } else {
                    left = mid + 1; // Adjust the left pointer
                }
            } 
            // Else, the right half must be sorted
            else {
                // If the target lies in the sorted right half
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1; // Adjust the left pointer
                } else {
                    right = mid - 1; // Adjust the right pointer
                }
            }
        }

        return -1; // Return -1 if the target is not found
    }
}




// 2. Kth Largest Element in an Array


class Solution {
    public int findKthLargest(int[] nums, int k) {
        int main_max = Integer.MIN_VALUE;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(hm.containsKey(nums[i])){
                hm.put(nums[i],hm.get(nums[i]) +1);
            }else{
                hm.put(nums[i],1);
            }
            main_max = Math.max(main_max,nums[i]);
        }
        if(hm.size()==1){
            return main_max;
        }
        k -= hm.get(main_max);
        while(k>0){
            main_max--;
            if(hm.containsKey(main_max)){
                k -= hm.get(main_max);
            }
        }
        return main_max;
    }
}
