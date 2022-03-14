package train03;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateFormat {
    public static String formatDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ld = LocalDate.parse(date);
        ld = isWeekend(ld);
        ld = ld.plusYears(543);
        return ld.format(formatter);
    }

    public static LocalDate isWeekend(LocalDate ld) {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        if(day == DayOfWeek.SUNDAY)
            ld = ld.plusDays(1);
        else if(day == DayOfWeek.SATURDAY)
            ld = ld.plusDays(2);
        return ld;
    }

    public static String getNextDay(String day) {
        LocalDate ld = LocalDate.parse(day);
        ld = ld.plusDays(1);
        if(isWeekend(ld) != ld)
            return String.valueOf(isWeekend(ld));
        return String.valueOf(ld);
    }
}