package agh.cs.project1;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aga on 02.01.2018.
 */
public class Uokik extends Document {
    private List<DocElement> sections = new ArrayList<>();

    public void addSection(DocElement section) {
        this.sections.add(section);
    }
    public void getTOC() {
        for (int i = 0; i < sections.size(); i++) {
            System.out.println(sections.get(i).getId() + " " + sections.get(i).getTitle());
            sections.get(i).writeChildren();
        }
    }

}

