package crackingcodinginterview.arraystring;

import java.util.HashMap;

/**
 * @author trinapal
 */
public class UniqueCharacterString {
    /*
    cracking the interview
    Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
cannot use additional data structures? HashMap
     */


    public static boolean isUnique(String input){
        HashMap<Character, Integer> countMap = new HashMap<>();
        char[] charArray = input.toCharArray();
        for(char each: charArray){
            countMap.put(each, countMap.getOrDefault(each, 0)+1);
        }
        long commonCharCount = countMap.entrySet().stream().filter(each -> each.getValue() > 1).count();
        if(commonCharCount > 0){
            return false;
        }
        return true;
    }
}
