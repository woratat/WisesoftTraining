package com.exercise4.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exercise4.response.Exercise4Response;
import com.exercise4.service.Exercise4Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Exercise4Controller {

	@Autowired
	Exercise4Service exercise4Service;

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();

		try {
			File fileToRead = new File(System.getProperty("user.dir") + "\\uploads\\" + fileName);
			file.transferTo(fileToRead);
			List<Exercise4Response> dataList = exercise4Service.readFile(fileToRead);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(exercise4Service.setAgenda(dataList));
			return jsonString;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// @GetMapping("/read")
	// public List<JSONObject> getData() {
	// return exercise4Service.StringCreateJson();
	// }
}
