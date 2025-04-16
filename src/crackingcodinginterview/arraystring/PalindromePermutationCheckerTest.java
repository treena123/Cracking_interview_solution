package crackingcodinginterview.arraystring;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author trinapal
 */
public class PalindromePermutationCheckerTest {

    @Test
    public void valid_permutation_palindrome_test(){
        String input = "Tact coa";
        assertTrue(PalindromePermutationChecker.isPalindromePermutation(input));
    }
}
