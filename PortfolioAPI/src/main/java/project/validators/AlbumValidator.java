package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.AlbumModel;

@Component
public class AlbumValidator implements Validator {

	public static final int MAXIMUM_NAME_LENGTH = 45;
	public static final int MAXIMUM_DESCRIPTION_LENGTH = 45;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return AlbumModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AlbumModel album = (AlbumModel) target;

		if (album.getName() == null) {
			errors.rejectValue("name", "album.name.not_null", "{album.name.not_null}");
		} else {
			if (album.getName().trim().isEmpty()) {
				errors.rejectValue("name", "album.name.not_empty", "{album.name.not_empty}");
			}
			if (album.getName().length() > MAXIMUM_NAME_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_NAME_LENGTH };
				errors.rejectValue("name", "album.name.size", args, "{album.name.size}");
			}
		}

		if (album.getDescription() == null) {
			errors.rejectValue("description", "album.description.not_null", "{album.description.not_null}");
		} else {
			if (album.getDescription().trim().isEmpty()) {
				errors.rejectValue("description", "album.description.not_empty", "{album.description.not_empty}");
			}
			if (album.getDescription().length() > MAXIMUM_DESCRIPTION_LENGTH) {
				Object[] args = new Object[] { MAXIMUM_DESCRIPTION_LENGTH };
				errors.rejectValue("description", "album.description.size", args, "{album.description.size}");
			}
		}

	}

}
