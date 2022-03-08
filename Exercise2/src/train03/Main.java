package train03;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        File currentDir = new File (".");
        String basePath = currentDir.getCanonicalPath();
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));

        File fileToRead = new File(basePath + "/src/SampleFiles/schedule.txt");
        File fileToWrite = new File(basePath + "/src/SampleFiles/" + formattedDate + "-" + fileToRead.getName());

        if (fileToRead.getName().toLowerCase().endsWith(".txt")) {
            try {
                Files files = new Files();
                files.readFile(fileToRead);
                files.writeToFile(fileToWrite);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}