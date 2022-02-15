package com.kona.kms.web.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

	public static void saveAsFile(String path, String content) throws IOException{
		
			int index = path.lastIndexOf("/");
			
			File dir = new File(path.substring(0, index));
			
			if(!dir.exists()){
				dir.mkdirs();
			}
		
			File file = new File(path);
						
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		
	}
	
	public static void saveAsFile(String path, byte[] content) throws IOException{
		
		int index = path.lastIndexOf("/");
		
		File dir = new File(path.substring(0, index));
		
		if(!dir.exists()){
			dir.mkdirs();
		}
	
		File file = new File(path);
					
		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fw = new FileOutputStream(file.getAbsoluteFile());
		fw.write(content);
		fw.close();	
	}
}
