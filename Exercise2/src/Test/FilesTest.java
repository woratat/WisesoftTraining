package Test;

import org.junit.jupiter.api.Test;
import train03.Files;

import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesTest {
    private final Files files = new Files();
    File currentDir = new File (".");
    String basePath = currentDir.getCanonicalPath();
    File fileToRead = new File(basePath + "/src/SampleFiles/schedule.txt");
//    File fileToWrite = new File(basePath + "/src/SampleFiles/testWriteFiles.txt");

    public FilesTest() throws IOException {}

    @Test
    void readFiles() {
        List<String> data = files.readFile(fileToRead);
        assertEquals("2022-02-25", data.get(0));
    }

    @Test
    void writeFiles() throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(fileToWrite));
//        if (br.readLine() == null) {
//            System.out.println("No errors, and file empty");
//        }
    }
}
