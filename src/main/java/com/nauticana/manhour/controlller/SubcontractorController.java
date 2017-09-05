package com.nauticana.manhour.controlller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.manhour.service.SubcontractorService;

@Controller
@RequestMapping("/sc")
public class SubcontractorController {

	@Autowired
	private SubcontractorService scs;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws IOException {
		
		// Read data and assign to model and view object
		List<Subcontractor> records = scs.findAll();
		ModelAndView model = new ModelAndView("list");
		model.addObject("records", records);
		return model;
	}
}
