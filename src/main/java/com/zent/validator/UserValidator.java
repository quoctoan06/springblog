package com.zent.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zent.entity.User;

@Component("userValidator")
public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if (user.getUsername() == null || user.getUsername().trim().equals("")) {
			errors.rejectValue("username", "user.username.empty");
		}
		
		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			errors.rejectValue("password", "user.password.empty");
		}
	}
	
}
