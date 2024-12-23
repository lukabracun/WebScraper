package fer.hr.webscraper.StoreService;

import fer.hr.webscraper.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class LinksData {
    public void extractDataFromLinks(Set<Item> items, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("col-6 col-md-4");

            for (Element ads : elements) {
                Item item = new Item();
                Element linkElement = ads.selectFirst("a.card-link");
                Element imgElement = ads.selectFirst("img.img-fluid");
                if (linkElement != null) {
                    item.setName(linkElement.attr("title"));
                    item.setUrl("https://www.links.hr" + linkElement.attr("href"));
                }
                if (imgElement != null) {
                    item.setImageUrl(imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("div.product-price span.active");
                if (priceElement != null) {
                    item.setPrice(priceElement.text());
                }
                Element oldPriceElement = ads.selectFirst("div.product-price span.inactive");
                if (oldPriceElement != null) {
                    item.setOldPrice(oldPriceElement.text().trim());
                }
                Element discountElement = ads.selectFirst("span.badge-discount");
                if (discountElement != null) {
                    item.setDiscount(discountElement.text().trim());
                    item.setDiscounted(true);
                }
                item.setState("Dostupno");
                item.setStoreName("Links");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}