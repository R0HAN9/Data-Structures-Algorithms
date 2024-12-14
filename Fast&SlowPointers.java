// Linked List Cycle


public class Solution {
    public boolean hasCycle(ListNode head) {
        // Initialize two pointers: `fast` and `slow`, both starting at the head of the list
        ListNode fast = head;
        ListNode slow = head;

        // Loop to traverse the list, ensuring `fast` and `fast.next` are not null
        // If either is null, it means the list has reached the end (no cycle)
        while (fast != null && fast.next != null) {
            // Move `fast` pointer two steps ahead
            fast = fast.next.next;
            // Move `slow` pointer one step ahead
            slow = slow.next;

            // If `fast` and `slow` meet, it means there is a cycle in the list
            if (fast == slow) {
                return true; // Cycle detected
            }
        }

        // If the loop terminates, it means there is no cycle in the list
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
