// Linked List Cycle


public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;        
    }
}


// Maximal Score After Applying K Operations


class Solution {
    public long maxKelements(int[] nums, int k) {
        
        PriorityQueue<Long> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(b, a));
        for (int num : nums) {
            maxHeap.add((long) num);
        }

        long score = 0;
        for (int i = 0; i < k; i++) {

            long maxVal = maxHeap.poll();
            score += maxVal;
            maxHeap.add((maxVal + 2) / 3);
        }

        return score;
    }
}
