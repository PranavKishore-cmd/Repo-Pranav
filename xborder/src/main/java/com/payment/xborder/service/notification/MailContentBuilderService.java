package com.payment.xborder.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 
 * @author pradeep
 * 
 *         Content Builder service using Thymleaf Template Engine.
 *
 */
@Service
public class MailContentBuilderService {
	private TemplateEngine templateEngine;

	@Autowired
	public MailContentBuilderService(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	/**
	 * Build the notification template.
	 * 
	 * @param templateName name of the template.
	 * @param context      context which holds the data for the template
	 * @return the template data as {@String}
	 */
	public String build(String templateName, Context context) {
		return templateEngine.process(templateName, context);
	}
}
