package com.exqzore.xml_parser.parser;

import com.exqzore.xml_parser.entity.Attribute;
import com.exqzore.xml_parser.entity.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser {
    private Vector<Node> nodes = new Vector<>();

    public Node parseXML(List<String> lines) {
        for (String str : lines) {
            if (!str.contains("<?")) {
                if (str.contains("</")) {
                    if (nodes.size() > 1) {
                        Node tmp = nodes.lastElement();
                        nodes.remove(tmp);
                        nodes.lastElement().addNode(tmp);
                    }
                } else if (str.contains("<")) {
                    nodes.add(new Node());
                    Pattern pattern = Pattern.compile("[^<\\s\\t][^\\s\\t>]+");
                    Matcher matcher = pattern.matcher(str);
                    matcher.find();
                    String name = matcher.group();
                    nodes.lastElement().setName(name);
                    List<String> attrs = new ArrayList<>();
                    pattern = Pattern.compile("\\S+=\"[^\"]*\"");
                    matcher = pattern.matcher(str);
                    while (matcher.find()) {
                        attrs.add(matcher.group());
                    }
                    nodes.lastElement().setAttributes(createAttributes(attrs));
                } else {

                    nodes.lastElement().setContent(str);
                }
            }
        }
        return nodes.lastElement();
    }

    private List<Attribute> createAttributes(List<String> lines) {
        List<Attribute> attributes = new ArrayList<>();
        for (String str : lines) {
            Pattern pattern = Pattern.compile("[^\"=]+");
            Matcher matcher = pattern.matcher(str);
            matcher.find();
            String name = matcher.group();
            matcher.find();
            String value = matcher.group();
            attributes.add(new Attribute(name, value));
        }
        return attributes;
    }
}
