package agh.cs.project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class ArgumentsVerifier {
    private boolean constitution;

    public void Verify(Map<Elements, String> options) throws IllegalArgumentException {
        if (options.get(Elements.Invalid) != null) {
            throw new IllegalArgumentException("W linii komend pojawił się nieprawidłowy argument.");
        }

        if (options.get(Elements.File) == null) {
            throw new IllegalArgumentException("Nie podano ścieżki do pliku.");
        }

        if (options.get(Elements.Content) != null && options.get(Elements.TOC) != null) {
            throw new IllegalArgumentException("Nie można jednocześnie wypisać treści i spisu treści pliku.");
        }

        /*if((options.get(Elements.Letter) != null && options.get(Elements.Point) == null) ||
                (options.get(Elements.Point) != null && options.get(Elements.Paragraph) == null) ||
                (options.get(Elements.Paragraph) != null && options.get(Elements.ArticleRange) == null)) {
            throw new IllegalArgumentException("Wprowadzono nieprawidłowe części składowe.");
        }*/

        constitution = isConstitution(options.get(Elements.File));
        if (constitution && options.get(Elements.Section) != null) {
            throw new IllegalArgumentException("W Konstytucji nie występują działy.");
        }

        if ((constitution && options.get(Elements.Article) != null && Integer.parseInt(options.get(Elements.Article)) > 243)
            || (!constitution && options.get(Elements.Article) != null && Integer.parseInt(options.get(Elements.Article)) > 138)) {
            throw new IllegalArgumentException("Artykuł o takim numerze nie występuje.");
        }


    }

    public boolean isConstitution(String path)  {
        BufferedReader br;
        String what = "";
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("Nie można otworzyć pliku.");
            return false;
        }
        try {
            String check = br.readLine();
            //System.out.println(check);
            //if (!check.substring(0, 17).equals("©Kancelaria Sejmu"))
            if (!check.startsWith("©Kancelaria Sejmu"))
                throw new IllegalArgumentException("Podano zły plik.");
            br.readLine();
            what = br.readLine();
        } catch (NullPointerException e) {
            System.out.println("Błąd odczytu.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (what.equals("KONSTYTUCJA"));
    }

    public boolean getIfConstitution() {
        return constitution;
    }

}
