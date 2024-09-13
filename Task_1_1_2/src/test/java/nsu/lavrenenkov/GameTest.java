package nsu.lavrenenkov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.ByteArrayInputStream;

/**
 * Class for testing Game class.
 */
public class GameTest {

    @Test
    void checkStartGame() {
        String input = "1\n 0\n 0\n 0\n 0\n 0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Game.main(new String[]{});
        assertTrue(true);
    }
}
