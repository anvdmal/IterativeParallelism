package javaVersion;

import java.io.*;
import java.util.Random;

public class FileGenerator {
    public static final int FILES_NUMBER = 19;
    public static final int LINES_NUMBER = 1_000_000;
    public static final String FILE_PATH = "C:/Users/aleks/IdeaProjects/IterativeParallelism/src/main/resources/javaVersion/";
    public static final String FILE_NAME = "numbers";
    public static final String FILE_TYPE = ".txt";

    public static void main(String[] args) {
        generateFile(FILES_NUMBER, LINES_NUMBER);
    }

    private static void generateFile(int filesNumber, int linesNumber) {
        for (int i = 1; i <= filesNumber; i++) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH + FILE_NAME + i + FILE_TYPE))) {
                bw.write(generateNumbers(linesNumber));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String generateNumbers(int linesNumber) {
        Random random = new Random();
        StringBuilder nums = new StringBuilder();
        for (int i = 1; i <= linesNumber; i++) {
            nums.append(random.nextInt(21) - 10).append("\n");
        }
        return nums.toString();
    }
}