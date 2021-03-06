//package Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.Test;
//import train03.*;
//
//import java.time.LocalDate;
//
//public class DateFormatTest {
//    private final DateFormat dateFormat = new DateFormat();
//    String testDate = "2022-03-03";
//
//    @Test
//    void formatDateTest() {
//        assertEquals("03/03/2565", dateFormat.formatDate(testDate));
//    }
//
//    @Test
//    void isWeekendTest() {
//        LocalDate ld = LocalDate.parse("2565-03-03");
//        assertEquals(ld, dateFormat.isWeekend(LocalDate.of(2022, 3, 3)));
//    }
//
//    @Test
//    void getTomorrowTest() {
//        assertEquals("2022-03-04", dateFormat.getNextDay(testDate));
//    }
//}