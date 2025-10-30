import constants.AppConstants;
import service.CipherService;
import util.FileManager;
import util.Validator;

import java.nio.file.Path;
import java.util.Scanner;

public class App {
    private static final CipherService CIPHER_SERVICE = new CipherService();
    private static final FileManager fileManager = new FileManager();
    private static final Scanner console = new Scanner(System.in);
    private static String inputFilePath;
    private static String outputFilePath;
    private static int key;


    public static void main(String[] args) {
        int menuItemChoice;
        String tempMenuItemChoice;

        System.out.println(AppConstants.WELCOME);
        do {
            System.out.println(AppConstants.MAIN_MENU);
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
                case 4 -> System.out.println(AppConstants.EXIT);
                default -> System.out.println(AppConstants.ERROR_CHOICE);
            }
        } while (menuItemChoice != 4);
    }

    private static void encryptFile() {
        inputFilePath = requestInputFilePath();
        outputFilePath = generateOutputFilePath(inputFilePath, "_encrypted");
        key = requestKey();
        System.out.println(AppConstants.ENCRYPT_GO);
        String inputText = fileManager.readFile(inputFilePath);
        if (inputText != null && !inputText.isEmpty()) {
            if (fileManager.writeFile(CIPHER_SERVICE.encrypt(inputText, key), outputFilePath)) {
                System.out.println(AppConstants.ENCRYPT_DONE + Path.of(outputFilePath).getFileName());
            }
        }
    }

    private static void decryptFile() {
        inputFilePath = requestInputFilePath();
        outputFilePath = generateOutputFilePath(inputFilePath, "_decrypted");
        key = requestKey();
        System.out.println(AppConstants.DECRYPT_GO);
        String inputText = fileManager.readFile(inputFilePath);
        if (inputText != null && !inputText.isEmpty()) {
            if (fileManager.writeFile(CIPHER_SERVICE.decrypt(inputText, key), outputFilePath)) {
                System.out.println(AppConstants.DECRYPT_DONE + Path.of(outputFilePath).getFileName());
            }
        }
    }

    private static void bruteForce() {
        inputFilePath = requestInputFilePath();
        System.out.println(AppConstants.BRUTEFORCE_GO);
        int countWritedFiles = 0;
        for (int i = 1; i <= CIPHER_SERVICE.ALPHABET_SIZE; i++) {
            outputFilePath = generateOutputFilePath(inputFilePath, "_decrypted_key" + i);
            String inputText = fileManager.readFile(inputFilePath);
            if (inputText != null && !inputText.isEmpty()) {
                if (fileManager.writeFile(CIPHER_SERVICE.decrypt(inputText, i), outputFilePath)) {
                    countWritedFiles++;
                }
            }
        }
        if (countWritedFiles == CIPHER_SERVICE.ALPHABET_SIZE) {
            System.out.println(AppConstants.BRUTEFORCE_DONE);
        }
    }

    private static String requestInputFilePath() {
        System.out.println(AppConstants.REQUEST_FILE_PATH);
        String filePath = console.nextLine();
        while (!Validator.isFileExists(filePath)) {
            System.out.println(AppConstants.ERROR_FILE_NOT_EXIST);
            filePath = console.nextLine();
        }
        return filePath;
    }

    private static String generateOutputFilePath(String inputFilePath, String suffix) {
        int indexOfLastDot = inputFilePath.lastIndexOf('.');
        return inputFilePath.substring(0, indexOfLastDot) + suffix + inputFilePath.substring(indexOfLastDot);
    }

    private static int requestKey() {
        System.out.println(AppConstants.REQUEST_KEY);
        String tempKey = console.nextLine();
        while (!Validator.isValidKey(tempKey)) {
            System.out.println(AppConstants.ERROR_KEY);
            tempKey = console.nextLine();
        }
        return Integer.parseInt(tempKey);
    }
}
