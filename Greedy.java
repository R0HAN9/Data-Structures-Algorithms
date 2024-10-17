// Separate Black and White Balls


class Solution {
    public long minimumSteps(String s) {
        
        long answer = 0;
        int blackCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                answer += blackCount;
            }
            else {
                blackCount++;
            }
        }
        return answer;
    }
}


// Longest Happy String


class Solution {
    public String longestDiverseString(int a, int b, int c) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        if (a > 0) pq.offer(new int[] {a, 'a'});
        if (b > 0) pq.offer(new int[] {b, 'b'});
        if (c > 0) pq.offer(new int[] {c, 'c'});

        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty()) {
            int[] first = pq.poll();

            if (result.length() >= 2 && result.charAt(result.length() - 1) == first[1] && result.charAt(result.length() - 2) == first[1]) {

                if (pq.isEmpty()) break;
                int[] second = pq.poll();

                result.append((char) second[1]);
                second[0]--;

                if (second[0] > 0) pq.offer(second);
                pq.offer(first);
            }
            else {
                result.append((char) first[1]);
                first[0]--;

                if (first[0] > 0) pq.offer(first);
            }
        }

        return result.toString();
    }
}


// Maximum Swap



class Solution {
    public int maximumSwap(int num) {
        
        char[] numArr = Integer.toString(num).toCharArray();
        int n = numArr.length;

        int[] last = new int[10];
        for (int i = 0; i < n; i++) {
            last[numArr[i] - '0'] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int d = 9; d > numArr[i] - '0'; d--) {

                if (last[d] > i) {
                    char temp = numArr[i];
                    numArr[i] = numArr[last[d]];
                    numArr[last[d]] = temp;

                    return Integer.parseInt(new String(numArr));
                }
            }
        }

        return num;
    }
}
