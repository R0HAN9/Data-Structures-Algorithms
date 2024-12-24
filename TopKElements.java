// Find K Pairs with Smallest Sums


class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Initialize the result list to store the k smallest pairs
        List<List<Integer>> result = new ArrayList<>();

        // Min-heap to keep track of the pairs with the smallest sums
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]])
        );

        // Add initial pairs (nums1[i], nums2[0]) to the heap for i < k
        for (int i = 0; i < nums1.length && i < k; ++i) {
            minHeap.offer(new int[] { i, 0 }); // Each element is represented by its index pair {i, 0}
        }

        // Process the heap to find the k smallest pairs
        while (k > 0 && !minHeap.isEmpty()) {
            // Extract the top pair from the heap (smallest sum)
            int[] top = minHeap.poll();
            int i = top[0], j = top[1];

            // Add the corresponding pair to the result list
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // If there is a next element in nums2, add the next pair (nums1[i], nums2[j+1]) to the heap
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[] { i, j + 1 });
            }

            // Decrement k since we added one pair to the result
            --k;
        }

        // Return the list of k smallest pairs
        return result;
    }
}

