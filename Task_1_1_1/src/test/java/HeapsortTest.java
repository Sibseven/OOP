import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
public class HeapsortTest {
    @Test
    void checkBasic(){
        int[] toSort = {5, 8, 9, 6, 7};
        heapsort.sort(toSort);
        assertArrayEquals(toSort, new int[]{5, 6, 7, 8, 9});
    }
    @Test
    void checkEmpty(){
        int[] toSort = {};
        heapsort.sort(toSort);
        assertArrayEquals(toSort, new int[]{});
    }
    @Test
    void checkSingle(){
        int[] toSort = {99};
        heapsort.sort(toSort);
        assertArrayEquals(toSort, new int[]{99});
    }
    @Test
    void checkLarge(){
        int[]  randomIntsArray = IntStream.generate(() -> new Random().nextInt(555555)).limit(1000000).toArray();
        int[]  randomIntsArray2 = randomIntsArray;
        heapsort.sort(randomIntsArray);
        Arrays.sort(randomIntsArray2);
        assertArrayEquals(randomIntsArray, randomIntsArray2);
    }
    @Test
    void checkSorted(){
        int[] toSort = {1, 3, 5, 6, 7};
        heapsort.sort(toSort);
        assertArrayEquals(toSort, new int[] {1, 3, 5, 6, 7});
    }
}
