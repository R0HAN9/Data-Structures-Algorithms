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
        
        Map<Node, Node> hashMap = new HashMap<>();
        Node current = head;

        while (current != null) {
            hashMap.put(current, new Node(current.val));
            current = current.next;
        }

        current = head;

        while (current != null) {
            Node copy = hashMap.get(current);

            copy.next = hashMap.get(current.next);
            copy.random = hashMap.get(current.random);
            current = current.next;

        }
        return hashMap.get(head);
    }
}



// Swap Nodes in Pairs

class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null)
        {
            return head;
        }

        ListNode tmp = head.next;

        head.next = swapPairs(head.next.next);
        tmp.next=head;

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
