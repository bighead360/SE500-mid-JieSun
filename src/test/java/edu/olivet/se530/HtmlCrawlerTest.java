package edu.olivet.se530;

import com.google.inject.Inject;
import edu.olivet.se530.modules.CrawlerModule;
import edu.olivet.se530.modules.ProfileModule;
import org.jsoup.nodes.Document;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(JukitoRunner.class)
@UseModules(value = {ProfileModule.class, CrawlerModule.class})
public class HtmlCrawlerTest {
	@Inject private HtmlCrawlerImpl htmlCrawler;

    @Test public void test_get_text() throws IOException {
        String condition = "new";
        String isbn = "0135157862";
        Document document = htmlCrawler.getDocument(isbn, condition);
        String selector = "#olpProductDetails > h1";
        String selectorb = "#olpOfferList > div > div > div:nth-child(3) > div.a-column.a-span2.olpSellerColumn > h3";

        Assert.assertTrue(document.select(selector).size() > 0);
        //Assert.assertTrue(document.select(selectorb).size() == 0);
        System.out.println(document.select(selector).text());
        System.out.println(document.select(selectorb).text().length());
	}

}
