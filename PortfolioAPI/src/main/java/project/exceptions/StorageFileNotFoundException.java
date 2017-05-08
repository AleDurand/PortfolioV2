package project.exceptions;

public class StorageFileNotFoundException extends CustomException {

	private static final long serialVersionUID = 1L;

	public StorageFileNotFoundException(String code, Object[] args) {
		super(code, args);
	}

}