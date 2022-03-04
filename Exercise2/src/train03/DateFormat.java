package train03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;

public class DateFormat {
    public String formatDate (String date) {
        List<Integer> list = new ArrayList<>();
        String newDate = "";

        Arrays.stream(date.split("-")).forEach(s -> list.add(Integer.parseInt(s)));

        int day = list.get(2);
        LocalDate checkWeekend = LocalDate.of(list.get(0), list.get(1), day);

        if (isWeekend(checkWeekend) == 2) { //saturday
            list.set(2, day + 2);
        }
        if (isWeekend(checkWeekend) == 1) { //sunday
            list.set(2, day + 1);
        }

        for (int i = list.size() - 1; i >= 0; i--){
            if(i == 0){
                newDate += list.get(i) + 543;
            } else {
                newDate += list.get(i) + "/";
            }
        }
        return newDate;
    }

    public int isWeekend(LocalDate ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        if(day == DayOfWeek.SUNDAY) return 1;
        else if(day == DayOfWeek.SATURDAY) return 2;
        else return 0;
    }

    public String getTomorrow(String today) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.setTime(sdf.parse(today));
            cal.add(Calendar.DATE, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    public String reverseFormat(String date){
        String[] arr = date.split("/");
        arr[2] = Integer.parseInt(arr[2]) - 543 + "";
        Collections.reverse(Arrays.asList(arr));
        return String.join("-", arr);
    }
}