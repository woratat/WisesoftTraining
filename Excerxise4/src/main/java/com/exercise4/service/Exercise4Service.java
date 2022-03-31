package com.exercise4.service;

import com.exercise4.response.Exercise4Response;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class Exercise4Service {

  public List<Exercise4Response> readFile(File fileToRead) {
    List<Exercise4Response> dataList = new ArrayList<>();
    String date = "";
    int i = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
      String strCurrentLine;
      while ((strCurrentLine = br.readLine()) != null) {
        Exercise4Response er = new Exercise4Response();
        if (i == 0) {
          date = strCurrentLine;
          er.setDate(date);
          er.setTime("");
          er.setTitle("");
          er.setDuration(0);
          ++i;
        } else {
          int time = Integer.parseInt(strCurrentLine.replaceAll("[^0-9]+", ""));
          er.setTime("");
          er.setTitle(strCurrentLine);
          er.setDate(date);
          er.setDuration(time);
        }
        dataList.add(er);
      }
    } catch (IOException e) {
      System.out.println("File not found.");
    }
    return dataList;
  }

  public List<Exercise4Response> setAgenda(List<Exercise4Response> data) {
    DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mma", Locale.US);
    LocalTime localTime = LocalTime.of(9, 0);
    List<Exercise4Response> list = new ArrayList<>();
    String date = data.get(0).getDate();
    boolean morning = false;
    int day = 1;

    list.add(new Exercise4Response("", "", ("Day " + day + " - " + formatDate(data.get(0).getDate()) + ":"), 0));
    day++;

    for (int i = 1; i < data.size(); i++) {
      int min = data.get(i).getDuration();
      int hour = min / 60;
      int minute = min % 60;

      if (
        localTime.equals(LocalTime.NOON) ||
        (localTime.isAfter(LocalTime.NOON) && morning)
      ) {
        list.add(new Exercise4Response("Lunch", (df.format(localTime)), date, 60));
        localTime = localTime.plusHours(1);
        morning = false;
      }
      if (
        localTime
          .plusHours(hour)
          .plusMinutes(minute)
          .isAfter(LocalTime.of(17, 0))
      ) {
        date = getNextDay(date);
        list.add(new Exercise4Response("Networking Event", (df.format(localTime)), date + ":", 0));
        list.add(new Exercise4Response("", "", "Day " + day + " - " + formatDate(date) + ":", 0));
        localTime = LocalTime.of(9, 0);
        morning = true;
        day++;
      }
      list.add(new Exercise4Response(data.get(i).getTitle(), df.format(localTime), date + ":", data.get(i).getDuration()));
      localTime = localTime.plusHours(hour).plusMinutes(minute);
      if (i == (data.size() - 1)) {
        list.add(new Exercise4Response("Networking Event", (df.format(localTime)), date + ":", 0));
      }
    }
    return list;
  }

  public static String formatDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate ld = LocalDate.parse(date);
    ld = isWeekend(ld);
    ThaiBuddhistDate tbd = ThaiBuddhistDate.from(ld);
    return tbd.format(formatter);
  }

  public static LocalDate isWeekend(LocalDate ld) {
    DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
    if (day == DayOfWeek.SUNDAY) ld = ld.plusDays(1); else if (
      day == DayOfWeek.SATURDAY
    ) ld = ld.plusDays(2);
    return ld;
  }

  public static String getNextDay(String day) {
    LocalDate ld = LocalDate.parse(day);
    ld = ld.plusDays(1);
    if (isWeekend(ld) != ld) return String.valueOf(isWeekend(ld));
    return String.valueOf(ld);
  }
}