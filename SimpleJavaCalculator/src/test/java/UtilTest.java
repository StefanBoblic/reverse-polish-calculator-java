import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void shouldCalculateCorrectWhenAdded() throws Exception {
        Util util = new Util();
        assertEquals(util.calculate("3 1 +"), Double.valueOf(4));
    }

    @Test
    public void shouldCalculateCorrectWhenMultiplied() throws Exception {
        Util util = new Util();
        assertEquals(util.calculate("3 1 *"), Double.valueOf(3));
    }

    @Test
    public void correctExecutionOfMultipleOperations() throws Exception {
        Util util = new Util();
        assertEquals(util.calculate("1 2 6 - 4 / +"), Double.valueOf(0));
    }

    @Test
    public void invalidOperationException() throws Exception {
        Util util = new Util();
        Throwable exception = assertThrows(Exception.class, () -> util.calculate("1 + 1"));
        assertEquals("Недопустимый символ в выражении", exception.getMessage());
    }
}