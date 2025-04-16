package bitmanipulation;

/**
 * @author trinapal
 */
public class PowerOf2Checker {

    public static boolean checkPowOf2(int number){
        //set a bit
        /*
        for power of 2, it has only one bit set to 1
         */
        if(number == 0){
            return false;
        }
        return (number & (number-1)) == 0 ;

    }
}
