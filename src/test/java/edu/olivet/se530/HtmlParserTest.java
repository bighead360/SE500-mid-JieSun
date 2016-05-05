package edu.olivet.se530;

import com.google.inject.Inject;
import edu.olivet.se530.dummy.DummyHtmlCrawler;
import edu.olivet.se530.model.Condition;
import edu.olivet.se530.model.Offer;
import edu.olivet.se530.model.Seller;
import edu.olivet.se530.modules.ProfileModule;
import org.jsoup.nodes.Document;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(JukitoRunner.class)
@UseModules(value = ProfileModule.class)
public class HtmlParserTest {
	@Inject private HtmlParser htmlParser;
	private static Document document;
	private static Document documentUsed;
	
	@BeforeClass public static void prepareDocument() throws IOException {
		document = new DummyHtmlCrawler().getDocument("0135157862", "new");
		documentUsed = new DummyHtmlCrawler().getDocument("0135157862", "used");
	}
	
	@Test public void test_parse_seller() {
		Seller seller = htmlParser.parseSeller(document.select("div.a-row.a-spacing-mini.olpOffer").get(3));
		Assert.assertEquals("Maria Pelagio", seller.getName());
		Assert.assertEquals("ANSJJXK5BX8ET", seller.getUuid());
		Assert.assertEquals(100, seller.getRating());
		Assert.assertEquals(1 , seller.getRatingCount());
		Assert.assertEquals("CA", seller.getShippingState());
		Assert.assertEquals("United States", seller.getShippingCountry());
//		Assert.assertTrue(seller.isExpeditedShippingAvailable());
//		Assert.assertTrue(seller.isIntlShippingAvailable());
	}
	
	@Test public void test_parse_condtion() {
		Condition cond = htmlParser.parseCondition(documentUsed.select("div.a-row.a-spacing-mini.olpOffer").get(3));
		Assert.assertEquals(new Condition("Used", "Acceptable"),  cond);
	}
	
	@Test
	public void testParseOffer() {

		List<Offer> offers = htmlParser.parseOffer(document);

		Assert.assertTrue(53.60f == offers.get(0).getPrice());
		Assert.assertTrue(0.0f == offers.get(0).getShippingPrice());
		Assert.assertTrue(3.99f == offers.get(1).getShippingPrice());
	}

	@Test
	public void testGetText() {
		Assert.assertEquals("New offers for The Meaning of Sociology: A Reader (9th Edition) (Paperback)", htmlParser.getText(document, "#olpProductDetails > h1"));
		String rateString = htmlParser.getText(documentUsed, "#olpOfferList > div > div > div:nth-child(3) > div.a-column.a-span2.olpSellerColumn > p > a > b");
		Assert.assertEquals(98, Integer.parseInt(rateString.replaceAll("[^0-9]", "")));
//		Assert.assertEquals("Amazon Prime TM", htmlParser.getText(document, "#olpTabContent > div > div.a-section.a-spacing-double-large > div:nth-child(7) > div:nth-child(1) > span.supersaver > i"));
	}

}
