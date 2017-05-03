package project.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import project.exceptions.EntityNotFoundException;
import project.models.AlbumModel;
import project.models.PhotoModel;
import project.repositories.AlbumRepository;
import project.repositories.PhotoRepository;
import project.services.PhotoService;
import project.services.StorageService;

@Service
public class PhotoServiceImp implements PhotoService {

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private StorageService storageService;

	@Override
	@Transactional
	public PhotoModel addPhoto(Integer albumId, MultipartFile file) {
		if (!albumRepository.exists(albumId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		AlbumModel album = albumRepository.findOne(albumId);
		String path = storageService.save(album.getPath(), file);
		PhotoModel photo = new PhotoModel();
		photo.setPath(path);
		photo.setAlbum(album);
		return this.photoRepository.save(photo);
	}

	@Override
	@Transactional
	public void deletePhoto(Integer albumId, Integer photoId) {
		if (!albumRepository.exists(albumId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		
		if (!photoRepository.exists(photoId)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		
		PhotoModel photo = photoRepository.findOne(photoId);
		photoRepository.delete(photoId);
		storageService.delete(photo.getPath());
	}

}
