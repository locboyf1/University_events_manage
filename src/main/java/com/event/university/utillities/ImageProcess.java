package com.event.university.utillities;

import java.util.Base64;

public class ImageProcess {

	public static String convertImage2String(byte[] imageBytes, String mimeType) {
		String base64String = Base64.getEncoder().encodeToString(imageBytes);

		return "data:" + mimeType + ";base64," + base64String;
	}

}
