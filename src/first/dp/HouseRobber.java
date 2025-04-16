package first.dp;

/**
 * @author trinapal
 */
public class HouseRobber {
    public static void main(String[] args) {
        System.out.println(rob(new int[] {2, 7, 9, 3, 1}));
    }

    private static int rob(int[] nums) {
        //if there is only one house
        if(nums.length ==0){
            return 0;
        }
        //if there is only one house
        if(nums.length ==1){
            return nums[0];
        }
        int [] dp = new int[nums.length];
        //consider first 2 house
        dp[0] = nums[0];
        dp[1] = nums[1];
        // now we will check if we add the current house(i) money to the i-2,
        // will it be better or previous house money is more
        for(int i =2; i< nums.length; i++){
            dp[i] = Math.max(dp[i-1], nums[i] +dp[i-2]);
        }
        //the last dp element will contain answer
        return dp[nums.length-1];
    }
}
