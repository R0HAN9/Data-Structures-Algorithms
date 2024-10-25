// Merge k Sorted Lists

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

        for(ListNode node : lists){
            if(node != null)
                heap.add(node);
        }

        ListNode head = new ListNode(0);
        ListNode current = head;

        while(!heap.isEmpty()){
            ListNode node = heap.poll();
            current.next = node;
            current = current.next;

            if (node.next != null) {
                heap.add(node.next);
            }
        }
        
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
