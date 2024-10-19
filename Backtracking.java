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
