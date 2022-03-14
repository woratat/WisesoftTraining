package train03;

import java.io.*;
import java.util.*;

public class Files {
    List<Object> dataList = new ArrayList<>();

    public void readFile(File fileToRead) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                dataList.add(strCurrentLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(File fileToWrite) {
        try (PrintWriter fileWriter = new PrintWriter(fileToWrite)){
            Agenda agenda = new Agenda();
            List<Object> list = agenda.setAgenda(dataList);
            list.forEach(fileWriter::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}