package service;

import java.util.ArrayList;
import java.util.Arrays;

public class CipherService {
    public static final int ALPHABET_SIZE = 41;
    private static final ArrayList<Character> ALPHABET = new ArrayList<>(ALPHABET_SIZE);

    public CipherService() {
        initializeAlphabet(ALPHABET);
    }

    private void initializeAlphabet(ArrayList<Character> alphabet) {
        for (int i = 0; i < 32; i++) {
            alphabet.add((char) ('а' + i));
        }
        alphabet.add(6, 'ё');
        alphabet.addAll(Arrays.asList('.', ',', '"', ':', '-', '!', '?', ' '));
    }

    public String encrypt(String text, int key) {
        if (key == 0) {
            return text;
        } else {
            StringBuilder encryptedText = new StringBuilder(text.length());
            for (int i = 0; i < text.length(); i++) {
                char currentChar = text.toLowerCase().charAt(i);
                if (ALPHABET.contains(currentChar)) {
                    int currentCharIndex = ALPHABET.indexOf(currentChar);
                    int encryptedCharIndex = currentCharIndex + key % ALPHABET_SIZE;
                    if (encryptedCharIndex >= ALPHABET_SIZE) {
                        encryptedCharIndex -= ALPHABET_SIZE;
                    } else if (encryptedCharIndex < 0) {
                        encryptedCharIndex += ALPHABET_SIZE;
                    }
                    encryptedText.append(ALPHABET.get(encryptedCharIndex));
//                    encryptedText.append(ALPHABET.get(currentCharIndex + key % ALPHABET_SIZE +
//                            (currentCharIndex + key % ALPHABET_SIZE) >= ALPHABET_SIZE ? (0 - ALPHABET_SIZE) :
//                            (currentCharIndex + key % ALPHABET_SIZE) < 0 ? ALPHABET_SIZE : 0));
                } else {
                    encryptedText.append(currentChar);
                }
            }
            return encryptedText.toString();
        }
    }

    public String decrypt(String text, int key) {
        return encrypt(text, -key);
    }
}
