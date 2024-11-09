// BST Sum

public class Solution {
    final long MOD = 1000000007;
    long[] fact = new long[100001];
    final long MODINV6 = 166666668;
    public Solution()
    {
        fact[0]=1;
        for(long i=1; i<=100000; i++)
        {
            fact[(int)i] = (fact[(int)(i-1)] * i)%MOD;
        }
    }
    public int solve(int A) {
        long a =A, ans;
        ans = ((fact[A-1]*a)%MOD * (a*a -1)%MOD)%MOD;
        return (int)((ans * MODINV6)%MOD);
    }
}



// Maximum level sum

public class Solution {
    public int solve(TreeNode A) {
        if (A == null) return Integer.MIN_VALUE;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(A);
        int res = Integer.MIN_VALUE;
        
        while (!q.isEmpty()) {
            int n = q.size();
            int sum = 0;
            
            for (int i = 0; i < n; i++) {
                TreeNode curr = q.poll();
                sum += curr.val;
                
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
            
            res = Math.max(sum, res);
        }
        
        return res;
    }
}


// Binary String Cleanup Challenge

public class Solution {
    public String solve(String A) {
        
        StringBuilder sb1=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        
        int i=A.length()-1;
        
        while(i>=0 && A.charAt(i)=='1'){
            sb1.append(A.charAt(i));
            i--;
        }
        while(i>=0){
            
            if(A.charAt(i)=='1'){
                sb2.delete(0,sb2.length());
                sb2.append('0');
            }else{
                sb2.append(A.charAt(i));
            }
            i--;
        }
        sb1.append(sb2.toString());
        String s1= sb1.toString();
        
        StringBuilder s3=new StringBuilder();
        
        for(int j=s1.length()-1;j>=0;j--){
            s3.append(s1.charAt(j));
        }
        return s3.toString();
    }
}


// Palindromic Words

public class Solution {
    public int solve(String A) {
        String[] arr = A.split(" ");
        int count = 0;
        for(String x : arr ) {
            count += isPalindrome(x);
        }
        return count;    
    }
   
    public static int isPalindrome(String str)
    {
        for (int i = str.length() - 1,j=0; j<i; i--,j++) {
           if (str.charAt(i) != str.charAt(j)){
               return 0;
           }
        }
        return 1;
    }
}


// Valid Password

public class Solution {
    public int solve(String A) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#%&!$*])(?=\\S+$).{8,15}$";
       
        if (A.matches(regex)) {
            return 1;
        } else {
            return 0;
        }
    }
}


// String Inversion

public class Solution {
    public String solve(String A) {
        
        char[]arr=A.toCharArray();
        
        for(int i=0;i<arr.length;i++){
            arr[i]=(char)(arr[i]^32);
        }
        return new String(arr);
    }
}


// Character Frequencies

public class Solution {
    public ArrayList<Integer> solve(String A) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashSet<Character> set = new HashSet<>();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0; i<A.length(); i++){
            char c = A.charAt(i);
            if(!map.containsKey(c)) map.put(c,1);
            else map.put(c,map.get(c) + 1);
        }
        for(int i=0; i<A.length(); i++){
            char c = A.charAt(i);
            if(!set.contains(c)){
                ans.add(map.get(c));
                set.add(c);
            }
        }
        return ans;
    }
}


// Maximum Substring

public class Solution {
    public int solve(String A, int B) {
       int even=A.length()%B;
      int max=0;
        for(int i=0;i<A.length()-even;i=i+B){
            String s=A.substring(i,i+B);
             int count=Count(s);
           
            max=Math.max(max,count);
           
        }
        max=Math.max(max,Count(A.substring(A.length()-even,A.length()))) ;
        return max;
    }
   
    int Count(String s){
        int count=0;
        for(int j=0;j<s.length();j++){
                char k=s.charAt(j);
                if(k=='a'){
                    count++;
                }
            }
            return count;
    }
}


// Frequency of Characters

public class Solution {
    public ArrayList<Integer> solve(String A) {
        // Initialize an array of size 26 to store the frequency of each character
        int[] arr = new int[26];
        
        // Iterate over each character in the input string
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            // Convert the character to its corresponding index (0 for 'a', 1 for 'b', etc.)
            int ascii = (int) c;
            arr[ascii - 97] += 1;
        }
        
