package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import train03.*;

import java.time.LocalDate;

public class DateFormatTest {
    private final DateFormat dateFormat = new DateFormat();
    String testDate = "2022-03-03";

    @Test
    void formatDate() {
        assertEquals("03/03/2565", dateFormat.formatDate(testDate));
    }

    @Test
    void isWeekend() {
        assertEquals(0, dateFormat.isWeekend(LocalDate.of(2022, 3, 3)));
    }

    @Test
    void getTomorrow() {
        assertEquals("2022-03-04", dateFormat.getTomorrow(testDate));
    }
}