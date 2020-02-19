package com.payment.xborder.service.file;

import com.payment.xborder.dao.filestorage.FileStorageDao;
import com.payment.xborder.dao.onboard.UserDao;
import com.payment.xborder.enums.DocumentVerificationStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.model.file.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserFilePathService {

   private static final Logger
         logger = LoggerFactory.getLogger(UserFilePathService.class);

	@Autowired
	FileStorageDao fileStorageDao;

	@Autowired
	UserDao userDao;
	
	public void createFileMap(UserFilePath userFilePath)
	{
		fileStorageDao.createFileMap(userFilePath);
	}

	public SaveDocumentResponse saveOrUpdateUserDocument(SaveDocumentRequest saveDocumentRequest){

      SaveMultipleDocumentRequest saveMultipleDocumentRequest =
            new SaveMultipleDocumentRequest();

      saveMultipleDocumentRequest.setUserEmail(saveDocumentRequest.getUserEmail());
      List<DocumentDetails> documentDetailsList = new ArrayList <>();
      documentDetailsList.add(saveDocumentRequest.getDocumentsDetails());
      saveMultipleDocumentRequest.setDocumentsDetails(documentDetailsList);

      return saveOrUpdateUserDocuments(saveMultipleDocumentRequest);
	}

   public SaveDocumentResponse saveOrUpdateUserDocuments(SaveMultipleDocumentRequest saveDocumentRequest){

      String userId =
            userDao.findUserIdByEmail(saveDocumentRequest.getUserEmail());

      if(userId == null){
         throw new BusinessException("User not found");
      }

      SaveDocumentResponse saveDocumentResponse =
            new SaveDocumentResponse(saveDocumentRequest.getUserEmail(), "");

      List <DocumentDetails> documentDetailsList =
            saveDocumentRequest.getDocumentsDetails();

      UserDocuments userDocuments = fileStorageDao.getSavedDocuments(userId);

      Map<String, DocumentDetails> docsMap = new HashMap<>();
      if(userDocuments != null){
         docsMap = userDocuments.getDocumentsUploaded();
         userDocuments.setUserDocumentID(userDocuments.getUserDocumentID());
      }
      else{
         userDocuments = new UserDocuments();
         userDocuments.setUserId(userId);
      }

      for (DocumentDetails documentDetails :documentDetailsList)
      {
         documentDetails.setUploadedTime(Calendar.getInstance().getTimeInMillis());
         documentDetails.setDocumentVerificationStatus(DocumentVerificationStatus.PENDING);
         String documentKey = documentDetails.getDocumentName().
                              replace(".","_").toUpperCase();

         if(docsMap.containsKey(documentKey)){
            logger.debug("Existing document by name "+ documentDetails.getDocumentName()
                         + " found for - " + saveDocumentRequest.getUserEmail());
            docsMap.put(documentKey, documentDetails);
         }else{
            logger.debug("Document by name "+ documentDetails.getDocumentName() +" "
                         + "found for - " + saveDocumentRequest.getUserEmail());
            docsMap.put(documentKey, documentDetails);
         }
      }

      userDocuments.setDocumentsUploaded(docsMap);
      userDocuments.setLastModifiedTime(Calendar.getInstance().getTimeInMillis());

      fileStorageDao.saveUserDocument(userDocuments);

      logger.debug(documentDetailsList.size() +" new Document details saved "
                   + "for user - " + saveDocumentRequest.getUserEmail());
      saveDocumentResponse.setMessage("Documents saved successfully");

      return saveDocumentResponse;
   }

   public GetDocumentsResponse getUserDocumentDetails(String userEmailId){

      String userId = userDao.findUserIdByEmail(userEmailId);

      if(userId == null){
         throw new BusinessException("User not found");
      }

      GetDocumentsResponse getDocumentsResponse = new GetDocumentsResponse();
      getDocumentsResponse.setUserEmail(userEmailId);
      getDocumentsResponse.setDocumentDetails(Collections.EMPTY_LIST);

      UserDocuments userDocuments = fileStorageDao.getSavedDocuments(userId);

      if(userDocuments != null){
         getDocumentsResponse.setDocumentDetails(
               new ArrayList<DocumentDetails>(userDocuments.getDocumentsUploaded().values()));
      }

      return getDocumentsResponse;
   }

   public SaveCompanyDocumentResponse saveOrUpdateCompanyDocument(SaveCompanyDocumentRequest saveCompanyDocumentRequest){

      SaveCompanyMultipleDocRequest saveCompanyMultipleDocRequest =
            new SaveCompanyMultipleDocRequest();

      saveCompanyMultipleDocRequest.setCompanyID(saveCompanyDocumentRequest.getCompanyID());
      List<DocumentDetails> documentDetailsList = new ArrayList <>();
      documentDetailsList.add(saveCompanyDocumentRequest.getDocumentsDetails());
      saveCompanyMultipleDocRequest.setDocumentsDetails(documentDetailsList);

      return saveOrUpdateCompanyDocuments(saveCompanyMultipleDocRequest);
   }

   public SaveCompanyDocumentResponse saveOrUpdateCompanyDocuments(SaveCompanyMultipleDocRequest saveCompanyMultipleDocRequest)
   {
      SaveCompanyDocumentResponse saveDocumentResponse =
            new SaveCompanyDocumentResponse(saveCompanyMultipleDocRequest.getCompanyID(), "");

      List <DocumentDetails> documentDetailsList =
            saveCompanyMultipleDocRequest.getDocumentsDetails();

      String companyId = saveCompanyMultipleDocRequest.getCompanyID();

      // TODO: validate company ID

      UserDocuments userDocuments =
            fileStorageDao.getSavedDocuments(companyId);

      Map<String, DocumentDetails> docsMap = new HashMap<>();
      if(userDocuments != null){
         docsMap = userDocuments.getDocumentsUploaded();
         userDocuments.setUserDocumentID(userDocuments.getUserDocumentID());
      }
      else{
         userDocuments = new UserDocuments();
         userDocuments.setUserId(companyId);
      }

      for (DocumentDetails documentDetails :documentDetailsList)
      {
         documentDetails.setUploadedTime(Calendar.getInstance().getTimeInMillis());
         documentDetails.setDocumentVerificationStatus(DocumentVerificationStatus.PENDING);
         String documentKey = documentDetails.getDocumentName().
               replace(".","_").toUpperCase();

         if(docsMap.containsKey(documentKey)){
            logger.debug("Existing document by name "+ documentDetails.getDocumentName()
                         + " found for - " + companyId);
            docsMap.put(documentKey, documentDetails);
         }else{
            logger.debug("Document by name "+ documentDetails.getDocumentName() +" "
                         + "found for - " +companyId);
            docsMap.put(documentKey, documentDetails);
         }
      }

      userDocuments.setDocumentsUploaded(docsMap);
      userDocuments.setLastModifiedTime(Calendar.getInstance().getTimeInMillis());

      fileStorageDao.saveUserDocument(userDocuments);

      logger.debug(documentDetailsList.size() +" new Document details saved "
                   + "for - " + companyId);
      saveDocumentResponse.setMessage("Documents saved successfully");

      return saveDocumentResponse;
   }

   public GetDocumentsResponse getCompanyDocumentDetails(String companyID){

      //TODO: validate companyID

      GetDocumentsResponse getDocumentsResponse = new GetDocumentsResponse();
      getDocumentsResponse.setUserEmail(companyID);
      getDocumentsResponse.setDocumentDetails(Collections.EMPTY_LIST);

      UserDocuments userDocuments = fileStorageDao.getSavedDocuments(companyID);

      if(userDocuments != null){
         getDocumentsResponse.setDocumentDetails(
               new ArrayList<DocumentDetails>(userDocuments.getDocumentsUploaded().values()));
      }

      return getDocumentsResponse;
   }
}
