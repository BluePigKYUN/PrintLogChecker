package com.JangNam.printlog;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PrintLogParser {
	public static List<String[]> parse(String html) {
		List<String[]> result = new ArrayList<>();

		Document doc = Jsoup.parse(html);
		Elements rows = doc.select("table tbody tr");

		//System.out.printf("%-12s %-6s %-8s %-20s %-30s %-15s %-15s \n", "사용자ID", "이름", "부서", "출력일자", "문서명", "사용자IP", "프린터");

		for (Element row : rows) {
			Elements cols = row.select("td");
			if (cols.size() >= 11) {
				String[] rowData = new String[] { cols.get(2).text(), // 사용자 ID
						cols.get(3).text(), // 이름
						cols.get(4).text(), // 부서
						cols.get(6).text(), // 출력일자
						cols.get(9).text(), // 문서명
						cols.get(10).text(), // 프린터이름
						cols.get(11).text() // 사용자 IP
				};
				result.add(rowData);
			}
		}
		return result;
	}
}
