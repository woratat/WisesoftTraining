package com.exercise4.service;

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
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.exercise4.model.Exercise4Model;

@Service
public class Exercise4Service {

	public JSONObject readFile(File fileToRead) {
		List<Exercise4Model> dataList = new LinkedList<>();
		JSONObject jsonObject = new JSONObject();
		int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
			String strCurrentLine;
			while ((strCurrentLine = br.readLine()) != null) {
				Exercise4Model er = new Exercise4Model();
				if (i == 0) {
					jsonObject.put("date", strCurrentLine);
					++i;
				} else {
					int time = Integer.parseInt(strCurrentLine.replaceAll("[^0-9]+", ""));
					er.setTitle(strCurrentLine);
					er.setDuration(time);
				}
				dataList.add(er);
			}
			dataList.remove(0);
			jsonObject.put("list", dataList);
		} catch (IOException e) {
			System.out.println("File not found.");
		}

		return jsonObject;
	}

	public List<JSONObject> setStringJson(JSONObject jsonObject) {
		List<Exercise4Model> dataList = new ArrayList<>();
		try {
			if (jsonObject.containsKey("date") && jsonObject.containsKey("list")) {
				List<JSONObject> arr = (List<JSONObject>) jsonObject.get("list");
				for (int i = 0; i < arr.size(); i++) {
					Exercise4Model er = new Exercise4Model();
					er.setTitle(arr.get(i).get("title").toString());
					er.setDuration(Integer.parseInt(arr.get(i).get("duration").toString()));
					dataList.add(er);
				}
				jsonObject.put("list", dataList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<JSONObject> json = setAgenda(jsonObject);
		return json;
	}

	public List<JSONObject> setAgenda(JSONObject jsonObject) {
		List<Exercise4Model> data = (List<Exercise4Model>) jsonObject.get("list");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mma", Locale.US);
		LocalTime localTime = LocalTime.of(9, 0);
		List<Exercise4Model> list = new ArrayList<>();
		String date = jsonObject.get("date").toString();
		boolean morning = false;

		for (int i = 0; i < data.size(); i++) {
			int min = data.get(i).getDuration();
			int hour = min / 60;
			int minute = min % 60;

			if (localTime.equals(LocalTime.NOON) || (localTime.isAfter(LocalTime.NOON) && morning)) {
				list.add(new Exercise4Model("Lunch", df.format(localTime), date, 60));
				localTime = localTime.plusHours(1);
				morning = false;
			}
			if (localTime.plusHours(hour).plusMinutes(minute).isAfter(LocalTime.of(17, 0))) {
				list.add(new Exercise4Model("Networking Event", df.format(localTime), date, 0));
				date = getNextDay(date);
				localTime = LocalTime.of(9, 0);
				morning = true;
			}
			list.add(new Exercise4Model(data.get(i).getTitle(), df.format(localTime), date,
					data.get(i).getDuration()));
			localTime = localTime.plusHours(hour).plusMinutes(minute);
			if (i == (data.size() - 1)) {
				list.add(new Exercise4Model("Networking Event", df.format(localTime), date, 0));
			}
		}
		List<JSONObject> json =  CreateJson(list);
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
		if (day == DayOfWeek.SUNDAY)
			ld = ld.plusDays(1);
		else if (day == DayOfWeek.SATURDAY)
			ld = ld.plusDays(2);
		return ld;
	}

	public static String getNextDay(String day) {
		LocalDate ld = LocalDate.parse(day);
		ld = ld.plusDays(1);
		if (isWeekend(ld) != ld)
			return String.valueOf(isWeekend(ld));
		return String.valueOf(ld);
	}

	public List<JSONObject> CreateJson(List<Exercise4Model> list) {
		List<String> myDate = new ArrayList<>();
		List<JSONObject> data = new ArrayList<>();
		LinkedHashSet<String> sets = new LinkedHashSet<>();
		String date = list.get(0).getDate();

		list.forEach(s -> sets.add(s.getDate()));
		myDate.addAll(sets);

		for (int i = 0; i <= myDate.size() - 1; i++) {
			List<JSONObject> jsonObjects = new ArrayList<>();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("day", i + 1);
			jsonObject.put("date", date);
			jsonObject.put("dateTH", formatDate(date));

			for (int j = 0; j < list.size(); j++) {
				JSONObject json = new JSONObject();
				if (list.get(j).getDate() == myDate.get(i)) {
					json.put("title", list.get(j).getTitle());
					json.put("time", list.get(j).getTime());
					json.put("duration", list.get(j).getDuration());
					jsonObjects.add(json);
				} else {
					continue;
				}
			}

			date = getNextDay(date);
			jsonObject.put("list", jsonObjects);
			data.add(jsonObject);
		}
		return data;
	}
}