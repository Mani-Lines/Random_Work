package com.challenge.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.challenge.test.Repo.DocReadRepo;
import com.challenge.test.model.Doc;
import com.challenge.test.model.DocRead;
import com.challenge.test.model.User;
import com.challenge.test.service.Service;

@RunWith(MockitoJUnitRunner.class)
public class TestServices {

	@InjectMocks
	Service service;

	@Mock
	DocReadRepo docreadRepo;

	@Test
	public void testCheckReadershipService() {

		User user1 = new User(1, "user1", null);
		User user2 = new User(2, "user2", null);
		Doc doc = new Doc(1, "doc1", null);
		DocRead docRead1 = new DocRead(1, user1, doc, new Date());
		DocRead docRead2 = new DocRead(1, user2, doc, new Date());
		List<DocRead> docReadList = new ArrayList<DocRead>();
		docReadList.add(docRead1);
		docReadList.add(docRead2);

		when(docreadRepo.find(Mockito.any(), Mockito.any())).thenReturn(docReadList);

		assertEquals(1, service.checkReadership("2021-05-06", "06-May-2021 23:59:59").size());
	}

	@Test
	public void testCheckPopularityService() throws ParseException {

		User user1 = new User(1, "user1", null);
		User user2 = new User(2, "user2", null);
		Doc doc = new Doc(1, "doc1", null);
		DocRead docRead1 = new DocRead(1, user1, doc, new Date());
		DocRead docRead2 = new DocRead(1, user2, doc, new Date());
		List<DocRead> docReadList = new ArrayList<DocRead>();
		docReadList.add(docRead1);
		docReadList.add(docRead2);

		when(docreadRepo.find(Mockito.any(), Mockito.any())).thenReturn(docReadList);
		assertEquals(1, service.checkPopularity("2021-05-06", "06-May-2021 23:59:59").size());

	}

}
