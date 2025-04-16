package bitmanipulation;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author trinapal
 */
public class PowerOf2CheckerTest {

    @Test
    public void valid_power_of_2_number(){
        assertTrue(PowerOf2Checker.checkPowOf2(8));
    }

    @Test
    public void invalid_power_of_2_number(){
        assertFalse(PowerOf2Checker.checkPowOf2(10));
    }
}
