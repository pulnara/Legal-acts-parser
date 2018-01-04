package agh.cs.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Preprocessor {
    private String path;
    private List<String> plik = new LinkedList<>();

    public Preprocessor(String path) {
        this.path = path;
    }

    public List<String> getList() {
        return plik;
    }

    public void clean(boolean isUokik) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new FileReader(path));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if(!line.startsWith("©Kancelaria Sejmu") && !line.equals("2017-06-28") && !line.equals("2009-11-16") )plik.add(line);
        }

        this.connectLines();
        this.separateArt(isUokik);

    }

    private void connectLines() throws IOException {
        for (int i = 0; i < plik.size(); i++) {
            if (plik.get(i).endsWith("-")) {
                int counter = 0;
                while (counter < plik.get(i+1).length() -1 && plik.get(i + 1).charAt(counter) != ' ') counter++;
                plik.set(i, plik.get(i).substring(0, plik.get(i).length()-1));
                plik.set(i, plik.get(i).concat(plik.get(i+1).substring(0, counter + 1)));
                plik.set(i+1, plik.get(i+1).substring(counter+1, plik.get(i+1).length()));
                if(plik.get(i+1).length() < 1) {
                    plik.remove(i+1);
                }
            }
        }
    }

    public void separateArt(boolean isUokik) {
        if(!isUokik) return;

        for (int i = 0; i < plik.size(); i++) {
            if (plik.get(i).startsWith("Art.")) {
                int counter = 1;
                while(plik.get(i).charAt(4+counter) != '.') counter++;
                plik.add(i+1, plik.get(i).substring(4+ counter +2, plik.get(i).length()));
                plik.set(i, plik.get(i).substring(0, 4 + counter + 2));
            }
        }
    }



}
