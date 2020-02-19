package com.payment.xborder.model.onboard.ws;

import java.util.Map;

import com.payment.xborder.model.file.DocumentDetails;
import com.payment.xborder.model.file.UserDocuments;

public class UserDocsStatus {
	
	private String docId;
	private Map<String, DocumentDetails> documentsUploaded;

	private long lastModifiedTime;
	
	public UserDocsStatus(
		String docId, 
		Map<String, DocumentDetails> documentsUploaded,
		long lastModifiedTime
		
			) {
		
		this.docId = docId;
		this.documentsUploaded = documentsUploaded;
		this.lastModifiedTime = lastModifiedTime;
		
		
	}
	
	public UserDocsStatus(UserDocuments userDocuments) {
		
		this.docId = userDocuments.getUserDocumentID();
		this.documentsUploaded = userDocuments.getDocumentsUploaded();
		this.lastModifiedTime = userDocuments.getLastModifiedTime();
		
		
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public Map<String, DocumentDetails> getDocumentsUploaded() {
		return documentsUploaded;
	}

	public void setDocumentsUploaded(Map<String, DocumentDetails> documentsUploaded) {
		this.documentsUploaded = documentsUploaded;
	}

	public long getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	
	
	

}
