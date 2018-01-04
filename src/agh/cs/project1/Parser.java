package agh.cs.project1;

import java.util.HashMap;
import java.util.Map;


public class Parser {
    private String[] args;
    private Map<Elements, String> options = new HashMap<>();

    public Parser (String[] args) {
        this.args = args;
    }

    public Map<Elements, String> getOptions() {
        return options;
    }


    public void parseArgs() {
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-file":
                    case "-f":
                        options.put(Elements.File, args[i+1]); i++;
                        break;
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
                        break;
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

    }


}
