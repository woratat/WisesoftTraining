package Test;

import org.junit.jupiter.api.Test;
import train03.Time;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {
    private final Time time = new Time();

    @Test
    void getMinTest() {
        String testStr = "test 30min";
        Duration dur = Duration.of(30, MINUTES);
        assertEquals(dur, time.getMin(testStr));
    }

    @Test
    void getTimeTest() {
        String testStr = "09:00AM Test getTime()";
        assertEquals(testStr, time.getTime(9, 0, "AM Test getTime()"));
    }
}
