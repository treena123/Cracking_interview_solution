package crackingcodinginterview.arraystring;

import java.util.HashMap;

/**
 * @author trinapal
 */
public class OneAwayEditChecker {

    public static boolean checkOneEditAway(String target, String input){
        //handle the edge case where the input is larger or smaller than 1 character
        if(Math.abs(target.length() - input.length()) >1){
            return false;
        }

        // Ensure input is the shorter or equal string
        if (input.length() > target.length()) {
            return checkOneEditAway(input, target);
        }
        boolean foundDifference = false;
        int index1 = 0, index2 = 0;

        while (index1 < target.length() && index2 < input.length()) {
            if (target.charAt(index1) != input.charAt(index2)) {
                if (foundDifference) return false;
                foundDifference = true;

                if (target.length() == input.length()) {
                    // replacement case
                    index1++;
                }
                // else, it's an insert or delete, so skip only index2
            } else {
                index1++; // same characters, move both
            }
            index2++;
        }

        return true;
    }
}
