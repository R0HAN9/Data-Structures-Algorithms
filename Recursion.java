// Reverse Nodes in k-Group


class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        
        ListNode prevTail = null;
        ListNode currentHead = head;
        ListNode currentTail = head;

        ListNode nextHead = null;
        while (currentHead != null) {

            int count = 1;
            while (currentTail.next != null && count < k) {

                currentTail = currentTail.next;
                count++;
            }
            if (count != k) {
                break;
            }

            nextHead = currentTail.next;
            currentTail.next = null;

            if (prevTail != null) {
                prevTail.next = null;
            }

            currentTail = reverse(currentHead);
            if (prevTail != null) {
                prevTail.next = currentTail;
            }
            else {
                head = currentTail;
            }

            currentHead.next = nextHead;
            prevTail = currentHead;
            currentHead = nextHead;
            currentTail = nextHead;

        }
        return head;
    }

    private ListNode reverse(ListNode head) {

        ListNode prevNode = null;
        ListNode currentNode = head;
        ListNode nextNode = head;

        while (currentNode != null) {

            nextNode = nextNode.next;
            currentNode.next = prevNode;

            prevNode = currentNode;
            currentNode = nextNode;
        }

        return prevNode;
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
