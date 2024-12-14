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
        
        char[] numArr = Integer.toString(num).toCharArray();
        int n = numArr.length;

        int[] last = new int[10];
        for (int i = 0; i < n; i++) {
            last[numArr[i] - '0'] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int d = 9; d > numArr[i] - '0'; d--) {

                if (last[d] > i) {
                    char temp = numArr[i];
                    numArr[i] = numArr[last[d]];
                    numArr[last[d]] = temp;

                    return Integer.parseInt(new String(numArr));
                }
            }
        }

        return num;
    }
}


// Jump Game

class Solution {
    public boolean canJump(int[] nums) {
        
        int goal = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }

        return goal == 0;
    }
}


// Minimum Number of Removals to Make Mountain Array

class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int num = nums.length;
        int[] LIS = new int[num];
        int[] LDS = new int[num];

        Arrays.fill(LIS, 1);
        Arrays.fill(LDS, 1);

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }

        for (int i = num - 1; i >= 0; --i) {
            for (int j = num - 1; j > i; --j) {

                if (nums[i] > nums[j]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }
        int maxMountainLength = 0;

        for (int i = 1; i < num - 1; ++i) {
            if (LIS[i] > 1 && LDS[i] > 1) {
                maxMountainLength = Math.max(maxMountainLength, LIS[i] + LDS[i] - 1);
            }
        }

        return num - maxMountainLength;
    }
}
