package com.github.h9lib.consultantutils.data;

import java.util.Optional;

import com.github.h9lib.consultantutils.constants.FileExtension;

public class FileManipulator {
	
	/**
	 * It returns the MimeType for the specified filename.
	 * 
	 * @param filename is the name of the file you want to retrieve the MimeType. It must contains the extension.
	 * 		  A filename example could be: <i>file.txt</i>
	 * @return a String which represents the retrieved MimeType.
	 */
	public static String retrieveMimeType(String filename) {
		String extension = retrieveExtensionFromFilename(filename);

		if(FileExtension.CSV.equalsIgnoreCase(extension)) return "text/csv";
		if(FileExtension.MSG.equalsIgnoreCase(extension)) return "application/vnd.ms-outlook";
		if(FileExtension.DOC.equalsIgnoreCase(extension)) return "application/msword";
		if(FileExtension.DOCX.equalsIgnoreCase(extension)) return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		if(FileExtension.XLS.equalsIgnoreCase(extension)) return "application/vnd.ms-excel";
		if(FileExtension.XLSX.equalsIgnoreCase(extension)) return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		if(FileExtension.PDF.equalsIgnoreCase(extension)) return "application/pdf";
		if(FileExtension.JPG.equalsIgnoreCase(extension) || FileExtension.JPEG.equalsIgnoreCase(extension)) return "image/jpeg";
		if(FileExtension.PNG.equalsIgnoreCase(extension)) return "image/png";
		if(FileExtension.TIF.equalsIgnoreCase(extension) || FileExtension.TIFF.equalsIgnoreCase(extension)) return "image/tiff";
		if(FileExtension.GIF.equalsIgnoreCase(extension)) return "image/gif";
		if(FileExtension.HTML.equalsIgnoreCase(extension) || FileExtension.HTM.equalsIgnoreCase(extension)) return "text/html";
		if(FileExtension.XML.equalsIgnoreCase(extension)) return "text/xml";
		if(FileExtension.SEVEN_ZIP.equalsIgnoreCase(extension)) return "application/x-7z-compressed";
		if(FileExtension.ZIP.equalsIgnoreCase(extension)) return "application/zip";
		if(FileExtension.TAR.equalsIgnoreCase(extension)) return "application/x-tar";
		if(FileExtension.GZ.equalsIgnoreCase(extension)) return "application/gzip";
		if(FileExtension.RAR.equalsIgnoreCase(extension)) return "application/vnd.rar";
		if(FileExtension.TXT.equalsIgnoreCase(extension)) return "text/plain";
		if(FileExtension.RTF.equalsIgnoreCase(extension)) return "application/rtf";
		if(FileExtension.JSON.equalsIgnoreCase(extension)) return "application/json";
		
		return "*/*";
	}
	
	/**
	 * It returns the extension for the specified filename.
	 * 
	 * @param filename is the name of the file you want to retrieve the MimeType. It must contains the extension.
	 * 		  A filename example could be: <i>file.txt</i>
	 * @return a String which represents the retrieved extension.
	 */
	public static String retrieveExtensionFromFilename(String filename) {
		return Optional.of(filename)
				.map(fn -> fn.split("\\."))
				.map(splittedFileName -> splittedFileName[splittedFileName.length - 1])
				.get();
	}
}
