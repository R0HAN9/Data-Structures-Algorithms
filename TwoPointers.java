// Remove Duplicates from Sorted Array

class Solution {
    public int removeDuplicates(int[] nums) {
        // If the array is empty, no elements to process, return 0
        if (nums.length == 0) return 0;

        // Pointer to track the position of the next unique element
        int idx = 1;

        // Loop through the array starting from the second element
        for (int j = 1; j < nums.length; j++) {
            // Check if the current element is different from the previous unique element
            if(nums[j] != nums[idx - 1]) {
                // Place the current unique element at the idx position
                nums[idx] = nums[j];
                // Move the index forward
                idx++;
            }
        }

        // Return the count of unique elements
        return idx;
    }
}



// Container With Most Water


class Solution {
      public int maxArea(int[] height) {
        // Variable to store the maximum area found
        int maxArea = 0;

        // Two pointers: one at the start and one at the end of the array
        int left = 0;
        int right = height.length - 1;

        // Iterate while the left pointer is less than the right pointer
        while (left < right) {
            // Calculate the area formed by the current pointers
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));

            // Move the pointer pointing to the shorter line inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        // Return the maximum area found
        return maxArea;        
    }
}
}


// 2. 3Sum


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Initialize a list to store the resulting triplets.
        List<List<Integer>> res = new ArrayList<>();
        
        // Sort the array to allow the use of the two-pointer technique.
        Arrays.sort(nums);

        // Iterate through the array with the first pointer, 'i'.
        for (int i = 0; i < nums.length; i++) {
            // Skip duplicate values for the first element to avoid duplicate triplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Initialize the two pointers: 
            // 'j' starts right after 'i' (as the second element),
            // 'k' starts at the end of the array (as the third element).
            int j = i + 1;
            int k = nums.length - 1;

            // Use the two-pointer technique to find triplets that sum to zero.
            while (j < k) {
                // Calculate the sum of the current triplet.
                int total = nums[i] + nums[j] + nums[k];

                if (total > 0) {
                    // If the sum is greater than zero, move the right pointer 'k' leftward 
                    // to decrease the sum.
                    k--;
                } else if (total < 0) {
                    // If the sum is less than zero, move the left pointer 'j' rightward 
                    // to increase the sum.
                    j++;
                } else {
                    // If the sum is zero, add the triplet to the result list.
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++; // Move the left pointer to find other potential triplets.

                    // Skip duplicate values for the second element to avoid duplicate triplets.
                    while (nums[j] == nums[j - 1] && j < k) {
                        j++;
                    }
                }
            }
        }

        // Return the list of all unique triplets that sum to zero.
        return res;        
    }
}



// 3. Longest Palindromic Substring



class Solution {
    public String longestPalindrome(String s) {
        // Edge case: if the string is null or empty, return an empty string.
        if (s == null || s.length() == 0) return "";
        
        // Variables to store the start and end indices of the longest palindrome found.
        int start = 0;
        int end = 0;

        // Iterate through each character in the string as a potential center of a palindrome.
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (single character center).
            int odd = expandAroundCenter(s, i, i);
            
            // Check for even-length palindromes (two-character center).
            int even = expandAroundCenter(s, i, i + 1);
            
            // Find the maximum length between odd and even palindromes.
            int maxLen = Math.max(odd, even);

            // Update the start and end indices if a longer palindrome is found.
            if (maxLen > end - start) {
                // Calculate the start index of the palindrome.
                start = i - (maxLen - 1) / 2;
                
                // Calculate the end index of the palindrome.
                end = i + maxLen / 2;
            }
        }

        // Extract and return the longest palindrome substring using the start and end indices.
        return s.substring(start, end + 1);
    }

    // Helper function to expand around the center and calculate palindrome length.
    private int expandAroundCenter(String s, int left, int right) {
        // Expand outward as long as the characters match and indices are within bounds.
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; // Move the left pointer outward.
            right++; // Move the right pointer outward.
        }

        // Calculate the length of the palindrome.
        return right - left - 1; // Subtract 1 because left and right have moved past the valid range.
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
