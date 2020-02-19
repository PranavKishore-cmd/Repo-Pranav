package com.payment.xborder.service.notification;

/**
 * 
 * The enum {@code NotificationEnumeration} captures the notifcation subject and
 * notification tempalte file name.
 * 
 * @author pradeep
 *
 * 
 */

public enum NotificationEnumeration {

	/**
	 *
	 */
	MAIL_CHECKER("Email Passcode", "emailChecker"),
	/**
	 * New user addition .
	 */
	ADD_USER("Your account has been created for x-Border", "addUserTemplate"),
	/**
	 * Forgot OR Reset Password.
	 */
	RESET_PASSWORD("x-Border Password reset request", "resetPassword");

	private String notificationSubject;

	private String templateName;

	NotificationEnumeration(String notificationSubject, String templateName) {
		this.notificationSubject = notificationSubject;
		this.templateName = templateName;
	}

	/**
	 * @return the notificationSubject
	 */
	public final String getNotificationSubject() {
		return notificationSubject;
	}

	/**
	 * @param notificationSubject the notificationSubject to set
	 */
	public final void setNotificationSubject(String notificationSubject) {
		this.notificationSubject = notificationSubject;
	}

	/**
	 * @return the templateName
	 */
	public final String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public final void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
