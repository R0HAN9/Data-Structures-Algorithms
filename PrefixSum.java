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
        int[] startTime = new int[n];
        int[] endTime = new int[n];

        for (int i = 0; i < n; i++) {
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }

        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int endPtr = 0;
        int groupCount = 0;

        for (int start : startTime) {
            if (start > endTime[endPtr]) {
                endPtr++;
            }
            else {
                groupCount++;
            }
        }

        return groupCount;
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
