
// Basic Calculator


class Solution {
    public int calculate(String s) {
        
        int number = 0;
        int signValue = 1;
        int result = 0;

        Stack<Integer> optStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) number = number * 10 + (c - '0');
            else if (c == '+' || c == '-') {

                result += number * signValue;
                signValue = (c == '-') ? -1 : 1;
                number = 0;
            }
            else if (c == '(') {

                optStack.push(result);
                optStack.push(signValue);
                result = 0;
                signValue = 1;
            }
            else if (c == ')') {

                result += signValue * number;
                result *= optStack.pop();
                result += optStack.pop();
                number = 0;
            }
        }

        return result + number * signValue;
    }
}






// Split a String Into the Max Number of Unique Substrings

class Solution {
    public int maxUniqueSplit(String s) {
        return solve(0, s, new HashSet<>());
    }

    public int solve(int start, String s, Set<String> set) {
        if (start == s.length()) {
            return 0;
        }

        int maxSplits = 0;
        for (int i = start + 1; i <= s.length(); i++) {
            
            String substring = s.substring(start, i);
            if (!set.contains(substring)) {
                set.add(substring);

                int splits = 1 + solve(i, s, set);
                maxSplits = Math.max(maxSplits, splits);
                set.remove(substring);
            }
        }

        return maxSplits;
    }
}
