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
        DateFormat df = new DateFormat();
        String startDay = df.formatDate(data.get(0));
        String nextDay = df.reverseFormat(startDay);

        for (int i = 0; i < data.size(); i++) {
            if (!data.get(i).endsWith("min")) {
                queue.add("Day " + day + " - " + startDay + ":");
                day++;
            }
            else {
                Duration dur = Time.getMin(data.get(i));

                Duration nextDur = Duration.ofMinutes(0);
                if(i != data.size()-1){
                    nextDur = Time.getMin(data.get(i+1));
                }

                if (hour != 12 && !afterNoon) {
                    time = Time.getTime(hour, minute, "AM ");
                }
                else {
                    if (hour == 12 && minute >= 0) { //พักเที่ยง
                        queue.add(Time.getTime(12, 0, "PM Lunch"));
                        hour = 1;
                        minute = 0;
                        afterNoon = true;
                    }
                    time = Time.getTime(hour, minute, "PM ");
                }

                queue.add(time + data.get(i));

                hour += dur.toHours();
                minute += dur.toMinutesPart();

                // ถ้า 4โมง และต่อไปนาทียังน้อยกว่าเท่ากับ 60นาที
                if(hour + nextDur.toHours() == 4 && minute + nextDur.toMinutesPart() <= 60 && afterNoon) {
                    if(minute + nextDur.toMinutesPart() == 60 && i == data.size() - 1){
                        queue.add(Time.getTime(++hour, minute-60, "PM Networking Event"));
                    }
                    else if(i == data.size() - 1) {
                        queue.add(Time.getTime(hour, minute, "PM Networking Event"));
                    }
                }
                // ถ้า 4โมง และนาทียังน้อยกว่าเท่ากับ 60นาที
                else if(hour == 4 && minute <= 60 && afterNoon) {
                    if(minute == 60){
                        hour++;
                        minute -= 60;
                    }

                    // ถ้าไม่ใช่ตัวสุดท้ายใส่วันต่อไป
                    if (!(i == data.size() - 1)) {
                        queue.add(Time.getTime(hour, minute, "PM Networking Event"));
                        nextDay = df.formatDate(df.getTomorrow(nextDay)); //28/2/2565
                        queue.add("Day "+ day + " - " + nextDay + ":");
                        nextDay = df.reverseFormat(nextDay); //2022-02-28
                        day++;
                    }
                    hour = 9;
                    minute = 0;
                    afterNoon = false;
                }
                else if (minute >= 60) {
                    hour++;
                    minute -= 60;
                }
            }
        }
        queue.forEach(System.out::println);
        return queue;
    }
}