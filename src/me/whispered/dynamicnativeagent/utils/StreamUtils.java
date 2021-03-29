package me.whispered.dynamicnativeagent.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {
	public static byte[] isToByteArray(InputStream inputStream) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		try {
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toByteArray();
	}
}
