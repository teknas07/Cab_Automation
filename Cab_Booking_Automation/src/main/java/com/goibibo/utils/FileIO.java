package com.goibibo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.goibibo.base.BaseUI;

public class FileIO {

	private static FileInputStream read_file;
	private static XSSFWorkbook workbook;
	private static XSSFSheet worksheet;
	private static Row row;
	private static FileOutputStream write_file;
	private static File file;
	private static Properties prop;

	/************** Get properties file ****************/
	public static Properties initProperties() {
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/objectrepository/config.properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return prop;
	}

	/**************
	 * Get Test Data from excel sheet based on Test Name
	 ****************/
	public static HashMap<String, ArrayList<String>> readExcelData(int index) throws IOException {

		// Creating hashmap to store data
		HashMap<String, ArrayList<String>> data = new HashMap<>();

		// Reading the excel sheet
		File src = new File(System.getProperty("user.dir") + prop.getProperty("testData_path"));
//		System.out.println(src.getAbsolutePath());
		read_file = new FileInputStream(src);
		
		workbook = new XSSFWorkbook(read_file);
		
		System.out.println("Sheet name :"+""+workbook.getSheetAt(index));
		System.out.println(workbook.getNumberOfSheets());
		worksheet = workbook.getSheetAt(index);

		
		// Iterating over all cells in the sheet
		Iterator<Row> rowIterator;
		ArrayList<String> rowData = new ArrayList<>();
		rowIterator = worksheet.iterator();
		int rowNum = 1;// "1": {..}
		if (rowIterator.hasNext())
			row = rowIterator.next();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			Iterator<Cell> cellIterator = row.iterator();
			if (row.getCell(0) == null) {
				break;
			}

			// Writing cell data to hashmap based on cell data type
			rowData = new ArrayList<>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				CellType type = cell.getCellTypeEnum();
				if (type.equals(CellType.STRING))
					rowData.add(cell.getStringCellValue());
				else if (type.equals(CellType.NUMERIC))
					rowData.add("" + (int) cell.getNumericCellValue());
			}
			data.put("" + (rowNum), rowData);
			rowNum++;
		}

		// Closing FileInputStream and XSSFWorkbook
		workbook.close();
		read_file.close();
		return data;
	}

}