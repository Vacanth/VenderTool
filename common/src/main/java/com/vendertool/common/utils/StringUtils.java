package com.vendertool.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.vendertool.common.validation.ValidationUtil;

/**
 * This is the simple utility commonly used for String operations
 * 
 */
public class StringUtils {

	private static final int initialSize = 4 * 1024;

	private static class StringUtilsHolder {
		private static final StringUtils INSTANCE = new StringUtils();
	}

	/**
	 * Get the singleton instance of the class
	 * 
	 * @return
	 */
	public static StringUtils getInstance() {
		return StringUtilsHolder.INSTANCE;
	}

	private StringUtils() {

	}
	
	/**
	 * This method compress the provided string and return actual String.
	 * 
	 * @param inputString
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public byte[] compressByteArray(String inputString) {

		if (ValidationUtil.getInstance().isEmpty(inputString)) {
			return null;
		}
		byte[] inputBytes = inputString.getBytes();
		ByteArrayOutputStream byteArrayOS = null;
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(inputBytes);
		deflater.finish();
		byteArrayOS = new ByteArrayOutputStream();
		byte[] temp = new byte[initialSize];
		try {
			while (!deflater.finished()) {
				int size = deflater.deflate(temp);
				byteArrayOS.write(temp, 0, size);
			}
		} catch (Exception ex) {
			// TODO log and return
		} finally {
			try {
				if (byteArrayOS != null)
					byteArrayOS.close();
			} catch (Exception ex) {
				// TODO log and return
			}
		}
		return byteArrayOS.toByteArray();
	}

	/**
	 * This method decompress the provided string and return actual String.
	 * 
	 * @param inputBytes
	 * @return
	 */
	public String decompressString(byte[] inputBytes) {
		if (inputBytes == null) {
			return null;
		}
		ByteArrayOutputStream byteArrayOS = null;
		Inflater inflater = new Inflater();
		inflater.setInput(inputBytes);
		byteArrayOS = new ByteArrayOutputStream();
		byte[] temp = new byte[initialSize];
		try {
			while (!inflater.finished()) {
				int size = inflater.inflate(temp);
				byteArrayOS.write(temp, 0, size);
			}
		} catch (Exception ex) {
			// TODO log and return
			System.out.println(""+ex.getMessage());
		} finally {
			try {
				if (byteArrayOS != null)
					byteArrayOS.close();
			} catch (Exception ex) {
				// TODO log and return
			}
		}
		return new String(byteArrayOS.toByteArray());
	}
	
	public boolean isEmpty(String value) {
		return !(value != null && value.trim().length() > 0);
	}
}