package com.exqzore.xml_parser.reader;

import com.exqzore.xml_parser.exception.ReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    private static final String DEFAULT_PATH = "resources/default_db.txt";

    public String read(String path) throws ReaderException {
        boolean fileExists = Files.exists(Path.of(path));
        if (!fileExists) {
            path = DEFAULT_PATH;
            System.out.format("File \"%s\" was not found, default will be used\n", path); //TODO: change to logger
        }
        String result;
        try {
            result = Files.readString(Path.of(path));
            System.out.format("File \"%s\" was successfully read\n", path); //TODO: change to logger
        } catch (IOException exception) {
            System.out.format("Exception (%s) w en reading file \"%s\"\n", exception.getMessage(), path); //TODO: change to logger
            throw new ReaderException(exception);
        }
        return result;
    }
}
