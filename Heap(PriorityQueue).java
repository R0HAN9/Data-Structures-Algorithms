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

// Maximum Sum Circular Subarray


class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        
        int totalSum = 0, currentMax = 0, currentMin = 0;
        int maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;

        for (int num : nums) {
            totalSum += num;

            currentMax = Math.max(currentMax + num, num);
            maxSum = Math.max(maxSum, currentMax);

            currentMin = Math.min(currentMin + num, num);
            minSum = Math.min(minSum, currentMin);
        }

        if (maxSum < 0) return maxSum;
        return Math.max(maxSum, totalSum - minSum);
    }
}

// Find Median from Data Stream



class MedianFinder {

    private PriorityQueue<Integer> lowerHalf;
    private PriorityQueue<Integer> upperHalf;

    public MedianFinder() {
        
        lowerHalf = new PriorityQueue<>((a,b) -> b-a);
        upperHalf = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        
        lowerHalf.offer(num);
        upperHalf.offer(lowerHalf.poll());

        if (lowerHalf.size() < upperHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }
    
    public double findMedian() {
        
        if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek();
        }
        else {
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        }
    }
}
