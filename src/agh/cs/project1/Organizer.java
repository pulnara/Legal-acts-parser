package agh.cs.project1;

import java.util.*;


public class Organizer {
    private List<String> plik = new LinkedList<>();

    public Organizer(List<String> lista) {
        this.plik = lista;
    }

    public Document organize(boolean isConstitution) {
        Document document; //= new Document();
        if (isConstitution) document = new Constitution();
        else document = new Uokik();

        DocElement section = null;
        DocElement chapter = null;
        DocElement article = null;
        DocElement paragraph = null;
        DocElement point = null;
        DocElement letter = null;

        for (int i = 0; i < plik.size(); i++) {

            if (plik.get(i).startsWith("DZIAŁ")) {
                String key = plik.get(i).substring(6, plik.get(i).length());
                chapter = null;
                section = new DocElement(key);
                section.addContent(plik.get(i));
                section.addContent(plik.get(i+1));
                ((Uokik) document).addSection(section);
                i++;
            }

            else if (plik.get(i).startsWith("Rozdział")) {
                String key = getKey(plik.get(i).substring(9, plik.get(i).length()));
                StringBuilder title = new StringBuilder(plik.get(i));
                title.append("\n");
                title.append(plik.get(i+1));
                i++;
                while (!plik.get(i+1).startsWith("Art.") && !plik.get(i+1).toUpperCase().equals(plik.get(i+1))) {
                    title.append(" ");
                    title.append(plik.get(i+1));
                    i++;
                }

                chapter = new DocElement(key);
                chapter.addContent(title.toString());
                if (section != null) section.addChild(chapter);
                else ((Constitution) document).addChapter(chapter);
            }

            else if (plik.get(i).startsWith("Art.")) {
                paragraph = null;
                point = null;

                String key = plik.get(i).substring(5, 5 + plik.get(i).substring(5, plik.get(i).length()).indexOf('.'));
                article = new DocElement(key);
                article.addContent(plik.get(i));
                document.addArticle(article);
                if (chapter != null) chapter.addChild(article);
                i++;


                while (i < plik.size() && !plik.get(i).startsWith("DZIAŁ")
                        && !plik.get(i).startsWith("Rozdział") && !plik.get(i).startsWith("Art.")) {

                    if (Character.isDigit(plik.get(i).charAt(0)) &&
                            (plik.get(i).charAt(1) == '.' || plik.get(i).charAt(2) == '.')) {
                        // mamy ustęp
                        int counter = plik.get(i).indexOf('.');
                        key = plik.get(i).substring(0, counter);
                        paragraph = new DocElement(key);
                        //parContent = new StringBuilder(plik.get(i));
                        paragraph.addContent(plik.get(i));
                        article.addChild(paragraph);
                        point = null;
                    }

                    else if (Character.isDigit(plik.get(i).charAt(0)) &&
                            (plik.get(i).charAt(1) == ')' || plik.get(i).charAt(2) == ')' ||
                                    (plik.get(i).charAt(3) == ')' && Character.isLetter(plik.get(i).charAt(2))))) {
                        // mamy punkt
                        int counter = plik.get(i).indexOf(')');
                        key = plik.get(i).substring(0, counter);
                        point = new DocElement(key);
                        //StringBuilder poiContent = new StringBuilder(plik.get(i));
                        point.addContent(plik.get(i));
                        letter = null;

                        if (paragraph == null) {
                            // punkt należy do artykułu
                            article.addChild(point);
                        }

                        else {
                            // punkt należy do ustępu
                            paragraph.addChild(point);
                        }
                    }

                    else if (Character.isLetter(plik.get(i).charAt(0)) &&
                            (plik.get(i).charAt(1) == ')' || plik.get(i).charAt(2) == ')')) {
                        // mamy literał
                        key = plik.get(i).substring(0, 1);
                        letter = new DocElement(key);
                        letter.addContent(plik.get(i));
                        point.addChild(letter);
                    }

                    else {
                        if (plik.get(i).toUpperCase().equals(plik.get(i))) {}
                        else if (paragraph == null && point == null) article.addContent(plik.get(i));
                        else if (point == null) paragraph.addContent(plik.get(i));
                        else if ((paragraph == null && letter == null) || letter == null) point.addContent(plik.get(i));
                        else letter.addContent(plik.get(i));
                    }
                    i++;
                }
                i--;
            }
        }
        return document;
    }

    private String getKey(String maybeRoman) {
        //System.out.println(maybeRoman);
        String key = maybeRoman;
        switch(maybeRoman) {
            case "I":
                key = "1"; break;
            case "II":
                key = "2"; break;
            case "III":
                key = "3"; break;
            case "IV":
                key = "4"; break;
            case "V":
                key = "5"; break;
            case "VI":
                key = "6"; break;
            case "VII":
                key = "7"; break;
            case "VIII":
                key = "8"; break;
            case "IX":
                key = "9"; break;
            case "X":
                key = "10"; break;
            case "XI":
                key = "11"; break;
            case "XII":
                key = "12"; break;
            case "XIII":
                key = "13"; break;
            default: {}
        }
        //System.out.println(key);
        return key;
    }
}
