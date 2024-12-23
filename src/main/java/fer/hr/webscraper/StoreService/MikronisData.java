package fer.hr.webscraper.StoreService;

import fer.hr.webscraper.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class MikronisData {
    public void extractDataFromMikronis(Set<Item> items, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("prod-item");

            for (Element ads : elements) {
                Item item = new Item();
                Element linkElement = ads.selectFirst("div.item-pic a");
                Element imgElement = ads.selectFirst("div.item-pic img.img-responsive");
                if (linkElement != null) {
                    item.setName(linkElement.attr("title"));
                    String extractedUrl = linkElement.attr("href");
                    item.setUrl("https://www.mikronis.hr" + extractedUrl);
                }
                if (imgElement != null) {
                    item.setImageUrl("https://www.mikronis.hr" + imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("li.item-price a");
                if (priceElement != null) {
                    item.setPrice(priceElement.text());
                }
                Element availabilityElement = ads.selectFirst("div.ip-available");
                if (availabilityElement != null) {
                    item.setState("Dostupno");
                } else {
                    item.setState("Nedostupno");
                }
                Element discountElement = ads.selectFirst("span.code-discount i");
                if (discountElement != null) {
                    item.setAdditionalInfo(discountElement.text().trim());
                }
                item.setStoreName("Mikronis");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}