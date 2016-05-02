package edu.olivet.se530;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.olivet.se530.model.Offer;
import edu.olivet.se530.model.Product;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Singleton
public class SellerHunter {
	@Inject private HtmlCrawler htmlFetcher;
	@Inject private HtmlParser htmlParser;




//	HtmlCrawlerImpl htmlFetcherReal = new HtmlCrawlerImpl();
//	HtmlParser htmlParserReal = new HtmlParser();
	/**
	 * 根据给定的isbn和condition，返回亚马逊网站上面的Offer列表
	 * @param isbn		产品的ISBN编号，参见:{@link Product#getIsbn()}
	 * @param condition	产品的Condition
	 */
	public Offer huntOffer(String isbn, String condition) throws IOException {

		this.setHtmlFetcher(new HtmlCrawlerImpl());
		this.setHtmlParser(new HtmlParser());
		Document doc = htmlFetcher.getDocument(isbn, condition);


		List<Offer> offers = htmlParser.parseOffer(doc);
		
		for (Iterator<Offer> iterator = offers.iterator(); iterator.hasNext();) {
			Offer offer = iterator.next();
			if (!this.evaluate(offer)) {
				iterator.remove();
			}
		}
		
		Collections.sort(offers);
		return offers.get(0);
	}

	/**
	 * 对一个Offer按照价格、运费、Rating等等标准进行审查
	 */
    private boolean evaluate(Offer offer) {
		return offer.getSeller().getRating() >= 95;
	}

	public void setHtmlFetcher(HtmlCrawler htmlFetcher) {
		this.htmlFetcher = htmlFetcher;
	}

	public void setHtmlParser(HtmlParser htmlParser) {
		this.htmlParser = htmlParser;
	}
}