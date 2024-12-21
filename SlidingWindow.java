// Smallest Range Covering Elements from K Lists


class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        
        // Priority Queue (Min-Heap) to store the current elements being considered from each list.
        // Each entry in the heap is an array of [value, listIndex, elementIndex].
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Variable to track the current maximum value among the elements in the heap.
        int currentMax = Integer.MIN_VALUE;

        // Initialize the heap with the first element of each list and update the current maximum.
        for (int i = 0; i < nums.size(); i++) {
            minHeap.offer(new int[] {nums.get(i).get(0), i, 0}); // Add the first element of each list to the heap
            currentMax = Math.max(currentMax, nums.get(i).get(0)); // Update the maximum value
        }

        // Array to store the smallest range [start, end].
        // Initially set to an invalid range (max range).
        int[] smallRange = new int[] {0, Integer.MAX_VALUE};

        // Process the heap until one of the lists is exhausted.
        while (true) {
            // Extract the smallest element from the heap (current minimum value).
            int[] curr = minHeap.poll();
            int currentMin = curr[0]; // Value of the current smallest element
            int listIdx = curr[1];   // Index of the list the element came from
            int elementIdx = curr[2]; // Index of the element in its list

            // Update the smallest range if the current range [currentMin, currentMax] is smaller.
            // Also prioritize smaller `currentMin` for tie-breaking.
            if ((currentMax - currentMin < smallRange[1] - smallRange[0]) ||
                (currentMax - currentMin == smallRange[1] - smallRange[0] && currentMin < smallRange[0])) {
                    smallRange[0] = currentMin;
                    smallRange[1] = currentMax;
            }

            // Check if there is a next element in the same list of the current smallest element.
            if (elementIdx + 1 < nums.get(listIdx).size()) {
                // Add the next element from the same list to the heap.
                int nextVal = nums.get(listIdx).get(elementIdx + 1);
                minHeap.offer(new int[] {nextVal, listIdx, elementIdx + 1});

                // Update the current maximum with the new value.
                currentMax = Math.max(currentMax, nextVal);
            } else {
                // If any list is exhausted, we cannot consider a range that includes all lists anymore.
                break;
            }
        }

        // Return the smallest range found.
        return smallRange;
    }
}




// Longest Substring Without Repeating Characters

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Variable to store the maximum length of a substring without repeating characters.
        int maxLength = 0;

        // Left pointer of the sliding window.
        int left = 0;

        // Map to keep track of the frequency of characters within the current window.
        Map<Character, Integer> count = new HashMap<>();

        // Iterate through the string using the right pointer of the sliding window.
        for (int right = 0; right < s.length(); right++) {
            // Get the current character at the right pointer.
            char c = s.charAt(right);

            // Increment the frequency of the character in the map.
            count.put(c, count.getOrDefault(c, 0) + 1);

            // Shrink the window from the left if there are duplicate characters in the current window.
            while (count.get(c) > 1) {
                // Get the character at the left pointer.
                char leftChar = s.charAt(left);

                // Decrease the frequency of the left character as it is removed from the window.
                count.put(leftChar, count.get(leftChar) - 1);

                // Move the left pointer to the right to shrink the window.
                left++;
            }

            // Update the maximum length of the substring.
            // The current window size is `right - left + 1`.
            maxLength = Math.max(maxLength, right - left + 1);
        }

        // Return the maximum length of substring found.
        return maxLength;       
    }
}


// 2. Group Anagrams


class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> ans = new HashMap<>();
        
        for (String s : strs) {
            int[] count = new int[26];

            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int num : count) {
                sb.append(num).append("#");
            }

            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(s);
        }

        return new ArrayList<>(ans.values());
    }
}



// Minimum Window Substring


class Solution {
    public String minWindow(String s, String t) {
        
        if (s.length() < t.length()) return "";
        Map<Character, Integer> charCount = new HashMap<>();

        for (char ch : t.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        int targetCharRemaining = t.length();
        int[] minWindow = {0, Integer.MAX_VALUE};
        int startIndex = 0;

        for (int endIndex = 0; endIndex < s.length(); endIndex++) {

            char ch = s.charAt(endIndex);
            if (charCount.containsKey(ch) && charCount.get(ch) > 0) {
                targetCharRemaining--;
            }
            charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);

            if (targetCharRemaining == 0) {
                while (true) {

                    char charAtStart = s.charAt(startIndex);
                    if (charCount.containsKey(charAtStart) && charCount.get(charAtStart) == 0) {
                        break;
                    }

                    charCount.put(charAtStart, charCount.getOrDefault(charAtStart, 0) + 1);
                    startIndex++;
                }

                if (endIndex - startIndex < minWindow[1] - minWindow[0]) {
                    minWindow[0] = startIndex;
                    minWindow[1] = endIndex;
                }

                charCount.put(s.charAt(startIndex), charCount.getOrDefault(s.charAt(startIndex), 0) + 1);
                targetCharRemaining++;
                startIndex++;
            }
        }

        return minWindow[1] >= s.length() ? "" : s.substring(minWindow[0], minWindow[1] + 1);
    }
}
