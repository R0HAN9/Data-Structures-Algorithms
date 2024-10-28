// Clone Graph

class Solution {
    public Node cloneGraph(Node node) {
     if(node==null)
     {
        return null;
     }
     //hashmap to keep track of all cloned nodes
     Map<Node , Node>map= new HashMap<>();

     //queue to perform a bfs
     Queue<Node> queue = new LinkedList<>();


     queue.add(node);
     map.put(node,new Node(node.val));

     while(!queue.isEmpty())
     {
        Node curr = queue.poll();
        Node currClone = map.get(curr);

      for(Node neighbor:curr.neighbors)
      {
        if(!map.containsKey(neighbor))
        {
            //clone the neighbor node and add it to map

            map.put(neighbor,new Node(neighbor.val));
            queue.add(neighbor);
        }

        currClone.neighbors.add(map.get(neighbor));
      }
     }

return map.get(node);
        
    }
}



// Cousins in Binary Tree II


class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        
        dfs(new TreeNode[] {root});
        root.val = 0;
        return root;
    }

    private void dfs(TreeNode[] arr) {

        if (arr.length == 0) return;
        int sum = 0;

        for (TreeNode node : arr) {

            if (node == null) continue;
            if (node.left != null) sum += node.left.val;
            if (node.right != null) sum += node.right.val;
        }

        TreeNode[] childArr = new TreeNode[arr.length * 2];
        int index = 0;

        for (TreeNode node : arr) {
            int currSum = 0;

            if (node.left != null) currSum += node.left.val;
            if (node.right != null) currSum += node.right.val;

            if (node.left != null) {
                node.left.val = sum - currSum;
                childArr[index++] = node.left;
            }

            if (node.right != null) {
                node.right.val = sum - currSum;
                childArr[index++] = node.right;
            }
        }

        dfs(java.util.Arrays.copyOf(childArr, index));
    }
}


// Flip Equivalent Binary Trees


class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        
        return checker(root1, root2);
    }

    public boolean checker(TreeNode node1, TreeNode node2) {

        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null || node1.val != node2.val) return false;

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
