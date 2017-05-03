package project.services.imp;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.exceptions.StorageException;
import project.services.StorageService;
import project.utils.HashGeneratorUtils;

@Service
public class StorageServiceImp implements StorageService {
	
	@Value("${storage.media:media}")
	private Path rootLocation; 
	
	@Override
	public String save(String basepath, MultipartFile file) {
		try {
            if (file.isEmpty()) {
                throw new StorageException("storage.empty_file", null);
            }
            File directory = new File(rootLocation.toString() + File.separator + basepath);
            if(!directory.exists()) {
            	directory.mkdirs();
            }
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String name = HashGeneratorUtils.generateMD5(file.getBytes()).concat(".").concat(extension);
            Files.copy(file.getInputStream(), directory.toPath().resolve(name));            
            return basepath + File.separator + name;
        } catch(FileAlreadyExistsException e) {
        	throw new StorageException("storage.file_already_exists", null);
        } catch (IOException e) {
            throw new StorageException("storage.upload_error", null);
        }
	}
	
	@Override
	public void delete(String path) {
		if (path.isEmpty()) {
		    throw new StorageException("storage.empty_path", null);
		}
		Path file = rootLocation.resolve(path);
		file.toFile().delete();
	}
	
}
