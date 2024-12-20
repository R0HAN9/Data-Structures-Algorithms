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

    private HashSet<String> data;
    private HashSet<String> prefs;

    public Trie() {
        
        data = new HashSet<>();
        prefs = new HashSet<>();
    }
    
    public void insert(String word) {
        
        StringBuilder curr = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            curr.append(word.charAt(i));
            prefs.add(curr.toString());
        }
        data.add(word);
    }
    
    public boolean search(String word) {
        return data.contains(word);
    }
    
    public boolean startsWith(String prefix) {
        return prefs.contains(prefix);
    }
}
