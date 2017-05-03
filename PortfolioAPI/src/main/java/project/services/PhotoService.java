package project.services;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

	public void addPhoto(Integer albumId, MultipartFile file);
	
	public void deletePhoto(Integer albumId, Integer photoId);
	
}
