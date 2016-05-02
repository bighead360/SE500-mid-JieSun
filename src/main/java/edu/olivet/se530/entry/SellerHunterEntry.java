package edu.olivet.se530.entry;

import edu.olivet.se530.SellerHunter;
import edu.olivet.se530.model.Offer;
import edu.olivet.se530.modules.CrawlerModule;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Seller猎手
 * @author <a href="mailto:nathanaelibport@gmail.com">Nathanael Yang</a> Jan 8, 2015 2:11:20 PM
 */
@RunWith(JukitoRunner.class)
@UseModules(value = CrawlerModule.class)
class SellerHunterEntry {

	public static void main(String[] args) throws IOException {
		SellerHunter hunter = new SellerHunter();
		Offer offer = hunter.huntOffer("020161622X", "Used");
		System.out.println(offer);
	}	
}
