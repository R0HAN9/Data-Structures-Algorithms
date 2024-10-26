// Word Search II

class Node {
    private Node[] links;
    private boolean wordEnd;

    public Node() {
        links = new Node[26];
        wordEnd = false;
    }

    public boolean isNull(int ind) {
        return links[ind] == null;
    }

    public void put(int ind, Node node) {
        links[ind] = node;
    }

    public Node get(int ind) {
        return links[ind];
    }

    public void setEnd() {
        wordEnd = true;
    }

    public void unSetEnd() {
        wordEnd = false;
    }

    public boolean isEnd() {
        return wordEnd;
    }
}


class Solution {
    private int[] Y = {-1, 0, 1, 0};
    private int[] X = {0, -1, 0, 1};

    private void insert(String word, Node node) {
        for (char ch : word.toCharArray()) {
            int ind = ch - 'a';
            if (node.isNull(ind)) {
                node.put(ind, new Node());
            }
            node = node.get(ind);
        }
        node.setEnd();
    }

    private void checkWord(int i, int j, char[][] board, boolean[][] vis, List<String> res, Node node, String s) {
        if (node.isEnd()) {
            res.add(s);
            node.unSetEnd();
        }

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || vis[i][j]) return;

        vis[i][j] = true;
        int ind = board[i][j] - 'a';
        if (!node.isNull(ind)) {
            for (int k = 0; k < 4; k++) {
                int ii = i + X[k];
                int jj = j + Y[k];
                checkWord(ii, jj, board, vis, res, node.get(ind), s + board[i][j]);
            }
        }

        vis[i][j] = false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        int rows = board.length, cols = board[0].length;
        List<String> res = new ArrayList<>();
        boolean[][] vis = new boolean[rows][cols];

        Node root = new Node();
        for (String word : words) {
            insert(word, root);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!root.isNull(board[i][j] - 'a')) {
                    checkWord(i, j, board, vis, res, root, "");
                }
            }
        }

        return res;
    }
}




// N-Queens

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        if (n == 1) {
            List<String> solution = new ArrayList<>();
            solution.add("Q");
            results.add(solution);
            return results;
        }
        if (n == 2 || n == 3) {
            return results;
        }

        int[] solution = new int[n];
        for (int i = 0; i < n; i++) {
            solution[i] = -1;
        }

        solveNQueensRec(n, solution, 0, results);
        return results;
    }

    // Recursive worker function
    private void solveNQueensRec(int n, int[] solution, int row, List<List<String>> results) {
        if (row == n) {
            List<String> solutionStr = constructSolutionString(solution);
            results.add(solutionStr);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValidMove(row, i, solution)) {
                solution[row] = i;
                solveNQueensRec(n, solution, row + 1, results);
                solution[row] = -1; // Backtrack
            }
        }
    }

    // This method determines if a queen can be placed at
    // proposedRow, proposedCol with the current solution
    private boolean isValidMove(int proposedRow, int proposedCol, int[] solution) {
        for (int i = 0; i < proposedRow; i++) {
            int oldRow = i;
            int oldCol = solution[i];
            int diagonalOffset = proposedRow - oldRow;

            if (oldCol == proposedCol || oldCol == proposedCol - diagonalOffset
                    || oldCol == proposedCol + diagonalOffset) {
                return false;
            }
        }
        return true;
    }

    // Constructs the board solution as a list of strings
    private List<String> constructSolutionString(int[] solution) {
        List<String> returnArr = new ArrayList<>();
        for (int i = 0; i < solution.length; i++) {
            char[] row = new char[solution.length];
            for (int j = 0; j < solution.length; j++) {
                row[j] = '.';
            }
            row[solution[i]] = 'Q';
            returnArr.add(new String(row));
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



