package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (Reader reader = new FileReader(file)) {
            int data;
            while ((data = reader.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }
}