// Remove Element


class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;        
    }
}



// Median of Two Sorted Arrays


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        double[] mergedArray = new double[nums1.length + nums2.length];
        int index = 0;

        for (int num : nums1) {
            mergedArray[index++] = num;
        }
        for (int num : nums2) {
            mergedArray[index++] = num;
        }

        Arrays.sort(mergedArray);
        int totalLength = mergedArray.length;

        if (totalLength % 2 == 0) {
            return (mergedArray[totalLength / 2] + mergedArray[totalLength / 2 - 1]) / 2.0;
        }
        else {
            return mergedArray[totalLength / 2];
        }
    }
}



// Longest Increasing Subsequence

class Solution {
    public int lengthOfLIS(int[] nums) {
        
        int[] tails = new int[nums.length];
        int size = 0;

        for (int x : nums) {
            int i = 0;
            int j = size;

            while (i != j) {
                int m = (i + j) / 2;

                if (tails[m] < x) i = m + 1;
                else j = m;
            }

            tails[i] = x;
            if (i == size) ++size;
        }

        return size;
    }
}




// Longest Consecutive Sequence

class Solution {
    public int longestConsecutive(int[] nums) {
        
        int maxSeq = 0;
        Set<Integer> sequences = new HashSet<>();

        for (int num : nums) {
            sequences.add(num);
        }

        for (int num : nums) {

            int nextInSeq = num + 1;
            int prevInSeq = num - 1;
            int currentSequence = 1;

            while (sequences.remove(prevInSeq--)) currentSequence++;
            while (sequences.remove(nextInSeq++)) currentSequence++;

            if (currentSequence > maxSeq) maxSeq = currentSequence;

        }
        return maxSeq;
    }



// Game of Life


class Solution {
    public void gameOfLife(int[][] board) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                setAns(i, j, board);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
                else if (board[i][j] == 4) {
                    board[i][j] = 0;
                }
            }
        }
    }

    public void setAns(int row, int col, int[][] board) {
        int countOne = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) continue;

                int nrow = row + i;
                int ncol = col + j;

                if (nrow >= 0 && nrow < board.length && ncol >= 0 && ncol < board[0].length) {
                    if (board[nrow][ncol] == 1 || board[nrow][ncol] == 4) {
                        countOne++;
                    }
                }
            }
        }

        if (board[row][col] == 0 && countOne == 3) {
            board[row][col] = 3;
        }
        else if (board[row][col] == 1 && countOne > 3) {
            board[row][col] = 4;
        }
        else if (board[row][col] == 1 && countOne < 2) {
            board[row][col] = 4;
        }
    }
}
