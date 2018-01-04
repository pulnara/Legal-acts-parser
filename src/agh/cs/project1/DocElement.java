package agh.cs.project1;


import java.util.*;

public class DocElement {
    private final String identifier;
    private String content;
    private Map<String, DocElement> children = new LinkedHashMap<>();


    public DocElement(String id) {
        this.identifier = id;
        this.content = "";
    }

    public String getId() {
        return identifier;
    }

    public void addChild(DocElement child) {
        children.put(child.getId(), child);
    }

    DocElement getChild(String index) {
        return children.get(index);
    }

    public LinkedList<DocElement> getChildren() {
        return new LinkedList(this.children.values());
    }

    public void addContent(String line) {
        StringBuilder builder = new StringBuilder(content);
        if (!content.equals("")) builder.append("\n");
        builder.append(line);
        this.content = builder.toString();
    }

    public String getContent() {
        return content;
    }

    public void printContent() {
        System.out.println(content);
    }

    public void deepPrinter(DocElement el) {
        el.printContent();
        for (DocElement child : el.getChildren()) {
            if (child!= null) deepPrinter(child);

        }
    }

}
