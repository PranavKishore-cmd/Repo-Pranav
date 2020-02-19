package com.payment.xborder.model.file;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "FILE_PATH")

public class UserFilePath {

	@NotBlank
	private String email;

	@NotBlank
	private Object files;
	
	@NotBlank
	private LocalDate uploadedOn;
	
	@NotBlank
	private boolean valid;
	
	public UserFilePath() {
		
	}

	public UserFilePath(@NotBlank String email, @NotBlank Object files, @NotBlank @NotBlank LocalDate uploadedOn,
			@NotBlank boolean valid) {
		this.email = email;
		this.files = files;
		this.uploadedOn = uploadedOn;
		this.valid = valid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Object getFiles() {
		return files;
	}

	public void setFiles(Object files) {
		this.files = files;
	}

	public @NotBlank LocalDate getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(LocalDate localDate) {
		this.uploadedOn = localDate;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
