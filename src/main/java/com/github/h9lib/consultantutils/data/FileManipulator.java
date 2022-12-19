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

import com.github.h9lib.consultantutils.annotations.data.file.excel.ExcelColumnName;
import com.github.h9lib.consultantutils.annotations.data.file.excel.ExcelColumnPosition;
import com.github.h9lib.consultantutils.annotations.data.file.excel.ToExcel;
import com.github.h9lib.consultantutils.annotations.data.file.excel.XslxExcel;
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
	public static final String retrieveMimeType(String filename) {
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
	public static final String retrieveExtensionFromFilename(String filename) {
		return Optional.of(filename)
				.map(fn -> fn.split("\\."))
				.map(splittedFileName -> splittedFileName[splittedFileName.length - 1])
				.get();
	}
	
	/**
	 * This method allow the user to generate an Excel from a list of Object. To use the method you have to pass both the parametrized 
	 * list and the class of the parametric object. For example: <br>
	 * <code>FileManipulator.generateExcelAsByteArray(peopleList, Person.class);</code> <br>
	 * where <code>peopleList</code> is defined as <code>List&lt;Person&gt;</code>. <br>
	 * The method return the Excel encripted as Base64 string.
	 * 
	 * 
	 * @param <T> 
	 * @param obj The list to export as Excel. Each element of this list will be a row of the Excel.
	 * @param clazz The parametric class.
	 * @return a String that represents the Base64 encripted file.
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws MissingAnnotationException 
	 * @throws IOException 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static final <T> String generateExcelAsBase64(List<T> obj, Class<T> clazz) throws IllegalArgumentException, IllegalAccessException, MissingAnnotationException, IOException, NoSuchFieldException, SecurityException {
		Workbook workbook;
		
		//workbook generation for Excel 1997-2007 and 2007-higher
		workbook = clazz.getAnnotation(XslxExcel.class).isXslxExcel()
				 ? new XSSFWorkbook() 
				 : new HSSFWorkbook();
		
		generateExcel(obj, workbook, clazz);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		return new String(Base64.encodeBase64(outputStream.toByteArray()));
	}
	
	/**
	 * This method allow the user to generate an Excel from a list of Object. To use the method you have to pass both the parametrized 
	 * list and the class of the parametric object. For example:  <br>
	 * <code>FileManipulator.generateExcelAsByteArray(peopleList, Person.class);</code> <br>
	 * where <code>peopleList</code> is defined as <code>List&lt;Person&gt;</code>. <br>
	 * The method return the Excel as a ByteArray (in particular as a ByteArrayOutputStream).
	 * 
	 * 
	 * @param <T> 
	 * @param obj The list to export as Excel. Each element of this list will be a row of the Excel.
	 * @param clazz The parametric class
	 * @return ByteArrayOutputStream containing the file
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws MissingAnnotationException 
	 * @throws IOException 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static final <T> byte[] generateExcelAsByteArray(List<T> obj, Class<T> clazz) throws IllegalArgumentException, IllegalAccessException, MissingAnnotationException, IOException, NoSuchFieldException, SecurityException {
		Workbook workbook;
		workbook = clazz.getAnnotation(XslxExcel.class).isXsltExcel()
				 ? new XSSFWorkbook() 
				 : new HSSFWorkbook();
		
		generateExcel(obj, workbook, clazz);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		return outputStream.toByteArray();
	}
	
	private static <T> void generateExcel(List<T> obj, Workbook workbook, Class<T> clazz) throws IllegalArgumentException, MissingAnnotationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if(CollectionUtils.isEmpty(obj)) {
			throw new IllegalArgumentException("Non-valid list. It could be empty or null");
		}
		
		Sheet sheet;
		CellStyle headerRowStyle;
		CellStyle dataRowStyle;
		Font headerRowFont;
		Font dataRowFont;
		
		if(!(clazz.isAnnotationPresent(ToExcel.class) && clazz.getAnnotation(ToExcel.class).exportToExcel())) { 
			throw new MissingAnnotationException("The class " + clazz.getName() + " cannot be exported as Excel. To export as Excel, the class must be annotated with @ToExcel.");
		} else {
			if(!clazz.isAnnotationPresent(XslxExcel.class))
				throw new MissingAnnotationException("It needed to specify if the Excel extension must be .xslx or not.");
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) {
				if(!(f.isAnnotationPresent(ExcelColumnName.class) || f.isAnnotationPresent(ExcelColumnPosition.class)))
					throw new MissingAnnotationException("No column defined");
			}
			
			sheet = workbook.createSheet(clazz.getName().substring(clazz.getName().lastIndexOf('.')+1));
			headerRowFont = workbook.createFont();
			dataRowFont = workbook.createFont();
			headerRowStyle = workbook.createCellStyle();
			dataRowStyle = workbook.createCellStyle();

			/*** START WRITING HEADER ROW ***/
	        Row headerRow = sheet.createRow(0);
	        
			headerRowFont.setFontHeightInPoints((short) 12);
			headerRowFont.setFontName("Arial");
			headerRowFont.setBold(true);		
			headerRowStyle.setFont(headerRowFont);
			
			for(Field f : fields) {
				String columnName = f.getAnnotation(ExcelColumnName.class).column();
				int columnPosition = f.getAnnotation(ExcelColumnPosition.class).position();
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
			
			//Create the row for each element of the list
			for(T t : obj) {
				Row dataRow = sheet.createRow(rowCount++);
				dataRow.setRowStyle(dataRowStyle);
				int cellCount = 0;
				
				//Create a cell for each element in the row
				for(Field f : fields) {
					f.setAccessible(true);
					int columnPosition = f.getAnnotation(ExcelColumnPosition.class).position();
					sheet.autoSizeColumn(columnPosition);
					Cell cell = dataRow.createCell(columnPosition);
					
					if (f.get(t) instanceof Integer) {
			            cell.setCellValue((Integer) f.get(t));
			        } else if (f.get(t) instanceof Boolean) {
			            cell.setCellValue((Boolean) f.get(t));
			        }else {
			            cell.setCellValue((String) f.get(t));
			        }
					
					cell.setCellStyle(dataRowStyle);
				}
			}
			/***  END WRITING DATA ROWS  ***/
		}
	}
}
