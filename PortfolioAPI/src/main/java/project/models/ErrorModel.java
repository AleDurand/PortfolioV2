package project.models;

import java.io.Serializable;

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String code;
	private String field;
	private String message;

	public ErrorModel(Integer status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public ErrorModel(Integer status, String code, String field, String message) {
		this.status = status;
		this.code = code;
		this.field = field;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}