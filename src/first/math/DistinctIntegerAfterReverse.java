package first.math;

import java.util.HashMap;

/**
 * @author trinapal
 */
public class DistinctIntegerAfterReverse {
    public static void main(String[] args) {
        countDistinctIntegers(new int[] {1,13,10,12,31});
    }
        public static int countDistinctIntegers(int[] nums) {
            // should I double the size of the array
            int [] modArray = new int[nums.length*2];
            int count =0;

            System.arraycopy(nums, 0, modArray, 0, nums.length);
            int j = modArray.length-nums.length;

            for(int num : nums){
                int reverseNumber = 0;
                //reverse digit
                while(num != 0){
                    int rem = num%10;

                    //check for overflow
                    if(reverseNumber > Integer.MAX_VALUE || reverseNumber == Integer.MAX_VALUE && rem > 7){
                        reverseNumber = 0;
                    }
                    //check for underflow
                    if(reverseNumber < Integer.MIN_VALUE || reverseNumber == Integer.MIN_VALUE && rem <-8){
                        reverseNumber = 0;
                    }
                    reverseNumber = reverseNumber*10 + rem;
                    num = num/10;

                }
                modArray[j] = reverseNumber;
                j++;
            }

            //distinct number
            HashMap<Integer,Integer> map = new HashMap<>();

            return map.size();
        }
    }

