package train03;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;

public class DateFormat {
    public String formatDate(String date) {
        List<Integer> list = new ArrayList<>();

        Arrays.stream(date.split("-")).forEach(s -> list.add(Integer.parseInt(s)));
        LocalDate checkWeekend = LocalDate.of(list.get(0), list.get(1), list.get(2));

        checkWeekend = isWeekend(checkWeekend);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return checkWeekend.format(formatters);
    }

    public LocalDate isWeekend(LocalDate ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        if(day == DayOfWeek.SUNDAY) {
            ld = ld.plusDays(1);
        }
        else if(day == DayOfWeek.SATURDAY) {
            ld = ld.plusDays(2);
        }
        ld = ld.plusYears(543);
        return ld;
    }

    public String getNextDay(String today) {
        LocalDate ld = LocalDate.parse(today);
        ld = ld.plusDays(1);
        return String.valueOf(ld);
    }

    public String reverseFormat(String date){
        String[] arr = date.split("/");
        arr[2] = Integer.parseInt(arr[2]) - 543 + "";
        Collections.reverse(Arrays.asList(arr));
        return String.join("-", arr);
    }
}