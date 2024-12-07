// Path Sum

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        
        // Base case: If the tree is empty, there's no path to check
        if (root == null) return false;

        // Check if the current node is a leaf node (no left or right child)
        // and if the value of this node equals the remaining targetSum
        if (root.left == null && root.right == null) return root.val == targetSum;

        // Calculate the remaining sum by subtracting the current node's value from targetSum
        int remainingSum = targetSum - root.val;

        // Recursively check the left and right subtrees to see if a valid path exists
        // If any of the two subtrees returns true, then there's a valid path
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }
}



// Binary Tree Maximum Path Sum


class Solution {

    // Helper function to compute the maximum path sum at each node
    private int findMaxPathSum(TreeNode root, int[] maximum) {
        // Base case: if the current node is null, return 0 (no contribution to the path sum)
        if (root == null) return 0;

        // Recursively calculate the maximum path sum of the left subtree
        // Use Math.max(0, ...) to ignore any negative contributions from the subtree
        int leftSum = Math.max(0, findMaxPathSum(root.left, maximum));
        
        // Recursively calculate the maximum path sum of the right subtree
        // Also use Math.max(0, ...) to ignore negative contributions
        int rightSum = Math.max(0, findMaxPathSum(root.right, maximum));

        // Update the overall maximum path sum that includes the current node
        // This is the sum of the current node's value and the maximum path sums from both left and right subtrees
        maximum[0] = Math.max(maximum[0], leftSum + rightSum + root.val);

        // Return the maximum path sum including the current node and either its left or right subtree
        // This will help build up the maximum path as we traverse upwards in the tree
        return root.val + Math.max(leftSum, rightSum);
    }

    // Main function to find the maximum path sum in the binary tree
    public int maxPathSum(TreeNode root) {
        // Initialize an array to store the overall maximum path sum
        // We use an array to hold the result because Java uses pass-by-value, and we need to maintain
        // a global reference to the maximum value as we recurse through the tree
        int[] maximum = new int[1];
        
        // Set the initial maximum path sum to the smallest possible integer value
        maximum[0] = Integer.MIN_VALUE;

        // Call the helper function to compute the maximum path sum starting from the root
        findMaxPathSum(root, maximum);

        // Return the overall maximum path sum found
        return maximum[0];
    }
}



// 2. Binary Tree Zigzag Level Order Traversal


class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        // Initialize a result list to store the zigzag level order traversal
        List<List<Integer>> arr = new ArrayList<>();
        if (root == null) return arr; // Return an empty list if the tree is empty

        // Use a queue to facilitate level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); // Start with the root node

        // A flag to determine the direction of traversal at each level
        boolean flag = true;

        // While there are nodes to process in the queue
        while (!queue.isEmpty()) {

            int n = queue.size(); // Number of nodes in the current level
            List<Integer> arr2 = new ArrayList<>(n); // Temporary list to store values at the current level

            // Process all nodes in the current level
            for (int i = 0; i < n; i++) {

                // Check the left child of the current node and add it to the queue if not null
                if (queue.peek().left != null) {
                    queue.add(queue.peek().left);
                }

                // Check the right child of the current node and add it to the queue if not null
                if (queue.peek().right != null) {
                    queue.add(queue.peek().right);
                }

                // If the flag is true, add the node's value to the end of the list
                if (flag) {
                    arr2.add(queue.poll().val);
                } 
                // Otherwise, add the node's value to the front of the list (reverse order for zigzag effect)
                else {
                    arr2.add(0, queue.poll().val);
                }
            }

            // Toggle the flag for the next level to reverse the traversal order
            flag = !flag;

            // Add the current level's values to the result list
            arr.add(arr2);
        }

        // Return the final zigzag level order traversal
        return arr;
    }
}




// 3. Construct Binary Tree from Preorder and Inorder Traversal



class Solution {

    private int preorderIndex; // Tracks the current index in the preorder array
    private Map<Integer, Integer> mapping; // Maps each value in the inorder array to its index for quick lookup

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Step 1: Build a map to quickly find the index of any value in the inorder array
        mapping = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            mapping.put(inorder[i], i);
        }

        preorderIndex = 0; // Initialize preorder index to start from the root (first element in preorder)
        return build(preorder, 0, inorder.length - 1); // Call helper method to construct the tree
    }

    private TreeNode build(int[] preorder, int start, int end) {
        // Base Case: If the start index is greater than the end index, no nodes exist in this subtree
        if (start > end) return null;

        // Step 2: Pick the current root node value from preorder array using preorderIndex
        int rootVal = preorder[preorderIndex++]; 

        // Create a new TreeNode with the root value
        TreeNode root = new TreeNode(rootVal);

        // Find the index of the root value in the inorder array using the mapping
        int mid = mapping.get(rootVal);

        // Step 3: Recursively build the left subtree with elements to the left of mid in inorder array
        root.left = build(preorder, start, mid - 1); 

        // Step 4: Recursively build the right subtree with elements to the right of mid in inorder array
        root.right = build(preorder, mid + 1, end);

        // Return the constructed subtree rooted at the current node
        return root; 
    }
}



// Binary Tree Level Order Traversal

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> ans = new ArrayList<>(); // Initialize the result list to store level-wise node values
        if (root == null) return ans; // If the tree is empty, return an empty list

        Queue<TreeNode> queue = new LinkedList<>(); // Initialize a queue to help process nodes level by level
        queue.add(root); // Start with the root node

        while (!queue.isEmpty()) { // Continue until there are no nodes left to process in the queue
            int levelSize = queue.size(); // Number of nodes in the current level

            List<Integer> level = new ArrayList<>(); // List to store node values for the current level
            for (int i = 0; i < levelSize; i++) { // Process all nodes in the current level

                TreeNode node = queue.poll(); // Remove a node from the queue
                level.add(node.val); // Add its value to the current level's list

                if (node.left != null) queue.add(node.left); // If the left child exists, add it to the queue
                if (node.right != null) queue.add(node.right); // If the right child exists, add it to the queue
            }
            ans.add(level); // Add the current level's list to the final result
        }

        return ans; // Return the list of levels
    }
}




// Binary Tree Right Side View


class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> temp = new LinkedList<>();
        temp.add(root);

        while (!temp.isEmpty()) {
            int l = temp.size();
            int val = 0;

            for (int i = 0; i < l; i++) {
                TreeNode node = temp.poll();
                val = node.val;

                if (node.left != null) temp.add(node.left);
                if (node.right != null) temp.add(node.right);
            }
            ans.add(val);
        }

        return ans;
    }
}
