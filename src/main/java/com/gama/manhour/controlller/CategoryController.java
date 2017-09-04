package com.gama.manhour.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gama.manhour.model.Category;
import com.gama.manhour.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController extends AbstractController<Category> {

	@Autowired
	private CategoryService categoryService;
	
	public CategoryController() {
		super("CATEGORY", "categoryList", "categoryEdit");
		this.modelService = categoryService;
	}

}
