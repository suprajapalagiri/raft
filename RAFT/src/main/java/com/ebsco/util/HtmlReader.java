package com.ebsco.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

import javax.print.Doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ebsco.entity.FullTextRequest;

public class HtmlReader {

	public static FullTextRequest processHtmlData(String fileData, int requestId) throws IOException {
		Document htmlFile = null;
		String title = null;
		String citaion = null;
		String pmId = null;
		String pwcId = null;
		String journalTitle = null;
		String nesId = null;
		try {
			htmlFile = Jsoup.parse(fileData);

			title = htmlFile.title();
			nesId = new SimpleDateFormat("yyyyMMdd").format(new Date()) + (requestId + 1);

			System.out.println("Jsoup can also parse HTML file directly");
			System.out.println("title : " + title);
			journalTitle = htmlFile.getElementsByAttributeValue("name", "citation_journal_title").attr("content");

			// journal period cit citation-doi full-view-identifiers
			citaion = htmlFile.getElementsByClass("journal").get(0).text() + ""
					+ htmlFile.getElementsByClass("period").get(0).text() + ""
					+ htmlFile.getElementsByClass("cit").get(0).text() + ""
					+ htmlFile.getElementsByClass("citation-doi").get(0).text();
			System.out.println(citaion);

			Element data = htmlFile.getElementById("full-view-identifiers");
			System.out.println(htmlFile.getElementsByClass("free-label"));

			pmId = data.getElementsByTag("a").get(0).text();
			pwcId = data.getElementsByTag("a").get(1).text();

			return FullTextRequest.builder().title(title).citation(citaion).pmId(Integer.valueOf(pmId))
					.creationDate(Instant.now()).priority("Normal").reOpened(false).isSlamRecord(false)
					.journalTitle(journalTitle).isRequested(true).requestID(requestId + 1).nesId(nesId)
					.pmId(Integer.valueOf(pmId)).build();

		} catch (Exception e) {
			return null;

		}
	}
//
//	public static void main(String[] args) throws IOException {
//
//		String fileData = Files.lines(Paths.get("./Untitled4.html")).map(n -> n + "\n").collect(Collectors.joining());
//		System.out.println(fileData);
//		System.out.println(main(fileData));
//
//	}

}
