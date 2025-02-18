import model.Konfrontationstyp;
import model.Result;
import parser.XMLParser;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        String xmlFilePath="D:\\Facultate\\Anul II\\SEM 1\\BleocaCristiana721Aufgabe1\\src\\marvel_konfrontationen.xml";
        parser.XMLParser xmlParser = parser.XMLParser.getInstance();

        List<Result> punkteListe = new ArrayList<>();


        try {
            punkteListe=xmlParser.parseStudents(xmlFilePath);
        } catch (IOException e) {
            System.out.println("A apărut o eroare la citirea fișierului  " + e.getMessage());
        }


//a) lesen von der Datei und auf dem Bildschirm zeigen

        for (Result result : punkteListe) {
            System.out.println(result.toString());
        }

//d)   die gesamtpunkten des Hauser
        double punktehaus1=0;
        double punktehaus2=0;
        double punktehaus3=0;
        double punktehaus4=0;
        for (Result result : punkteListe) {
            if (result.getKonfrontationstyp() == Konfrontationstyp.Galaktisch) {
                punktehaus1 += result.getGlobalerEinfluss();
            }
            if (result.getKonfrontationstyp() == Konfrontationstyp.Individuell) {
                punktehaus2 += result.getGlobalerEinfluss();
            }
            if (result.getKonfrontationstyp() == Konfrontationstyp.Team) {
                punktehaus3 += result.getGlobalerEinfluss();
            }
            if (result.getKonfrontationstyp() == Konfrontationstyp.Multiversal) {
                punktehaus4 += result.getGlobalerEinfluss();
            }
        }
        List<String> ergebnisListe = new ArrayList<>();
        ergebnisListe.add("Galaktisch$" + punktehaus1);
        ergebnisListe.add("Individuell$" + punktehaus2);
        ergebnisListe.add("Team$" + punktehaus3);
        ergebnisListe.add("Multiversal$" + punktehaus4);


//        ergebnisListe.sort((a, b) -> {
//            double puncteA = Double.parseDouble(a.split("$")[1]);
//            double puncteB = Double.parseDouble(b.split("$")[1]);
//            return Double.compare(puncteB, puncteA);
//        });




//scriu in fisier
        String fileName = "D:\\Facultate\\Anul II\\SEM 1\\BleocaCristiana721Aufgabe1\\src\\bericht_konfrontationen.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String ergebnis : ergebnisListe) {
                writer.write(ergebnis);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("A apărut o eroare la salvarea datelor: " + e.getMessage());
        }
}}

