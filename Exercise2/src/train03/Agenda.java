package train03;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static train03.DateFormat.*;

public class Agenda {
    public List<Object> setAgenda(List<InputObj> data, String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mma", Locale.US);
        LocalTime localTime = LocalTime.of(9, 0);
        List<Object> list = new ArrayList<>();
        boolean morning = false;
        int day = 1;

        list.add("Day " + day + " - " + formatDate(date) + ":");
        day++;

        for (int i = 1; i < data.size(); i++) {
            int min = data.get(i).getTime();
            int hour = min / 60;
            int minute = min % 60;

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
            list.add(df.format(localTime) + " " + data.get(i).getTitle());
            localTime = localTime.plusHours(hour).plusMinutes(minute);
            if (i == (data.size() - 1))
                list.add(df.format(localTime) + " Networking Event");
        }
        return list;
    }
}