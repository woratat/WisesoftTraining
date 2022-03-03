package Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import train03.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesTest {
    private final Files files = new Files();
//    File currentDir = new File (".");
//    String basePath = currentDir.getCanonicalPath();
//    File fileToRead = new File(basePath + "/src/SampleFiles/schedule.txt");
//    File fileToWrite = new File(basePath + "/src/SampleFiles/testWriteFiles.txt");

    public FilesTest() throws IOException {}

    @Test
    void readFiles() {

    }

    @Test
    void writeFiles() {

    }
}
