package agh.cs.project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ArgumentsVerifier {
    private Map<Elements, String> options;

    public ArgumentsVerifier (Map<Elements, String> options) {
        this.options = options;
    }

    public boolean verifyArgs () throws IllegalArgumentException {
        if (options.size() == 0) throw new IllegalArgumentException("Nie podano argumentów.");

        if (options.size() < 2) throw new IllegalArgumentException("Podano za mało argumentów.");

        if (options.get(Elements.TOC) != null && options.get(Elements.Section) != null && options.size() > 3) {
            throw new IllegalArgumentException("Podano za dużo argumentów.");
        }

        if (options.get(Elements.TOC) != null && options.get(Elements.Section) == null && options.size() > 2) {
            throw new IllegalArgumentException("Podano za dużo argumentów.");
        }

        if (options.get(Elements.Invalid) != null) {
            throw new IllegalArgumentException("W linii komend pojawił się nieprawidłowy argument.");
        }

        if (options.get(Elements.File) == null) {
            throw new IllegalArgumentException("Nie podano ścieżki do pliku.");
        }

        if((options.get(Elements.Letter) != null && options.get(Elements.Point) == null) ||
                (options.get(Elements.Paragraph) != null && options.get(Elements.Article) == null) ||
                (options.get(Elements.Point) != null && options.get(Elements.Article) == null)) {
            throw new IllegalArgumentException("Wprowadzono nieprawidłowe argumenty.");
        }

        boolean constitution = isConstitution(options.get(Elements.File));
        if (constitution && options.get(Elements.Section) != null) {
            throw new IllegalArgumentException("W Konstytucji nie występują działy.");
        }

        if (!constitution && options.get(Elements.Chapter) != null && options.get(Elements.Section) == null) {
            throw new IllegalArgumentException("Nie podano działu, z którego ma zostać wypisany rozdział.");
        }

        return constitution;

    }

    private boolean isConstitution(String path) throws IllegalArgumentException {
        BufferedReader br;
        String what = "";
        try {
            br = new BufferedReader(new FileReader(path));
            String check = br.readLine();
            if (!check.startsWith("©Kancelaria Sejmu"))
                throw new IllegalArgumentException("Podano zły plik.");
            br.readLine();
            what = br.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("Nie można otworzyć pliku.");
            System.exit(1);
        } catch (NullPointerException e) {
            System.out.println("Błąd odczytu.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (what.equals("KONSTYTUCJA"));
    }
}
