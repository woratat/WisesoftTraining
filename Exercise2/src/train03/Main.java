package train03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Files files = new Files();
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));

        File fileToRead = new File(System.getProperty("user.dir") + "/src/SampleFiles/schedule.txt");
        File fileToWrite = new File(System.getProperty("user.dir") + "/src/SampleFiles/" + formattedDate + "-" + fileToRead.getName());
        if (fileToRead.getName().toLowerCase().endsWith(".txt")) {
            try {
                files.readFile(fileToRead);
                files.writeToFile(fileToWrite);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}