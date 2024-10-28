// Palindrome Number


class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int original = x;
        int reversed = 0;

        while (x > 0) {
            reversed = (reversed * 10) + (x % 10);
            x /= 10;
        }

        return reversed == original;
    }
}


 // Plus One

class Solution {
    public int[] plusOne(int[] digits) {
        
        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] + 1 != 10) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }

        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;

        return newDigits;
    }
}


// Sqrt(x)

class Solution {
    public int mySqrt(int x) {
        
        if (x == 0) return 0;

        long left = 1;
        long right = x;
        long result = 0;

        while (left <= right) {

            long mid = left + (right - left) / 2;
            long midSquared = mid * mid;

            if (midSquared == x) {
                return (int) mid;
            }
            else if (midSquared < x) {
                result = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return (int) result;
    }
}
