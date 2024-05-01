package ru.job4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SaveFile {

    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
}
