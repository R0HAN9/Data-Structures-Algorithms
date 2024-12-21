// Remove Sub-Folders from the Filesystem

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // Step 1: Sort the folder array lexicographically.
        // Sorting ensures that all subfolders come immediately after their parent folder in the list.
        Arrays.sort(folder);

        // Step 2: Create a list to store the final answer (unique parent folders).
        List<String> answer = new ArrayList<>();

        // Step 3: Add the first folder as it's the smallest lexicographically and cannot be a subfolder.
        answer.add(folder[0]);

        // Step 4: Iterate through the remaining folders.
        for (int i = 1; i < folder.length; i++) {
            // Retrieve the last folder added to the answer list and append a slash (`/`).
            // The slash ensures that we only consider full folder paths as subfolders.
            String lastFolder = answer.get(answer.size() - 1) + "/";

            // Check if the current folder does NOT start with the last added folder plus a slash.
            // If it doesn't, it's not a subfolder, so we add it to the answer.
            if (!folder[i].startsWith(lastFolder)) {
                answer.add(folder[i]);
            }
        }

        // Step 5: Return the list of unique parent folders.
        return answer;
    }
}



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



 // Design Add and Search Words Data Structure


class WordDictionary {

    private List<String> wordsList;

    public WordDictionary() {
        wordsList = new ArrayList<>();
    }
    
    public void addWord(String word) {
        wordsList.add(word);
    }
    
    public boolean search(String word) {
        
        for (String savedWord : wordsList) {
            if (savedWord.length() != word.length()) continue;

            boolean wordsMatch = true;

            for (int i = 0; i < word.length(); i++) {

                if (word.charAt(i) != '.' && savedWord.charAt(i) != word.charAt(i)) {
                    wordsMatch = false;
                    break;
                }
            }
            
            if (wordsMatch) return true;
        }

        return false;
    }
}


// Letter Combinations of a Phone Number

class Solution {

    private static final Map<Character, String> phoneMap = new HashMap<>();

    static {
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz"); 
    }

    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder combination, String digits, int index) {

        if (index == digits.length()) {
            result.add(combination.toString());
            return;
        }

        char d = digits.charAt(index);
        String letters = phoneMap.get(d);

        for (char l : letters.toCharArray()) {

            combination.append(l);
            backtrack(result, combination, digits, index + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}


// Generate Parentheses


class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        dfs(0, 0, "", n, result);
        return result;
    }

    private void dfs(int openP, int closeP, String str, int n, List<String> result) {
       
        if (openP == closeP && openP + closeP == n * 2) {
            result.add(str);
            return;
        }
        if (openP < n) dfs(openP + 1, closeP, str + "(", n, result);
        if (closeP < openP) dfs(openP, closeP + 1, str + ")", n, result);
    }
}
