package bitmanipulation;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author trinapal
 */
public class SingleUniqueElementCheckerTest {

    @Test
    public void valid_Single_Unique_element_test(){
        int expected = 4;
        int [] arr = {1,4,2,1,2};
        assertEquals(expected, SingleUniqueElementChecker.checkUniqueElement(arr));
    }
}
