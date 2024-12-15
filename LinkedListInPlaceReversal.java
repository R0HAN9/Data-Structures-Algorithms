// Remove Nth Node From End of List


class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        // Create a dummy node to simplify edge cases, such as when the head itself is removed.
        ListNode result = new ListNode(0, head);
        
        // A pointer to traverse the list, starting from the dummy node.
        ListNode dummy = result;

        // Move the 'head' pointer n steps ahead to create a gap of n between dummy and the node to be removed.
        for (int i = 0; i < n; i++) {
            head = head.next;
        }

        // Traverse the list until 'head' reaches the end.
        // This ensures that 'dummy' will be positioned right before the node to be removed.
        while (head != null) {
            head = head.next;
            dummy = dummy.next;
        }

        // Now 'dummy' points to the node just before the one we need to remove.
        // Skip the nth node from the end by adjusting the next pointer.
        dummy.next = dummy.next.next;

        // Return the modified list, starting from the original head.
        return result.next;
    }
}



// Copy List with Random Pointer

class Solution {
    public Node copyRandomList(Node head) {
        
        // A map to store the mapping between original nodes and their corresponding copies.
        Map<Node, Node> hashMap = new HashMap<>();
        Node current = head;

        // First pass: Create copies of each node and store them in the map.
        while (current != null) {
            hashMap.put(current, new Node(current.val));
            current = current.next;
        }

        // Second pass: Set the next and random pointers for each copied node.
        current = head;
        while (current != null) {
            // Get the copied node from the map.
            Node copy = hashMap.get(current);

            // Set the next pointer of the copied node to the copied node of current.next.
            copy.next = hashMap.get(current.next);

            // Set the random pointer of the copied node to the copied node of current.random.
            copy.random = hashMap.get(current.random);

            // Move to the next node in the original list.
            current = current.next;
        }

        // Return the copied list starting from the new head (the copy of the original head).
        return hashMap.get(head);
    }
}




// Swap Nodes in Pairs

class Solution {
    public ListNode swapPairs(ListNode head) {
        
        // Base case: if the list is empty or has only one node, return the head as is.
        if(head == null || head.next == null) {
            return head;
        }

        // Store the second node in a temporary variable, as it will be the new head after swapping.
        ListNode tmp = head.next;

        // Recursively swap the pairs starting from the third node and link it back to the current pair.
        head.next = swapPairs(head.next.next);

        // Set the 'next' of the second node (tmp) to the first node (head), completing the swap.
        tmp.next = head;

        // Return the new head of the swapped pair (tmp).
        return tmp;
    }
}



// 2. Flatten Binary Tree to Linked List


class Solution {
    public void flatten(TreeNode root) {

        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {

                TreeNode runner = curr.left;
                while (runner.right != null) runner = runner.right;
                
                runner.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}
