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
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.exercise4.response.Exercise4Response;

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
					++i;
				} else {
					int time = Integer.parseInt(strCurrentLine.replaceAll("[^0-9]+", ""));
					er.setTitle(strCurrentLine);
					er.setDuration(time);
				}
				dataList.add(er);
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}
		return dataList;
	}

	public List<JSONObject> setAgenda(List<Exercise4Response> data) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mma", Locale.US);
		LocalTime localTime = LocalTime.of(9, 0);
		List<Exercise4Response> list = new ArrayList<>();
		String date = data.get(0).getDate();
		boolean morning = false;

		for (int i = 1; i < data.size(); i++) {
			int min = data.get(i).getDuration();
			int hour = min / 60;
			int minute = min % 60;

			if (localTime.equals(LocalTime.NOON) || (localTime.isAfter(LocalTime.NOON) && morning)) {
				list.add(new Exercise4Response("Lunch", df.format(localTime), date, 60));
				localTime = localTime.plusHours(1);
				morning = false;
			}
			if (localTime.plusHours(hour).plusMinutes(minute).isAfter(LocalTime.of(17, 0))) {
				list.add(new Exercise4Response("Networking Event", df.format(localTime), date, 0));
				date = getNextDay(date);
				localTime = LocalTime.of(9, 0);
				morning = true;
			}
			list.add(new Exercise4Response(data.get(i).getTitle(), df.format(localTime), date,
					data.get(i).getDuration()));
			localTime = localTime.plusHours(hour).plusMinutes(minute);
			if (i == (data.size() - 1)) {
				list.add(new Exercise4Response("Networking Event", df.format(localTime), date, 0));
			}
		}
		List<JSONObject> jsonData = StringCreateJson(list);
		return jsonData;
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

	public List<JSONObject> StringCreateJson(List<Exercise4Response> list) {
		// int i = 1;
		// List<JSONObject> jsonObjects1 = new ArrayList<JSONObject>();
		// List<JSONObject> jsonObjects2 = new ArrayList<JSONObject>();
		// List<JSONObject> data = new ArrayList<>();
		// JSONObject jsonObject = new JSONObject();
		// jsonObject.put("day", i);
		// jsonObject.put("date", list.get(0).getDate());
		// i++;

		// for (int j = 0; j < list.size(); j++) {
		// JSONObject jsonObject1 = new JSONObject();
		// JSONObject jsonObject2 = new JSONObject();
		// if(list.get(j).getDate() == list.get(0).getDate()){
		// jsonObject1.put("title", list.get(j).getTitle());
		// jsonObject1.put("time", list.get(j).getTime());
		// jsonObject1.put("duration", list.get(j).getDuration());
		// jsonObjects1.add(jsonObject1);
		// }else{
		// jsonObject2.put("title", list.get(j).getTitle());
		// jsonObject2.put("time", list.get(j).getTime());
		// jsonObject2.put("duration", list.get(j).getDuration());
		// jsonObjects2.add(jsonObject2);
		// }
		// }
		// jsonObject.put("list", jsonObjects1);
		// data.add(jsonObject);
		// System.out.println(data);

		List<JSONObject> jsonObjects = new ArrayList<>();
		List<JSONObject> jsonObjects2 = new ArrayList<JSONObject>();
		List<JSONObject> data = new ArrayList<>();
		String date = list.get(0).getDate();
		boolean test = true;

    Collection<Exercise4Response> map = list.stream().collect(Collectors.toMap(Exercise4Response::getDate, p -> p, (p, q) -> p)).values();

		for (int i = 1; i < map.size()+1; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("day", i);
			jsonObject.put("date", date);

			for (int j = 0; j < list.size(); j++) {
				JSONObject jsonObject1 = new JSONObject();
				JSONObject jsonObject2 = new JSONObject();
				if (list.get(j).getDate() == list.get(0).getDate() && test) {
					jsonObject1.put("title", list.get(j).getTitle());
					jsonObject1.put("time", list.get(j).getTime());
					jsonObject1.put("duration", list.get(j).getDuration());
					jsonObjects.add(jsonObject1);
				} else if (list.get(j).getDate() != list.get(0).getDate() && test) {
					jsonObject2.put("title", list.get(j).getTitle());
					jsonObject2.put("time", list.get(j).getTime());
					jsonObject2.put("duration", list.get(j).getDuration());
					jsonObjects2.add(jsonObject2);
				}
			}

			if (i == 1) {
				jsonObject.put("list", jsonObjects);
			} else {
				jsonObject.put("list", jsonObjects2);
			}
			date = getNextDay(date);
			data.add(jsonObject);
			test = false;
		}

		return data;
	}
}