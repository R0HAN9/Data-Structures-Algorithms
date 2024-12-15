// Merge k Sorted Lists

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // PriorityQueue (min-heap) to store the nodes based on their values in ascending order
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the first node of each list into the heap (if not null)
        for(ListNode node : lists){
            if(node != null)
                heap.add(node);
        }

        // Create a dummy node to serve as the head of the merged list
        ListNode head = new ListNode(0);
        ListNode current = head; // Pointer to track the current node in the merged list

        // Process the nodes in the heap
        while(!heap.isEmpty()){
            // Poll the node with the smallest value from the heap
            ListNode node = heap.poll();
            
            // Attach the node to the merged list
            current.next = node;
            current = current.next; // Move the current pointer to the newly added node

            // If the current node has a next node, add it to the heap
            if (node.next != null) {
                heap.add(node.next);
            }
        }
        
        // Return the merged list, skipping the dummy node at the beginning
        return head.next;
    }
}





// Top K Frequent Elements


class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        // Create a bucket array where index represents frequency and each index stores a list of numbers with that frequency.
        List<Integer>[] bucket = new List[nums.length + 1];
        
        // HashMap to store the frequency of each number in the nums array.
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        // Count the frequency of each number in the input array.
        for (int num : nums) {
            // Use getOrDefault to simplify incrementing the frequency count.
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        // Group numbers by their frequency into the bucket array.
        for (int key : hashMap.keySet()) {
            int freq = hashMap.get(key);

            // If no bucket for this frequency exists, create one.
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            
            // Add the number to the corresponding bucket based on its frequency.
            bucket[freq].add(key);
        }

        // Prepare the result array to store the top K frequent elements.
        int[] ans = new int[k];
        int pos = 0;

        // Traverse the bucket array from the highest frequency to the lowest.
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                // Iterate over the numbers in the current bucket and add them to the result array.
                for (int j = 0; j < bucket[i].size() && pos < k; j++) {
                    ans[pos] = bucket[i].get(j);
                    pos++;
                }
            }
        }

        // Return the top K frequent elements.
        return ans;
    }
}


// Maximum Sum Circular Subarray


class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        
        // Variables to keep track of the total sum of the array, 
        // the current maximum and minimum subarray sums, and 
        // the global maximum and minimum subarray sums.
        int totalSum = 0, currentMax = 0, currentMin = 0;
        int maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;

        // Traverse through the array to calculate both the maximum subarray sum
        // and the minimum subarray sum using Kadane's Algorithm.
        for (int num : nums) {
            totalSum += num; // Update the total sum of the array.

            // Kadane's Algorithm for maximum subarray sum.
            currentMax = Math.max(currentMax + num, num); // Either continue the current subarray or start a new subarray.
            maxSum = Math.max(maxSum, currentMax); // Update the global maximum sum.

            // Kadane's Algorithm for minimum subarray sum.
            currentMin = Math.min(currentMin + num, num); // Either continue the current subarray or start a new subarray.
            minSum = Math.min(minSum, currentMin); // Update the global minimum sum.
        }

        // If the maximum sum is negative, it means all elements are negative,
        // so we simply return the maximum subarray sum.
        if (maxSum < 0) return maxSum;

        // Otherwise, calculate the maximum sum that can be obtained by considering circular subarrays.
        // The totalSum - minSum gives the sum of the array excluding the minimum subarray sum, 
        // effectively considering the circular subarray.
        return Math.max(maxSum, totalSum - minSum);
    }
}


// Find Median from Data Stream



class MedianFinder {

    // Priority queues (heaps) to store the two halves of the numbers.
    private PriorityQueue<Integer> lowerHalf; // Max-heap to store the lower half of numbers.
    private PriorityQueue<Integer> upperHalf; // Min-heap to store the upper half of numbers.

    public MedianFinder() {
        // Initialize the two heaps:
        // - lowerHalf stores the smaller half (max-heap), and 
        // - upperHalf stores the larger half (min-heap).
        lowerHalf = new PriorityQueue<>((a, b) -> b - a); // Max-heap (reverse order for largest at the top)
        upperHalf = new PriorityQueue<>(); // Min-heap (natural order for smallest at the top)
    }
    
    // Adds a number to the data structure.
    public void addNum(int num) {
        // Add the number to the lower half (max-heap).
        lowerHalf.offer(num);

        // Move the largest element from lowerHalf to upperHalf (min-heap).
        upperHalf.offer(lowerHalf.poll());

        // Ensure the heaps are balanced. If upperHalf has more elements, move one back to lowerHalf.
        if (lowerHalf.size() < upperHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }
    
    // Finds the median of the current numbers.
    public double findMedian() {
        // If lowerHalf has more elements, the median is the top element of lowerHalf.
        if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek();
        }
        // Otherwise, the median is the average of the top elements of both heaps.
        else {
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        }
    }
}

