import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SubstringFinderTest {
    ArrayList<Long> check = new ArrayList<>();

    @BeforeEach
    public void setUp() throws IOException {

        String filePath = "./src/test/resources/test.txt";
        String targetSubstring = "fileno1";
        long totalSizeInBytes = 1L * 1024L * 1024L * 1024L;
        File file = new File(filePath);
        file.deleteOnExit();
        file.createNewFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            long bytesWritten = 0;
            long offset = 0;

            while (file.length() < totalSizeInBytes) {
                writer.append("Текст без подстроки.\n");
                writer.append("Другая строка.\n");
                bytesWritten += 21 + 15;
                int rand = (int) (Math.random() * 10000);
                if(rand == 1) {
                    writer.append("Немного текста с подстрокой: " + targetSubstring + "\n");
                    check.add(bytesWritten + 29);
                    bytesWritten += 29 + targetSubstring.length() + 1;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void findSubstring() {
        SubstringFinder substringFinder = new SubstringFinder();
        ArrayList<Long> result = new ArrayList<>();
        try {
            result = substringFinder.find("./src/test/resources/test.txt", "fileno1");
        }
        catch (Exception e) {
            assert false;
        }
        assertEquals(check, result);
    }

    @AfterAll
    public static void tearDown() {
        File file = new File("./src/test/resources/test.txt");
        file.delete();
    }
}
