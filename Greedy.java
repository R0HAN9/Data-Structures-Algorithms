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
