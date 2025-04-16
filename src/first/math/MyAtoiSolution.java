package first.math;

import java.util.Scanner;
/**
 * @author trinapal
 */
public class MyAtoiSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println(myAtoi(input));
    }

    public static int myAtoi(String s) {
        /*  1. checks if there is any leading white space
            2. check for positive opr negative
            3.ignore leading zeroes
            4. if start with non degit character return 0
        */
        // Step 1: Trim leading and trailing whitespaces
        s = s.trim();

        // Step 2: Handle empty string case
        if (s.isEmpty()) {
            return 0;
        }

        int result = 0;
        int sign = 1;
        int i = 0;

        // Step 3: Check if there's a sign ('+' or '-')
        if (s.charAt(i) == '-') {
            sign = -1;
            i++; // move to the next character
        } else if (s.charAt(i) == '+') {
            i++; // move to the next character
        }

        // Step 4: Process the digits and build the result
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0'; // Convert char to integer

            // Step 5: Check for overflow before updating the result
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        // Step 6: Apply the sign and return the result
        return result * sign;
    }
}
