package com.JangNam.printlog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PrintLogParser {
	public static void parse(String html) {
		Document doc = Jsoup.parse(html);
		Elements rows = doc.select("table tbody tr");
		
		System.out.printf("%-12s %-6s %-8s %-20s %-30s %-15s\n", "사용자ID", "이름", "부서", "출력일자", "문서명", "사용자IP");
		
		for (Element row : rows) {
			Elements cols = row.select("td");
			
			if (cols.size() >= 11) {
				String userId = cols.get(2).text();
				String userName = cols.get(3).text();
				String dept = cols.get(4).text();
				String printDate= cols.get(6).text();
				String fileName= cols.get(9).text();
				String ip = cols.get(11).text();
				
				System.out.printf("%-12s %-6s %-8s %-20s %-30s %-15s\n", userId, userName, dept, printDate, fileName, ip);
				
			}
		}
	}

}
