package com.payment.xborder.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Default Application property configuration.
 * 
 * @author Pradeep
 *
 */
@Component
@ConfigurationProperties("xborder.app")
public class ApplicationProperties {

	/**
	 * Backend url.
	 */
	private String backendUrl;

	/**
	 * Frontend url.
	 */
	private String frontendUrl;

	/**
	 * @return the backendUrl
	 */
	public final String getBackendUrl() {
		return backendUrl;
	}

	/**
	 * @param backendUrl the backendUrl to set
	 */
	public final void setBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
	}

	/**
	 * @return the frontendUrl
	 */
	public final String getFrontendUrl() {
		return frontendUrl;
	}

	/**
	 * @param frontendUrl the frontendUrl to set
	 */
	public final void setFrontendUrl(String frontendUrl) {
		this.frontendUrl = frontendUrl;
	}

}
