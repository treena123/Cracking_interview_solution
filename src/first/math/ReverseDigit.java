package first.math;

/**
 * @author trinapal
 */
public class ReverseDigit {
    public static void main(String[] args) {
    System.out.println(reverse(123));
    }

    public static int reverse(int x) {
        int reverseNumber = 0;
        if(x == 0){
            return 0;
        }
        while(x != 0){
            int rem = x%10;
            if(reverseNumber < Integer.MIN_VALUE /10 || (reverseNumber == Integer.MIN_VALUE /10 && rem < -8 )){
                return 0;
            }
            if(reverseNumber > Integer.MAX_VALUE /10 || (reverseNumber == Integer.MAX_VALUE /10 && rem > 7 )){
                return 0;
            }
            reverseNumber = reverseNumber*10 + rem;
            x = x/10;
        }

        return reverseNumber;

    }
}
