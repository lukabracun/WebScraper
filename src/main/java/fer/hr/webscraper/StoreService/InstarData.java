package fer.hr.webscraper.StoreService;

import fer.hr.webscraper.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class InstarData {
    public void extractDataFromInstar(Set<Item> items, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("product-item-box");

            for (Element ads: elements) {
                Item item = new Item();
                item.setName(ads.select("a.productEntityClick span").text());
                item.setUrl("https://www.instar-informatika.hr" + ads.select("a.productEntityClick").attr("href"));
                Element imgElement = ads.selectFirst("div.product-image img");
                if (imgElement != null) {
                    item.setImageUrl(imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("div.price span.standard-price");
                if (priceElement != null) {
                    item.setPrice(priceElement.text());
                }
                Element oldPriceElement = ads.selectFirst("div.pricelistpriceLow span:first-child");
                if (oldPriceElement != null) {
                    item.setOldPrice(oldPriceElement.text().trim());
                }
                Element discountElement = ads.selectFirst("div.pricelistpriceLow span.order-4");
                if (discountElement != null) {
                    item.setDiscount(discountElement.text().trim());
                    item.setDiscounted(true);
                }
                Element availabilityElement = ads.selectFirst("div.product-tehspec_footer.instock span");
                if (availabilityElement != null) {
                    item.setAvailability(availabilityElement.text().trim());
                } else {
                    item.setAvailability("Nije dostupno");
                }
                item.setStoreName("Instar Informatika");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}