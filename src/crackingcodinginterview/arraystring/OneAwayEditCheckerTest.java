package crackingcodinginterview.arraystring;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author trinapal
 */
public class OneAwayEditCheckerTest {

    @Test
    public void valid_oneWayEdit_test(){
        String target = "Pale";
        String input = "ple";
        assertTrue(OneAwayEditChecker.checkOneEditAway(target,input));
    }

    @Test
    public void invalid_oneWayEdit_test(){
        String target = "Pales";
        String input = "ple";
        assertFalse(OneAwayEditChecker.checkOneEditAway(target,input));
    }
}
