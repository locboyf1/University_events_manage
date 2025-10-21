package com.event.university.utillities;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Functions {

	public static String convertStringToAlias(String input) {
		if (input == null || input.isEmpty()) {
			return "";
		}

		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{M}");
		String temp = pattern.matcher(normalized).replaceAll("");

		temp = temp.toLowerCase();
		temp = temp.replace('đ', 'd');
		temp = temp.replaceAll("\\s+", "_");
		temp = temp.replaceAll("[^a-z0-9_]", "");
		temp = temp.replaceAll("_{2,}", "_");
		temp = temp.replaceAll("^_|_$", "");
		return temp;
	}

}
