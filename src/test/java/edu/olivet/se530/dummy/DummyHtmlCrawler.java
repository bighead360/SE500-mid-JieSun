package edu.olivet.se530.dummy;

import edu.olivet.se530.HtmlCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class DummyHtmlCrawler implements HtmlCrawler {
	private static final String Directory = System.getProperty("user.dir");
	private static final int ISBN_LENGTH = 10;
	

	@Override
	public Document getDocument(String isbn, String condition) throws MalformedURLException, IOException {
		String inputFile = String.format("%s/Assignment/%s_%s_1.html", Directory,isbn,condition.toUpperCase());
		File input = new File(inputFile);
		Document doc = Jsoup.parse(input, "UTF-8");
		return doc;
	}

}
