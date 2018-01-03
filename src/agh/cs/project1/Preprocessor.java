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
            plik.add(line);
        }

//1864
//1904
//42
        this.connectLines();
        this.deleteRubbish();
        this.separateArt(isUokik);

/*        for (String el : plik) {
            System.out.println(el);
        }*/
      /*  for (int i = 0; i < plik.size(); i++) {
            if (plik.get(i).endsWith("-")) {
                System.out.println(plik.get(i));
                System.out.println(plik.get(i+1));
                System.out.println();
            }
        }*/

    }

    private void deleteRubbish() {
        for (int i = 0; i < plik.size(); i++) {
            //if (plik.get(i).equals("©Kancelaria Sejmu") || (plik.get(i).length() > 16 && plik.get(i).substring(0, 17).equals("©Kancelaria Sejmu"))) plik.remove(i);

            if(plik.get(i).startsWith("©Kancelaria Sejmu")) plik.remove(i);

            if (plik.get(i).equals("2017-06-28")) plik.remove(i);

            if (plik.get(i).equals("2009-11-16")) plik.remove(i);

            while (plik.get(i).length() <= 1) {
                plik.remove(i);
                i--;
            }
        }
    }

    private void connectLines() throws IOException {
        for (int i = 0; i < plik.size(); i++) {
            if (plik.get(i).endsWith("-")) {
/*                System.out.println(plik.get(i));
                System.out.println(plik.get(i+1));
                System.out.println();*/
                int counter = 0;
                while (counter < plik.get(i+1).length() -1 && plik.get(i + 1).charAt(counter) != ' ') counter++;
                //System.out.println(counter);
                plik.set(i, plik.get(i).substring(0, plik.get(i).length()-1));
                plik.set(i, plik.get(i).concat(plik.get(i+1).substring(0, counter + 1)));
                plik.set(i+1, plik.get(i+1).substring(counter+1, plik.get(i+1).length()));
/*                System.out.println(plik.get(i));
                System.out.println(plik.get(i+1));
                System.out.println("-----------------------------------");*/

                //System.out.println();
            }
        }
    }

    public void separateArt(boolean isUokik) {
        if(!isUokik) return;

        for (int i = 0; i < plik.size(); i++) {
            if (plik.get(i).startsWith("Art.")) {
                int counter = 1;
               /* System.out.println(plik.get(i));
                System.out.println("-----------------------------------");*/
                while(plik.get(i).charAt(4+counter) != '.') counter++;
                plik.add(i+1, plik.get(i).substring(4+ counter +2, plik.get(i).length()));
                plik.set(i, plik.get(i).substring(0, 4 + counter + 2));
                /*System.out.println(plik.get(i));
                System.out.println(plik.get(i+1));
                System.out.println();*/
            }
        }
    }



}
