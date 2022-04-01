package com.exercise4.controller;

import java.io.File;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exercise4.service.Exercise4Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Exercise4Controller {

	@Autowired
	private Exercise4Service exercise4Service;

	@PostMapping("/uploadFile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();

		try {
			File fileToRead = new File(System.getProperty("user.dir") + "\\uploads\\" + fileName);
			file.transferTo(fileToRead);
			JSONObject jsonObject = exercise4Service.readFile(fileToRead);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(exercise4Service.setAgenda(jsonObject));
			return jsonString;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/jsonText")
	public String convertJson(@RequestBody String text) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(text);
			exercise4Service.setStringJson(jsonObject);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(exercise4Service.setAgenda(jsonObject));
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
