package agh.cs.project1;

import java.util.*;

public class Constitution extends Document {
    private Map<String, DocElement> chapters = new LinkedHashMap<>();

    public void addChapter(DocElement chapter) {
        chapters.put(chapter.getId(), chapter);
    }

    public LinkedList<DocElement> getChapters() {
        return new LinkedList<>(chapters.values());
    }

    public DocElement getChapter(String index) {
        return chapters.get(index);
    }
}
