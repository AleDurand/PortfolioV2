package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.PhotoModel;

@Component
public class PhotoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PhotoModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

}
