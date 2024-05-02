package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        return getContentByPredicate(data -> true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContentByPredicate(data -> data < 0x80);
    }

    public String getContentByPredicate(Predicate<Integer> predicate) throws IOException {
        try (InputStream input = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = input.read()) != -1) {
                if (predicate.test(data)) {
                    output.append((char) data);
                }
            }
            return output.toString();
        }
    }
}