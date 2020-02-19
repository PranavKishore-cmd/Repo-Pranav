package com.payment.xborder.service.file;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.payment.xborder.configuration.AWSProperties;
import com.payment.xborder.configuration.FileStorageProperties;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ErrorEnumeration;
import com.payment.xborder.model.file.DownloadFileRequestType;
import com.payment.xborder.model.file.DownloadFileResponseType;
import com.payment.xborder.model.file.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * The class {@code FileStorageService} service contains the method to manage
 * the file upload and download from AWS 3.
 *
 * 
 * @author pradeep
 *
 */
@Service
public class FileStorageService {

	/**
	 * Storage Location of the server file which is downloaded and uploaded from
	 * AWS.
	 */
	private final Path fileStorageLocation;

	@Autowired
	AWSProperties properties;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new BusinessException(ErrorEnumeration.UNABLE_TO_CREATE_DIRECTORY, ex);

		}
	}



	/**
	 * Method the upload the file in to AWS S3.
	 * 
	 * @param file      the file to Upload.
	 * @param companyId the company id of the file
	 * @return the {@code UploadFileResponse} which contains the details about the
	 *         uploaded file.
	 */
	public UploadFileResponse uploadFile(MultipartFile file, String companyId) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			AWSCredentials credentials = null;
			credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
			AmazonS3 s3client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_WEST_2)
					.build();
			String bucketName = properties.getBucketName();
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
			String folderName = dateFormat.format(date);
			String fileUrl = companyId + "/" + folderName + "/" + fileName;

			fileName = companyId + "_" + folderName + "_" + fileName;
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new BusinessException(ErrorEnumeration.INVALID_FILE_PATH.toString() + "File Name :" + fileName);

			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			s3client.putObject(bucketName, fileUrl, targetLocation.toFile());
			Files.delete(targetLocation);

			UploadFileResponse uploadFileResponse = new UploadFileResponse();
			uploadFileResponse.setFileName(fileUrl);
			uploadFileResponse.setFileDownloadUri(properties.getUri() + "/" + (fileUrl));
			uploadFileResponse.setFileType(file.getContentType());
			uploadFileResponse.setSize(file.getSize());

			return uploadFileResponse;
		} catch (IOException ex) {
			throw new BusinessException(ErrorEnumeration.ISSUE_WITH_FILE_STORE, ex);

		}
	}

	/**
	 * Method the handle file download from S3.
	 * 
	 * @param downloadFileRequestType the properties of downladed file.
	 * @param request                 Httpservlet request.
	 * @return {@code DownloadFileResponseType} which contains the details about the
	 *         downlaoded files.
	 */
	public DownloadFileResponseType downloadFile(DownloadFileRequestType downloadFileRequestType,
												 HttpServletRequest request) {
		String fileName = downloadFileRequestType.getFileName();
		try {
			AWSCredentials credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
			AmazonS3 s3client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_WEST_2)
					.build();
			String bucketName = properties.getBucketName();
			S3Object s3object = s3client.getObject(bucketName, fileName);
			S3ObjectInputStream inputStream = s3object.getObjectContent();
			fileName = fileName.replaceAll("/", "_");
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);

			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			DownloadFileResponseType downloadFileResponseType = new DownloadFileResponseType(resource, contentType);

			// Fallback to the default content type if type could not be determined
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			if (resource.exists()) {
				return downloadFileResponseType;
			} else {
				throw new BusinessException(ErrorEnumeration.FILE_NOT_FOUND.toString() + "File Name" + fileName);
			}
		} catch (Exception ex) {
			throw new BusinessException(ErrorEnumeration.ISSUE_WHILE_DOWNLOAD, ex);
		}
	}

}
