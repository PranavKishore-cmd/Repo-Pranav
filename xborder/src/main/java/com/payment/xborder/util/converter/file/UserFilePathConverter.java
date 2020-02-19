package com.payment.xborder.util.converter.file;
import java.time.LocalDate;
import java.util.HashMap;
import com.payment.xborder.model.file.UploadFileResponse;
import com.payment.xborder.model.file.UserFilePath;
import com.payment.xborder.model.file.UserFileUploadRequestType;


public class UserFilePathConverter {

	public static UserFilePath wsToModel(UserFileUploadRequestType userFileUploadRequestType, UploadFileResponse uploadFileResponse) {
		UserFilePath userFilePath = new UserFilePath();
		HashMap<String, String> fileMap = new HashMap<String, String>();
		fileMap.put(userFileUploadRequestType.getFileName(), uploadFileResponse.getFileDownloadUri());
		userFilePath.setEmail(userFileUploadRequestType.getEmail());
		userFilePath.setFiles(fileMap);
		userFilePath.setUploadedOn(LocalDate.now());
		userFilePath.setValid(false);;
		return userFilePath;
	}

}


