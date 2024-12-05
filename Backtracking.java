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
    public void swap(List<Integer> numberList, int indexA, int indexB) {

        int temp = numberList.get(indexA);
        numberList.set(indexA, numberList.get(indexB));
        numberList.set(indexB, temp);
    }

    public void generatePermutations(List<List<Integer>> allPermutations, List<Integer> currentPermutation, int currentIndex) {

        if (currentIndex == currentPermutation.size()) {
            allPermutations.add(new ArrayList<>(currentPermutation));
            return;
        }

        for (int j = currentIndex; j < currentPermutation.size(); j++) {

            swap(currentPermutation, currentIndex, j);
            generatePermutations(allPermutations, currentPermutation, currentIndex + 1);
            swap(currentPermutation, currentIndex, j); // backtrack
        }
    }

    public List<List<Integer>> permute(int[] inputArray) {

        List<List<Integer>> allPermutations = new ArrayList<>();
        List<Integer> currentPermutation = new ArrayList<>();

        for (int element : inputArray) {
            currentPermutation.add(element);
        }
        
        generatePermutations(allPermutations, currentPermutation, 0);
        return allPermutations;
    }
}


// 3. Combination Sum

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> result = new ArrayList<>();
        makeCombination(candidates, target, 0, new ArrayList<>(), 0, result);
        return result;
    }

    private void makeCombination(int[] candidates, int target, int index, List<Integer> comb, int total, List<List<Integer>> result) {

        if (total == target) {
            result.add(new ArrayList<>(comb));
            return;
        }

        if (total > target || index >= candidates.length) return;

        comb.add(candidates[index]);
        makeCombination(candidates, target, index, comb, total + candidates[index], result);

        comb.remove(comb.size() - 1);
        makeCombination(candidates, target, index + 1, comb, total, result);
    }
}




// Subsets


class Solution {
    List<List<Integer>> Ans = new ArrayList<>();
    int n;

    public List<List<Integer>> subsets(int[] nums) {
        
        List<Integer> op = new ArrayList<>();
        n = nums.length;

        Helper(op, nums, 0);
        return Ans;
    }

    public void Helper(List<Integer> op, int[] nums, int startIndex) {

        if (startIndex == n) {
            Ans.add(new ArrayList<>(op));
            return;
        }

        op.add(nums[startIndex]);
        Helper(op, nums, startIndex + 1);

        op.remove(op.size() - 1);
        Helper(op, nums, startIndex + 1);
    }
}



// Word Search


class Solution {
    public boolean exist(char[][] board, String word) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == word.charAt(0)) {
                    boolean ans = search(board, word, i, j, 0);

                    if (ans) return ans; 
                }
            }
        }
        return false;
    }

    public static boolean search(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) return true;

        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != word.charAt(index)) {
            return false;
        }

        board[row][col] = '*';
        int[] rows = {-1, 1, 0, 0};
        int[] cols = {0, 0, -1, 1};

        for (int i = 0; i < cols.length; i++) {
            boolean ans = search(board, word, row + rows[i], col + cols[i], index + 1);

            if (ans == true) return ans;
        }

        board[row][col] = word.charAt(index);
        return false;
    }
}



