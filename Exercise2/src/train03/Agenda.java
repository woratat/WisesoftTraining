package train03;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static train03.DateFormat.*;

public class Agenda {
    boolean afterNoon = false;
    int hour = 0;
    int minute = 0;
    int day = 1;

    public List<Object> setAgenda(List<Object> data) {
        String date = data.get(0).toString();
        List<Object> list = new ArrayList<>();
        LocalTime localTime = LocalTime.of(9, 0);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mma", Locale.US);

        list.add("Day " + day + " - " + formatDate(date) + ":");
        day++;

        for (int i = 1; i < data.size(); i++) {
            list.add(df.format(localTime) + " " + data.get(i));
            int min = getMinute(data.get(i));

            int nextMin = 0;
            if (i != data.size() - 1)
                nextMin = getMinute(data.get(i + 1));

            hour = min / 60;
            minute = min % 60;
            localTime = localTime.plusHours(hour).plusMinutes(minute);

            hour = nextMin / 60;
            minute = nextMin % 60;

            if (localTime.equals(LocalTime.NOON) || (localTime.isAfter(LocalTime.NOON) && afterNoon)) {
                list.add(df.format(localTime) + " Lunch");
                localTime = localTime.plusHours(1);
                afterNoon = false;
            }
            if (localTime.plusHours(hour).plusMinutes(minute).isAfter(LocalTime.of(17, 0))) {
                list.add(df.format(localTime) + " Networking Event");
                date = getNextDay(date);
                list.add("Day " + day + " - " + formatDate(date) + ":");
                localTime = LocalTime.of(9, 0);
                afterNoon = true;
                day++;
            }
            if (i == (data.size() - 1))
                list.add(df.format(localTime) + " Networking Event");
        }
        return list;
    }

    public int getMinute(Object data) {
        return Integer.parseInt(data.toString().replaceAll("[^0-9]+", ""));
    }
}