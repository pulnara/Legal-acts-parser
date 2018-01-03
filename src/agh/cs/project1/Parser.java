package agh.cs.project1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Parser {
    private String[] args;
    private Map<Elements, String> options = new HashMap<>();
    private boolean isConstitution;

    public Parser (String[] args) {
        this.args = args;
    }

    public void parseArgs() throws IOException {
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-file":
                    case "-f":
                        options.put(Elements.File, args[i+1]); i++;
                        break;
                    case "-t":
                        options.put(Elements.Content, ""); break;
                    case "-st":
                        options.put(Elements.TOC, ""); break;
                    case "-art":
                    case "-a":
                        options.put(Elements.Article, args[i+1]); i++;
                        break;
                    case "-az":
                    case "-artz":
                        options.put(Elements.ArticleRange, args[i+1] + " " + args[i+2]);
                        i += 2;
                    case "-roz":
                    case "-r":
                        options.put(Elements.Chapter, args[i+1]); i++;
                        break;
                    case "-u":
                        options.put(Elements.Paragraph, args[i+1]); i++;
                        break;
                    case "-pkt":
                    case "-p":
                        options.put(Elements.Point, args[i+1]); i++;
                        break;
                    case "-lit":
                    case "-l":
                        options.put(Elements.Letter, args[i+1]); i++;
                        break;
                    case "-dz":
                        options.put(Elements.Section, args[i+1]); i++;
                        break;
                    default:
                        options.put(Elements.Invalid, "");
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Podano nieprawidłowe argumenty. Spróbuj jeszcze raz.");
            return;
        }

        try {
            ArgumentsVerifier verifier = new ArgumentsVerifier();
            verifier.Verify(options);
            isConstitution = verifier.getIfConstitution();

        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
            return;
        }

        Preprocessor cleaner = new Preprocessor(options.get(Elements.File));
        cleaner.clean(!isConstitution);

        Organizer organizer = new Organizer(cleaner.getList());
        Document doc = organizer.organize();
        /*if (doc instanceof Uokik) {
            ((Uokik) doc).getTOC();
        }*/
        for (int i = 0; i < doc.articles.size(); i++) {
            if (doc.articles.get(i).getId().equals("4")) {
                System.out.println("ALA");
               doc.articles.get(i).viewContent();
                List<DocElement> points = doc.articles.get(i).getChildren();
               for (int j = 0; j < points.size(); j++) {
                   points.get(j).viewContent();
                   List <DocElement> letters = points.get(j).getChildren();
                   for (int k = 0; k < letters.size(); k++) {
                       letters.get(k).viewContent();
                   }
               }
            }
        }
    }


}
