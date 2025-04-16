package crackingcodinginterview.arraystring;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author trinapal
 */public class UniqueCharacterStringTest {

     @Test
     public void valid_unique_string_test(){
         String input = "Lucky Sam";
         assertTrue(UniqueCharacterString.isUnique(input));
     }

    @Test
    public void invalid_unique_string_test(){
        String input = "Good Luck Lucky";
        assertFalse(UniqueCharacterString.isUnique(input));
    }
}
