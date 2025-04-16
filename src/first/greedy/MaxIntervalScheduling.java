package first.greedy;

import java.util.Arrays;

/**
 * @author trinapal
 */
public class MaxIntervalScheduling {
    public static void main(String[] args) {
        System.out.println(nonOverlappingInterval(new int [][] {{1, 2},{2, 3}, {3, 4}, {1, 3}}));
    }

    private static int nonOverlappingInterval(int[][] nums) {
        //sort the interval by end time
        Arrays.sort(nums, (a, b) -> Integer.compare(a[1],b[1]));
        int count =0;
        int currentTime =0;
        for(int [] num: nums){
            if(currentTime <= num[0]){
                currentTime = num[1];  // Update the current time to the end of this interval
                count++;
            }
        }
        return count;
    }
}
