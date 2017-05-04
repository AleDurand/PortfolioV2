package project.services.imp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.types.dsl.BooleanExpression;

import project.Application;
import project.exceptions.EntityNotFoundException;
import project.models.AlbumModel;
import project.models.QAlbumModel;
import project.repositories.AlbumRepository;
import project.services.AlbumService;
import project.utils.HashGeneratorUtils;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class AlbumServiceImpTest {

	@InjectMocks
	@Autowired
	private AlbumService albumService;

	@Mock
	private AlbumRepository albumRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		AlbumModel expectedAlbum = new AlbumModel();
		expectedAlbum.setId(1);
		expectedAlbum.setName("name");
		expectedAlbum.setDescription("address");
		expectedAlbum.setPath(HashGeneratorUtils.generateMD5("1"));
		when(albumRepositoryMock.save(expectedAlbum)).thenReturn(expectedAlbum);

		AlbumModel actualAlbum = albumService.create(expectedAlbum);
		
		assertEquals(expectedAlbum.getId(), actualAlbum.getId());
		assertEquals(expectedAlbum.getName(), actualAlbum.getName());
		assertEquals(expectedAlbum.getDescription(), actualAlbum.getDescription());
		assertEquals(expectedAlbum.getPath(), actualAlbum.getPath());
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		AlbumModel expectedAlbum = new AlbumModel();
		expectedAlbum.setId(1);
		expectedAlbum.setName("name");
		expectedAlbum.setDescription("description");
		expectedAlbum.setPath(HashGeneratorUtils.generateMD5("1"));
		
		when(albumRepositoryMock.exists(1)).thenReturn(true);
		when(albumRepositoryMock.findOne(1)).thenReturn(expectedAlbum);

		AlbumModel actualAlbum = albumService.read(1);
		
		assertEquals(expectedAlbum.getId(), actualAlbum.getId());
		assertEquals(expectedAlbum.getName(), actualAlbum.getName());
		assertEquals(expectedAlbum.getDescription(), actualAlbum.getDescription());
		assertEquals(expectedAlbum.getPath(), actualAlbum.getPath());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(albumRepositoryMock.exists(1)).thenReturn(false);

		albumService.read(1);
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(albumRepositoryMock.exists(1)).thenReturn(true);

		albumService.delete(1);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(albumRepositoryMock.exists(1)).thenReturn(false);

		albumService.delete(1);
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		Page<AlbumModel> expectedAlbums = new PageImpl<AlbumModel>(Arrays.asList(new AlbumModel()));
		Pageable pageable = new PageRequest(0, 1000);
		QAlbumModel album = QAlbumModel.albumModel;		
		when(albumRepositoryMock.findAll(album.instanceOfAny(), pageable)).thenReturn(expectedAlbums);

		Page<AlbumModel> actualAlbums = albumService.getAll(null, null, pageable);
		
		assertEquals(expectedAlbums.getTotalElements(), actualAlbums.getTotalElements());
		assertEquals(expectedAlbums, actualAlbums);
		// @formatter:on
	}

	@Test
	public void getAllFilteredById() throws Exception {
		// @formatter:off
		Page<AlbumModel> expectedAlbums = new PageImpl<AlbumModel>(Arrays.asList(new AlbumModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QAlbumModel.albumModel.id.eq(1);	
			
		when(albumRepositoryMock.findAll(expression, pageable)).thenReturn(expectedAlbums);

		Page<AlbumModel> actualAlbums = albumService.getAll(1, null, pageable);
		
		assertEquals(expectedAlbums.getTotalElements(), actualAlbums.getTotalElements());
		assertEquals(expectedAlbums, actualAlbums);
		// @formatter:on
	}

	@Test
	public void getAllFilteredByName() throws Exception {
		// @formatter:off
		Page<AlbumModel> expectedAlbums = new PageImpl<AlbumModel>(Arrays.asList(new AlbumModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QAlbumModel.albumModel.name.contains("name");	
			
		when(albumRepositoryMock.findAll(expression, pageable)).thenReturn(expectedAlbums);

		Page<AlbumModel> actualAlbums = albumService.getAll(null, "name", pageable);
		
		assertEquals(expectedAlbums.getTotalElements(), actualAlbums.getTotalElements());
		assertEquals(expectedAlbums, actualAlbums);
		// @formatter:on
	}

}
