import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {

    public static boolean isFileExists(String filePath) {
        Path path = Path.of(filePath);
        if (Files.exists(path)) {
            return true;
        } else return false;
    }

    public static boolean isValidKey(String key) {
        try {
            int intKey = Integer.parseInt(key);
            if (intKey < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
