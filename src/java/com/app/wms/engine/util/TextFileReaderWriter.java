package com.app.wms.engine.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileReaderWriter 
{
	public static List<String> getLinesFromFile(File file) throws IOException {
		System.out.println("file : "+file);
		List<String> result = new ArrayList<String>();
		String line;
		
		FileReader fileReader = new FileReader(file);
		System.out.println("fileReader : "+fileReader);
		
		BufferedReader reader = new BufferedReader(fileReader);
		System.out.println("reader : "+reader);
		
		while((line = reader.readLine()) != null) {
			result.add(line);
		}
		reader.close();
		if (result.size() == 0)
			result = null;
		
		return result;
	}
	
	public static void putLinesToFile(List<String> lines, File file) throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter writer = new BufferedWriter(fileWriter);
		
		for (String line : lines) {
			writer.write(line);
			writer.newLine();
		}
		
		writer.close();
	}

}
