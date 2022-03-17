package train03;

import java.io.*;
import java.util.*;

public class Files {
    private List<InputObj> dataList = new ArrayList<>();
    private String date;
    private int i = 0;

    public void readFile(File fileToRead) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                InputObj inputObj = new InputObj();
                if (i == 0) {
                    date = strCurrentLine;
                    ++i;
                }
                int time = Integer.parseInt(strCurrentLine.replaceAll("[^0-9]+", ""));
                inputObj.setTime(time);
                inputObj.setTitle(strCurrentLine);
                dataList.add(inputObj);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void writeToFile(File fileToWrite) {
        try (PrintWriter fileWriter = new PrintWriter(fileToWrite)) {
            Agenda agenda = new Agenda();
            List<Object> result = agenda.setAgenda(dataList, date);
            result.forEach(fileWriter::println);
            result.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}