// X - Permutations

public class Solution {
    private static final long MOD = 1000000007;

    // Function to perform modular exponentiation
    private long modularExponentiation(long base, long exponent, long modulus) {
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent /= 2;
        }
        return result;
    }

    // Function to find modular inverse
    private long modularInverse(long number, long modulus) {
        return modularExponentiation(number, modulus - 2, modulus);
    }

    public ArrayList<Integer> solve(int A) {
        ArrayList<Long> factorial = new ArrayList<>(A + 1);
        ArrayList<Long> inverseFactorial = new ArrayList<>(A + 1);
        ArrayList<Long> derangements = new ArrayList<>(A + 1);

        // Initialize factorial, inverseFactorial, and derangements
        for (int i = 0; i <= A; i++) {
            factorial.add(0L);
            inverseFactorial.add(0L);
            derangements.add(0L);
        }

        factorial.set(0, 1L);
        for (int i = 1; i <= A; i++) {
            factorial.set(i, (factorial.get(i - 1) * i) % MOD);
        }

        for (int i = 0; i <= A; i++) {
            inverseFactorial.set(i, modularInverse(factorial.get(i), MOD));
        }

        derangements.set(0, 1L);
        if (A > 0) {
            derangements.set(1, 0L);
        }
        for (int i = 2; i <= A; i++) {
            derangements.set(i, ((i - 1) * (derangements.get(i - 1) + derangements.get(i - 2))) % MOD);
        }

        ArrayList<Integer> result = new ArrayList<>(A + 1);
        for (int x = 0; x <= A; x++) {
            long combinations = (factorial.get(A) * inverseFactorial.get(x)) % MOD * inverseFactorial.get(A - x) % MOD;
            long numberOfPermutations = (combinations * derangements.get(A - x)) % MOD;
            result.add((int) numberOfPermutations);
        }

        return result; // Return the result list
    }
}





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


// Digital Root

public class Solution {
    public int solve(int A) {
        if(A==0){
            return 0;
        }
        else if(A%9==0){
            return 9;
        }
        return A%9;
    }        
}   



// Number of Sundays

public class Solution {
    public int solve(String A, int B) {
        int day=0;
        switch(A)
        {
        case "Sunday": day=6;
        break;
        case "Tuesday": day=1;
        break;
        case "Wednesday": day=2;
        break;
        case "Thursday": day=3;
        break;
        case "Friday": day=4;
        break;
        case "Saturday": day=5;
        break;
        }
        int count =(B+day)/7;
        return count;
    }
}


// Product of Digits

public class Solution {
    public int solve(int A) {
        String[] s = String.valueOf(A).split("");
        int[] num = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            num[i] = Integer.parseInt(s[i]);
        }
        int a = 1;
        for (int i = 0; i < num.length; i++) {
            a *= num[i];
        }
        return a;
    }
}


// Round Table

public class Solution {
    public int solve(int A) {
        int mod = 1000000007;
        long ans = 2;  // Start with 2 as given in the original code

        for (int i = 2; i <= A; i++) {
            ans *= i;
            ans = ans % mod;  // Apply modulo to keep the result within bounds
        }

        return (int) ans;  // Cast to int before returning since `solve` returns an int
    }
}


// Odd Even Rule

public class Solution {
    public int solve(ArrayList<Integer> A, int B, int C) {
        int ans =0;
        if(B%2 == 0){
            for(int i =0; i<A.size(); i++){
                if(A.get(i) %2 != 0){
                    ans += C;
                }
            }
        }else{
             for(int i =0; i<A.size(); i++){
                if(A.get(i) % 2 == 0){
                    ans += C;
                }
            }
        }
        return ans;
    }
}


// Armstrong Number

public class Solution {
    static int countDigit(int n)
    {
        return (int)Math.floor(Math.log10(n) + 1);
    }
    public int solve(int A) {
        int temp =A;
        int len = countDigit(A);
    long rem = 0;
    long sum =0;
        while(A>0){
            rem = A%10;
            A=A/10;
            sum+=Math.pow(rem,len);
        }

        if(temp==(int)sum){
            return 1;
        }

        return 0;
    }
}


// Leap Year

