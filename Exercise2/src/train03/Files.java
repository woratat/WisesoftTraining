package train03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Files {
    List<String> dataList = new ArrayList<>();

    public void writeToFile(File fileToWrite) {
        try (PrintWriter fileWriter = new PrintWriter(fileToWrite)){
            Agenda agenda = new Agenda();
            agenda.setAgenda(dataList).forEach(fileWriter::println);
        } catch (FileNotFoundException e) {
            System.out.println("File name " + fileToWrite.getName() + " was not found.");
        }
    }

    public void readFile(File fileToRead) {
        try(Scanner scanner = new Scanner(fileToRead)){
            while(scanner.hasNext()){
                dataList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File name " + fileToRead.getName() + " was not found.");
        }
    }

    public List<String> getDataList() {
        return dataList;
    }
}