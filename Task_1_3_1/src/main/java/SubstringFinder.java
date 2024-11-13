import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for funding indexes of substring in a string.
 */
public class SubstringFinder {
    private static int  BUFFER_SIZE = 1200000;
    private static int PRIME = 101;

    /**
     * Method to decode first n UTF-8 chars from byte array.
     *
     * @param bytes - byte arr
     *
     * @param n - number of chars to decode
     *
     * @return - String and how many bytes in it
     *
     * @throws CharacterCodingException - if at least one char is not in UTF-8
     */
    private static Result decodeNcharacters(byte[] bytes, int n) throws CharacterCodingException {
        CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        CharBuffer charBuffer = CharBuffer.allocate(n);

        int bytesUsed = 0;
        int charsRead = 0;

        while (charsRead < n && byteBuffer.hasRemaining()) {
            // Сохраняем позицию байтбуфера перед декодированием
            bytesUsed -= byteBuffer.position();
            CoderResult result = decoder.decode(byteBuffer, charBuffer, false);

            // Проверка на недопустимую последовательность байтов или ошибку декодирования
            if (result.isError()) {
                result.throwException();
            }

            // Считаем число успешно декодированных символов
            charBuffer.flip();
            while (charBuffer.hasRemaining() && charsRead < n) {
                charBuffer.get();
                charsRead++;
            }
            charBuffer.clear();

            // Подсчитываем количество использованных байтов
            bytesUsed += byteBuffer.position();

            // Останавливаем, если достигли n символов
            if (charsRead == n){
                break;
            }
        }

        // Возвращаем результат как строку и количество использованных байтов
        return new Result(charBuffer.toString(), bytesUsed);
    }

    private static record Result(String str, int bytesUsed){

    }

    /**
     * Method to perform Robin-Carp algorithm.
     *
     * @param text - text to find in
     *
     * @param substring - substring
     *
     * @return - list of indexes from which pattern is matched
     */
    private List<Long> robin(String text, String substring) {
        List<Long> result = new ArrayList<>();
        int textLength = text.length();
        int substringLength = substring.length();

        if (substringLength > textLength) {
            return result;
        }

        long substringHash = calculateHash(substring, substringLength);
        long textHash = calculateHash(text, substringLength);

        for (int i = 0; i <= textLength - substringLength; i++) {
            if (substringHash == textHash && text.regionMatches(i, substring, 0, substringLength)) {
                result.add((long) i);
            }

            if (i < textLength - substringLength) {
                textHash = recalculateHash(text, textHash, i, i + substringLength, substringLength);
            }
        }

        return result;
    }

    /**
     * Calculates hash of string for Robin Carp algorithm.
     *
     * @param str - string of which hash is calculated
     *
     * @param length - of a string
     *
     * @return hash
     */
    private long calculateHash(String str, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += (long) (str.charAt(i) * Math.pow(PRIME, i));
        }
        return hash;
    }

    /**
     * Method for recalculating hash of string of robin carp algorithm.
     *
     * @param text - text to get new chars from
     *
     * @param oldHash - old hash value
     *
     * @param oldIndex - index of first char of substring
     *
     * @param newIndex - index of new added char to substring
     *
     * @param length - of a substring
     *
     * @return - new hash value
     */
    private long recalculateHash(String text, long oldHash, int oldIndex,
                                              int newIndex, int length) {
        long newHash = oldHash - text.charAt(oldIndex);
        newHash /= PRIME;
        newHash += (long) (text.charAt(newIndex) * Math.pow(PRIME, length - 1));
        return newHash;
    }


    /**
     * Method to perform search.
     *
     * @param fileName - file to search pattern in
     *
     * @param substring - pattern to find
     *
     * @return - list of occurrences indexes of substring in fileName file
     *
     */
    public ArrayList<Long> find(String fileName, String substring) {
        ArrayList<Long> result = new ArrayList<>();
        Path path = Paths.get(fileName);
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            long fileSize = Files.size(path);
            long byteOffset = 0;
            long indexOffset = 0;
            while (byteOffset < fileSize) {
                long remaining = fileSize - byteOffset;
                int size = (int) Math.min(BUFFER_SIZE * 4 + substring.length() * 4, remaining);
                MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,
                                                          byteOffset, size);
                byte[] bytes = new byte[size];
                buffer.get(bytes);
                Result res = decodeNcharacters(bytes, BUFFER_SIZE + substring.length());
                String str = res.str;
                ArrayList<Long> intermediate = (ArrayList<Long>) robin(str, substring);
                for (Long now : intermediate) {
                    result.add(now + indexOffset);
                }
                indexOffset += str.length() - substring.length() + 1;
                byteOffset += res.bytesUsed - res.str.substring(BUFFER_SIZE + 1).getBytes().length;
                if (size == remaining) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return result;
    }
}




