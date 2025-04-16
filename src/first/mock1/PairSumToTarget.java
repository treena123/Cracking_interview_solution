package first.mock1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author trinapal
 */
public class PairSumToTarget {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(findPairs(nums,target)));
    }

    private static int[] findPairs(int[] nums, int target) {
        HashMap<Integer,Integer> recordVal = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            int rem = target - nums[i];

            if(recordVal.containsKey(rem)){
                return new int[]{rem, nums[i]};
            } else{
                recordVal.put(nums[i], i);
            }
        }
        return new int[]{-1,-1};
    }
}
