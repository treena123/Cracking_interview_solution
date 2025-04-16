package bitmanipulation;

/**
 * @author trinapal
 */
public class SingleUniqueElementChecker {

    public static int checkUniqueElement(int [] inputArray){
        int result = 0;
        for(int num : inputArray){
            result ^= num;
        }
        return result;
    }
}
