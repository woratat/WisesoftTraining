package train03;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static train03.DateFormat.*;

public class Agenda {
    int hour = 0;
    int minute = 0;
    int day = 1;

    public List<Object> setAgenda(List<Object> data) {
        String date = data.get(0).toString();
        List<Object> list = new ArrayList<>();
        LocalTime localTime = LocalTime.of(9,0);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mma", Locale.US); //HH != hh

        list.add("Day " + day + " - " + formatDate(date) + ":");
        day++;

        for (int i=1; i< data.size(); i++){
            list.add(df.format(localTime) + " " + data.get(i));
            int min = Integer.parseInt(data.get(i).toString().replaceAll("[^0-9]+", ""));

            int nextMin = 0;
            if(i != data.size()-1){
                nextMin = Integer.parseInt(data.get(i+1).toString().replaceAll("[^0-9]+", ""));
            }
            int nextHour = nextMin/60;
            int nextMinute = nextMin%60;

            hour = min/60;
            minute = min%60;
            localTime = localTime.plusHours(hour).plusMinutes(minute);

            if(localTime.equals(LocalTime.NOON)){
                list.add(df.format(localTime) + " Lunch");
                localTime = localTime.plusHours(1);
            }
            if(localTime.plusHours(nextHour).plusMinutes(nextMinute).isAfter(LocalTime.of(17,0))){
                list.add(df.format(localTime) + " Networking Event");
                date = getNextDay(date);
                list.add("Day " + day + " - " + formatDate(date) + ":");
                localTime = LocalTime.of(9,0);
                day++;
            }
            if(i == data.size() - 1){
                list.add(df.format(localTime) + " Networking Event");
            }
        }
        list.forEach(System.out::println);
        return list;
    }
}