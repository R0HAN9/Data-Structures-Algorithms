// Word Search II

class Node {
    private Node[] links; // Array to store references to child nodes
    private boolean wordEnd; // Flag to mark the end of a word

    public Node() {
        links = new Node[26]; // Initialize links for 26 lowercase English letters
        wordEnd = false; // Initialize word end flag as false
    }

    public boolean isNull(int ind) {
        return links[ind] == null; // Check if a child node at a given index is null
    }

    public void put(int ind, Node node) {
        links[ind] = node; // Add a child node at the given index
    }

    public Node get(int ind) {
        return links[ind]; // Retrieve the child node at the given index
    }

    public void setEnd() {
        wordEnd = true; // Mark this node as the end of a word
    }

    public void unSetEnd() {
        wordEnd = false; // Unmark this node as the end of a word
    }

    public boolean isEnd() {
        return wordEnd; // Check if this node is the end of a word
    }
}

class Solution {
    private int[] Y = {-1, 0, 1, 0}; // Directions for vertical movement (up, right, down, left)
    private int[] X = {0, -1, 0, 1}; // Directions for horizontal movement (up, right, down, left)

    // Inserts a word into the Trie
    private void insert(String word, Node node) {
        for (char ch : word.toCharArray()) { // Iterate through each character in the word
            int ind = ch - 'a'; // Get the index of the character in the links array
            if (node.isNull(ind)) { // If the node does not exist, create it
                node.put(ind, new Node());
            }
            node = node.get(ind); // Move to the next node
        }
        node.setEnd(); // Mark the last node as the end of the word
    }

    // Recursive function to search for words in the board
    private void checkWord(int i, int j, char[][] board, boolean[][] vis, List<String> res, Node node, String s) {
        // If a complete word is found
        if (node.isEnd()) {
            res.add(s); // Add the word to the result list
            node.unSetEnd(); // Prevent adding the same word multiple times
        }

        // Boundary conditions and visited check
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || vis[i][j]) return;

        vis[i][j] = true; // Mark the cell as visited
        int ind = board[i][j] - 'a'; // Get the index for the current character
        if (!node.isNull(ind)) { // If the character exists in the Trie
            for (int k = 0; k < 4; k++) { // Explore all 4 directions
                int ii = i + X[k];
                int jj = j + Y[k];
                checkWord(ii, jj, board, vis, res, node.get(ind), s + board[i][j]); // Recursive call
            }
        }

        vis[i][j] = false; // Backtrack: Unmark the cell as visited
    }

    // Main function to find all words from the list in the board
    public List<String> findWords(char[][] board, String[] words) {
        int rows = board.length, cols = board[0].length;
        List<String> res = new ArrayList<>(); // Result list to store found words
        boolean[][] vis = new boolean[rows][cols]; // Visited array to track visited cells

        Node root = new Node(); // Root node of the Trie
        for (String word : words) { // Insert all words into the Trie
            insert(word, root);
        }

        for (int i = 0; i < rows; i++) { // Traverse each cell of the board
            for (int j = 0; j < cols; j++) {
                if (!root.isNull(board[i][j] - 'a')) { // Start searching if the character exists in the Trie
                    checkWord(i, j, board, vis, res, root, "");
                }
            }
        }

        return res; // Return the list of found words
    }
}





// N-Queens

class Solution {
    public List<List<String>> solveNQueens(int n) {
        // List to store all valid solutions
        List<List<String>> results = new ArrayList<>();
        
        // Edge case: Single queen solution
        if (n == 1) {
            List<String> solution = new ArrayList<>();
            solution.add("Q");
            results.add(solution);
            return results;
        }
        
        // Edge case: No solutions for 2x2 or 3x3 boards
        if (n == 2 || n == 3) {
            return results;
        }

        // Array to track queen positions; -1 means no queen placed in that row
        int[] solution = new int[n];
        for (int i = 0; i < n; i++) {
            solution[i] = -1;
        }

        // Start recursive backtracking
        solveNQueensRec(n, solution, 0, results);
        return results;
    }

    // Recursive function to solve N-Queens
    private void solveNQueensRec(int n, int[] solution, int row, List<List<String>> results) {
        // Base case: All queens are placed
        if (row == n) {
            // Convert solution array into board representation
            List<String> solutionStr = constructSolutionString(solution);
            results.add(solutionStr); // Add to results
            return;
        }

        // Try placing a queen in every column of the current row
        for (int i = 0; i < n; i++) {
            if (isValidMove(row, i, solution)) { // Check if position is valid
                solution[row] = i;              // Place the queen
                solveNQueensRec(n, solution, row + 1, results); // Recurse to next row
                solution[row] = -1;             // Backtrack
            }
        }
    }

    // Function to check if placing a queen at (proposedRow, proposedCol) is valid
    private boolean isValidMove(int proposedRow, int proposedCol, int[] solution) {
        // Check against all previously placed queens
        for (int i = 0; i < proposedRow; i++) {
            int oldRow = i; // Row of a previously placed queen
            int oldCol = solution[i]; // Column of that queen
            int diagonalOffset = proposedRow - oldRow; // Distance in rows

            // Check for same column or diagonal conflict
            if (oldCol == proposedCol || 
                oldCol == proposedCol - diagonalOffset || 
                oldCol == proposedCol + diagonalOffset) {
                return false; // Invalid position
            }
        }
        return true; // Valid position
    }

    // Converts the solution array into a list of strings representing the board
    private List<String> constructSolutionString(int[] solution) {
        List<String> returnArr = new ArrayList<>();
        for (int i = 0; i < solution.length; i++) {
            char[] row = new char[solution.length]; // Create a row
            for (int j = 0; j < solution.length; j++) {
                row[j] = '.'; // Fill the row with empty spaces
            }
            row[solution[i]] = 'Q'; // Place the queen
            returnArr.add(new String(row)); // Add the row to the board
        }
        return returnArr;
    }
}




