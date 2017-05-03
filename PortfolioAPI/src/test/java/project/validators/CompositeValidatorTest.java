package project.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import project.Application;
import project.models.AlbumModel;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class CompositeValidatorTest {

	@Autowired
	private CompositeValidator validator;

	@Autowired
	private AlbumValidator albumValidator;

	@Before
	public void setUp() {
		List<Validator> validators = new ArrayList<Validator>();
		validators.add(albumValidator);
		validator.setValidators(validators);
	}

	@Test
	public void supports() {
		assertTrue(validator.supports(AlbumModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		AlbumModel album = new AlbumModel();
		album.setId(1);
		album.setName("name");
		album.setDescription("description");
		BindException errors = new BindException(album, "album");
		ValidationUtils.invokeValidator(validator, album, errors);
		assertFalse(errors.hasErrors());
	}

}
