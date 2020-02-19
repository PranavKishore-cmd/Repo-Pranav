package com.payment.xborder.service.payment;

import com.payment.xborder.dao.payment.PaymentFileDao;
import com.payment.xborder.model.payment.PaymentFileRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class PaymentFileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public PaymentFileStorageService(FileStorageProperties fileStorageProperties) {
      //FileStorageProperties fileStorageProperties =
      //      new FileStorageProperties();
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new RuntimeException("Unable to create the directory");

		}
	}

	public String saveFileToServer(MultipartFile file) {

	   //TODO: Construct FileName with timestamp
	   // Normalize file name
		String fileName = null;
		try {
			Path targetLocation = this.fileStorageLocation.resolve(StringUtils
                                                                      .cleanPath(file.getOriginalFilename()));

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			fileName = targetLocation.toFile().getAbsolutePath();



			// Files.delete(Paths.get(file.getOriginalFilename()) );

		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileName;
	}

}