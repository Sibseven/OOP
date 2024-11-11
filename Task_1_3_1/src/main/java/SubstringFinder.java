import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class SubstringFinder {
    private static int  BUFFER_SIZE = 1000000;

//    public ArrayList<Integer> find(String fileName, String substring) throws IOException {
//        ArrayList<Long> result = new ArrayList<>();
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        char[] buffer = new char[BUFFER_SIZE];
//        long offset = 0;
//        long numCharsRead = br.read(buffer, offset, BUFFER_SIZE + substring.length());
//
//        while (numCharsRead > 0) {
//            int chunkOffset = 0;
//            String str = new String(buffer, offset, numCharsRead);
//            int index = str.indexOf(substring, chunkOffset);
//            while (index != -1) {
//                result.add(index + offset);
//                chunkOffset += substring.length();
//                index = str.indexOf(substring, chunkOffset);
//            }
//            offset += BUFFER_SIZE;
//            numCharsRead = br.read(buffer, offset, BUFFER_SIZE + substring.length());
//        }
//        return result;
//    }
//
    public ArrayList<Long> find(String fileName, String substring) throws IOException {
        ArrayList<Long> result = new ArrayList<>();
        Path path = Paths.get(fileName);
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            long fileSize = Files.size(path);
            long position = 0;
            while (position < fileSize) {
                long remaining = fileSize - position;
                int size = (int) Math.min(BUFFER_SIZE + substring.length(), remaining);
                MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, position, size);
                byte[] bytes = new byte[size];
                buffer.get(bytes);

                String str = new String(bytes, StandardCharsets.UTF_8);
                int index = str.indexOf(substring);
                while (index != -1) {
                    result.add(position + index);
                    index = str.indexOf(substring, index + 1); // continue searching after current match
                }

                position += size;
            }
        }
        return result;
    }
}

