package project.services.imp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.exceptions.StorageException;
import project.exceptions.StorageFileNotFoundException;
import project.services.StorageService;
import project.utils.HashGeneratorUtils;

@Service
public class StorageServiceImp implements StorageService {

	@Value("${storage.media:media}")
	private Path rootLocation;

	public String getRootLocation() {
		return rootLocation.toString() + File.separator;
	}
	 
	@Override
	public String save(String basepath, MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("storage.empty_file", null);
			}
			File directory = new File(rootLocation.toString() + File.separator + basepath);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String name = HashGeneratorUtils.generateMD5(file.getBytes()).concat(".").concat(extension);
			Files.copy(file.getInputStream(), directory.toPath().resolve(name));
			return URLEncoder.encode(basepath + File.separator + name, "UTF-8");
		} catch (FileAlreadyExistsException e) {
			throw new StorageException("storage.file_already_exists", null);
		} catch (IOException e) {
			throw new StorageException("storage.upload_error", null);
		}
	}
	
	@Override
    public Resource load(String path) {
        try {
            Path file = rootLocation.resolve(path);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("storage.file_not_found", null);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("storage.malformed_url", null);
        }
    }

	@Override
	public void delete(String path) {
		try {
			if (path.isEmpty()) {
				throw new StorageException("storage.empty_path", null);
			}
			Files.delete(rootLocation.resolve(path));
		} catch (NoSuchFileException e) {
			throw new StorageException("storage.file_not_found", null);
		} catch (DirectoryNotEmptyException x) {
			throw new StorageException("storage.not_empty_directory", null);
		} catch (IOException x) {
			throw new StorageException("storage.delete_error", null);
		}
	}

}
