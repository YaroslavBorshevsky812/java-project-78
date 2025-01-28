package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void checkTest() {
        String expectedResult = "123";
        String result = Main.doSomething(expectedResult);
        assertEquals(expectedResult, result);
    }
}
