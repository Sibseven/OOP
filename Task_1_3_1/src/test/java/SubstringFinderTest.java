import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 * Class for testing.
 */
public class SubstringFinderTest {
    static ArrayList<Long> check = new ArrayList<>();


    /**
     * creates a big test.
     *
     * @throws IOException - if file don`t exist.
     */
    @BeforeAll
    public static void setUp() throws IOException {

        String filePath = "./src/test/resources/test.txt";
        String targetSubstring = "fileno1";
        long totalSizeInBytes = 8L * 1024L * 1024L * 1024L;
        File file = new File(filePath);
        file.createNewFile();
        file.deleteOnExit();


        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            long currentOffset = 0;
            StringBuilder sb = new StringBuilder();

            while (file.length() < totalSizeInBytes) {
                writer.write("Текст без подстроки.\nДругая строка.\n");
                currentOffset += 36;
                int rand = (int) (Math.random() * 1000000);

                if (rand == 1) {
                    currentOffset += 29;
                    writer.write("Немного текста с подстрокой: ");
                    writer.write(targetSubstring);
                    writer.write("\n");
                    check.add(currentOffset);
                    currentOffset += 1 + targetSubstring.length();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void findSubstringBig() {
        SubstringFinder substringFinder = new SubstringFinder();
        ArrayList<Long> result = new ArrayList<>();
        try {
            result = substringFinder.find("./src/test/resources/test.txt", "fileno1");
        } catch (Exception e) {
            System.out.println(e);
            assert false;
        }
        assertEquals(check, result);
    }

    @Test
    public void findSubstring2() throws IOException {
        SubstringFinder substringFinder = new SubstringFinder();
        ArrayList<Long> result = new ArrayList<>();
        ArrayList<Long> check = new ArrayList<>();
        check.add(3L);
        result = (ArrayList<Long>) substringFinder.find("./src/test/resources/tex1", "имТ");
        assertEquals(check, result);
    }

    @AfterAll
    public static void tearDown() {
        File file = new File("./src/test/resources/test.txt");
        file.delete();
    }
}
