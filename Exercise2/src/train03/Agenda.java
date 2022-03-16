package train03;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static train03.DateFormat.*;

public class Agenda {
    private boolean morning = false;
    private int day = 1;

    public List<Object> setAgenda(List<Object> data) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mma", Locale.US);
        LocalTime localTime = LocalTime.of(9, 0);
        List<Object> list = new ArrayList<>();
        String date = data.get(0).toString();

        list.add("Day " + day + " - " + formatDate(date) + ":");
        day++;

        for (int i = 1; i < data.size(); i++) {
            list.add(df.format(localTime) + " " + data.get(i));
            int min = getMinute(data.get(i));

            int hour = min / 60;
            int minute = min % 60;
            localTime = localTime.plusHours(hour).plusMinutes(minute);

            if (i != data.size() - 1)
                min = getMinute(data.get(i + 1));

            hour = min / 60;
            minute = min % 60;

            if (localTime.equals(LocalTime.NOON) || (localTime.isAfter(LocalTime.NOON) && morning)) {
                list.add(df.format(localTime) + " Lunch");
                localTime = localTime.plusHours(1);
                morning = false;
            }
            if (localTime.plusHours(hour).plusMinutes(minute).isAfter(LocalTime.of(17, 0))) {
                date = getNextDay(date);
                list.add(df.format(localTime) + " Networking Event");
                list.add("Day " + day + " - " + formatDate(date) + ":");
                localTime = LocalTime.of(9, 0);
                morning = true;
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