package com.payment.xborder.model.onboard;

import java.util.Calendar;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Document(collection = "USER_SESSION")
public class UserSession {

	@NotBlank
	private String email;
	
	@Id
	@NotBlank
	private String sessionId;
	
	private long lastAccessTime;

	public UserSession() {
	}

	public UserSession(@NotBlank String email, @NotBlank String sessionId) {
		this.email = email;
		this.sessionId = sessionId;
		this.lastAccessTime = Calendar.getInstance().getTimeInMillis();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	@Override
	public String toString() {
		return "UserSession [email=" + email + ", sessionId=" + sessionId + ", lastAccessTime=" + lastAccessTime + "]";
	}
	
}
