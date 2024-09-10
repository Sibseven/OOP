import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * Tests for Heapsort.java.
 *
 *  @author vadim lavrenenkov
 *
 *  @version 1.0
 */
public class HeapsortTest {
    @Test
    void checkMain() {
        Heapsort.main(new String[]{});
        assertTrue(true);
    }

    @Test
    void checkBasic() {
        int[] toSort = {5, 8, 9, 6, 7};
        Heapsort.sort(toSort);
        assertArrayEquals(toSort, new int[]{5, 6, 7, 8, 9});
    }

    @Test
    void checkEmpty() {
        int[] toSort = {};
        Heapsort.sort(toSort);
        assertArrayEquals(toSort, new int[]{});
    }

    @Test
    void checkSingle() {
        int[] toSort = {99};
        Heapsort.sort(toSort);
        assertArrayEquals(toSort, new int[]{99});
    }

    @Test
    void checkLarge() {
        int[]  randomIntsArray = IntStream.generate(() -> new Random().nextInt(555555)).limit(1000000).toArray();
        int[]  randomIntsArray2 = randomIntsArray;
        Heapsort.sort(randomIntsArray);
        Arrays.sort(randomIntsArray2);
        assertArrayEquals(randomIntsArray, randomIntsArray2);
    }

    @Test
    void checkSorted() {
        int[] toSort = {1, 3, 5, 6, 7};
        Heapsort.sort(toSort);
        assertArrayEquals(toSort, new int[] {1, 3, 5, 6, 7});
    }

    @Test
    void checkShiftDown() {
        int[] toShift = {5, 3, 7};
        Heapsort.shiftdown(toShift, 3, 0);
        assertArrayEquals(toShift, new int[]{7, 3, 5});
    }
}
