package com.searchservice.test1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.searchservice.test1.service.SearchServiceTest;

@RestController
@RequestMapping("/search")
public class SearchDeleteTestController {

	@Autowired
	private SearchServiceTest searchServiceDelete;
	@PostMapping("/{clientId}/{tableName}")
	public boolean insertRecord(@PathVariable("clientId") int clientId,@PathVariable("tableName") String tableName) {
		return searchServiceDelete.insertDeleteRecord(clientId, "");
	}
}
