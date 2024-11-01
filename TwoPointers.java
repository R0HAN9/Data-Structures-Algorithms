// Remove Duplicates from Sorted Array

class Solution {
    public int removeDuplicates(int[] nums) {
        
        if (nums.length == 0) return 0;
        int idx = 1;

        for (int j = 1; j < nums.length; j++) {
            if(nums[j] != nums[idx - 1]) {

                nums[idx] = nums[j];
                idx++;
            }
        }

        return idx;
    }
}


// Container With Most Water


class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;        
    }
}


// 2. 3Sum


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];

                if (total > 0) {
                    k--;
                } else if (total < 0) {
                    j++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    while (nums[j] == nums[j-1] && j < k) {
                        j++;
                    }
                }
            }
        }
        return res;        
    }
}


// 3. Longest Palindromic Substring



class Solution {
    public String longestPalindrome(String s) {
        
        if (s == null || s.length() == 0) return "";
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            
            int odd = expandAroundCenter(s, i , i);
            int even = expandAroundCenter(s, i, i + 1);
            int maxLen = Math.max(odd, even);

            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}


// Two Sum II - Input Array Is Sorted


class Solution {
    public int[] twoSum(int[] numbers, int target) {
        
        int[] ans = new int[2];
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            
            if ((numbers[left] + numbers[right]) < target) left++;
            else if ((numbers[left] + numbers[right]) > target) right--;

            else {
                ans[0] = left + 1;
                ans[1] = right + 1;
                break;
            }
        }

        return ans;
    }
}


 // Merge Sorted Array


class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int midx = m - 1;
        int nidx = n - 1;
        int right = m + n - 1;

        while (nidx >= 0) {
            
            if (midx >= 0 && nums1[midx] > nums2[nidx]) {
                nums1[right] = nums1[midx];
                midx--;
            }
            else {
                nums1[right] = nums2[nidx];
                nidx--;
            }
            right--;
        }
    }
}


// Sort List


class Solution {
    private ListNode mid(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeSort(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            }
            else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = mid(head);
        ListNode newNode = mid.next;
        mid.next = null;

        ListNode leftHalf = sortList(head);
        ListNode rightHalf = sortList(newNode);

        return mergeSort(leftHalf, rightHalf);
    }
}
