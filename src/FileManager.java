import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class FileManager {

    public String readFile(String filePath) {
        Path path = Path.of(filePath);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println(Texts.ERROR_FILE_READ);
        }
        return null;
    }

    public boolean writeFile(String content, String filePath) {
        Path path = Path.of(filePath);
        try {
            Files.writeString(path, content, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE_NEW);
            return true;
        } catch (IOException e) {
            System.out.println(Texts.ERROR_FILE_WRITE);
        }
        return false;
    }

}
