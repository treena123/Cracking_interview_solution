package first.dp;

import java.util.Arrays;

/**
 * @author trinapal
 */
public class CircularHouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 3, 2}));
    }

    public static int rob(int [] nums){
        // two options - start with 1st house and finish at n-2
        // start with 2nd house and finish at n
        //then compare
        //case 1
        int oddHouse = robInLine(Arrays.copyOfRange(nums, 0, nums.length-1));
        //case 2
        int evenHouse = robInLine(Arrays.copyOfRange(nums,1,nums.length));
        return Math.max(oddHouse,evenHouse);
    }
    static int robInLine(int [] nums){
        int [] dp = new int[nums.length+1];
        if(nums.length == 1){
            return nums[0];
        }
        dp[0] = nums[0];
        dp[1] = nums[1];

        for(int i=2; i<nums.length; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }
}
