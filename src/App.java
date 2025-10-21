import java.nio.file.Path;
import java.util.Scanner;

public class App {

    private static final Cipher cipher = new Cipher();
    private static final FileManager fileManager = new FileManager();
    private static final Scanner console = new Scanner(System.in);
    private static String inputFilePath;
    private static String outputFilePath;
    private static int key;


    public static void main(String[] args) {

        int menuItemChoice;
        String tempMenuItemChoice;

        System.out.println(Texts.WELCOME);
        do {
            System.out.println(Texts.MAIN_MENU);
            tempMenuItemChoice = console.nextLine();
            try {
                menuItemChoice = Integer.parseInt(tempMenuItemChoice);
            } catch (NumberFormatException e) {
                menuItemChoice = -1;
            }
            switch (menuItemChoice) {
                case 1 -> encryptFile();
                case 2 -> decryptFile();
                case 3 -> bruteForce();
                case 4 -> System.out.println(Texts.EXIT);
                default -> System.out.println(Texts.ERROR_CHOICE);
            }
        } while (menuItemChoice != 4);
    }

    private static void encryptFile() {
        inputFilePath = requestInputFilePath();
        outputFilePath = generateOutputFilePath(inputFilePath, "_encrypted");
        key = requestKey();
        System.out.println(Texts.ENCRYPT_GO);
        String inputText = fileManager.readFile(inputFilePath);
        if (inputText != null && !inputText.isEmpty()) {
            if (fileManager.writeFile(cipher.encrypt(inputText, key), outputFilePath)) {
                System.out.println(Texts.ENCRYPT_DONE + Path.of(outputFilePath).getFileName());
            }
        }
    }

    private static void decryptFile() {
        inputFilePath = requestInputFilePath();
        outputFilePath = generateOutputFilePath(inputFilePath, "_decrypted");
        key = requestKey();
        System.out.println(Texts.DECRYPT_GO);
        String inputText = fileManager.readFile(inputFilePath);
        if (inputText != null && !inputText.isEmpty()) {
            if (fileManager.writeFile(cipher.decrypt(inputText, key), outputFilePath)) {
                System.out.println(Texts.DECRYPT_DONE + Path.of(outputFilePath).getFileName());
            }
        }
    }

    private static void bruteForce() {
        inputFilePath = requestInputFilePath();
        System.out.println(Texts.BRUTEFORCE_GO);
        int countWritedFiles = 0;
        for (int i = 0; i < cipher.ALPHABET_SIZE; i++) {
            outputFilePath = generateOutputFilePath(inputFilePath, "_decrypted_key" + i);
            String inputText = fileManager.readFile(inputFilePath);
            if (inputText != null && !inputText.isEmpty()) {
                if (fileManager.writeFile(cipher.decrypt(inputText, i), outputFilePath)) {
                    countWritedFiles++;
                }
            }
        }
        if (countWritedFiles == cipher.ALPHABET_SIZE) {
            System.out.println(Texts.BRUTEFORCE_DONE);
        }
    }

    private static String requestInputFilePath() {
        System.out.println(Texts.REQUEST_FILE_PATH);
        String filePath = console.nextLine();
        while (!Validator.isFileExists(filePath)) {
            System.out.println(Texts.ERROR_FILE_NOT_EXIST);
            filePath = console.nextLine();
        }
        return filePath;
    }

    private static String generateOutputFilePath(String inputFilePath, String suffix) {
        int indexOfLastDot = inputFilePath.lastIndexOf('.');
        return inputFilePath.substring(0, indexOfLastDot) + suffix + inputFilePath.substring(indexOfLastDot);
    }

    private static int requestKey() {
        System.out.println(Texts.REQUEST_KEY);
        String tempKey = console.nextLine();
        while (!Validator.isValidKey(tempKey)) {
            System.out.println(Texts.ERROR_KEY);
            tempKey = console.nextLine();
        }
        return Integer.parseInt(tempKey);
    }
}
