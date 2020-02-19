package com.payment.xborder.util.calucation;

import java.math.BigDecimal;

public final class OTPGenerator {

	public static final BigDecimal min = new BigDecimal(100000);
	public static final BigDecimal max = new BigDecimal(999999);

	public static long getOTP() {
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
		return randomBigDecimal.setScale(0, BigDecimal.ROUND_CEILING).longValue();
	}
}
