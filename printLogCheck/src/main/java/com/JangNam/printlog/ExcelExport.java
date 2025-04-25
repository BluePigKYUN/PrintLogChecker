package com.JangNam.printlog;

import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport {
	public static void exportToExcel(List<String[]> dataRows, String fileName){
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("출력로그");
		
		//머리글
		String[] headers = {"사용자ID", "이름", "부서", "출력일자", "문서명", "사용자IP"};
		Row headerRows = sheet.createRow(0);
		for (int i = 0 ; i < headers.length ; i++) {
			Cell cell = headerRows.createCell(i);
			cell.setCellValue(headers[i]);
		}
		
		//데이터
		for (int i = 0 ; i < dataRows.size() ; i++) {
			Row row = sheet.createRow(i + 1);
			String[] rowData = dataRows.get(i);
			for(int j = 0 ; j < rowData.length ; j++) {
				row.createCell(j).setCellValue(rowData[j]);
			}
		}
		
		
	}

}
