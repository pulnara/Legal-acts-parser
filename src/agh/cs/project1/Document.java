package agh.cs.project1;

import java.util.*;

public class Document {
    protected Map<String, DocElement> articles = new LinkedHashMap<>();

    public void addArticle(DocElement article) {
        articles.put(article.getId(), article);
    }

    public DocElement getArticle (String index) {
        return articles.get(index);
    }

    public LinkedList<DocElement> getArticles() {
        return new LinkedList<>(this.articles.values());
    }

    public boolean checkRange(String start, String end) {
        if (this.articles.get(start) == null || this.articles.get(end) == null) return false;
        int s = 0;
        int e = 0;
        LinkedList<DocElement> art = this.getArticles();
        for (int i = 0; i < art.size(); i++) {
            if (art.get(i).getId().equals(start)) s = i;
            if (art.get(i).getId().equals(end)) e = i;
        }

        if (s > e) return false;
        return true;
    }

}
