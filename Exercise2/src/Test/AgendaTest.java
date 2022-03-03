package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import train03.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AgendaTest {
    private final Agenda agenda = new Agenda();
    List<String> data = new Files().getDataList();
    Queue<String> queue = new LinkedList<>();

    @Test
    void setAgenda() {
//        queue.addAll(data);
//        Assertions.assertTrue(Arrays.equals(queue.toArray(), agenda.setAgenda(data).toArray()));
    }
}