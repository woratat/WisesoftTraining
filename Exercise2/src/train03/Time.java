package train03;

import java.time.Duration;

public class Time {
    public static Duration getMin(String data){
        int min = Integer.parseInt(data.replaceAll("[^0-9]+", ""));
        Duration dur = Duration.ofMinutes(min);
        return dur;
    }

    public static String getTime(int hour, int min, String period){
        return String.format("%02d:%02d%s", hour, min, period);
    }
}
