package train03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.*;

public class DateFormat {
    public String formatDate (String date) {
        Stack<String> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        String newDate = "";

        for (String s : date.split("-")){
            stack.push(s);
            list.add(Integer.parseInt(s));
        }

        int day = list.get(2);
        LocalDate checkWeekend = LocalDate.of(list.get(0), list.get(1), day);

        if (isWeekend(checkWeekend) == 2) { //saturday
            stack.pop();
            stack.push(day + 2 + "");
        }
        if (isWeekend(checkWeekend) == 1) { //sunday
            stack.pop();
            stack.push(day + 1 + "");
        }

        while(!stack.empty()){
            if (stack.size() > 1){
                newDate += stack.pop() + "/";
            } else {
                newDate += Integer.parseInt(stack.pop()) + 543;
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
            if(today.substring(today.length() - 3).startsWith("-")){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cal.setTime(sdf.parse(today));
                cal.add(Calendar.DATE, 1);
            } else {
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy"); //more than 2 days
                cal.setTime(sdf2.parse(today));
                cal.add(Calendar.DATE, 1);
                cal.add(Calendar.YEAR, -543);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
}