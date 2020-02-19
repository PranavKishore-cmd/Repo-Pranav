package com.payment.xborder.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * The class {@code FileStorageProperties} fetches the AWS S3 upload directory
 * properties from configuration file.
 *
 * 
 * @author pradeep
 *
 */
@ConfigurationProperties(prefix = "xborder.aws.s3")
public class FileStorageProperties {

	public FileStorageProperties() {

	}

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
