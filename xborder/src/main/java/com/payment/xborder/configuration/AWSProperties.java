package com.payment.xborder.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * The class {@code AWSProperties} fetches the AWS S3 properties from
 * configuration file.
 *
 * 
 * @author pradeep
 *
 */
@Component
@ConfigurationProperties("xborder.aws.s3")
public class AWSProperties {

	/**
	 * AWS S3 Bucket Name.
	 */
	private String bucketName;

	/**
	 * AWS Access Key.
	 */
	private String accessKey;

	/**
	 * AWS Secret Key.
	 */
	private String secretKey;

	/**
	 * AWS S3 Secret Key.
	 */
	private String region;

	/**
	 * AWS temp Directory.
	 */
	private String tempDir;

	/**
	 * AWS Root URI.
	 */
	private String uri;

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

	public AWSProperties() {
	}

	/**
	 * @return the bucketName
	 */
	public String getBucketName() {
		return bucketName;
	}

	/**
	 * @param bucketName the bucketName to set
	 */
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	/**
	 * @return the accessKey
	 */
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * @param accessKey the accessKey to set
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the tempDir
	 */
	public String getTempDir() {
		return tempDir;
	}

	/**
	 * @param tempDir the tempDir to set
	 */
	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

}
