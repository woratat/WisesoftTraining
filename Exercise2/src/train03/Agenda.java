package train03;

import java.time.Duration;
import java.util.*;

public class Agenda {
    int hour = 9;
    int minute = 0;
    int day = 1;

    public Queue<String> setAgenda(List<String> data) {
        String time;
        boolean afterNoon = false;
        Queue<String> queue = new ArrayDeque<>();
        DateFormat dateFormat = new DateFormat();
        String tomorrow = dateFormat.formatDate(dateFormat.getTomorrow(data.get(0)));

        for (int i = 0; i < data.size(); i++) {
            if (!data.get(i).endsWith("min")) {
                queue.add("Day " + day + " - " + dateFormat.formatDate(data.get(0)) + ":");
                day++;
            }
            else {
                int min = Integer.parseInt(data.get(i).replaceAll("[^0-9]+", ""));
                Duration dur = Duration.ofMinutes(min);

                int nextMin = 0;
                if(i != data.size()-1){
                    nextMin = Integer.parseInt(data.get(i+1).replaceAll("[^0-9]+", ""));
                }
                Duration dur2 = Duration.ofMinutes(nextMin);

                if (hour != 12 && !afterNoon) {
                    time = String.format("%02d:%02d%s", hour, minute, "AM ");
                }
                else {
                    if (hour == 12 && minute >= 0) { //พักเที่ยง
                        queue.add("12:00PM Lunch");
                        hour = 1;
                        minute = 0;
                        afterNoon = true;
                    }
                    time = String.format("%02d:%02d%s", hour, minute, "PM ");
                }

                queue.add(time + data.get(i));

                // ถ้าตอนบ่ายชั่วโมงต่อไปเวลา 4โมง และต่อไปนาทียังน้อยกว่า 60นาที
                if(hour + dur.toHours() + dur2.toHours() == 4 && minute + dur.toMinutesPart() + dur2.toMinutesPart() < 60 && afterNoon) {
                    minute += dur.toMinutesPart();
                    //ถ้าเป็นตัวสุดท้าย
                    if(i == data.size() - 1){
                        queue.add(String.format("%02d:%02d%s", hour, minute, "PM Networking Event"));
                    }
                }
                // ถ้าตอนบ่ายชั่วโมงเวลามากกว่าเท่ากับ 4โมง และนาทียังน้อยกว่าเท่ากับ 60นาที
                else if(hour + dur.toHours() >= 4 && minute + dur.toMinutesPart() <= 60 && afterNoon) {
                    if(minute + dur.toMinutesPart() >= 60){
                        hour += dur.toHours() + 1;
                        minute += dur.toMinutesPart() - 60;
                    }

                    hour += dur.toHours();
                    minute += dur.toMinutesPart();

                    // ถ้าไม่ใช่ตัวสุดท้ายใส่วันที่ต่อไป
                    if (!(i == data.size() - 1)) {
                        queue.add(String.format("%02d:%02d%s", hour, minute, "PM Networking Event"));
                        queue.add("Day "+ day + " - " + tomorrow + ":");
                        String nextDay = dateFormat.reverseFormat(tomorrow);
                        tomorrow = dateFormat.formatDate(dateFormat.getTomorrow(nextDay));
                        day++;
                    }
                    hour = 9;
                    minute = 0;
                    afterNoon = false;
                }
                else if (hour != 12 && minute + dur.toMinutesPart() >= 60) {
                    hour += dur.toHours() + 1;
                    minute += dur.toMinutesPart() - 60;
                }
                else {
                    hour += dur.toHours();
                    minute += dur.toMinutesPart();
                }
            }
        }
        queue.forEach(System.out::println);
        return queue;
    }
}