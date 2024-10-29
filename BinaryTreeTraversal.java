// Path Sum

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;

        int remainingSum = targetSum - root.val;
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
        
        List<List<Integer>> arr = new ArrayList<>();
        if (root == null) return arr;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean flag = true;
        while (!queue.isEmpty()) {

            int n = queue.size();
            List<Integer> arr2 = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                if (queue.peek().left != null) {
                    queue.add(queue.peek().left);
                }
                if (queue.peek().right != null) {
                    queue.add(queue.peek().right);
                }

                if (flag) {
                    arr2.add(queue.poll().val);
                }
                else {
                    arr2.add(0, queue.poll().val);
                }
            }
            flag =! flag;
            arr.add(arr2);
        }

        return arr;
    }
}



// 3. Construct Binary Tree from Preorder and Inorder Traversal



class Solution {

    private int preorderIndex;
    private Map<Integer, Integer> mapping;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        mapping = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            mapping.put(inorder[i], i);
        }

        preorderIndex = 0;
        return build(preorder, 0, inorder.length - 1);

    }

    private TreeNode build(int[] preorder, int start, int end) {

        if (start > end) return null;
        int rootVal = preorder[preorderIndex++];

        TreeNode root = new TreeNode(rootVal);
        int mid = mapping.get(rootVal);

        root.left = build(preorder, start, mid - 1); 
        root.right = build(preorder, mid + 1, end);

        return root; 
    }
}


// Binary Tree Level Order Traversal

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {

                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(level);
        }

        return ans;
    }
}
