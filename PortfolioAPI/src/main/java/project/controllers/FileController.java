package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.services.StorageService;

@RestController
@RequestMapping(value = "/files")
public class FileController {

	@Autowired
	private StorageService storageService;

	@RequestMapping(value = "/{path}", method = RequestMethod.GET)
	public ResponseEntity<Resource> serveFile(@PathVariable("path") String path) {
		Resource resource = storageService.load(path);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();	
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
