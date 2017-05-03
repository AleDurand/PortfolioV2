package project.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import project.exceptions.EntityNotFoundException;
import project.models.AlbumModel;
import project.models.QAlbumModel;
import project.repositories.AlbumRepository;
import project.services.AlbumService;
import project.utils.HashGeneratorUtils;

@Service
public class AlbumServiceImp implements AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	@Override
	@Transactional
	public AlbumModel create(AlbumModel album) {
		AlbumModel aux = albumRepository.save(album);
		aux.setPath(HashGeneratorUtils.generateMD5(aux.getId().toString()));
		return albumRepository.save(aux);
	}

	@Override
	public AlbumModel read(Integer id) {
		if (!albumRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return albumRepository.findOne(id);
	}
	
	@Override
	public AlbumModel update(Integer id, AlbumModel album) {
		if (!albumRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		AlbumModel current = albumRepository.findOne(id);
		current.setName(album.getName());
		current.setDescription(album.getDescription());
		return albumRepository.save(current);
	}

	@Override
	public void delete(Integer id) {
		if (!albumRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		albumRepository.delete(id);
	}

	@Override
	public Page<AlbumModel> getAll(Integer id, String name, Pageable pageable) {
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();

		QAlbumModel album = QAlbumModel.albumModel;
		BooleanExpression expression1 = (id != null) ? album.id.eq(id) : null;
		expressions.add(expression1);

		BooleanExpression expression2 = (name != null) ? album.name.contains(name) : null;
		expressions.add(expression2);

		BooleanExpression expression = null;
		for (BooleanExpression ex : expressions) {
			if (expression == null) {
				expression = ex;
			} else {
				expression = expression.and(ex);
			}
		}

		return albumRepository.findAll(expression, pageable);
	}

}
