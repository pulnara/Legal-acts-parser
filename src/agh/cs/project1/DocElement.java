package agh.cs.project1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aga on 30.12.2017.
 */
public class DocElement {
    private String identifier;
    private Elements type;
    private String title;
    private List<String> subtitles = new LinkedList<>();
    private List<String> content = new LinkedList<>();
    private List<DocElement> children = new ArrayList<>();

    public DocElement(String id, Elements type) {
        this.identifier = id;
        this.type = type;
    }

    public DocElement(String id, String title, Elements type) {
        this.title = title;
        this.identifier = id;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return identifier;
    }

    public void addChild(DocElement child) {
        children.add(child);
    }

    public void writeChildren() {
        for (DocElement el : children) {
            System.out.println("    " + el.getId()+ " " + el.getTitle());
        }
    }

    public List<DocElement> getChildren() {
        return this.children;
    }

    public void addContent(String line) {
        content.add(line);
    }

    public void addSubtitle(String line) {
        subtitles.add(line);
    }

    public void viewContent() {
        for(String el : content) System.out.println("   " + el);
    }

    public void viewSubtitles() {
        for (String el : subtitles) {
            System.out.println("    " + el);
        }
    }

}
