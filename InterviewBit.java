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

