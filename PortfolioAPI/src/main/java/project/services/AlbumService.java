package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.AlbumModel;

public interface AlbumService {

	public AlbumModel create(AlbumModel album);

	public AlbumModel read(Integer id);

	public AlbumModel update(Integer id, AlbumModel album);

	public void delete(Integer id);

	public Page<AlbumModel> getAll(Integer id, String name, Pageable pageable);

}
