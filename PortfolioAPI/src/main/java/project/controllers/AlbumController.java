package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.models.AlbumModel;
import project.services.AlbumService;
import project.validators.AlbumValidator;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@Autowired
	private AlbumValidator albumValidator;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<AlbumModel> create(@Validated @RequestBody AlbumModel album) {
		AlbumModel toReturn = albumService.create(album);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AlbumModel> read(@PathVariable("id") Integer id) {
		AlbumModel toReturn = albumService.read(id);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ResponseEntity<AlbumModel> update(@PathVariable("id") Integer id, @Validated @RequestBody AlbumModel album) {
		AlbumModel toReturn = albumService.update(id, album);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		albumService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Page<AlbumModel>> getAll( // @formatter:off
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "name", required = false) String name, 
			Pageable pageable
	) { // @formatter:on
		Page<AlbumModel> albums = albumService.getAll(id, name, pageable);
		return new ResponseEntity<>(albums, HttpStatus.OK);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(albumValidator);
	}

}