package com.exqzore.xml_parser.main;

import com.exqzore.xml_parser.exception.ReaderException;
import com.exqzore.xml_parser.parser.XMLParser;
import com.exqzore.xml_parser.reader.FileReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String PATH_TO_XML = "resources/notes.xml";

    public static void main(String[] args) throws IOException {
        XMLParser xmlParser = new XMLParser();
        FileReader fileReader = new FileReader();
        String dataString;
        try {
            dataString = fileReader.read(PATH_TO_XML);
            dataString = dataString.replace("\n", "").replace("\r", "")
                    .replace("<", "\n<").replace(">", ">\n");
            List<String> lines = dataString.lines().collect(Collectors.toList());
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).isBlank()) {
                    lines.remove(i);
                }
            }
            System.out.println(xmlParser.parseXML(lines));
        } catch (ReaderException e) {
            System.out.println(e.getMessage()); //TODO: change to logger
        }
    }
}
