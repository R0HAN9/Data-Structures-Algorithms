// Reverse Nodes in k-Group


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        
        // Pointer to keep track of the previous group's tail
        ListNode prevTail = null;
        // Pointers to track the current group's head and tail
        ListNode currentHead = head;
        ListNode currentTail = head;

        // Pointer to store the head of the next group
        ListNode nextHead = null;

        // Iterate through the linked list to process groups of size k
        while (currentHead != null) {

            int count = 1; // Counter to track the size of the current group

            // Move the currentTail pointer to the end of the group
            while (currentTail.next != null && count < k) {
                currentTail = currentTail.next;
                count++;
            }

            // If the group size is less than k, exit the loop (no reversal needed)
            if (count != k) {
                break;
            }

            // Save the head of the next group
            nextHead = currentTail.next;
            // Disconnect the current group from the rest of the list
            currentTail.next = null;

            // If there's a previous group, disconnect its tail
            if (prevTail != null) {
                prevTail.next = null;
            }

            // Reverse the current group
            currentTail = reverse(currentHead);

            // Connect the previous group's tail to the reversed group's head
            if (prevTail != null) {
                prevTail.next = currentTail;
            } else {
                head = currentTail; // Update head if it's the first group
            }

            // Connect the reversed group's tail to the next group
            currentHead.next = nextHead;

            // Update pointers for the next iteration
            prevTail = currentHead;
            currentHead = nextHead;
            currentTail = nextHead;
        }

        return head;
    }

    // Helper function to reverse a linked list
    private ListNode reverse(ListNode head) {
        // Initialize pointers for reversing the list
        ListNode prevNode = null;
        ListNode currentNode = head;
        ListNode nextNode = head;

        // Traverse and reverse the list
        while (currentNode != null) {
            nextNode = nextNode.next; // Move to the next node
            currentNode.next = prevNode; // Reverse the current node's pointer

            prevNode = currentNode; // Move prevNode forward
            currentNode = nextNode; // Move currentNode forward
        }

        return prevNode; // Return the new head of the reversed list
    }
}





// Parsing A Boolean Expression

import java.util.*;

class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (c == ')') {
                List<Character> subExpr = new ArrayList<>();
                while (stack.peek() != '(') {
                    subExpr.add(stack.pop());
                }
                stack.pop(); // Remove '('

                char op = stack.pop(); // Get the operator

                if (op == '!') {
                    stack.push(subExpr.get(0) == 't' ? 'f' : 't');
                } else if (op == '&') {
                    char result = 't';
                    for (char e : subExpr) {
                        if (e == 'f') {
                            result = 'f';
                            break;
                        }
                    }
                    stack.push(result);
                } else if (op == '|') {
                    char result = 'f';
                    for (char e : subExpr) {
                        if (e == 't') {
                            result = 't';
                            break;
                        }
                    }
                    stack.push(result);
                }
            } else if (c != ',') {
                stack.push(c);
            }
        }
        
        return stack.peek() == 't';
    }
}



// Find Kth Bit in Nth Binary String


class Solution {
    public char findKthBit(int n, int k) {
        // Base case: When n = 1, the binary string is "0"
        if (n == 1) return '0';
        
        // Find the length of the current string Sn, which is 2^n - 1
        int length = (1 << n) - 1;
        
        // Find the middle position
        int mid = length / 2 + 1;
        
        // If k is the middle position, return '1'
        if (k == mid) return '1';
        
        // If k is in the first half, find the bit in Sn-1
        if (k < mid) return findKthBit(n - 1, k);
        
        // If k is in the second half, find the bit in Sn-1 and invert it
        return findKthBit(n - 1, length - k + 1) == '0' ? '1' : '0';
    }
}


// Add Two Numbers


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode dummy = new ListNode();
        ListNode result = dummy;
        
        int total = 0;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            total = carry;

            if (l1 != null) {
                total += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                total += l2.val;
                l2 = l2.next;
            }

            int num = total % 10;
            carry = total / 10;

            dummy.next = new ListNode(num);
            dummy = dummy.next;
        }

        return result.next;
    }
}
