package edu.olivet.se530.entry;

import com.google.inject.Guice;
import edu.olivet.se530.SellerHunter;
import edu.olivet.se530.model.Offer;
import edu.olivet.se530.modules.CrawlerModule;

import java.io.IOException;

/**
 * Seller猎手
 * @author <a href="mailto:nathanaelibport@gmail.com">Nathanael Yang</a> Jan 8, 2015 2:11:20 PM
 */

class SellerHunterEntry {

	public static void main(String[] args) throws IOException {
		SellerHunter hunter = Guice.createInjector(new CrawlerModule()).getInstance(SellerHunter.class);
        Offer offer = hunter.huntOffer("020161622X", "Used");
		System.out.println(offer);
	}	
}
