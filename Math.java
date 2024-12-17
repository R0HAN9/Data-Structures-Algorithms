// Palindrome Number


class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers cannot be palindromes, so return false immediately.
        if (x < 0) return false;

        // Store the original number for comparison later.
        int original = x;
        int reversed = 0;

        // Reverse the digits of the number.
        while (x > 0) {
            // Extract the last digit of x and add it to the reversed number.
            reversed = (reversed * 10) + (x % 10);
            
            // Remove the last digit from x.
            x /= 10;
        }

        // Check if the reversed number is the same as the original number.
        return reversed == original;
    }
}



 // Plus One

class Solution {
    public int[] plusOne(int[] digits) {
        // Iterate through the array from the last digit (rightmost) to the first (leftmost).
        for (int i = digits.length - 1; i >= 0; i--) {
            // If adding 1 to the current digit doesn't result in a carry (not 10):
            if (digits[i] + 1 != 10) {
                // Increment the current digit by 1.
                digits[i] += 1;
                
                // Return the updated array as no carry is needed further.
                return digits;
            }
            
            // If adding 1 results in 10, set the current digit to 0 (carry over to the next digit).
            digits[i] = 0;
        }

        // If we reach here, all digits were 9 and we have a carry beyond the leftmost digit.
        // Create a new array with an additional digit to handle the carry.
        int[] newDigits = new int[digits.length + 1];
        
        // Set the first digit to 1 as it's the carry.
        newDigits[0] = 1;

        // Return the new array. The rest of the digits are already initialized to 0 by default.
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



// Pow(x, n)


class Solution {
    public double myPow(double x, int n) {
        return binaryExp(x, (long) n);
    }

    private double binaryExp(double x, long n) {

        if (n == 0) return 1;
        if (n < 0) return 1.0 / binaryExp(x, -n);

        if (n % 2 == 1) {
            return x * binaryExp(x * x, (n - 1) / 2);
        }
        else {
            return binaryExp(x * x, n / 2);
        }
    }
}
