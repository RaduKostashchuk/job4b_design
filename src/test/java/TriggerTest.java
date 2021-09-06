import org.junit.Test;

import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void someLogicTest() {
        Trigger trigger = new Trigger();
        int result = trigger.someLogic();
        int expected = 1;
        assertEquals(expected, result);
    }

}