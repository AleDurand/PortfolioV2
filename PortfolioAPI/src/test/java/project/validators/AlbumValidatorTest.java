package project.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import project.Application;
import project.models.AlbumModel;
import project.utils.TestUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class AlbumValidatorTest {

	@Autowired
	private AlbumValidator validator;

	@Test
	public void supports() {
		assertTrue(validator.supports(AlbumModel.class));
		assertFalse(validator.supports(Object.class));
	}

	@Test
	public void isValid() {
		AlbumModel album = new AlbumModel();
		album.setName("name");
		album.setDescription("description");
		BindException errors = new BindException(album, "album");
		ValidationUtils.invokeValidator(validator, album, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void invalidNameNull() {
		AlbumModel album = new AlbumModel();
		album.setName(null);
		album.setDescription("description");
		BindException errors = new BindException(album, "album");
		ValidationUtils.invokeValidator(validator, album, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNameEmpty() {
		AlbumModel album = new AlbumModel();
		album.setName(" ");
		album.setDescription("description");
		BindException errors = new BindException(album, "album");
		ValidationUtils.invokeValidator(validator, album, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidNameTooLong() {
		AlbumModel album = new AlbumModel();
		album.setName(TestUtil.createStringWithLength(AlbumValidator.MAXIMUM_NAME_LENGTH + 1));
		album.setDescription("description");
		BindException errors = new BindException(album, "album");
		ValidationUtils.invokeValidator(validator, album, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidDescriptionNull() {
		AlbumModel album = new AlbumModel();
		album.setName("name");
		album.setDescription(null);
		BindException errors = new BindException(album, "album");
		ValidationUtils.invokeValidator(validator, album, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void invalidDescriptionEmpty() {
		AlbumModel album = new AlbumModel();
		album.setName("name");
		album.setDescription(" ");
		BindException errors = new BindException(album, "album");
		ValidationUtils.invokeValidator(validator, album, errors);
		assertTrue(errors.hasErrors());
	}
	
	@Test
	public void invalidDescriptionTooLong() {
		AlbumModel album = new AlbumModel();
		album.setName("name");
		album.setDescription(TestUtil.createStringWithLength(AlbumValidator.MAXIMUM_DESCRIPTION_LENGTH + 1));
		BindException errors = new BindException(album, "album");
		ValidationUtils.invokeValidator(validator, album, errors);
		assertTrue(errors.hasErrors());
	}

}
