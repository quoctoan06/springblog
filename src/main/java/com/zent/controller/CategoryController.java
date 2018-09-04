package com.zent.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zent.dao.ICategoryDAO;
import com.zent.entity.Category;
import com.zent.util.Constant;
import com.zent.validator.CategoryValidator;

@Controller
public class CategoryController {
	@Autowired
	private ICategoryDAO categoryDAO;
	
	@Autowired
	private CategoryValidator categoryValidator;

	// getters and setters
	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public CategoryValidator getCategoryValidator() {
		return categoryValidator;
	}

	public void setCategoryValidator(CategoryValidator categoryValidator) {
		this.categoryValidator = categoryValidator;
	}
	
	// đăng kí sử dụng validatior
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(categoryValidator);
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String add(Model model, HttpSession session) {
		if(session.getAttribute("userLogin") == null) {
			return "redirect:login";
		}
		
		model.addAttribute("newCategory", new Category());
		return "addCategory";
		
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public String addCategory(Model model, @ModelAttribute("newCategory") @Validated Category category,
			 BindingResult rs) {
		if(rs.hasErrors()) {
			return "addCategory";
		}
		
		categoryDAO.addCategory(category);
		return "redirect:/category";
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public String deleteCategory(@RequestParam("id") Integer id) {
		categoryDAO.deleteCategory(id);
		return "redirect:/category";
	}
	
	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	public String update(Model model, HttpSession session, @RequestParam("id") Integer id) {
		if(session.getAttribute("userLogin") == null) {
			return "redirect:login";
		}
		
		Category category = categoryDAO.getCategoryById(id);
		model.addAttribute("categoryCurrent", category);
		model.addAttribute("id", id);
		model.addAttribute("updateCategory", new Category());
		return "editCategory";
	}
	
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String updateCategory(Model model, @ModelAttribute("updateCategory") @Validated Category category, 
			@RequestParam("id") Integer id, BindingResult rs) {
		if(rs.hasErrors()) {
			return "editCategory";
		}
		
		categoryDAO.updateCategory(category, id);
		return "redirect:/category";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String category(HttpSession session, Model model,
			@RequestParam(required = false, value = "curPage") Integer curPage,
			@RequestParam(required = false, value = "curSection") Integer curSection,
			@RequestParam(required = false, value = "status") String curStatus) {
		
		if (session.getAttribute("userLogin") == null) {
			return "redirect:login";
		}
		model.addAttribute("category", new Category());
		
		Integer page = 1;
		Integer section = 1;
		Integer nextSection = 1;
		Integer prevSection = 1;
		if (curPage == null) {		// get curPage's value
			page = 1;
		}
		else {
			page = curPage.intValue();
		}

		if (curSection == null) {	// get curSection's value
			section = 1;
		}
		else {
			section = curSection.intValue();
		}
		
		if ("next".equals(curStatus)) {		// next section
			section++;
			nextSection = section;
			page = (section - 1) * 3 + 1;
		} else if ("prev".equals(curStatus)) {
			prevSection = section;
			if (section <= 0)
				section = 1;
			page = (section - 1) * 3 + 1;
		} else if (curStatus == null && curSection != null) {
			section = curSection;
			prevSection = curSection - 1;
			nextSection = curSection + 1;
		}
		Integer count = categoryDAO.countAll(new Category());		// tổng số category
		Integer numberOfPage = (int) Math.ceil((double) count / Constant.PAGE_SIZE_CATEGORY);		// số page
		Integer numberOfSection = (int) Math.ceil((double) numberOfPage / 3);						// số section
		
		model.addAttribute("startPage", (section - 1) * 3 + 1);		// trang bắt đầu
		if (section == numberOfSection) {
			model.addAttribute("endPage", numberOfPage); 			// trang kết thúc section

		} else {
			model.addAttribute("endPage", section * 3);				// trang kết thúc section
		}
		ArrayList<Category> list = (ArrayList<Category>) categoryDAO.getAll(new Category(), page);
		model.addAttribute("categoryList", list);
		model.addAttribute("curSection", section);
		model.addAttribute("activePage", page);						// page hiện tại
		model.addAttribute("nextSection", nextSection);
		model.addAttribute("prevSection", prevSection);
		model.addAttribute("numberOfSection", numberOfSection);
		return "manageCategory";
	}
	
	@RequestMapping(value = "/searchCategory", method = RequestMethod.GET)
	public String search(HttpSession session, Model model, @ModelAttribute("category") Category category,
			@RequestParam(required = false, value = "curPage") Integer curPage,
			@RequestParam(required = false, value = "section") Integer curSection,
			@RequestParam(required = false, value = "status") String curStatus) {
		if (category == null) {
			category = new Category();
		}
		if (session.getAttribute("userLogin") == null) {
			return "redirect:login";
		}
		Integer page = 1;
		Integer section = 1;
		Integer nextSection = 1;
		Integer prevSection = 1;
		if (curPage == null)
			page = 1;
		else
			page = curPage.intValue();

		if (curSection != null) {
			section = curSection.intValue();

		} else
			section = 1;
		if ("next".equals(curStatus)) {
			section++;
			nextSection = section;
			page = (section - 1) * 3 + 1;
		} else if ("prev".equals(curStatus)) {
			prevSection = section;
			if (section <= 0)
				section = 1;
			page = (section - 1) * 3 + 1;
		} else if (curStatus == null && curSection != null) {
			section = curSection;
			prevSection = curSection - 1;
			nextSection = curSection + 1;
		}
		Integer count = categoryDAO.countAll(category);// tổng số sản phẩm
		Integer numberPage = (int) Math.ceil((double) count / Constant.PAGE_SIZE_CATEGORY);// tổng page
		Integer numberOfSection = (int) Math.ceil((double) numberPage / 3);// tổng section
		model.addAttribute("startPage", (section - 1) * 3 + 1);// trang bắt đầu
		if (section == numberOfSection) {
			model.addAttribute("endPage", numberPage); // trang kết thúc section

		} else {
			model.addAttribute("endPage", section * 3);// trang kết thúc section

		}
		model.addAttribute("curSection", section);
		ArrayList<Category> list = (ArrayList<Category>) categoryDAO.getAll(category, page);
		model.addAttribute("categoryList", list);
		model.addAttribute("activePage", page);// page hiện tại
		model.addAttribute("nextSection", nextSection);
		model.addAttribute("prevSection", prevSection);
		model.addAttribute("numberOfSection", numberOfSection);
		return "manageCategory";
	}
}
