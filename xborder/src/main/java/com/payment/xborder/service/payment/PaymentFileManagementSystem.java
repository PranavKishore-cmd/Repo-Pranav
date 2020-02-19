package com.payment.xborder.service.payment;

import com.payment.xborder.model.payment.PaymentRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PaymentFileManagementSystem {

	private final Map<String, PaymentFileParser> extensionToParser = new HashMap<>();
	private List<PaymentRecord> payments;

	public PaymentFileManagementSystem() {
		payments = new ArrayList<>();
		extensionToParser.put("csv", new PaymentFileCSVParser());
		extensionToParser.put("xlsx", new PaymentFileExcelParser());
	}

	public final List<PaymentRecord> getPaymentRecords() {
		return payments;
	}

	public void parseFile(final String path) {
		final File file = new File(path);
		if (file.exists()) {
			final int extensionIndex = path.lastIndexOf('.');
			if (extensionIndex != -1) {
				final String extension = path.substring(extensionIndex + 1);
				final PaymentFileParser parser = extensionToParser.get(extension);
				if (parser == null) {
					throw new RuntimeException("No supported format: " + path);
				}
				payments = parser.parse(path);
			}
		} else {
			throw new RuntimeException("File not found");
		}
	}

}
