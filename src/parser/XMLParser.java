package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Konfrontationstyp;
import model.Result;

public class XMLParser {

    private static final Pattern ID_PATTERN = Pattern.compile("\\p{javaSpaceChar}*<Id>(\\d+)</Id>");
    private static final Pattern HELD_PATTERN = Pattern.compile("\\p{javaSpaceChar}*<Held>(.*)</Held>");
    private static final Pattern ANTAGONIST_PATTERN = Pattern.compile("\\p{javaSpaceChar}*<Antagonist>(.*)</Antagonist>");
    private static final Pattern KONFRONTATION_PATTERN = Pattern.compile("\\p{javaSpaceChar}*<Konfrontationstyp>(.*)</Konfrontationstyp>");
    private static final Pattern ORT_PATTERN = Pattern.compile("\\p{javaSpaceChar}*<Ort>(.*)</Ort>");
    private static final Pattern DATUM_PATTERN = Pattern.compile("\\p{javaSpaceChar}*<Datum>(.*)</Datum>");
    private static final Pattern GLOBALER_PATTERN = Pattern.compile("\\p{javaSpaceChar}*<GlobalerEinfluss>(-?\\d+)</GlobalerEinfluss>");



    private static XMLParser instance;

    private XMLParser() {
    }

    public static XMLParser getInstance() {
        if (instance == null) {
            instance = new XMLParser();
        }
        return instance;
    }

    /**
     * Parse the XML file and return a list of students
     *
     * @param path the path of the XML file to be parsed
     * @return List of students
     * @throws IOException if an I/O error occurs
     */
    public List<Result> parseStudents(String path) throws IOException {
        Path filePath = Path.of(path);

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            if (!reader.readLine().equals("<logs>")) {
                throw new IOException("Invalid file");
            }

            List<Result> students = new ArrayList<>();
            String nextLine = reader.readLine();

            while (!nextLine.equals("</logs>")) {
                students.add(parseResult(reader, nextLine));
                nextLine = reader.readLine();
            }
            return students;
        }
    }

    /**
     * Parses a single student entry
     *
     * @param reader the Reader to read from
     * @param firstLine the first line that was already read
     * @return the parsed Student
     */
    private Result parseResult(BufferedReader reader, String firstLine) throws IOException {
        Result marvel = new Result();

        if (!firstLine.contains("<log>")) {
            throw new IOException("Invalid marvel file");
        }

        String nextLine = reader.readLine();
        while (true) {
            parseField(reader, nextLine,marvel);
            nextLine = reader.readLine();
            if (nextLine.contains("</log>")) {
                return marvel;
            }
        }
    }

    /**
     * Parses the next marvel field
     *
     * @param reader the Reader to read from
     * @param firstLine the first line that was already read
     * @param marvel the Student where the field is set
     */
    private void parseField(BufferedReader reader, String firstLine, Result marvel) throws IOException {
        Matcher idMatcher = ID_PATTERN.matcher(firstLine);
        if (idMatcher.matches()) {
            marvel.setId(Integer.parseInt(idMatcher.group(1)));
        }

        Matcher nameMatcher = HELD_PATTERN.matcher(firstLine);
        if (nameMatcher.matches()) {
            marvel.setHeld(nameMatcher.group(1));
        }

        Matcher houseMatcher = KONFRONTATION_PATTERN.matcher(firstLine);
        if (houseMatcher.matches()) {
            try {
                marvel.setKonfrontationstyp(Konfrontationstyp.valueOf(houseMatcher.group(1)));
            } catch (IllegalArgumentException e) {
                throw new IOException("Invalid Haus value: " + houseMatcher.group(1), e);
            }
        }

        Matcher antMatcher = ANTAGONIST_PATTERN.matcher(firstLine);
        if (antMatcher.matches()) {
            marvel.setAntagonist(antMatcher.group(1));
        }

        Matcher pointsMatcher = GLOBALER_PATTERN.matcher(firstLine);
        if (pointsMatcher.matches()) {
            marvel.setGlobalerEinfluss(Double.parseDouble(pointsMatcher.group(1)));
        }
        Matcher ortMatcher = ORT_PATTERN.matcher(firstLine);
        if (ortMatcher.matches()) {
            marvel.setOrt(ortMatcher.group(1));
        }
        Matcher datumMatcher = DATUM_PATTERN.matcher(firstLine);
        if (datumMatcher.matches()) {
            marvel.setDatum(LocalDate.parse(datumMatcher.group(1)));
        }
    }
}
