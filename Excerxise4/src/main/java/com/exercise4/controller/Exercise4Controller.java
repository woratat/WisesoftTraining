package com.exercise4.controller;

import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exercise4.service.Exercise4Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class Exercise4Controller {

	@Autowired
	private Exercise4Service exercise4Service;

	@Operation(summary = "This API uploads .txt file and return data as JSON")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Uploaded successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Not available", content = @Content) })
	@PostMapping(path = "/file", consumes = "multipart/form-data", produces = "application/json")
	public ResponseEntity<JSONArray> handleFileUpload(@RequestPart("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		try {
			File fileToRead = new File(System.getProperty("user.dir") + "\\uploads\\" + fileName);
			file.transferTo(fileToRead);
			JSONObject jsonObject = exercise4Service.readFile(fileToRead);
			JSONArray json = exercise4Service.setAgenda(jsonObject);
			return ResponseEntity.ok().body(json);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Operation(summary = "This API retrieve JSON string format and return data as new JSON")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Uploaded successfully", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Not available", content = @Content) })
	@PostMapping(path = "/json")
	public ResponseEntity<JSONArray> convertJson(@RequestBody String text) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(text);
			JSONArray json = exercise4Service.setStringJson(jsonObject);
			return ResponseEntity.ok().body(json);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}
