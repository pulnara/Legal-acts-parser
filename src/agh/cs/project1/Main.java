package agh.cs.project1;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(args);
        parser.parseArgs();
        try {
            Printer printer = new Printer(parser.getOptions());
            printer.print();

        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
            System.exit(2);
        }
    }
}
