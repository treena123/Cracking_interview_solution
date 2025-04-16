package crackingcodinginterview.arraystring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author trinapal
 */
public class PalindromePermutationChecker {

    public static boolean isPalindromePermutation(String input){
        HashMap<Character, Integer> charCount = new HashMap<>();
        char [] inputArray = input.toLowerCase().replaceAll("\\s", "").toCharArray();
        for(int i = 0; i < inputArray.length; i++){
            charCount.put(inputArray[i], charCount.getOrDefault(inputArray[i], 0) +1);
        }
        int oddCount = 0;
        int evenCount = 0;
        for(Map.Entry<Character,Integer>each: charCount.entrySet()){
            if(each.getValue() %2 == 0){
                evenCount++;
            }
            if(each.getValue()%2 != 0){
                oddCount++;
            }
        }
        if(oddCount <= 1){
            return true;
        }
        return false;
    }
}
