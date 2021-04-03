package com.inn.kafkaproducer.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class KafkaUtils {

	/**
	 *  converting input stream to byte Array
	 * @param input
	 * @return
	 * @throws IOException
	 */

	public static byte[] convertToByteArray(InputStream input)
			throws IOException {
		byte[] buffer = new byte[16384]; 
		int bytesRead;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
		output.flush();
		return output.toByteArray();
	}
	
}
