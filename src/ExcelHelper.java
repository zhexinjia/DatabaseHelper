import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class ExcelHelper {
	
	//read excel file and convert it to list of hash-map
	public static ArrayList<HashMap<String, String>> readXLSXFile(String path, String[] keylist) throws IOException, EncryptedDocumentException, InvalidFormatException {
		ArrayList<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();
		
		InputStream ExcelFileToRead = new FileInputStream(path);
        XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row; 
        XSSFCell cell;
        
        //formatter to read as string value regardless the type
        DataFormatter dataFormatter = new DataFormatter();
        FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wb);

        Iterator<Row> rows = sheet.rowIterator();
        //skip the first row
        rows.next();
        
        while (rows.hasNext())
        {
            row=(XSSFRow) rows.next();
            HashMap<String, String> map = new HashMap<String, String>();
            for(int i = 0; i< keylist.length;i++) {
            		cell = row.getCell(i);
            		objFormulaEvaluator.evaluate(cell);
            		if(DateUtil.isCellDateFormatted(cell)) {
            			Date date = cell.getDateCellValue();
            			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            			String cellValue = formatter.format(date);
            			map.put(keylist[i], cellValue);
            		}else {
            			String cellValue = dataFormatter.formatCellValue(cell, objFormulaEvaluator);
                		map.put(keylist[i], cellValue);    
            		}           	
            }
            maplist.add(map);
        }
		return maplist;
	}

	//write given hash-map list into a excel file
	public static void writeXLSXFile(ArrayList<HashMap<String, String>> maplist, String[] fieldlist, String[] keylist, String path) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("database in java");
		int rowNum = 0;
		
		//set up header line
		Row row = sheet.createRow(rowNum++);
		int headerColNum = 0;
		for (String field:fieldlist) {
			Cell cell = row.createCell(headerColNum++);
			cell.setCellValue(field);
		}
		//set up data entries
		for (HashMap<String, String> map:maplist) {
			row = sheet.createRow(rowNum++);
			int colNum = 0;
			for(String key:keylist) {
				//FIXME: fix format for some keys
				Cell cell = row.createCell(colNum++);
				cell.setCellValue((String)map.get(key));
			}
		}
		
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			workbook.write(outputStream);
			workbook.close();
		}catch(Exception e) {
			//FIXME: popUP warning box
			e.printStackTrace();
		}
		
		
	}
	
}
