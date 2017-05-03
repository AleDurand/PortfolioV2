package project.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public String save(String basepath, MultipartFile file);
	
	public void delete(String path);
}
