package fer.hr.webscraper.StoreService;

import fer.hr.webscraper.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class RonisData {
    public void extractDataFromRonis(Set<Item> items, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("product-item-box");

            for (Element ads : elements) {
                Item item = new Item();
                Element linkElement = ads.selectFirst("a.productEntityClick");
                Element imgElement = ads.selectFirst("img.img-fluid");
                Element titleElement = ads.selectFirst("h2.title a");
                Element priceElement = ads.selectFirst("span.standard-price");
                Element discountElement = ads.selectFirst("div.pricemin-title span");

                if (linkElement != null) {
                    item.setName(titleElement.text());
                    item.setUrl("https://www.ronis.hr" + linkElement.attr("href"));
                }
                if (imgElement != null) {
                    item.setImageUrl(imgElement.attr("src"));
                }
                if (priceElement != null) {
                    item.setPrice(priceElement.text().trim());
                }
                if (discountElement != null) {
                    item.setAdditionalInfo(discountElement.text().trim());
                }
                item.setStoreName("Ronis");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}