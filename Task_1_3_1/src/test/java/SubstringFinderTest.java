import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 * Class for testing.
 */
public class SubstringFinderTest {
    static ArrayList<Long> check = new ArrayList<>();
    static File bigFile;


    /**
     * creates a big test.
     *
     * @throws IOException - if file don`t exist.
     */
    @BeforeAll
    public static void setUp() throws IOException {

        String targetSubstring = "fileno1";
        long totalSizeInBytes = 8L * 1024L * 1024L * 1024L;
        Path resourcesPath = Paths.get("src", "test", "resources");
        File resourcesDir = resourcesPath.toFile();
        if (!resourcesDir.exists() && !resourcesDir.mkdirs()) {
            throw new IOException("Failed to create resources directory");
        }

        // Создаём файл test.txt в папке ресурсов
        bigFile = new File(resourcesDir, "test.txt");
        if (!bigFile.exists() && !bigFile.createNewFile()) {
            throw new IOException("Failed to create test file");
        }
        bigFile.deleteOnExit();

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(bigFile), StandardCharsets.UTF_8))) {
            long currentOffset = 0;

            while (bigFile.length() < totalSizeInBytes) {
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
        System.out.println("Created File");
    }

    @Test
    public void findSubstringBig() {
        SubstringFinder substringFinder = new SubstringFinder();
        List<Long> result = new ArrayList<>();
        try {
            result = substringFinder.find(bigFile.toString(), "fileno1");
        } catch (Exception e) {
            System.out.println(e);
            assert false;
        }
        assertEquals(check, result);
        System.out.println("bigTest done");
    }

    @Test
    public void findSubstring2() throws URISyntaxException {
        SubstringFinder substringFinder = new SubstringFinder();
        List<Long> result;
        List<Long> check = new ArrayList<>();
        check.add(3L);
        File resource = new File(getClass().getClassLoader().getResource("tex1").toURI());
        result = substringFinder.find(resource.toString(), "имТ");
        assertEquals(check, result);
        System.out.println("simpletest done");
    }

    @Test
    public void chinese() throws URISyntaxException {
        SubstringFinder substringFinder = new SubstringFinder();
        List<Long> result;
        List<Long> check = new ArrayList<Long>();
        check.add(34L);
        File resource = new File(getClass().getClassLoader().getResource("china").toURI());
        result = substringFinder.find(resource.toString(), "你好世界");
        assertEquals(check, result);
        System.out.println("Chinatest done");
    }

    @Test
    public void checkStringOnJointOfBuffer() throws URISyntaxException {
        SubstringFinder substringFinder = new SubstringFinder();
        substringFinder.setBufferSize(9);
        List<Long> result;
        List<Long> check = new ArrayList<>();
        check.add(12L);
        File resourse = new File(getClass().getClassLoader().getResource("joint").toURI());
        result = substringFinder.find(resourse.toString(), "String");
        assertEquals(check, result);
        System.out.println("jointTestDone");
    }
}
