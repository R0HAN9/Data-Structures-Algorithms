// Course Schedule II


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        
        int numOfPrerequisites = prerequisites.length;
        for (int i = 0; i < numOfPrerequisites; i++) {
            
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            adjacencyList.get(prerequisite).add(course);
        }

        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int neighbor : adjacencyList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] topologicalOrder = new int[numCourses];
        int index = 0;

        while (!queue.isEmpty()) {

            int course = queue.peek();
            queue.remove();
            topologicalOrder[index++] = course;

            for (int neighbor : adjacencyList.get(course)) {
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (index == numCourses) {
            return topologicalOrder;
        }

        int[] emptyArray = {};
        return emptyArray;
    }
}


// Word Ladder

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // Create a set of all words in the word list for quick lookup.
        Set<String> wordSet = new HashSet<>();

        // Flag to check if the endWord is present in the word list.
        Boolean isPresent = false;

        // Add all words from the word list to the set.
        wordSet.addAll(wordList);

        // Check if the endWord is in the word list.
        for (String currWord : wordList) {   
            if (endWord.equals(currWord)) {
                isPresent = true;
                break; // If found, break out of the loop.
            }
        }

        // If endWord is not in the list, return 0 as there's no valid transformation.
        if (!isPresent) return 0;

        // Use a queue to perform BFS (Breadth-First Search).
        Queue<String> wordQueue = new LinkedList<>();

        // Start BFS with the beginWord.
        wordQueue.add(beginWord);

        // Distance from the beginWord (initially 0).
        int distance = 0;

        // BFS loop: continue until the queue is empty.
        while (!wordQueue.isEmpty()) {
            int size = wordQueue.size();
            distance++; // Increment distance at each level of BFS.

            // Process each word in the current level.
            while (size-- != 0) {
                String currWord = wordQueue.poll(); // Get the front word from the queue.

                // Try changing each character in the current word.
                for (int i = 0; i < currWord.length(); i++) {
                    char[] temp = currWord.toCharArray(); // Convert the word to a character array.

                    // Replace the character at index i with every letter from 'a' to 'z'.
                    for (char j = 'a'; j <= 'z'; j++) {
                        temp[i] = j;

                        String newWord = new String(temp); // Form a new word.

                        // If the new word matches the endWord, return the distance + 1.
                        if (newWord.equals(endWord)) return distance + 1;

                        // If the new word is in the word set, it's a valid transformation.
                        if (wordSet.contains(newWord)) {
                            wordQueue.add(newWord); // Add it to the queue for further exploration.
                            wordSet.remove(newWord); // Remove it from the set to avoid revisiting.

                            // Print the new word being added to the queue (for debugging).
                            System.out.println(newWord);
                        }
                    }
                }
            }
        }

        // If no transformation sequence leads to the endWord, return 0.
        return 0;
    }
}



// Clone Graph


class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> oldToNew = new HashMap<>();
        return dfs(node, oldToNew);
    }

    private Node dfs(Node node, Map<Node, Node> oldToNew) {
        if (oldToNew.containsKey(node)) return oldToNew.get(node);

        Node copy = new Node(node.val);
        oldToNew.put(node, copy);

        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(dfs(neighbor, oldToNew));
        }

        return copy;
    }    
}



// Evaluate Division


class Solution {
    public void dfs(String node, String dest, HashMap<String, HashMap<String, Double>> gr, HashSet<String> vis, double[] ans, double temp) {
        if (vis.contains(node)) return;

        vis.add(node);
        if (node.equals(dest)){
            ans[0] = temp;
            return;
        }

        for (Map.Entry<String, Double> entry : gr.get(node).entrySet()) {
            String ne = entry.getKey();

            double val = entry.getValue();
            dfs(ne, dest, gr, vis, ans, temp * val);
        }
    }
    public HashMap<String, HashMap<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        HashMap<String, HashMap<String, Double>> gr = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);
            double value = values[i];

            gr.putIfAbsent(dividend, new HashMap<>());
            gr.putIfAbsent(divisor, new HashMap<>());
            
            gr.get(dividend).put(divisor, value);
            gr.get(divisor).put(dividend, 1.0 / value);
        }
        return gr;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, HashMap<String, Double>> gr = buildGraph(equations, values);
        double[] finalAns = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);

            if (!gr.containsKey(dividend) || !gr.containsKey(divisor)) {
                finalAns[i] = -1.0;
            } else {
                HashSet<String> vis = new HashSet<>();
                double[] ans = {-1.0};
                double temp = 1.0;
                dfs(dividend, divisor, gr, vis, ans, temp);
                finalAns[i] = ans[0];
            }
        }

        return finalAns;
    }
}
