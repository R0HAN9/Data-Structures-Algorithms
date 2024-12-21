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
        // Initialize variables
        int number = 0; // To build the current number from digits in the string
        int signValue = 1; // Tracks the current sign (+1 for positive, -1 for negative)
        int result = 0; // The running result of the calculation

        // Stack to handle results and signs when encountering parentheses
        Stack<Integer> optStack = new Stack<>();

        // Iterate through each character in the input string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // Build the number digit by digit
                number = number * 10 + (c - '0');
            } 
            else if (c == '+' || c == '-') {
                // When encountering a '+' or '-' operator:
                // Add the previously built number to the result using the current sign
                result += number * signValue;

                // Update the sign based on the current operator
                signValue = (c == '-') ? -1 : 1;

                // Reset the number to start building the next number
                number = 0;
            } 
            else if (c == '(') {
                // When encountering an open parenthesis '(':
                // Push the current result and sign onto the stack
                optStack.push(result);
                optStack.push(signValue);

                // Reset the result and sign for the new expression inside the parentheses
                result = 0;
                signValue = 1;
            } 
            else if (c == ')') {
                // When encountering a closing parenthesis ')':
                // Add the current number (if any) to the result
                result += signValue * number;

                // Multiply the result by the sign value popped from the stack
                result *= optStack.pop();

                // Add the result from before the parentheses (also popped from the stack)
                result += optStack.pop();

                // Reset the number for subsequent calculations
                number = 0;
            }
        }

        // Add any remaining number to the result (to account for the last number)
        return result + number * signValue;
    }
}







// Split a String Into the Max Number of Unique Substrings

class Solution {
    // Main method that calls the helper function
    public int maxUniqueSplit(String s) {
        return solve(0, s, new HashSet<>());
    }

    // Helper function that uses recursion to solve the problem
    public int solve(int start, String s, Set<String> set) {
        // If the starting index reaches the length of the string, no more splits are possible
        if (start == s.length()) {
            return 0;  // No more splits, return 0
        }

        int maxSplits = 0; // Variable to keep track of the maximum splits possible

        // Iterate through the string, trying all possible substrings starting from 'start'
        for (int i = start + 1; i <= s.length(); i++) {
            // Extract a substring from 'start' to 'i'
            String substring = s.substring(start, i);

            // Check if the substring is not already present in the set
            if (!set.contains(substring)) {
                // If it's not in the set, add it
                set.add(substring);

                // Recursively calculate the splits starting from index 'i' and add 1 for the current split
                int splits = 1 + solve(i, s, set);

                // Update maxSplits with the maximum of current maxSplits and splits
                maxSplits = Math.max(maxSplits, splits);

                // Remove the substring from the set after exploring this path
                set.remove(substring);
            }
        }

        // Return the maximum number of unique splits
        return maxSplits;
    }
}




 // Design Add and Search Words Data Structure


class WordDictionary {

    private List<String> wordsList;  // List to store all the words in the dictionary

    // Constructor to initialize the wordsList
    public WordDictionary() {
        wordsList = new ArrayList<>();
    }
    
    // Method to add a word to the dictionary
    public void addWord(String word) {
        wordsList.add(word);  // Add the given word to the wordsList
    }
    
    // Method to search for a word in the dictionary, where '.' can be a wildcard character
    public boolean search(String word) {
        
        // Loop through each word in the wordsList
        for (String savedWord : wordsList) {
            // If the lengths of the word and savedWord are not equal, continue to the next savedWord
            if (savedWord.length() != word.length()) continue;

            boolean wordsMatch = true;  // Flag to check if the words match

            // Compare each character in the word and savedWord
            for (int i = 0; i < word.length(); i++) {
                // If the character in the search word is not '.' and does not match the saved word, set wordsMatch to false
                if (word.charAt(i) != '.' && savedWord.charAt(i) != word.charAt(i)) {
                    wordsMatch = false;
                    break;  // No need to check further if there is a mismatch
                }
            }
            
            // If wordsMatch is still true, it means the word matches the savedWord, so return true
            if (wordsMatch) return true;
        }

        // If no match was found after checking all words, return false
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
