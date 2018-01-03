package agh.cs.project1;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aga on 02.01.2018.
 */
public class Document {
    protected List<DocElement> chapters = new ArrayList<>();
    protected List<DocElement> articles = new ArrayList<>();

    // > articles = new HashMap<>();

    public void addChapter(DocElement chapter) {
        chapters.add(chapter);
    }

    public void addArticle(DocElement article) {
        articles.add(article);
    }


}
