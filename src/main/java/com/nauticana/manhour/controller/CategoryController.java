package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.Category;

@Controller
@RequestMapping("/category")
public class CategoryController extends AbstractController<Category, Integer> {

//	@Autowired
//	protected CategoryService modelService;

	public CategoryController() {
		super(Category.tableName, "categoryList", "categoryEdit");
	}

}
