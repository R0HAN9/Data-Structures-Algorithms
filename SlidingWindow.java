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
    // Map to store the grouped anagrams.
    // Key: A unique representation of character counts (frequency signature).
    // Value: List of strings (anagrams) that share the same key.
    Map<String, List<String>> ans = new HashMap<>();
    
    // Iterate through each string in the input array.
    for (String s : strs) {
        // Array to store the frequency of each character in the current string.
        // Since we are dealing with lowercase English letters, the size is 26.
        int[] count = new int[26];

        // Count the frequency of each character in the string.
        for (char c : s.toCharArray()) {
            count[c - 'a']++; // Increment the count for the corresponding character.
        }

        // Create a unique string representation of the frequency array.
        // This will act as the key in the map.
        StringBuilder sb = new StringBuilder();
        for (int num : count) {
            sb.append(num).append("#"); // Use a delimiter ("#") to separate counts for uniqueness.
        }

        // Convert the StringBuilder to a string key.
        String key = sb.toString();

        // If the key is not already in the map, add a new entry.
        if (!ans.containsKey(key)) {
            ans.put(key, new ArrayList<>());
        }

        // Add the current string to the list corresponding to its key.
        ans.get(key).add(s);
    }

    // Convert the map values to a list and return it.
    // Each value in the map represents a group of anagrams.
    return new ArrayList<>(ans.values());
}




// Minimum Window Substring


class Solution {
    public String minWindow(String s, String t) {
        // If the length of `s` is smaller than `t`, it's impossible to form the substring.
        if (s.length() < t.length()) return "";

        // Map to store the frequency of each character in `t`.
        Map<Character, Integer> charCount = new HashMap<>();

        // Initialize the map with characters in `t`.
        for (char ch : t.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        // Total number of characters in `t` that need to be matched.
        int targetCharRemaining = t.length();

        // Array to store the start and end indices of the smallest valid window found so far.
        int[] minWindow = {0, Integer.MAX_VALUE};

        // Start index of the sliding window.
        int startIndex = 0;

        // Iterate through `s` with the `endIndex` pointer as the right bound of the window.
        for (int endIndex = 0; endIndex < s.length(); endIndex++) {
            char ch = s.charAt(endIndex);

            // If `ch` is in the target map and has a positive count, decrement `targetCharRemaining`.
            if (charCount.containsKey(ch) && charCount.get(ch) > 0) {
                targetCharRemaining--;
            }

            // Decrement the count of the current character in the map (if it's not in the map, count will become negative).
            charCount.put(ch, charCount.getOrDefault(ch, 0) - 1);

            // When all target characters are matched (`targetCharRemaining` == 0), try to shrink the window.
            if (targetCharRemaining == 0) {
                while (true) {
                    // Character at the start of the current window.
                    char charAtStart = s.charAt(startIndex);

                    // Stop shrinking if removing `charAtStart` would make the window invalid.
                    if (charCount.containsKey(charAtStart) && charCount.get(charAtStart) == 0) {
                        break;
                    }

                    // Otherwise, increase the count of `charAtStart` in the map and move the start pointer.
                    charCount.put(charAtStart, charCount.getOrDefault(charAtStart, 0) + 1);
                    startIndex++;
                }

                // Update the smallest window if the current window is smaller.
                if (endIndex - startIndex < minWindow[1] - minWindow[0]) {
                    minWindow[0] = startIndex;
                    minWindow[1] = endIndex;
                }

                // Move the `startIndex` pointer to shrink the window further and update the counts.
                charCount.put(s.charAt(startIndex), charCount.getOrDefault(s.charAt(startIndex), 0) + 1);
                targetCharRemaining++;
                startIndex++;
            }
        }

        // If no valid window was found, return an empty string.
        // Otherwise, return the substring corresponding to the smallest valid window.
        return minWindow[1] >= s.length() ? "" : s.substring(minWindow[0], minWindow[1] + 1);
    }
}

