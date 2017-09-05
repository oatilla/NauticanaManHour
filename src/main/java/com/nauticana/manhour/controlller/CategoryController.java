package com.nauticana.manhour.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.Category;

@Controller
@RequestMapping("/category")
public class CategoryController extends AbstractController<Category, Integer> {

	public CategoryController() {
		super("CATEGORY", "categoryList", "categoryEdit");
	}

}
