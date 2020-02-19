package com.payment.xborder.ws;

import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.file.*;
import com.payment.xborder.service.file.FileStorageService;
import com.payment.xborder.service.file.UserFilePathService;
import com.payment.xborder.util.converter.file.UserFilePathConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The class {@code FileStorageController} controller provides the REST API
 * method to upload and download the files.
 *
 * @author pradeep
 */
@RestController
@RequestMapping("file")
public class FileStorageController  {

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private UserFilePathService userFilePathService;
	
	public static final String BUCKET_CODE="TEST_AWS";

	/**
	 * Rest Method to upload the file.
	 *
	 * @param file    The file to upload.
	 * @param request The Htpp Servlet request.
	 * @return the Response Entity of {@code UploadFileResponse} which contains the
	 *         details about the uploaded file.
	 */
	@PostMapping("/uploadFile")
	public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file,
														 HttpServletRequest request) {

		UploadFileResponse uploadFileResponse = fileStorageService.uploadFile(file,
				BUCKET_CODE);

		return ResponseEntity.ok().body(uploadFileResponse);
	}

	/**
	 * Rest Method to upload the file.
	 *
	 * @param files   The files to upload.
	 * @return List of Response Entity of {@code UploadFileResponse} which contains
	 *         the details about the uploaded files.
	 */
	@PostMapping("/uploadMultipleFiles")
	public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		List<UploadFileResponse> responseEntity = Arrays.asList(files).stream()
				.map(file -> fileStorageService.uploadFile(file, BUCKET_CODE))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(responseEntity);
	}

	/**
	 * Rest Method to Download the file.
	 *
	 * @return ResponseEntity of {@code Resource} which contains the details about
	 *         the downlaoded files.
	 */
	@PostMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFilewithBody(@RequestBody DownloadFileRequestType downloadFileRequestType, HttpServletRequest request) {

		DownloadFileResponseType responseType = fileStorageService.downloadFile(downloadFileRequestType,
				request);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(responseType.getContentType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + responseType.getResource().getFilename() + "\"")
				.body(responseType.getResource());
	}

	/**
	 * Rest Method to Download the file.
	 *
	 * @param request The Htpp Servlet request.
	 * @return ResponseEntity of {@code Resource} which contains the details about
	 *         the downlaoded files.
	 */
	@PostMapping("/downloadFile/{file-name}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("file-name") String fileName, HttpServletRequest request) {

		DownloadFileRequestType downloadFileRequestType=new DownloadFileRequestType();
		downloadFileRequestType.setFileName(fileName);
		DownloadFileResponseType responseType = fileStorageService.downloadFile(downloadFileRequestType,
				request);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(responseType.getContentType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + responseType.getResource().getFilename() + "\"")
				.body(responseType.getResource());
	}
	
	@PostMapping("/saveDocument/")
	public ResponseEntity<String> saveDocument(@RequestParam("file") MultipartFile file,
														 HttpServletRequest request, UserFileUploadRequestType userFileUploadRequestType) {

		UploadFileResponse uploadFileResponse = fileStorageService.uploadFile(file, BUCKET_CODE);
		
		userFilePathService.createFileMap(UserFilePathConverter.wsToModel(userFileUploadRequestType, uploadFileResponse));

		return new ResponseEntity <>("Document Saved Successfully", HttpStatus.ACCEPTED);
	}

	@PostMapping("/saveDocumentData")
	public ResponseEntity<SaveDocumentResponse> saveDocumentReference(@Valid @NotNull @RequestBody SaveDocumentRequest saveDocumentRequest) {

      SaveDocumentResponse saveDocumentResponse = new SaveDocumentResponse();
      try
      {
         saveDocumentResponse = userFilePathService
                     .saveOrUpdateUserDocument(saveDocumentRequest);
      } catch (BusinessException ex){
         return new ResponseEntity <>(saveDocumentResponse, HttpStatus.BAD_REQUEST);
      }

		return new ResponseEntity <>(saveDocumentResponse, HttpStatus.ACCEPTED);
	}

   @PostMapping("/saveMultipleDocumentData")
   public ResponseEntity<SaveDocumentResponse> saveMultipleDocumentReference(@Valid @NotNull @RequestBody SaveMultipleDocumentRequest saveDocumentRequest) {

      SaveDocumentResponse saveDocumentResponse = new SaveDocumentResponse();
      try
      {
         saveDocumentResponse = userFilePathService
               .saveOrUpdateUserDocuments(saveDocumentRequest);
      } catch (BusinessException ex){
         return new ResponseEntity <>(saveDocumentResponse, HttpStatus.BAD_REQUEST);
      }

      return new ResponseEntity <>(saveDocumentResponse, HttpStatus.ACCEPTED);
   }

   @GetMapping("/getUserDocuments/{email}")
   public ResponseEntity<GetDocumentsResponse> getUserDocuments(@Valid @NotNull @PathVariable String email) {

      GetDocumentsResponse getDocumentsResponse = new GetDocumentsResponse();
      try
      {
         getDocumentsResponse = userFilePathService.getUserDocumentDetails(email);
      } catch (BusinessException ex){
         return new ResponseEntity <>(getDocumentsResponse, HttpStatus.BAD_REQUEST);
      }

      return new ResponseEntity <>(getDocumentsResponse, HttpStatus.ACCEPTED);
   }

   @PostMapping("/saveCompanyDocumentRef")
   public ResponseEntity<SaveCompanyDocumentResponse> saveCompanyDocumentRef(@Valid @NotNull @RequestBody SaveCompanyDocumentRequest saveCompanyDocumentRequest) {

      SaveCompanyDocumentResponse saveDocumentResponse = new SaveCompanyDocumentResponse();
      try
      {
         saveDocumentResponse = userFilePathService
               .saveOrUpdateCompanyDocument(saveCompanyDocumentRequest);
      } catch (BusinessException ex){
         return new ResponseEntity <>(saveDocumentResponse, HttpStatus.BAD_REQUEST);
      }

      return new ResponseEntity <>(saveDocumentResponse, HttpStatus.ACCEPTED);
   }

   @PostMapping("/saveCompanyMultipleDocumentRefs")
   public ResponseEntity<SaveCompanyDocumentResponse> saveCompanyMultipleDocumentRefs(@Valid @NotNull @RequestBody SaveCompanyMultipleDocRequest saveCompanyMultipleDocRequest) {

      SaveCompanyDocumentResponse saveDocumentResponse = new SaveCompanyDocumentResponse();
      try
      {
         saveDocumentResponse = userFilePathService
               .saveOrUpdateCompanyDocuments(saveCompanyMultipleDocRequest);
      } catch (BusinessException ex){
         return new ResponseEntity <>(saveDocumentResponse, HttpStatus.BAD_REQUEST);
      }

      return new ResponseEntity <>(saveDocumentResponse, HttpStatus.ACCEPTED);
   }

   @GetMapping("/getCompanyDocuments/{companyId}")
   public ResponseEntity<GetDocumentsResponse> getCompanyDocuments(@Valid @NotNull @PathVariable String companyId) {

      GetDocumentsResponse getDocumentsResponse = new GetDocumentsResponse();
      try
      {
         getDocumentsResponse = userFilePathService.getCompanyDocumentDetails(companyId);
      } catch (BusinessException ex){
         return new ResponseEntity <>(getDocumentsResponse, HttpStatus.BAD_REQUEST);
      }

      return new ResponseEntity <>(getDocumentsResponse, HttpStatus.ACCEPTED);
   }




}