        // Create an ArrayList to store the result
        ArrayList<Integer> result = new ArrayList<>();
        
        // Populate the ArrayList with the frequencies from the array
        for (int i = 0; i < 26; i++) {
            result.add(arr[i]);
        }
        
        // Return the ArrayList containing the frequency of each character
        return result;
    }
}



// Text Editor

public class Solution {
    static int N = 500005;
    //static ArrayList < ArrayList < Integer > > f;
    static int[] ans = new int[N];
    //static int flag = 0;
   /* public static void sieve() {
          f = new ArrayList < ArrayList < Integer > > (N);
        for (int i = 0; i < N; i++)
            f.add(new ArrayList < Integer > ());
        for (int i = 1; i < N; i++) {
            for (int j = i; j < N; j += i)
                f.get(j).add(i);
        }
    }*/
    public static int[] prefix_function(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] pi = new int[n];
        Arrays.fill(pi, 0);
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s[i] != s[j])
                j = pi[j - 1];
            if (s[i] == s[j])
                j++;
            pi[i] = j;
        }
        return pi;
    }
    public int solve(String A) {
        /*if(flag==0)
        {
            sieve();
            flag=1;
        }*/
        Arrays.fill(ans,0);
        char[] s = A.toCharArray();
        int[] pi = prefix_function(A);
        ans[0] = 1;
        for (int i = 1; i < s.length; i++) {
            ans[i] = 1 + ans[i - 1];
            int k = i + 1 - pi[i];
            if (((i + 1) % k)>0)
                k = i + 1;
            int c = (i + 1) / k;
            ArrayList<Integer> f = getDiv(c);
            for (int j: f) {
                if (j == c) break;
                ans[i] = Math.min(ans[i], ans[k * j - 1] + c / j);
            }
        }
        return ans[s.length - 1];
    }
    public static ArrayList<Integer> getDiv(int c)
    {
        ArrayList<Integer>ret = new ArrayList<>();
        for(int i=1;i*i<=c;i++)
        {
            if(c%i==0)
            {
                if(i==c/i)
                    ret.add(i);
                else
                {
                    ret.add(i);
                    ret.add(c/i);
                }
            }
        }
        Collections.sort(ret);
        return ret;
    }
}




// Flipping String

public class Solution {
    public int solve(String A) {
        ArrayList<String> alist=new ArrayList<>();
        int one=0;
        int zero=0;
        for(int i=0;i<A.length();i++){
            while(i<A.length() && A.charAt(i)=='1'){
                one++;
                i++;
            }
            if(one>0){
                alist.add(""+one);
                one=0;
            }
            while(i<A.length() && A.charAt(i)=='0'){
                zero++;
                i++;
            }
            if(zero>0){
                alist.add(zero+"o");
                zero=0;
            }
            i=i-1;
        }
        int max=0;
        for(int i=0;i<alist.size();i++){
            int count=0;
            if(alist.get(i).charAt(alist.get(i).length()-1)=='o'){
                count+=Integer.valueOf(alist.get(i).substring(0,alist.get(i).length()-1));
                if(i!=0){
                    count+=Integer.valueOf(alist.get(i-1).substring(0,alist.get(i-1).length()));
                }
                if(i!=alist.size()-1){
                    count+=Integer.valueOf(alist.get(i+1).substring(0,alist.get(i+1).length()));
                }
            }
            max=Math.max(count,max);
        }
        return max;
    }
}


// Queries in Infinite String!

import java.util.ArrayList;

public class Solution {
   static int flag = 0;
   static ArrayList<Integer> v = new ArrayList<Integer>();

