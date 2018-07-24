/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordDecode {

	private PasswordDecode() {

	}

	public static String getHash(String text, String digestText, String algorithmInstance) {
		String strHash = "";
		try {
			MessageDigest md = MessageDigest.getInstance(algorithmInstance);
			md.update(text.getBytes());
			byte[] hasByte = md.digest(digestText.getBytes());
			strHash = bytesToHexString(hasByte);
		} catch (NoSuchAlgorithmException objException) {
		}
		return strHash;
	}

	public static String getHash(String text, String algorithmInstance) {
		String strHash = "";
		try {
			MessageDigest md = MessageDigest.getInstance(algorithmInstance);
			md.update(text.getBytes());
			byte[] hasByte = md.digest();
			strHash = bytesToHexString(hasByte);
		} catch (NoSuchAlgorithmException objException) {
		}
		return strHash;
	}

	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
}
