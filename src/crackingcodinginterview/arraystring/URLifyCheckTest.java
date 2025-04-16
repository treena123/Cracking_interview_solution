package crackingcodinginterview.arraystring;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author trinapal
 */
public class URLifyCheckTest {

    @Test
    public void convert_urlify(){
        String input = "  Mr John Smith   ";
        String expectedPutput = "Mr%20John%20Smith";
        assertEquals(expectedPutput, URLifyCheck.occupySpaces(input));
    }

    @Test
    public void convert_urlify_in_place(){
        String input = "Mr John Smith    ";
        int trueLength = 13;
        String expectedPutput = "Mr%20John%20Smith";
        assertEquals(expectedPutput, URLifyCheck.modifiedOccupySpaces(input,trueLength));
    }
}
