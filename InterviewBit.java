// Expected number of Segments

public class Solution {
    private static final int MOD = 1000000007;

    // Function to calculate modular exponentiation
    private long modPow(long base, long exp, int mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // Function to solve the problem
    public int solve(int A, int B) {
        if (B == 1) return 1; // Only one character in the alphabet, so only 1 segment

        // Calculate (B-1) * (A-1)
        long numerator = (long)(A - 1) * (B - 1) % MOD;

        // Calculate modular inverse of B
        long denominator = modPow(B, MOD - 2, MOD);

        // Calculate the result as per formula
        long expectedSegments = (1 + numerator * denominator % MOD) % MOD;

        return (int) expectedSegments;
    }
}


// Maximum height of staircase

public class Solution {
    public int solve(int A) {
        int blocksUsed = 0;
        int height = 0;
        
        while (blocksUsed <= A) {
            height++;
            blocksUsed += height;
        }
        
        // Adjust height if we used more blocks than allowed
        if (blocksUsed > A) {
            height--;
        }
        
        return height;
    }
}



// Sum of 7's Multiple

public class Solution {
    public long solve(int A, int B) {
        long first = (A % 7 == 0) ? A : A + (7 - A % 7);
        long last = (B % 7 == 0) ? B : B - (B % 7);
        long n = (last - first) / 7 + 1;
        long ans = n * (2 * first + (n - 1) * 7) / 2;
        
        return ans;
    }
}


// Last digit K count

public class Solution {
    public int solve(int A, int B, int C) {
        for (int i = A; i <= B; i++) {
            if (String.valueOf(C).equals(String.valueOf(i % 10))) {
                return (B - i) / 10 + 1;
            }
        }
        return 0;
    }
}


// Find Last Digit

public class Solution {
    public int solve(String A, String B) {
        int a = A.charAt(A.length()-1) - '0';
        int b = Integer.parseInt(B.substring(B.length()-2));
        long ans = (long)Math.pow(a,(b%4+4));
        return (int)(ans%10);
    }
}
