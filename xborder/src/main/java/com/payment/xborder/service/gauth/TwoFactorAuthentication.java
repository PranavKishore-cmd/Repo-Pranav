package com.payment.xborder.service.gauth;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.payment.xborder.model.onboard.User;
import com.payment.xborder.model.onboard.ws.AuthKeyResponse;
import com.warrenstrange.googleauth.GoogleAuthenticator;

public class TwoFactorAuthentication {
	
	private long KEY_VALIDATION_INTERVAL_MS = TimeUnit.SECONDS.toMillis(30);

	public Map<String, String> qrCodeGeneration(User user) throws URISyntaxException, WriterException, IOException {
		String key = getTwoFactorKey(user);
		
		String id = user.getEmail().replaceAll("[-+.^:,@]","");
		String filePath = "./" + File.separator + id + ".png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new EnumMap<>(EncodeHintType.class);
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		String keyUri = generateKeyUri("XBORDER", user.getEmail(), key);
		String qrCodeData = keyUri;
		String base64Image = createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
		Map<String, String> map = new HashMap<>();
		map.put("fileName", id + ".png");
		map.put("key", key);
		map.put("base64Image", base64Image);
		map.put("email", user.getEmail());
		return map;
	}

	public AuthKeyResponse qrCodeGeneration(String userEmail,
														 String userTfaKey)
			throws URISyntaxException, WriterException, IOException
	{

		String id = userEmail.replaceAll("[-+.^:,@]","");
		String filePath = "./" + File.separator + id + ".png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new EnumMap<>(EncodeHintType.class);
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		String keyUri = generateKeyUri("XBORDER", userEmail, userTfaKey);
		String base64Image = createQRCode(keyUri, filePath, charset, hintMap, 200, 200);
		AuthKeyResponse authKeyResponse = new AuthKeyResponse();
		authKeyResponse.setEmail(userEmail);
		authKeyResponse.setAuthKeyString(userTfaKey);
		authKeyResponse.setQrCode(base64Image);

		return authKeyResponse;
	}

	private String generateKeyUri(String issuer, String email, String key) throws URISyntaxException {
		URI uri = new URI("otpauth", "totp", "/" + issuer + ":" + email, "secret=" + key + "&issuer=" + issuer,null);
		String uriStr = uri.toASCIIString();		
		return uriStr;
	}

	private String getTwoFactorKey(User user) {
		String key = user.getAuthKey();
		return key;
	}

	//Configure the Google Authenticator App by scanning the following QR code image
	private static String createQRCode(String qrCodeData, String filePath, String charset,
			@SuppressWarnings("rawtypes") Map hintMap, int qrCodeheight, int qrCodewidth)
					throws WriterException, IOException {
		@SuppressWarnings("unchecked")
		BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		File file = new File(filePath);
		MatrixToImageWriter.writeToPath(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), file.toPath());
		Encoder encoder = Base64.getEncoder();
		String base64Image = encoder.encodeToString(Files.readAllBytes(file.toPath()));
		/*// using PosixFilePermission to set file permissions 777
		Set<PosixFilePermission> perms = new HashSet<>();
		// add owners permission
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		// add group permissions
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		// add others permissions
		perms.add(PosixFilePermission.OTHERS_READ);
		Files.setPosixFilePermissions(Paths.get(file.toString()), perms);*/
		//logger.info("2FA QR code generated");
		file.delete();
		return "data:image/png;base64," + base64Image;
	}

	public boolean performAuthentication(String value, String secret) {
		final GoogleAuthenticator gAuth = new GoogleAuthenticator();
		Integer totp = Integer.valueOf((value.equals("") ? "-1" : value));
		AtomicInteger windowSize = new AtomicInteger(3);
		boolean unused = isUnusedPassword(totp, windowSize.get());
		boolean matches = gAuth.authorize(secret, totp);
		return (unused && matches);
	}

	private boolean isUnusedPassword(int password, int windowSize) {
		long now = new Date().getTime();
		int lastUsedPassword = -1; // last successfully used password 
		long lastVerifiedTime = 0; // time of last success 
		long timeslotNow = now / KEY_VALIDATION_INTERVAL_MS;
		int forwardTimeslots = ((windowSize - 1) / 2);
		long timeslotThen = lastVerifiedTime / KEY_VALIDATION_INTERVAL_MS;
		if (password != lastUsedPassword || timeslotNow > timeslotThen + forwardTimeslots) {
			lastUsedPassword = password;
			lastVerifiedTime = now;
			return true;
		}
		return false;
	}

}