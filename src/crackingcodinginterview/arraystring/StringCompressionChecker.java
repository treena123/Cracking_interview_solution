package crackingcodinginterview.arraystring;

import java.util.HashMap;

/**
 * @author trinapal
 */
public class StringCompressionChecker {

    public static String compressingString(String input){
        StringBuilder stringbuilder = new StringBuilder();
        /*HashMap<Character, Integer> countChar = new HashMap<>();
        for(char c: input.toLowerCase().toCharArray()){
            countChar.put(c, countChar.getOrDefault(c, 0)+1);
        }


        countChar.entrySet().stream().forEach(each -> stringbuilder.append(each.getKey()).append(each.getValue())); */
        if (input == null || input.isEmpty()) return input;
        char first = input.charAt(0);
        int count = 1;
        for(int i =1; i<input.length(); i++){
            if(input.charAt(i) == first){
                //update count
                count++;
            }
            else{
                stringbuilder.append(first).append(count);
                first = input.charAt(i);
                count = 1;
            }
        }
        stringbuilder.append(first).append(count);
        return stringbuilder.toString().length() >= input.length()? input : stringbuilder.toString();

    }
}
