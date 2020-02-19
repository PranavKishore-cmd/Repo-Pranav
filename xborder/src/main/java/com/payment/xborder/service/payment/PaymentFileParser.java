package com.payment.xborder.service.payment;

import com.payment.xborder.model.payment.PaymentRecord;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pradeep P 12-11-2019
 */
public interface PaymentFileParser {

	public PaymentRecord parseLine(String line);

	public List<String> readLines(String path);

	public default List<PaymentRecord> parse(String path) {
		return this.readLines(path).stream().skip(1).map(this::parseLine).collect(Collectors.toList());
	}
}
