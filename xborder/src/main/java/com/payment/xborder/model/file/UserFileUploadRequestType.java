package com.payment.xborder.model.file;

public class UserFileUploadRequestType {
	

	private String fileName;
	private String email;
	private String fileCatogery;

	public UserFileUploadRequestType() {
	}
	
	public UserFileUploadRequestType(String email, String fileName, String fileCatogery) {
		super();
		this.email = email;
		this.fileName = fileName;
		this.fileCatogery = fileCatogery;
	}
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFileCatogery() {
		return fileCatogery;
	}

	public void setFileCatogery(String fileCatogery) {
		this.fileCatogery = fileCatogery;
	}
	
	@Override
	public String toString() {
		return "UserFileUpload{fileName=" + fileName + ", email=" + email + ", fileCatogery=" + fileCatogery
				+ "}";
	}


}
