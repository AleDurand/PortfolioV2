package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.models.AlbumModel;
import project.services.PhotoService;
import project.validators.PhotoValidator;

@RestController
@RequestMapping(value = "/albums/{albumId}")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private PhotoValidator photoValidator;

	@RequestMapping(value = "/photos", method = RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<AlbumModel> create(@PathVariable("albumId") Integer albumId, @RequestParam("file") MultipartFile file) {
		photoService.addPhoto(albumId, file);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/photos/{photoId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("albumId") Integer albumId, @PathVariable("photoId") Integer photoId) {
		photoService.deletePhoto(albumId, photoId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(photoValidator);
	}
}
