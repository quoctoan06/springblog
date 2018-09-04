package com.zent.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zent.entity.Category;

@Component("categoryValidator")
public class CategoryValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Category category = (Category) target;
		if(category.getCode() == null || category.getCode().trim().equals("") || 
				category.getName() == null || category.getName().trim().equals("") || 
				category.getDescription() == null || category.getDescription().trim().equals("")) {
			errors.rejectValue("error", "notCategory");
		}
		
	}
}
