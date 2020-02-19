package com.payment.xborder.model.file;

import org.springframework.core.io.Resource;

/**
 * The class {@code DownloadFileResponseType} contains model to capture the
 * response type of File Download.
 * 
 * @author pradeep
 */
public class DownloadFileResponseType {

	private Resource resource;
	private String contentType;


	/**
	 * Default Constructor
	 */
	public DownloadFileResponseType() {

	}

	/**
	 * 
	 * @param resource
	 * @param contentType
	 */
	public DownloadFileResponseType(Resource resource, String contentType) {
		super();
		this.resource = resource;
		this.contentType = contentType;
	}

	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
