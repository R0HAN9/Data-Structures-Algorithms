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

// Define a class Pair to represent intervals with a start (first) and end (second)
public class Solution {
    static class Pair implements Comparable<Pair> {

        int first, second; // Interval start and end

        Pair(int f, int s) { // Constructor to initialize a Pair
            first = f;
            second = s;
        }

        @Override
        public int compareTo(Pair o) {
            // Comparator to sort intervals by their ending time (second value)
            if (this.second > o.second)
                return 1;
            else if (this.second == o.second)
                return 0;
            return -1;
        }
    }

    // Function to find and print the maximum number of disjoint intervals
    static void maxDisjointIntervals(Pair[] list) {
        // Sort intervals based on their end times
        Collections.sort(Arrays.asList(list));

        // Print the first interval as it is always part of the solution
        System.out.println("[" + list[0].first + "," + list[0].second + "]" + "\n");

        int r1 = list[0].second; // Track the end time of the last included interval

        // Iterate through the intervals starting from the second one
        for (int i = 1; i < list.length; i++) {
            int l1 = list[i].first; // Start of the current interval
            int r2 = list[i].second; // End of the current interval

            // Check if the current interval does not overlap with the last included interval
            if (l1 > r1) {
                // Print the interval and update the end time tracker
                System.out.println("[" + l1 + ", " + r2 + "]" + "\n");
                r1 = r2;
            }
        }
    }

    public static void main(String[] args) {
        // Initialize a list of intervals
        Pair[] intervals = {
                new Pair(1, 4),
                new Pair(2, 3),
                new Pair(4, 6),
                new Pair(8, 9) };

        // Find and print the maximum number of disjoint intervals
        maxDisjointIntervals(intervals);
    }
}

