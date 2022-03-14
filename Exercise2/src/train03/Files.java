package train03;

import java.io.*;
import java.util.*;

public class Files {
    List<String> dataList = new ArrayList<>();

    public List<String> readFile(File fileToRead) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                dataList.add(strCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public void writeToFile(File fileToWrite) {
        try (PrintWriter fileWriter = new PrintWriter(fileToWrite)){
            Agenda agenda = new Agenda();
            agenda.setAgenda(dataList).forEach(fileWriter::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}