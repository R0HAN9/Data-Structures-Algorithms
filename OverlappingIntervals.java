// Non-overlapping Intervals


class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int res = 0; // To count the number of overlapping intervals to remove

        // Sort intervals by their end times in ascending order
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int prev_end = intervals[0][1]; // Initialize with the end time of the first interval

        // Iterate through the intervals starting from the second one
        for (int i = 1; i < intervals.length; i++) {
            // Check if the current interval overlaps with the previous one
            if (prev_end > intervals[i][0]) {
                res++; // Increment the count of intervals to remove
            } else {
                // Update the previous end time if there's no overlap
                prev_end = intervals[i][1];
            }
        }

        return res; // Return the number of intervals removed
    }
}




// **Maximal Disjoint Intervals**


import java.util.*;

public class Solution {
    static class Pair implements Comparable<Pair> {

        int first, second;

        Pair(int f, int s) {
            first = f;
            second = s;
        }

        @Override

        public int compareTo(Pair o) {

            if (this.second > o.second)
                return 1;
            else if (this.second == o.second)
                return 0;
            return -1;
        }
    }

    static void maxDisjointIntervals(Pair[] list) {

        Collections.sort(Arrays.asList(list));
        System.out.println("[" + list[0].first + "," + list[0].second + "]" + "\n");

        int r1 = list[0].second;
        for (int i = 1; i < list.length; i++) {

            int l1 = list[i].first;
            int r2 = list[i].second;

            if (l1 > r1) {
                System.out.println("[" + l1 + ", " + r2 + "]" + "\n");
                r1 = r2;
            }
        }
    }

    public static void main(String[] args) {

        Pair[] intervals = {
                new Pair(1, 4),
                new Pair(2, 3),
                new Pair(4, 6),
                new Pair(8, 9) };

        maxDisjointIntervals(intervals);
    }
}
