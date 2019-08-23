package com.scratchframework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;

public class DataUtil {
	
	private static final Logger log = Logger.getLogger(DataUtil.class);

	public static void main(String[] args) {

		try {
			File file = new File("./src/main/resources/org/data/TestData.xlsx");
			FileInputStream fileStream = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(fileStream);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.iterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					log.debug("A cell has been read");

					if (cell.getCellType().equals(CellType.STRING)) {
						System.out.println(cell.getStringCellValue());
					} else if (cell.getCellType().equals(CellType.NUMERIC)) {
						System.out.println(cell.getNumericCellValue());
					}

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
