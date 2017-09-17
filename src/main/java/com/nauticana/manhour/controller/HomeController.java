package com.nauticana.manhour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.utils.Icons;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.Utils;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView home(HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		ModelAndView model = new ModelAndView("home");
		model.addObject("userCaption", username);
		model.addObject(Icons.MENU, Icons.getIcon(Icons.MENU));
		model.addObject(Icons.LOGIN, Icons.getIcon(Icons.LOGIN));
		model.addObject(Icons.LOGOFF, Icons.getIcon(Icons.LOGOFF));
		model.addObject("menu", (String) session.getAttribute(Labels.MENU));
		return model;
	}
	
}
