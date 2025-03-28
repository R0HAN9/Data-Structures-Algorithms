// Separate Black and White Balls


class Solution {
    public long minimumSteps(String s) {
        // Variable to keep track of the total steps required
        long answer = 0;
        // Variable to count the number of '1's (black tiles) encountered so far
        int blackCount = 0;

        // Iterate through the string `s`
        for (int i = 0; i < s.length(); i++) {
            // If the current character is '0' (white tile)
            if (s.charAt(i) == '0') {
                // Add the number of black tiles encountered so far to the answer
                answer += blackCount;
            } 
            // If the current character is '1' (black tile)
            else {
                // Increment the blackCount to track the black tiles
                blackCount++;
            }
        }

        // Return the total minimum steps required
        return answer;
    }
}



// Longest Happy String


class Solution {
    public String longestDiverseString(int a, int b, int c) {
        // Create a max-heap (PriorityQueue) to store the counts of each character and the character itself
        // The character with the highest count will be at the top
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] - x[0]);

        // Add the counts of each character to the heap if they are greater than 0
        if (a > 0) pq.offer(new int[] {a, 'a'});
        if (b > 0) pq.offer(new int[] {b, 'b'});
        if (c > 0) pq.offer(new int[] {c, 'c'});

        // StringBuilder to construct the result string
        StringBuilder result = new StringBuilder();

        // Continue until the heap is empty
        while (!pq.isEmpty()) {
            // Get the character with the highest count
            int[] first = pq.poll();

            // Check if adding the current character would cause three consecutive occurrences
            if (result.length() >= 2 &&
                result.charAt(result.length() - 1) == first[1] &&
                result.charAt(result.length() - 2) == first[1]) {

                // If we cannot add the current character, check if there is another character in the heap
                if (pq.isEmpty()) break; // No other character to use, so break the loop

                // Get the second-highest character from the heap
                int[] second = pq.poll();

                // Add the second-highest character to the result
                result.append((char) second[1]);
                second[0]--; // Decrease its count

                // If the second character still has remaining count, add it back to the heap
                if (second[0] > 0) pq.offer(second);

                // Add the first character back to the heap to process later
                pq.offer(first);
            } else {
                // If adding the current character is safe, append it to the result
                result.append((char) first[1]);
                first[0]--; // Decrease its count

                // If the current character still has remaining count, add it back to the heap
                if (first[0] > 0) pq.offer(first);
            }
        }

        // Return the constructed string as the result
        return result.toString();
    }
}



// Maximum Swap



class Solution {
    public int maximumSwap(int num) {
        // Convert the number to a character array for easier manipulation
        char[] numArr = Integer.toString(num).toCharArray();
        int n = numArr.length;

        // Array to store the last occurrence of each digit (0-9)
        int[] last = new int[10];
        for (int i = 0; i < n; i++) {
            last[numArr[i] - '0'] = i; // Record the last index of the digit
        }

        // Iterate through each digit in the number
        for (int i = 0; i < n; i++) {
            // Check for a larger digit (starting from 9) to swap with the current digit
            for (int d = 9; d > numArr[i] - '0'; d--) {
                // If a larger digit exists and its index is after the current index
                if (last[d] > i) {
                    // Swap the current digit with the larger digit
                    char temp = numArr[i];
                    numArr[i] = numArr[last[d]];
                    numArr[last[d]] = temp;

                    // Convert the modified array back to an integer and return it
                    return Integer.parseInt(new String(numArr));
                }
            }
        }

        // If no swap is performed, return the original number
        return num;
    }
}



// Jump Game

class Solution {
    public boolean canJump(int[] nums) {
        // Initialize the goal as the last index of the array
        int goal = nums.length - 1;

        // Traverse the array backward from the second-to-last element
        for (int i = nums.length - 2; i >= 0; i--) {
            // Check if the current index can reach or surpass the goal
            if (i + nums[i] >= goal) {
                // Update the goal to the current index
                goal = i;
            }
        }

        // If the goal has moved to index 0, it means the first index can reach the last
        return goal == 0;
    }
}



// Minimum Number of Removals to Make Mountain Array

class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int num = nums.length;

        // Arrays to store the length of the Longest Increasing Subsequence (LIS)
        // and Longest Decreasing Subsequence (LDS) for each element
        int[] LIS = new int[num];
        int[] LDS = new int[num];

        // Initialize LIS and LDS arrays to 1 (each element is a subsequence of length 1 by itself)
        Arrays.fill(LIS, 1);
        Arrays.fill(LDS, 1);

        // Calculate LIS for each element (left to right)
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < i; j++) {
                // Update LIS[i] if nums[i] can extend the increasing subsequence ending at nums[j]
                if (nums[i] > nums[j]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }

        // Calculate LDS for each element (right to left)
        for (int i = num - 1; i >= 0; --i) {
            for (int j = num - 1; j > i; --j) {
                // Update LDS[i] if nums[i] can extend the decreasing subsequence starting at nums[j]
                if (nums[i] > nums[j]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }

        // Variable to store the maximum length of a valid mountain subsequence
        int maxMountainLength = 0;

        // Find the maximum mountain subsequence length
        for (int i = 1; i < num - 1; ++i) {
            // A valid mountain peak must have LIS > 1 and LDS > 1 at index i
            if (LIS[i] > 1 && LDS[i] > 1) {
                // Combine LIS and LDS at i and subtract 1 (to avoid double-counting the peak element)
                maxMountainLength = Math.max(maxMountainLength, LIS[i] + LDS[i] - 1);
            }
        }

        // The minimum removals required to form a mountain array
        return num - maxMountainLength;
    }
}

