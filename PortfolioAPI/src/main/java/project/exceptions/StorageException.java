package project.exceptions;

public class StorageException extends CustomException {

	private static final long serialVersionUID = 1L;
	
	public StorageException(String code, Object[] args) {
		super(code, args);
	}

}
