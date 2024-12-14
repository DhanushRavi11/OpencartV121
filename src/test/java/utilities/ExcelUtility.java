package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook workbook;
	public  XSSFSheet sheet;
	public  XSSFRow row;
	public  XSSFCell cell;
	public  CellStyle style;
	String path;
	
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	// 1st method -- Get row count
	
	public int getRowCount(String sheetName) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount= sheet.getLastRowNum();
		workbook.close(); fi.close();
		return rowCount;
		
	}
	
	// 2nd method -- get cell count
	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close(); fi.close();
		return cellCount;
		
		
	}
	
	// 3rd method -- 
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		String data;
		try {
			//data = cell.toString();
			DataFormatter formatter = new DataFormatter();
			data=formatter.formatCellValue(cell);
		}
		catch (Exception e){
			data="";
		} // try catch is used because if there is no data in the cell, it will throw no data exception thus in catch block data is made null("") which ignores exception if there is no data
		
		workbook.close();
		fi.close();
		return data;
	
	}
	
	// 4th method writing data into cell
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		
		// here we are reading and writing data into same excel sheet so we use both input and output stream
		
		File xlfile = new File(path);
		if(!xlfile.exists()) {
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName)==-1) 
			workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);	
		
		
		if(sheet.getRow(rownum)==null) 
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		
		
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo= new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	
	}
}