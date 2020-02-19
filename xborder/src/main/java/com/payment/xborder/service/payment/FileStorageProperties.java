package com.payment.xborder.service.payment;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * The class {@code FileStorageProperties} fetches the directory
 * properties from configuration file.
 *
 * 
 * @author pradeep
 *
 */
@Component
@ConfigurationProperties (prefix = "payments.file")
public class FileStorageProperties {

	/**
	 * Server local upload directory.
	 */
	private String uploadDir;

	/**
	 * @return the uploadDir
	 */
	public String getUploadDir() {
		return uploadDir;
	}

	/**
	 * @param uploadDir the uploadDir to set
	 */
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

}
