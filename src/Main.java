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

}}

