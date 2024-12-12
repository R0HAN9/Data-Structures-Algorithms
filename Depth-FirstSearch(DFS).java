// Clone Graph

class Solution {
    public Node cloneGraph(Node node) {
        // Step 1: Base case - if the input node is null, return null
        if(node == null) {
            return null;
        }

        // Step 2: Create a HashMap to store the mapping between original nodes and their clones
        Map<Node, Node> map = new HashMap<>();

        // Step 3: Use a Queue to perform BFS traversal of the graph
        Queue<Node> queue = new LinkedList<>();

        // Add the starting node to the queue and create its clone, store it in the map
        queue.add(node);
        map.put(node, new Node(node.val));

        // Step 4: Perform BFS traversal
        while(!queue.isEmpty()) {
            // Get the current node from the queue
            Node curr = queue.poll();
            // Get the clone of the current node from the map
            Node currClone = map.get(curr);

            // Step 5: Traverse the neighbors of the current node
            for(Node neighbor: curr.neighbors) {
                // If the neighbor hasn't been cloned yet, clone it and add to map
                if(!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);  // Add the neighbor to the queue for further processing
                }

                // Step 6: Add the cloned neighbor to the cloned node's neighbors list
                currClone.neighbors.add(map.get(neighbor));
            }
        }

        // Step 7: Return the cloned node corresponding to the original input node
        return map.get(node);
    }
}




// Cousins in Binary Tree II


class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        // Step 1: Start the DFS traversal with the root node wrapped in an array
        dfs(new TreeNode[] {root});
        
        // Step 2: Set the root's value to 0 as the final step of the transformation
        root.val = 0;
        
        // Step 3: Return the modified root node
        return root;
    }

    private void dfs(TreeNode[] arr) {
        // Step 4: Base case - if the array is empty, return (no nodes to process)
        if (arr.length == 0) return;

        // Step 5: Calculate the total sum of values of all the left and right children of the nodes in the current level
        int sum = 0;
        for (TreeNode node : arr) {
            if (node == null) continue;
            // Add the left and right children's values to the sum
            if (node.left != null) sum += node.left.val;
            if (node.right != null) sum += node.right.val;
        }

        // Step 6: Create a new array to hold the children nodes for the next level
        TreeNode[] childArr = new TreeNode[arr.length * 2];
        int index = 0;

        // Step 7: Process each node in the current level and update its children
        for (TreeNode node : arr) {
            int currSum = 0;

            // Calculate the sum of the left and right children's values of the current node
            if (node.left != null) currSum += node.left.val;
            if (node.right != null) currSum += node.right.val;

            // Step 8: For each child, set its new value to the total sum minus the current node's children sum
            if (node.left != null) {
                node.left.val = sum - currSum; // Set the new value for the left child
                childArr[index++] = node.left; // Add left child to the next level
            }

            if (node.right != null) {
                node.right.val = sum - currSum; // Set the new value for the right child
                childArr[index++] = node.right; // Add right child to the next level
            }
        }

        // Step 9: Recursively call dfs with the new array of children to process the next level
        dfs(java.util.Arrays.copyOf(childArr, index));
    }
}



// Flip Equivalent Binary Trees


class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // Step 1: Start by calling the checker function to compare both trees
        return checker(root1, root2);
    }

    public boolean checker(TreeNode node1, TreeNode node2) {
        // Step 2: Base case - both nodes are null, trees are considered equal at this point
        if (node1 == null && node2 == null) return true;
        
        // Step 3: If one of the nodes is null or the values don't match, return false
        if (node1 == null || node2 == null || node1.val != node2.val) return false;

        // Step 4: Recursively check both children with two possible orientations (flip or no flip)
        return (checker(node1.left, node2.left) || checker(node1.left, node2.right)) && 
               (checker(node1.right, node2.right) || checker(node1.right, node2.left));
    }
}




// Construct Binary Tree from Inorder and Postorder Traversal

class Solution {
    public TreeNode buildHelper(int[] inorder, int inorderStart, int inorderEnd, 
                                 int[] postorder, int postorderStart, int postorderEnd, 
                                 Map<Integer, Integer> indexMap) {
        if (postorderStart > postorderEnd || inorderStart > inorderEnd) {
            return null;
        }

        TreeNode rootNode = new TreeNode(postorder[postorderEnd]);
        int rootIndexInInorder = indexMap.get(rootNode.val);
        int leftSubtreeSize = rootIndexInInorder - inorderStart;

        rootNode.left = buildHelper(inorder, inorderStart, rootIndexInInorder - 1, 
                                     postorder, postorderStart, postorderStart + leftSubtreeSize - 1, 
                                     indexMap);
        rootNode.right = buildHelper(inorder, rootIndexInInorder + 1, inorderEnd, 
                                      postorder, postorderStart + leftSubtreeSize, postorderEnd - 1, 
                                      indexMap);

        return rootNode;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, indexMap);
    }
}
