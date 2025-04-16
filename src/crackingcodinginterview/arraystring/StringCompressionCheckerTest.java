package crackingcodinginterview.arraystring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author trinapal
 */
public class StringCompressionCheckerTest {

    @Test
    public void valid_string_compressed_test(){
      String input = "aabcccccaaa";
      String output = "a2b1c5a3";
      assertEquals(output, StringCompressionChecker.compressingString(input));
    }
}
