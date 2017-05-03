package project.services;

import org.springframework.web.multipart.MultipartFile;

import project.models.PhotoModel;

public interface PhotoService {

	public PhotoModel addPhoto(Integer albumId, MultipartFile file);
	
	public void deletePhoto(Integer albumId, Integer photoId);
	
}
