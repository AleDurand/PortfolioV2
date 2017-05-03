package project.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.DigestUtils;

import project.exceptions.HashGenerationException;

public class HashGeneratorUtils {

	public static String generateMD5(String message) throws HashGenerationException {
		return hashString(message, "MD5");
	}
	
	public static String generateMD5(byte[] bytes) throws HashGenerationException {
		return DigestUtils.md5DigestAsHex(bytes);
	}

	public static String generateSHA1(String message) throws HashGenerationException {
		return hashString(message, "SHA-1");
	}
	
	public static String generateSHA1(byte[] bytes) throws HashGenerationException {
		return hashBytes(bytes, "SHA-1");
	}

	public static String generateSHA256(String message) throws HashGenerationException {
		return hashString(message, "SHA-256");
	}
	
	public static String generateSHA256(byte[] bytes) throws HashGenerationException {
		return hashBytes(bytes, "SHA-256");
	}

	private static String hashString(String message, String algorithm) throws HashGenerationException {

		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
			return convertByteArrayToHexString(hashedBytes);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new HashGenerationException("exception.hash_generator", null);
		}
	}
	
	private static String hashBytes(byte[] bytes, String algorithm) throws HashGenerationException {

		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			return convertByteArrayToHexString(digest.digest(bytes));
		} catch (NoSuchAlgorithmException ex) {
			throw new HashGenerationException("exception.hash_generator", null);
		}
	}

	private static String convertByteArrayToHexString(byte[] bytes) {
		return new String(Hex.encode(bytes));
	}
}
