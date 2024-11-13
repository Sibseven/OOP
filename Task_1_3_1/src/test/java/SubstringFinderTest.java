import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class for testing.
 */
public class SubstringFinderTest {
    ArrayList<Long> check = new ArrayList<>();

    @BeforeEach
    public void setUp() throws IOException {

        String filePath = "./src/test/resources/test.txt";
        String targetSubstring = "fileno1";
        long totalSizeInBytes = 16L * 1024L * 1024L * 1024L;
        File file = new File(filePath);
        file.createNewFile();


        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            long currentOffset = 0;
            StringBuilder sb = new StringBuilder();

            while (file.length() < totalSizeInBytes) {
                sb.append("Текст без подстроки.\n");
                sb.append("Другая строка.\n");
                currentOffset += 36;
                int rand = (int) (Math.random() * 100);

                if (rand == 1) {
                    currentOffset += 29;
                    sb.append("Немного текста с подстрокой: ").append(targetSubstring).append("\n");
                    check.add(currentOffset);
                    currentOffset += 1 + targetSubstring.length();
                }
                if (sb.length() > 1024 * 1024 * 10) {
                    writer.write(sb.toString());
                    sb.setLength(0);
                }
            }
            if (!sb.isEmpty()) {
                writer.write(sb.toString());
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
            result = (ArrayList<Long>) substringFinder.find("./src/test/resources/test.txt", "fileno1");
        }
        catch (Exception e) {
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
