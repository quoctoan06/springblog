package com.zent.controller;

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

import com.zent.dao.IUserDAO;
import com.zent.entity.User;
import com.zent.util.VerifyUtils;
import com.zent.validator.UserValidator;

@Controller
public class LoginController {
	@Autowired
	IUserDAO userDAO;
	
	@Autowired
	UserValidator userValidator;
	
	// getters and setters
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserValidator getUserValidator() {
		return userValidator;
	}

	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	// đăng kí sử dụng validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value = "/manager")
	public String checkLogin(Model model, @ModelAttribute("user") @Validated User user, BindingResult bindingResult,
			HttpSession session, @RequestParam("g-recaptcha-response") String captcha) {
		if(bindingResult.hasErrors()) {
			return "login";
		}
		
		if(!VerifyUtils.verify(captcha)) {
			model.addAttribute("captcha", "0");
			return "login";
		}
		
		if(userDAO.checkLogin(user) == null) {
			model.addAttribute("notExistUser", "0");
			return "login";
		} else {
			User userLogin = userDAO.checkLogin(user);
			session.setAttribute("userLogin", userLogin);
			return "manager";
		}
		
	}
	
	
	
}
