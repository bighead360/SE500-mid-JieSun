package edu.olivet.se530;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by <a href="davidsunjie.sun@gmail.com">jerrysun</a> on 5/2/16.
 */


public class HtmlCrawlerReal {

    HtmlCrawlerImpl htmlCrawlerReal = new HtmlCrawlerImpl();
    @Test
    public void test_get_text() throws IOException {
        String condition = "new";
        String isbn = "020161622X";
        Document document = htmlCrawlerReal.getDocument(isbn, condition);
        String selector = "#olpProductDetails > h1";
        String selectorb = "#olpOfferList > div > div > div:nth-child(3) > div.a-column.a-span2.olpSellerColumn > h3";

        Assert.assertTrue(document.select(selector).size() > 0);
        //Assert.assertTrue(document.select(selectorb).size() == 0);
        System.out.println(document.select(selector).text());
        System.out.println(document.select(selectorb).text().length());
    }
}