   // This is the updated solve method accepting ArrayList<ArrayList<Integer>> A
   public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A) {
      if (flag == 0) {
         flag = 1;
         pre();
      }
      
      ArrayList<Integer> ans = new ArrayList<Integer>(); // To hold the result
      char[] arr = new char[]{'a', 'e', 'i', 'o', 'u'};  // Vowels array
      
      for (int i = 0; i < A.size(); i++) {
         int res = 0;
         ArrayList<Integer> row = A.get(i); // Get the current row from ArrayList<ArrayList<Integer>>

         for (int j = 0; j < 5; j++) {
            res += (f(row.get(1), arr[j]) - (f(row.get(0) - 1, arr[j])));
         }

         ans.add(res);  // Add result for the current row to the result list
      }
      
      return ans;  // Return the final list of results
   }

   public static void pre() {
      v.add(0);
      int diff = 26;
      int prev = 0;
      for (int i = 1; i <= 8771; i++) {
         v.add(prev + diff);
         prev = prev + diff;
         diff += 26;
      }
   }

   public static int f(int index, char temp) {
      if (index == 0)
         return 0;

      int ascii = (temp - 'a') + 1;
      int id = upperBound(v, 0, v.size(), index);
      id--;
      int ans = ((id) * (id + 1)) / 2;

      if (index == v.get(id))
         return ans;

      int ct = id + 1;
      int startIndex = v.get(id) + 1 + ct * (ascii - 1);
      int endIndex = Math.min(index, startIndex + ct - 1);
      if (startIndex > endIndex)
         return ans;

      ans += (endIndex - startIndex + 1);
      return ans;
   }

   static int upperBound(ArrayList<Integer> a, int low, int high, int element) {
      while (low < high) {
         int middle = low + (high - low) / 2;
         if (a.get(middle) > element) {
            high = middle;
         } else {
            low = middle + 1;
         }
      }
      return low;
   }

   public static void main(String[] args) {
      Solution solution = new Solution();

      // Example input as ArrayList<ArrayList<Integer>>
      ArrayList<ArrayList<Integer>> input = new ArrayList<>();
      ArrayList<Integer> row1 = new ArrayList<>();
      row1.add(1); row1.add(10);
      ArrayList<Integer> row2 = new ArrayList<>();
      row2.add(5); row2.add(20);
      input.add(row1);
      input.add(row2);

      // Solve the problem
      ArrayList<Integer> result = solution.solve(input);

      // Print the result
      System.out.println(result);
   }
}



// Coin Queue

public class Solution {
    Stack<Integer> stk1, stk2;
    Stack<ArrayList<Integer> > l1, l2;
    ArrayList<Integer> push(ArrayList<Integer> l, int x)
    {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(int i=0; i<x; i++)
        {
            ans.add(l.get(i));
        }
        for(int i = x; i <=350; i++)
        {
            if(l.get(i-x)>=0)
                ans.add(Math.max(l.get(i-x) +1,  l.get(i)));
            else
                ans.add(l.get(i));
        }
        return ans;
    }
    public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        ArrayList<Integer> starting = new ArrayList<Integer>();
        starting.add(0);
        for(int i=1;i<=350;i++)
            starting.add(-1);
        ArrayList <Integer> ans = new ArrayList<Integer>();
        stk1 = new Stack<Integer>(); 
        l1 = new Stack<ArrayList<Integer> >();
        stk2 = new Stack<Integer>();
        l2 = new Stack<ArrayList<Integer> >();
        for(int i = 0; i < n; i++)
        {
            int x = A.get(i).get(0), y = A.get(i).get(1);
            if(x==1)
            {
                stk1.push(y);
                if(l1.empty())
                {
                    l1.push(push(starting, y));
                }
                else
                {
                    l1.push(push(l1.peek(), y));
                }
            }
            else if(x==2)
            {
                if(stk2.empty())
                {
                    while(!stk1.empty())
                    {
                        stk2.push(stk1.peek());
                        if(l2.empty())
                        {
                            l2.push(push(starting, stk1.peek()));
                        }
                        else
                        {
                            l2.push(push(l2.peek(), stk1.peek()));
                        }
                        stk1.pop();
                        l1.pop();
                    }
                }
                stk2.pop();
                l2.pop();
            }
            else
            {
                int a=-1;
                if(l1.empty() && l2.empty())
                    ans.add(-1);
                else if(l1.empty())
                    ans.add(l2.peek().get(y));
                else if(l2.empty())
                    ans.add(l1.peek().get(y));
                else{
                    for(int j = 0; j<=y; j++)
                    {
                        if(l1.peek().get(j) != -1 && l2.peek().get(y-j) != -1)
                            a = Math.max(a, l1.peek().get(j) + l2.peek().get(y-j));
                    }
                    ans.add(a);
                }
            }
        }
        return ans;
    }
}



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
