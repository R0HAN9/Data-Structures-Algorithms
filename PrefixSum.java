// Contiguous Array

class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) { // Base Case
            return 0;
        }
        // Converting all 0 to -1
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) nums[i] = -1;
        }
        int sum = 0; // current
        int max = 0; // final-ans
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // put reference in the starting of 0 & -1, as i have tell you in the starting  
        for(int i = 0; i < nums.length; i++){
            sum += nums[i]; // cumulative sum
            if(map.containsKey(sum)){ // if cumulative sum key :- 0, -1, 1 already present
                int last = map.get(sum); // we get it's value
                max = Math.max(max, i - last); // and update max
            }
            else{ // if it's not present then create it's key-value pair
                map.put(sum, i);
            }
        }
        return max; // finally return it
    }
}


// 2. Divide Intervals Into Minimum Number of Groups


class Solution {
    public int minGroups(int[][] intervals) {
        
        int n = intervals.length;
        int[] startTime = new int[n]; // Array to store start times of intervals
        int[] endTime = new int[n];   // Array to store end times of intervals

        for (int i = 0; i < n; i++) {
            startTime[i] = intervals[i][0]; // Fill start times
            endTime[i] = intervals[i][1];  // Fill end times
        }

        Arrays.sort(startTime); // Sort start times
        Arrays.sort(endTime);   // Sort end times

        int endPtr = 0; // Pointer for end times
        int groupCount = 0; // Count of overlapping groups

        for (int start : startTime) {
            if (start > endTime[endPtr]) { // Check if current start is after earliest end
                endPtr++; // Move end pointer forward
            }
            else {
                groupCount++; // Increment group count for overlap
            }
        }

        return groupCount; // Return total groups needed
    }
}




// 3. Implement Trie (Prefix Tree)


class Trie {

    // A HashSet to store complete words added to the Trie
    private HashSet<String> data;

    // A HashSet to store all prefixes of the words in the Trie
    private HashSet<String> prefs;

    // Constructor to initialize the data structures
    public Trie() {
        data = new HashSet<>(); // Initialize HashSet for storing complete words
        prefs = new HashSet<>(); // Initialize HashSet for storing prefixes
    }
    
    // Method to insert a word into the Trie
    public void insert(String word) {
        StringBuilder curr = new StringBuilder(); // StringBuilder to build prefixes step by step

        // Iterate through each character of the word
        for (int i = 0; i < word.length(); i++) {
            curr.append(word.charAt(i)); // Add the current character to the prefix being constructed
            prefs.add(curr.toString()); // Store the current prefix in the prefix set
        }

        // Add the full word to the set of words
        data.add(word);
    }
    
    // Method to check if a word exists in the Trie
    public boolean search(String word) {
        return data.contains(word); // Return true if the word is found in the word set
    }
    
    // Method to check if there is any word in the Trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        return prefs.contains(prefix); // Return true if the prefix is found in the prefix set
    }
}
