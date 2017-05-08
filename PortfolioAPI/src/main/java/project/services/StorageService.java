package project.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public String getRootLocation();
	
	public String save(String basepath, MultipartFile file);
	
	public Resource load(String path);
	
	public void delete(String path);
}
