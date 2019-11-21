package edu.mum.wap.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

 

public class ImageProcessor {

//	public static String saveFileToFolder(MultipartFile file) {
//		String filePath = "";
//		InputStream in = null;
//		try {
//			in = new ByteArrayInputStream(file.getBytes());
//			filePath = UUID.randomUUID() + file.getOriginalFilename();
//			Path path = Paths.get(filePath);
//			Files.copy(in, path);
//
//			filePath = UUID.randomUUID() + file.getOriginalFilename();
//			FileUtils.writeByteArrayToFile(new File("\\src\\files\\" + filePath), file.getBytes());
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (in != null)
//					in.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
//		return filePath;
//	}

}
