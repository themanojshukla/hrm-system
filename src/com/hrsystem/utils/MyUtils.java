package com.hrsystem.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class MyUtils {
	public static byte[] getByteArray(Part file) throws IOException {
		ByteArrayOutputStream output = null;
		if (file != null) {
			InputStream input = file.getInputStream();
			byte[] buffer = new byte[8192];
			int bytesRead;
			output = new ByteArrayOutputStream();
			while ((bytesRead = input.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
			
		}
		return  output.toByteArray();
	}
}
