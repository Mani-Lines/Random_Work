package com.challenge.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.test.Repo.DocReadRepo;
import com.challenge.test.Repo.DocRepository;
import com.challenge.test.model.Doc;
import com.challenge.test.model.DocRead;
import com.challenge.test.model.User;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	DocRepository docRepository;

	@Autowired
	DocReadRepo docreadRepo; 

	public Map<String, List<String>> checkReadership(String fromDate, String toDate) {
		List<DocRead> list = fetchData(fromDate, toDate);
		Map<String, List<String>> docInfo = new HashMap<String, List<String>>();

		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {

				DocRead docRead = list.get(i);
				Doc doc = docRead.getDoc();
				User user = docRead.getUser();

				if (docInfo.containsKey(doc.getDocTitle())
						&& !docInfo.get(doc.getDocTitle()).contains(user.getUserName())) {
					docInfo.get(doc.getDocTitle()).add(user.getUserName());
				} else {
					List<String> userList = new ArrayList<String>();
					userList.add(user.getUserName());
					docInfo.put(doc.getDocTitle(), userList);
				}

			}
			return docInfo;
		}
		return docInfo;
	}

	public Map<String, Integer> checkPopularity(String fromDate, String toDate) throws ParseException {

		List<DocRead> list = fetchData(fromDate, toDate);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				String docName = list.get(i).getDoc().getDocTitle();
				if (map.containsKey(docName)) {
					map.put(docName, map.get(docName) + 1);
				} else {
					map.put(docName, 1);
				}
			}
			return sortByValue(map);
		}
		return map;

	}

	public List<DocRead> fetchData(String fromDate, String toDate) {
		List<DocRead> list = null;
		try {
			list = docreadRepo.find(new SimpleDateFormat("yyyy-MM-dd").parse(fromDate),
					new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(toDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
		return wordCounts.entrySet().stream().sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
