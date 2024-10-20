// Top K Frequent Elements


class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        List<Integer>[] bucket = new List[nums.length + 1];
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        for (int key : hashMap.keySet()) {
            int freq = hashMap.get(key);

            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        int[] ans = new int[k];
        int pos = 0;

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {

                for (int j = 0; j < bucket[i].size() && pos < k; j++) {
                    ans[pos] = bucket[i].get(j);
                    pos++;
                }
            }
        }

        return ans;
    }
}
