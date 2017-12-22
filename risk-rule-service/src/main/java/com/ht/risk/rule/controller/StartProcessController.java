package com.ht.risk.rule.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;



@RestController
public class StartProcessController {

	@Resource
	private RuntimeService runtimeService;

	@Resource
	RepositoryService repositoryService;

	@Resource
	TaskService taskService;

	
	
	@GetMapping(value = "/startProcess/{key}")
	public void startHireProcess(@PathVariable("key") String key) throws Exception {
		System.out.println("########################### startHireProcess ! processId : " + key);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("applicantName", "John Doe");
		variables.put("email", "john.doe@activiti.com");
		variables.put("phoneNumber", "123456789");
		run();
	}

	private void run() throws Exception {
	}

}