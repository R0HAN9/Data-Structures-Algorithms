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
