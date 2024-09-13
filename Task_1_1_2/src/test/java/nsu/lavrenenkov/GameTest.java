package nsu.lavrenenkov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
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
