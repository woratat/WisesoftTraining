//package Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.testng.annotations.Test;
//import train03.*;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//
//public class AgendaTest {
//    private final Agenda agenda = new Agenda();
//    File currentDir = new File (".");
//    String basePath = currentDir.getCanonicalPath();
//    File fileToRead = new File(basePath + "/src/SampleFiles/schedule.txt");
//
//    List<String> data = new Files().readFile(fileToRead);
//    Queue<String> queue = agenda.setAgenda(data);
//
//    public AgendaTest() throws IOException {}
//
//    @Test
//    void setAgendaTest() throws Exception {
//        assertEquals(true, queue.poll().startsWith("Day 1"));
//    }
//}