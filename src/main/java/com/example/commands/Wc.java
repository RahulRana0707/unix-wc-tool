package com.example.commands;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import com.beust.jcommander.Parameter;

interface WcManager {
}

public class Wc implements WcManager {

    final static Charset ENCODING = StandardCharsets.UTF_8;

    @Parameter(names = { "-c", "--count" }, description = "Print the number of bytes in the file")
    private boolean countBytesFlag = false;

    @Parameter(names = { "-l", "--lines" }, description = "Print the number of lines in the file")
    private boolean countLinesFlag = false;

    @Parameter(names = { "-w", "--words" }, description = "Print the number of words in the file")
    private boolean countWordsFlag = false;

    @Parameter(description = "File to process", required = true)
    private String fileName;

    public void execute() {

        if (countBytesFlag) {
            long bytes = this.countBytes(fileName);
            System.out.println(bytes + " " + fileName);
        }
        if (countLinesFlag) {
            long lines = this.countLines(fileName);
            System.out.println(lines + " " + fileName);
        }
        if (countWordsFlag) {
            long words = this.countWords(fileName);
            System.out.println(words + " " + fileName);
        }

        if (!countBytesFlag && !countLinesFlag && !countWordsFlag) {
            long bytes = this.countBytes(fileName);
            long lines = this.countLines(fileName);
            long words = this.countWords(fileName);

            System.out.println(bytes + " " + lines + " " + words + " " + fileName);
        }
    }

    private long countWords(String fileName) {
        Path path = Paths.get(fileName);
        long words = 0;
        try (Scanner scanner = new Scanner(path, ENCODING.name())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim().replaceAll("\\s+", " ");
                if (!line.isEmpty()) {
                    words += line.split(" ").length;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return words;
    }

    private long countLines(String fileName) {
        Path path = Paths.get(fileName);
        long lines = 0;
        try (Scanner scanner = new Scanner(path, ENCODING.name())) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lines++;
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return lines;
    }

    private long countBytes(String fileName) {
        // A single character eg "A" or "z" is a byte
        Path path = Paths.get(fileName);
        long bytes = 0;
        try (Scanner scanner = new Scanner(path, ENCODING.name())) {
            while (scanner.hasNextLine()) {
                bytes += scanner.nextLine().getBytes(ENCODING).length;
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return bytes;
    }

}
