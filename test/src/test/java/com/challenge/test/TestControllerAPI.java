package com.challenge.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.challenge.test.controller.Controller;
import com.challenge.test.model.Doc;
import com.challenge.test.model.DocRead;
import com.challenge.test.model.User;
import com.challenge.test.service.Service;

@RunWith(MockitoJUnitRunner.class)
public class TestControllerAPI {

	@Mock
	private Service service;

	@InjectMocks
	Controller controller;

	@Test
	public void checkReadershipFound() {

		User user = new User(1, "user1", null);
		Doc doc = new Doc(1, "doc1", null);
		DocRead docRead = new DocRead(1, user, doc, new Date());
		List<DocRead> docReadList = new ArrayList<DocRead>();
		docReadList.add(docRead);

		Map<String, List<String>> eMap = new HashMap<String, List<String>>();
		List<String> userList = new ArrayList<String>();
		userList.add(docRead.getUser().getUserName());
		eMap.put(docRead.getDoc().getDocTitle(), userList);

		when(service.checkReadership(Mockito.anyString(), Mockito.any())).thenReturn(eMap);
		assertEquals(eMap, controller.getReaderships("2021-05-06", "06-May-2021 23:59:59").getBody());
		assertEquals(HttpStatus.FOUND, controller.getReaderships("2021-05-06", "06-May-2021 23:59:59").getStatusCode());

	}

	@Test
	public void checkReadershipNotFound() {

		Map<String, List<String>> eMap = new HashMap<String, List<String>>();

		when(service.checkReadership(Mockito.anyString(), Mockito.any())).thenReturn(eMap);
		assertEquals(HttpStatus.NO_CONTENT,
				controller.getReaderships("2021-05-06", "06-May-2021 23:59:59").getStatusCode());

	}

	@Test
	public void checkPopularityFound() throws ParseException {

		User user = new User(1, "user1", null);
		Doc doc = new Doc(1, "doc1", null);
		DocRead docRead = new DocRead(1, user, doc, new Date());

		Map<String, Integer> eMap = new HashMap<String, Integer>();
		eMap.put(docRead.getDoc().getDocTitle(), docRead.getUser().getUserId());

		when(service.checkPopularity(Mockito.anyString(), Mockito.anyString())).thenReturn(eMap);
		assertEquals(eMap, controller.getPopularity("2021-05-06", "06-May-2021 23:59:59").getBody());
		assertEquals(HttpStatus.FOUND, controller.getPopularity("2021-05-06", "06-May-2021 23:59:59").getStatusCode());

	}

	@Test
	public void checkPopularityNotFound() throws ParseException {

		Map<String, Integer> eMap = new HashMap<String, Integer>();

		when(service.checkPopularity(Mockito.anyString(), Mockito.any())).thenReturn(eMap);
		assertEquals(HttpStatus.NO_CONTENT,
				controller.getPopularity("2021-05-06", "06-May-2021 23:59:59").getStatusCode());

	}

}
