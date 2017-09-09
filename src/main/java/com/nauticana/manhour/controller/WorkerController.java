package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.Worker;

@Controller
@RequestMapping("/worker")
public class WorkerController  extends AbstractController<Worker,Integer> {

	public WorkerController() {
		super(Worker.tableName, "workerList", "workerEdit");
	}

}