// 2. Permutations


class Solution {

    // Swap two elements in a list at specified indices
    public void swap(List<Integer> numberList, int indexA, int indexB) {
        int temp = numberList.get(indexA);
        numberList.set(indexA, numberList.get(indexB));
        numberList.set(indexB, temp);
    }

    // Recursive method to generate all permutations
    public void generatePermutations(List<List<Integer>> allPermutations, List<Integer> currentPermutation, int currentIndex) {

        // Base case: If we have a complete permutation
        if (currentIndex == currentPermutation.size()) {
            allPermutations.add(new ArrayList<>(currentPermutation)); // Add a copy to the result list
            return;
        }

        // Iterate through the elements starting from the current index
        for (int j = currentIndex; j < currentPermutation.size(); j++) {
            swap(currentPermutation, currentIndex, j); // Place element j at the current index
            generatePermutations(allPermutations, currentPermutation, currentIndex + 1); // Generate permutations for the next position
            swap(currentPermutation, currentIndex, j); // Backtrack to restore the original order
        }
    }

    // Public method to initiate the permutation generation
    public List<List<Integer>> permute(int[] inputArray) {

        List<List<Integer>> allPermutations = new ArrayList<>(); // List to store all permutations
        List<Integer> currentPermutation = new ArrayList<>();    // Current permutation being constructed

        // Convert the input array into a list
        for (int element : inputArray) {
            currentPermutation.add(element);
        }
        
        // Start generating permutations from index 0
        generatePermutations(allPermutations, currentPermutation, 0);
        return allPermutations;
    }
}



// 3. Combination Sum

class Solution {

    // Public method to find all unique combinations that sum to the target
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>(); // List to store all valid combinations
        makeCombination(candidates, target, 0, new ArrayList<>(), 0, result); // Start the recursive process
        return result; // Return the result
    }

    // Recursive method to generate combinations
    private void makeCombination(
        int[] candidates,            // Array of candidate numbers
        int target,                  // Desired target sum
        int index,                   // Current index in candidates array
        List<Integer> comb,          // Current combination being constructed
        int total,                   // Current sum of elements in comb
        List<List<Integer>> result   // List to store all valid combinations
    ) {

        // Base case: If the current sum equals the target, add the combination to the result
        if (total == target) {
            result.add(new ArrayList<>(comb)); // Create a new list and add it to the result
            return;
        }

        // Base case: If the sum exceeds the target or index is out of bounds, stop further exploration
        if (total > target || index >= candidates.length) return;

        // Include the current candidate in the combination
        comb.add(candidates[index]);

        // Explore further by including the current candidate again (allow repetitions)
        makeCombination(candidates, target, index, comb, total + candidates[index], result);

        // Backtrack: Remove the last added candidate to explore other possibilities
        comb.remove(comb.size() - 1);

        // Explore further by moving to the next candidate
        makeCombination(candidates, target, index + 1, comb, total, result);
    }
}





// Subsets


class Solution {

    // Global variable to store all subsets
    List<List<Integer>> Ans = new ArrayList<>();
    int n; // Length of the input array

    // Public method to generate all subsets of the input array
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> op = new ArrayList<>(); // Temporary list to hold the current subset
        n = nums.length; // Store the length of the input array
        Helper(op, nums, 0); // Start the recursive process
        return Ans; // Return the result
    }

    // Recursive helper function to explore all subsets
    public void Helper(List<Integer> op, int[] nums, int startIndex) {

        // Base case: If startIndex reaches the length of nums, add the current subset to Ans
        if (startIndex == n) {
            Ans.add(new ArrayList<>(op)); // Create a copy of op and add it to the result list
            return; // Stop further recursion
        }

        // Case 1: Include the current element in the subset
        op.add(nums[startIndex]); // Add the current element to the temporary list
        Helper(op, nums, startIndex + 1); // Recursive call to process the next index

        // Case 2: Exclude the current element from the subset
        op.remove(op.size() - 1); // Backtrack by removing the last element
        Helper(op, nums, startIndex + 1); // Recursive call to process the next index
    }
}




// Word Search


class Solution {
    // Main function to check if the word exists in the board
    public boolean exist(char[][] board, String word) {
        
        // Iterate through each cell in the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                // Check if the current cell matches the first character of the word
                if (board[i][j] == word.charAt(0)) {

                    // Start the recursive search from this cell
                    boolean ans = search(board, word, i, j, 0);

                    // If the word is found, return true
                    if (ans) return ans; 
                }
            }
        }
        // If the word is not found, return false
        return false;
    }

    // Helper method to perform a recursive search for the word
    public static boolean search(char[][] board, String word, int row, int col, int index) {

        // Base case: If we've matched all characters of the word, return true
        if (index == word.length()) return true;

        // Boundary and character mismatch check
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark the current cell as visited by replacing its value
        board[row][col] = '*';

        // Define the directions for moving up, down, left, and right
        int[] rows = {-1, 1, 0, 0};
        int[] cols = {0, 0, -1, 1};

        // Explore all four possible directions
        for (int i = 0; i < cols.length; i++) {

            // Recursively search the next character in the word
            boolean ans = search(board, word, row + rows[i], col + cols[i], index + 1);

            // If the word is found, return true
            if (ans == true) return ans;
        }

        // Backtrack: Restore the cell's original value
        board[row][col] = word.charAt(index);

        // If the word is not found in any direction, return false
        return false;
    }
}




