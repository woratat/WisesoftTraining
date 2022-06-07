package com.exercise4.service;

import com.exercise4.model.Exercise4Model;
import com.exercise4.model.Exercise4JsonModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Exercise4Service {

  public HashMap<String, Object> readTextFile(File fileToRead) {
    List<Exercise4Model> dataList = new LinkedList<>();
    HashMap<String, Object> hm = new HashMap<>();
    int i = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
      String strCurrentLine;
      while ((strCurrentLine = br.readLine()) != null) {
        Exercise4Model model = new Exercise4Model();
        if (i == 0) {
          hm.put("date", strCurrentLine);
          ++i;
        } else {
          int time = Integer.parseInt(strCurrentLine.replaceAll("[^0-9]+", ""));
          model.setTitle(strCurrentLine);
          model.setDuration(time);
        }
        dataList.add(model);
      }
      dataList.remove(0);
      hm.put("list", dataList);
    } catch (IOException e) {
      System.out.println("File not found.");
    }

    return hm;
  }

  public JSONObject stringToJson(JSONObject jsonObject) {
    List<Exercise4Model> dataList = new ArrayList<>();
    try {
      if (jsonObject.containsKey("date") && jsonObject.containsKey("list")) {
        List<JSONObject> jsonList = (List<JSONObject>) jsonObject.get("list");
        for (int i = 0; i < jsonList.size(); i++) {
          Exercise4Model er = new Exercise4Model();
          er.setTitle(jsonList.get(i).get("title").toString());
          er.setDuration(
            Integer.parseInt(jsonList.get(i).get("duration").toString())
          );
          dataList.add(er);
        }
        jsonObject.put("list", dataList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return jsonObject;
  }

  public String setAgenda(HashMap<String, Object> obj) {
    List<Exercise4Model> data = (List<Exercise4Model>) obj.get("list");
    DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mma", Locale.US);
    String date = obj.get("date").toString();
    List<Exercise4Model> list = new ArrayList<>();
    LocalTime localTime = LocalTime.of(9, 0);
    boolean morning = false;

    for (int i = 0; i < data.size(); i++) {
      int min = data.get(i).getDuration();
      int hour = min / 60;
      int minute = min % 60;

      if (
        localTime.equals(LocalTime.NOON) ||
        (localTime.isAfter(LocalTime.NOON) && morning)
      ) {
        list.add(new Exercise4Model("Lunch", df.format(localTime), date, 60));
        localTime = localTime.plusHours(1);
        morning = false;
      }
      if (
        localTime
          .plusHours(hour)
          .plusMinutes(minute)
          .isAfter(LocalTime.of(17, 0))
      ) {
        list.add(
          new Exercise4Model("Networking Event", df.format(localTime), date, 0)
        );
        date = getNextDay(date);
        localTime = LocalTime.of(9, 0);
        morning = true;
      }
      list.add(
        new Exercise4Model(
          data.get(i).getTitle(),
          df.format(localTime),
          date,
          data.get(i).getDuration()
        )
      );
      localTime = localTime.plusHours(hour).plusMinutes(minute);
      if (i == (data.size() - 1)) {
        list.add(
          new Exercise4Model("Networking Event", df.format(localTime), date, 0)
        );
      }
    }
    String json = mapJsonArray(list);
    return json;
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

  public String mapJsonArray(List<Exercise4Model> list) {
    List<String> dateList = new ArrayList<>();
    List<Object> data = new ArrayList<>();

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    list.forEach(
      s -> {
        if (!dateList.contains(s.getDate())) {
          dateList.add(s.getDate());
        }
      }
    );

    for (int i = 0; i <= dateList.size() - 1; i++) {
      HashMap<String, Object> hm = new HashMap<>();
      List<Object> obj = new ArrayList<>();

      hm.put("day", i + 1);
      hm.put("date", dateList.get(i));
      hm.put("dateTH", formatDate(dateList.get(i)));

      for (int j = 0; j < list.size(); j++) {
        Exercise4JsonModel jsonModel = new Exercise4JsonModel();

        if (list.get(j).getDate() == dateList.get(i)) {
          jsonModel.setDuration(list.get(j).getDuration());
          jsonModel.setTime(list.get(j).getTime());
          jsonModel.setTitle(list.get(j).getTitle());
        } else {
          continue;
        }
        obj.add(jsonModel);
      }
      hm.put("list", obj);
      data.add(hm);
    }

    String json = gson.toJson(data);
    return json;
  }

  public static void saveFile(String fileName, MultipartFile multipartFile)
    throws IOException {
    Path uploadDirectory = Paths.get("file-upload");
    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path filePath = uploadDirectory.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      throw new IOException("Error saving uploaded file: " + fileName, ioe);
    }
  }
}
