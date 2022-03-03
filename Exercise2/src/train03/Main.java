package train03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        File currentDir = new File (".");
        String basePath = currentDir.getCanonicalPath();
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));

        File fileToRead = new File(basePath + "/src/SampleFiles/schedule.txt");
        File fileToWrite = new File(basePath + "/src/SampleFiles/" + formattedDate + " schedule.txt");

        if (fileToRead.getName().toLowerCase().endsWith(".txt")) {
            try {
                Files agenda = new Files();
                agenda.readFile(fileToRead);
                agenda.writeToFile(fileToWrite);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}