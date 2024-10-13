// Search in Rotated Sorted Array ***


class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;        
    }
}



// 2. Kth Largest Element in an Array


class Solution {
    public int findKthLargest(int[] nums, int k) {
        int main_max = Integer.MIN_VALUE;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(hm.containsKey(nums[i])){
                hm.put(nums[i],hm.get(nums[i]) +1);
            }else{
                hm.put(nums[i],1);
            }
            main_max = Math.max(main_max,nums[i]);
        }
        if(hm.size()==1){
            return main_max;
        }
        k -= hm.get(main_max);
        while(k>0){
            main_max--;
            if(hm.containsKey(main_max)){
                k -= hm.get(main_max);
            }
        }
        return main_max;
    }
}
