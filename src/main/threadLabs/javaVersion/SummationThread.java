package javaVersion;

import javaVersion.FileGenerator;
import javaVersion.SummationCallback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SummationThread extends Thread {

    private long startFrom, endTo;

    private SummationCallback callback;

    public SummationThread(long startFrom, long endTo, SummationCallback callback) {
        this.startFrom = startFrom;
        this.endTo = endTo;
        this.callback = callback;
    }

    @Override
    public void run() {
        long partOfSum = 0;
        for (long i = startFrom + 1; i <= endTo; i++) {
            try (BufferedReader br = new BufferedReader(new FileReader(FileGenerator.FILE_PATH + FileGenerator.FILE_NAME + i + FileGenerator.FILE_TYPE))) {
                String line;

                while ((line = br.readLine()) != null) {
                    partOfSum += Integer.parseInt(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        callback.call(partOfSum);
    }
}