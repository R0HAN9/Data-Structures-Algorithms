// Smallest Range Covering Elements from K Lists


class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int currentMax = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            minHeap.offer(new int[] {nums.get(i).get(0), i, 0});
            currentMax = Math.max(currentMax, nums.get(i).get(0));
        }

        int[] smallRange = new int[] {0, Integer.MAX_VALUE};

        while (true) {
            int[] curr = minHeap.poll();
            int currentMin = curr[0], listIdx = curr[1], elementIdx = curr[2];

            if ((currentMax - currentMin < smallRange[1] - smallRange[0]) ||
                (currentMax - currentMin == smallRange[1] - smallRange[0] && currentMin < smallRange[0])) {
                    smallRange[0] = currentMin;
                    smallRange[1] = currentMax;
                }

            if (elementIdx + 1 < nums.get(listIdx).size()) {

                int nextVal = nums.get(listIdx).get(elementIdx + 1);
                minHeap.offer(new int[] {nextVal, listIdx, elementIdx + 1});
                currentMax = Math.max(currentMax, nextVal);
            }
            else {
                break;
            }
        }

        return smallRange;
    }
}



// Longest Substring Without Repeating Characters

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> count = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count.put(c, count.getOrDefault(c, 0) + 1);
            
            while (count.get(c) > 1) {
                char leftChar = s.charAt(left);
                count.put(leftChar, count.get(leftChar) - 1);
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
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
