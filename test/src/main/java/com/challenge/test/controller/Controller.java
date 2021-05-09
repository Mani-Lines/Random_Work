package com.challenge.test.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.test.service.Service;

@RestController
public class Controller {
	
	@Autowired
	private Service service;

	@GetMapping(value = "/readership")
	public ResponseEntity<Map<String, List<String>>> getReaderships(@RequestParam String fromDate,
			@RequestParam String toDate) {
		Map<String, List<String>> map = null;
		try {
			map = service.checkReadership(fromDate, toDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (map.size() != 0)
			return ResponseEntity.status(HttpStatus.FOUND).body(map);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new HashMap<String, List<String>>());
	}

	@GetMapping(value = "/popularity")
	public ResponseEntity<Map<String, Integer>> getPopularity(@RequestParam String fromDate, @RequestParam String toDate) {
		Map<String, Integer> map = null;
		try {
			map = service.checkPopularity(fromDate, toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (map.size() != 0)
			return ResponseEntity.status(HttpStatus.FOUND).body(map);
		else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new HashMap<String, Integer>());
	}

}
