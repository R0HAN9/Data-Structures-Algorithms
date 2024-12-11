// Course Schedule


import java.util.*;

public class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Initialize inDegree array and adjacency list
        // `inDegree[i]` represents the number of prerequisites for course `i`.
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adjList = new ArrayList<>();

        // Create an adjacency list to represent the graph of courses and prerequisites.
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 2: Populate the adjacency list and inDegree array
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0]; // The course to be taken.
            int pre = prerequisite[1];    // The prerequisite for the course.

            // Add the course to the adjacency list of the prerequisite.
            adjList.get(pre).add(course);

            // Increment the inDegree of the course, indicating one more prerequisite.
            inDegree[course]++;
        }

        // Step 3: Initialize a queue for courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();

        // Add all courses with inDegree of 0 (no prerequisites) to the queue.
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 4: Process the courses using a BFS approach
        int count = 0; // Counter for the number of courses that can be completed.

        while (!queue.isEmpty()) {
            // Remove a course from the queue and increment the count.
            int current = queue.poll();
            count++;

            // Iterate over all the courses that depend on the current course.
            for (int neighbor : adjList.get(current)) {
                // Decrease the inDegree of the dependent course.
                inDegree[neighbor]--;

                // If the dependent course has no more prerequisites, add it to the queue.
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 5: Check if all courses have been processed
        // If `count` equals `numCourses`, all courses can be finished; otherwise, they cannot.
        return count == numCourses;
    }
}



// Minimum Absolute Difference in BST

class Solution {
    public int getMinimumDifference(TreeNode root) {
        // Step 1: Create a list to store the values of nodes in in-order
        List<Integer> values = new ArrayList<>();
        
        // Step 2: Perform in-order traversal to fill the values list
        inOrder(root, values);

        // Step 3: Initialize the minDiff to the largest possible integer
        int minDiff = Integer.MAX_VALUE;
        
        // Step 4: Iterate through the values list and find the minimum difference
        for (int i = 1; i < values.size(); i++) {
            // Update the minDiff with the smaller value between the current minDiff and the difference between adjacent values
            minDiff = Math.min(minDiff, values.get(i) - values.get(i - 1));
        }

        // Step 5: Return the minimum difference found
        return minDiff;
    }
    
    // Helper function for in-order traversal
    private void inOrder(TreeNode node, List<Integer> values) {
        // Base case: if the node is null, return
        if (node == null) return;

        // Recursively traverse the left subtree
        inOrder(node.left, values);

        // Add the current node's value to the list
        values.add(node.val);

        // Recursively traverse the right subtree
        inOrder(node.right, values);
    }
}




// Kth Largest Sum in a Binary Tree

class Solution {
    public long kthLargestLevelSum(TreeNode root, int k) {
        // Initialize a list to store the sum of each level
        List<Long> result = new ArrayList<>();
        
        // Initialize a queue for level-order (BFS) traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);  // Add the root node to the queue

        // Perform level-order traversal
        while (!queue.isEmpty()) {
            int num = queue.size();  // Number of nodes at the current level
            long sum = 0;  // Sum of values at the current level

            // Process all nodes at the current level
            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();  // Remove node from the front of the queue
                sum += node.val;  // Add the node's value to the sum

                // Add left and right child nodes to the queue if they exist
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            // Store the sum of the current level
            result.add(sum);
        }

        // If k is greater than the number of levels, return -1
        if (k > result.size()) return -1;

        // Sort the sums of each level in descending order to find the k-th largest level sum
        result.sort(Collections.reverseOrder());

        // Return the k-th largest sum (index k-1 since list is 0-based)
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