public class Solution {
    public int solve(int A) {
        // Check if A is a leap year
        if (A % 4 == 0 && A % 100 != 0) {
            return 1;
        } else if (A % 400 == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}


// Lowest Common Multiple (LCM)

public class Solution {
    public Long solve(int A, int B) {
         long a = A;
       long b = B;
       long gcd = gcd(A, B);
       
       return ((a * b) / gcd);
    }
    
    int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
}


// Which Season?

public class Solution {
    public String solve(int A) {
        if(A>12)
            return "Invalid";
        else if(A>=3 && A<=5)
            return "Spring";
        else if(A>=6 && A<=8)
            return "Summer";
        else if(A>=9 && A<=11)
            return "Autumn";
        else
            return "Winter";
    }
}


// Socks Pair

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public int solve(ArrayList<Integer> A) {
        HashMap<Integer, Integer> mp = new HashMap<>(); // To store the frequency of each sock type

        // Count the frequency of each sock type
        for (int i = 0; i < A.size(); i++) {
            mp.put(A.get(i), mp.getOrDefault(A.get(i), 0) + 1);
        }

        int count = 0; // To count the pairs
        // Calculate the number of pairs
        for (int frequency : mp.values()) {
            count += frequency / 2; // Each pair consists of 2 socks
        }

        return count; // Return the total count of pairs
    }
}


// Two Stores

public class Solution {
    public int solve(int a, int b, int c, int d, int e) {
        int ans = Integer.MAX_VALUE; // Initialize answer to maximum integer value

        // First loop to calculate costs using type 1
        for (int i = 0; i <= a / b; i++) {
            int temp = c * i; // Cost of using type 1
            if ((a - b * i) >= d && (a - b * i) % d == 0) {
                temp += ((a - b * i) / d) * e; // Add cost of using type 2
                ans = Math.min(ans, temp); // Update the minimum cost
            }
        }

        // Second loop to calculate costs using type 2
        for (int i = 0; i <= a / d; i++) {
            int temp = e * i; // Cost of using type 2
            if ((a - d * i) >= b && (a - d * i) % b == 0) {
                temp += ((a - d * i) / b) * c; // Add cost of using type 1
                ans = Math.min(ans, temp); // Update the minimum cost
            }
        }

        // If no valid solution found, return -1
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}


// GP Triplets QXSsA

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public int solve(ArrayList<Integer> A) {
        // Sort the array
        Collections.sort(A);

        int count = 0;
        int n = A.size();

        // Loop through each triplet
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // Case 1: All three elements are the same
                    if (A.get(i).equals(A.get(j)) && A.get(j).equals(A.get(k))) {
                        count += 6; // 3! permutations for the same elements
                    }
                    // Case 2: Check for geometric progression
                    else if ((long) A.get(k) * A.get(i) == (long) A.get(j) * A.get(j) &&
                             A.get(j) % A.get(i) == 0 &&
                             A.get(k) % A.get(j) == 0) {
                        count++;
                    }
                }
            }
        }

        return count; // Return the total count of triplets
    }
}



// Prime calculations

import java.util.*;

public class Solution {

    final static int MAX_N = 1000006;
    final static int MOD = (int) 1e9 + 7;
    int[] primeFactorsCount = new int[MAX_N];

    // Sieve to count distinct prime factors
    public void sieve() {
        for (int i = 2; i < MAX_N; i++) {
            if (primeFactorsCount[i] == 0) {
                for (int j = i; j < MAX_N; j += i) {
                    primeFactorsCount[j]++;
                }
            }
        }
    }

    public int solve(ArrayList<Integer> A, int B) {
        int n = A.size();
        if (primeFactorsCount[0] == 0) {
            sieve();
        }

        // Variable to hold the sum of results
        long sum = 0;
        
        // Deque to maintain the index of elements in the current window
        Deque<Integer> deque = new ArrayDeque<>();

        // Traverse the array with sliding window approach
        for (int i = 0; i < n; i++) {
            // Remove elements outside the current window
            if (!deque.isEmpty() && deque.peekFirst() <= i - B) {
                deque.pollFirst();
            }

            // Maintain the deque such that it holds the index of elements
            // with the maximum number of distinct prime factors
            while (!deque.isEmpty() && primeFactorsCount[A.get(deque.peekLast())] < primeFactorsCount[A.get(i)]) {
                deque.pollLast();
            }

            // Add the current element to the deque
            deque.offer(i);

            // If we have filled a full window, we calculate the result
            if (i >= B - 1) {
                // The leftmost element with the max prime factors is at the front of the deque
                sum = (sum + A.get(deque.peekFirst())) % MOD;
            }
        }

        return (int) sum;
    }
}


// Ticket Counter


import java.util.List;

public class Solution {
    public int solve(List<Integer> A, List<Integer> B) {
        int time = 0;
        int ans = 0;
        
        // Iterate through the array
        for (int i = 0; i < A.size(); i++) {
            // If the time is greater than or equal to the arrival time
            if (A.get(i) < time) {
                ans++;  // Increment the answer if the current event starts before the previous one finishes
            } else {
                time += B.get(i);  // Add the duration of the current event to the time
            }
        }
        return ans;
    }
}
