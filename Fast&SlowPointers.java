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
    // Create a max-heap (PriorityQueue with descending order) to store elements as long
    // Max-heap ensures the largest element is always at the top
    PriorityQueue<Long> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(b, a));
    
    // Add all elements from the nums array into the max-heap, converting them to long
    for (int num : nums) {
        maxHeap.add((long) num);
    }

    long score = 0; // Variable to keep track of the total score
    
    // Perform the operation k times
    for (int i = 0; i < k; i++) {
        // Poll (remove and retrieve) the largest element from the heap
        long maxVal = maxHeap.poll();
        // Add the largest element's value to the score
        score += maxVal;
        // Calculate the new value (maxVal + 2) / 3 and add it back to the heap
        maxHeap.add((maxVal + 2) / 3);
    }

    // Return the final score after k operations
    return score;
  }
}
