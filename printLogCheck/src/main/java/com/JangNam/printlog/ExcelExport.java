package com.JangNam.printlog;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport {
	public static void exportToExcel(List<String[]> dataRows, String fileName){
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("출력로그");
		
		//머리글
		String[] headers = {"사용자ID", "이름", "부서", "출력일자", "문서명", "프린터이름","사용자IP"};
		Row headerRows = sheet.createRow(0);
		for (int i = 0 ; i < headers.length ; i++) {
			Cell cell = headerRows.createCell(i);
			cell.setCellValue(headers[i]);
		}
		
		int rowIndex = 1; // 0은 헤더, 1부터 데이터 시작
		//데이터
		for (int i = 0 ; i < dataRows.size() ; i++) {
		    String[] rowData = dataRows.get(i);
		    if (rowData == null || rowData.length == 0 || rowData[0].trim().isEmpty()) {
		        continue;
		    }

		    Row row = sheet.createRow(rowIndex++);
		    for (int j = 0 ; j < rowData.length ; j++) {
		        row.createCell(j).setCellValue(rowData[j]);
		    }
		}
		
		for (int i = 0 ; i < headers.length ; i++) {
			sheet.autoSizeColumn(i);
		}
		
		try (FileOutputStream fileOut = new FileOutputStream(fileName)){
			workbook.write(fileOut);
			System.out.println("엑셀 저장 완료: " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
