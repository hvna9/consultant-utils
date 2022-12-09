package com.github.h9lib.consultantutils.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.h9lib.consultantutils.annotations.data.file.excel.DocBindByName;
import com.github.h9lib.consultantutils.annotations.data.file.excel.DocBindByPosition;
import com.github.h9lib.consultantutils.annotations.data.file.excel.ToExcel;
import com.github.h9lib.consultantutils.annotations.data.file.excel.XsltExcel;
import com.github.h9lib.consultantutils.constants.FileExtension;
import com.github.h9lib.consultantutils.exceptions.annotations.MissingAnnotationException;

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
	
	public static <T> String generateExcelAsBase64(List<T> obj, Class<T> clazz) throws IllegalArgumentException, IllegalAccessException, MissingAnnotationException, IOException {
		Workbook workbook;
//		final Class<T> clazz;
		if(!CollectionUtils.isEmpty(obj))
			System.out.println("clazz*** -> " + clazz.getName());
			//clazz = getType(obj.get(0));
		else
			throw new IllegalArgumentException("Non-valid list. It could be empty or null");
		workbook = clazz.getAnnotation(XsltExcel.class).isXsltExcel()
				 ? new XSSFWorkbook() 
				 : new HSSFWorkbook();
		
		generateExcel(obj, workbook, clazz);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		
		return Base64.encodeBase64(outputStream.toByteArray()).toString();
	}
	
	//TODO: cambiare tipo di ritorno e nome metodo (eventualmente chiamarlo exportExcel e differenziare
	//la signature a seconda che si voglia esportare un ByteArray o altro **** DA CAPIRE
	public static <T> void generateExcel(List<T> obj, Workbook workbook, Class<T> clazz) throws IllegalArgumentException, MissingAnnotationException, IllegalAccessException {
//		final Class<T> clazz;
		if(!CollectionUtils.isEmpty(obj)) {
		//	clazz = getType(obj.get(0));
//			clazz = obj.get(0).getClass();
			//System.out.println("clazz: " + clazz.getClass());
		}
		else
			throw new IllegalArgumentException("Non-valid list. It could be empty or null");
		
		//Workbook workbook;
		Sheet sheet;
		CellStyle headerRowStyle;
		CellStyle dataRowStyle;
		Font headerRowFont;
		Font dataRowFont;
		//Cell cell;
		
		if(clazz.isAnnotationPresent(ToExcel.class) && clazz.getAnnotation(ToExcel.class).exportToExcel()) {
			if(!clazz.isAnnotationPresent(XsltExcel.class))
				throw new MissingAnnotationException("It needed to specify if the Excel extension must be .xslt or not.");
			//TODO: SISTEMARE QUESTO CONTROLLO --> VANNO CONTROLLATI I FIELDS
			//			if(!(clazz.isAnnotationPresent(DocBindByName.class) || clazz.isAnnotationPresent(DocBindByPosition.class)))
//				throw new MissingAnnotationException("No column defined");
			
//			workbook = clazz.getAnnotation(XsltExcel.class).isXsltExcel()
//					 ? new XSSFWorkbook() 
//					 : new HSSFWorkbook();
			sheet = workbook.createSheet(clazz.getName());
			headerRowFont = workbook.createFont();
			dataRowFont = workbook.createFont();
			headerRowStyle = workbook.createCellStyle();
			dataRowStyle = workbook.createCellStyle();
			
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) {
				System.out.println(f.getName());
			}

			/*** START WRITING HEADER ROW ***/
	        Row headerRow = sheet.createRow(0);
	        
			headerRowFont.setFontHeightInPoints((short) 12);
			headerRowFont.setFontName("Arial");
			headerRowFont.setBold(true);		
			headerRowStyle.setFont(headerRowFont);
			
			for(Field f : fields) {
				String columnName = f.getAnnotation(DocBindByName.class).column();
				System.out.println("\n\n**** " + columnName + " ****\n\n");
				int columnPosition = f.getAnnotation(DocBindByPosition.class).position();
				sheet.autoSizeColumn(columnPosition);
				Cell cell = headerRow.createCell(columnPosition);
				
				cell.setCellValue(columnName);
				cell.setCellStyle(headerRowStyle);
			}
			/***  END WRITING HEADER ROW  ***/
			
			/*** START WRITING DATA ROWS ***/
			int rowCount = 1;

			dataRowFont.setFontHeightInPoints((short) 10);
			dataRowStyle.setFont(dataRowFont);
			dataRowStyle.setAlignment(HorizontalAlignment.LEFT);
			
			//creo la riga per ogni elemento della lista
			for(T t : obj) {
				Row dataRow = sheet.createRow(rowCount++); //TODO: forse qui ci va solo rowCount e rowCount++ va fatto in seguito
				dataRow.setRowStyle(dataRowStyle);
				int cellCount = 0;
				
				//creo la cella per la singola riga
				for(Field f : fields) {
					int columnPosition = f.getAnnotation(DocBindByPosition.class).position();
					sheet.autoSizeColumn(columnPosition);
					Cell cell = dataRow.createCell(columnPosition);
					
					//TODO: controllare se f.get(clazz) va bene 
					if (f.get(clazz) instanceof Integer) {
			            cell.setCellValue((Integer) f.get(clazz));
			        } else if (f.get(clazz) instanceof Boolean) {
			            cell.setCellValue((Boolean) f.get(clazz));
			        }else {
			            cell.setCellValue((String) f.get(clazz));
			        }
					
					cell.setCellStyle(dataRowStyle);
				}
			}
			/***  END WRITING DATA ROWS  ***/
			
			//TODO: sistemare posiione di questa eccezione
			throw new MissingAnnotationException("The class " + clazz.getName() + "cannot be exported as Excel.");
		}
		
		return; //return se non è presente l'annotazione ToExcel
	}
	
	//
	private static <T> Class<T> getType(T type) {
		System.out.println("***" + type.getClass());
		return null;
	}
}
