package com.api.CrudMvcRestFul.CommonsImage;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class FileToBase64StringConversionUnitTest {
	private String inputFilePath = "me.jpg";
	private String outputFilePath = "me_copy.jpg";

	public void fileToBase64StringConversion() throws IOException {
		// load file from /src/test/resources
		ClassLoader classLoader = getClass().getClassLoader();
		File inputFile = new File(classLoader.getResource(inputFilePath).getFile());

		byte[] fileContent = FileUtils.readFileToByteArray(inputFile);
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		System.out.println("array em base 64");
		System.out.println(encodedString);
		
		// create output file
		File outputFile = new File(inputFile.getParentFile().getAbsolutePath() + File.pathSeparator + outputFilePath);

		// decode the string and write to file
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		FileUtils.writeByteArrayToFile(outputFile, decodedBytes);

//		assertTrue(FileUtils.contentEquals(inputFile, outputFile));
	}

}
