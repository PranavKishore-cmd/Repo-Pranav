package com.payment.xborder.model.file;

/**
 * The class {@code DownloadFileRequestType} contains model to capture the
 * request type of File Download.
 * 
 * @author pradeep
 */
public class DownloadFileRequestType {

	/**
	 * Default Constructor
	 */
	public DownloadFileRequestType() {
	}

	/**
	 * 
	 * @param fileName
	 */
	public DownloadFileRequestType(String fileName) {
		super();
		this.fileName = fileName;
	}

	private String fileName;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
