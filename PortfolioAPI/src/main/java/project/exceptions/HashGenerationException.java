package project.exceptions;

public class HashGenerationException extends CustomException {

	private static final long serialVersionUID = 1L;

	public HashGenerationException(String code, Object[] args) {
		super(code, args);
	}	
}
