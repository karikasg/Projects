package tavalyiZV.workhours;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkHours {


    public String minWork(String file) {
        int min = Integer.MAX_VALUE;
        String result = "";
        try (BufferedReader br = Files.newBufferedReader(Path.of(file))) {
            String[] elements;
            String line;
            while ((line = br.readLine()) != null) {
                elements = line.split(",");
                String name = elements[0];
                int workhours = Integer.parseInt(elements[1]);
                String date = elements[2];
                if (workhours < min) {
                    result = name + ": " + date;
                    min = workhours;
                }
            }
        }
        catch (IOException ioe) {
            throw new IllegalStateException("File beolvasási hiba", ioe);
        }
        return result;
    }
}
