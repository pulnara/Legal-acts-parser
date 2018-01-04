package agh.cs.project1;

import java.util.*;


public class Uokik extends Document {
    public Map<String, DocElement> sections = new LinkedHashMap<>();

    public void addSection(DocElement section) {
        this.sections.put(section.getId(), section);
    }

    public DocElement getSection(String index) {
        return this.sections.get(index);
    }

    public LinkedList<DocElement> getSections() {
        return new LinkedList<>(this.sections.values());
    }

    public DocElement getChapter(String indexOfSec, String indexOfChap) {
        DocElement section = this.getSection(indexOfSec);
        DocElement chapterOrArt = section.getChild(indexOfChap);
        if (chapterOrArt == null) return null;
        if (chapterOrArt.getContent().startsWith("Rozdział")) return chapterOrArt;
        return null;
    }

}

