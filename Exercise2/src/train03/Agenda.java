package train03;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class Agenda {
    int startHour = 9;
    int startMin = 0;
    int day = 1;

    public Queue setAgenda(List<String> data) {
        String hm;
        boolean afterNoon = false;
        Queue<String> queue = new LinkedList<>();
        DateFormat dateFormat = new DateFormat();
        String date = data.get(0);
        String tomorrow = dateFormat.formatDate(dateFormat.getTomorrow(date));

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).endsWith("min")) {
                long time = Long.parseLong(data.get(i).replaceAll("[^0-9]+", ""));
                Duration dur = Duration.ofMinutes(time);

                if (!afterNoon && startHour != 12) {
                    hm = String.format("%02d:%02d%s", startHour, startMin, "AM ");
                } else {
                    hm = String.format("%02d:%02d%s", startHour, startMin, "PM ");
                }

                if (startHour == 12 && startMin >= 0) {
                    queue.add(hm + "Lunch");
                    startHour = 1;
                    afterNoon = true;
                    hm = String.format("%02d:%02d%s", startHour, startMin, "PM ");
                }

                queue.add(hm + data.get(i));

                if (startHour != 12 && startMin + dur.toMinutesPart() >= 60) {
                    startHour += dur.toHours() + 1;
                    startMin += dur.toMinutesPart() - 60;
                }
                else if(startHour + dur.toHours() >= 4 && startMin + dur.toMinutesPart() < 60 && afterNoon && i == data.size() - 2) {
                    startMin += dur.toMinutesPart();
                }
                else if(startHour + dur.toHours() == 4 && startMin <= 59 && afterNoon) {
                    queue.add(String.format("%02d:%02d%s", startHour + dur.toHours(), startMin + dur.toMinutesPart(), "PM Networking Event"));
                    if (!(i == data.size() - 1)) {
                        String nextDay = Arrays.stream(tomorrow.split("/")).collect(Collectors.joining("-"));
                        queue.add("Day "+ day + " - " + tomorrow + ":");
                        tomorrow = dateFormat.formatDate(dateFormat.getTomorrow(nextDay));
                        day++;
                    }
                    startHour = 9;
                    startMin = 0;
                    afterNoon = false;
                }
                else {
                    startHour += dur.toHours();
                    startMin += dur.toMinutesPart();
                }
            } else {
                queue.add("Day " + day + " - " + dateFormat.formatDate(date) + ":");
                day++;
            }
        }
        queue.forEach(System.out::println);
        return queue;
    }
}