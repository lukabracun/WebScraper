package fer.hr.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class SanctaData {
    public void extractDataFromSanctaDomenica(Set<Item> items, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("product-item-info");

            for (Element ads : elements) {
                Item item = new Item();
                Element linkElement = ads.selectFirst("a.product-item-link");
                Element imgElement = ads.selectFirst("img.photo");
                if (linkElement != null) {
                    item.setTitle(linkElement.text().trim());
                    item.setUrl(linkElement.attr("href"));
                }
                if (imgElement != null) {
                    item.setPictureUrl(imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("div.price-box span span span");
                if (priceElement != null) {
                    item.setPrice(priceElement.text().trim());
                }
                item.setStore("Sancta Domenica");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
