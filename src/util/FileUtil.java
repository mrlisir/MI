package util;

import java.util.UUID;

public class FileUtil {
	public static String makeFileName(String filename) { // 2.jpg
		return UUID.randomUUID().toString() 
				+ "_" + filename;
	}
}
