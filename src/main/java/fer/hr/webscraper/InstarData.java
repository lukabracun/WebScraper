package fer.hr.webscraper;

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
                item.setTitle(ads.select("a.productEntityClick span").text());
                item.setUrl("https://www.instar-informatika.hr" + ads.select("a.productEntityClick").attr("href"));
                Element imgElement = ads.selectFirst("div.product-image img");
                if (imgElement != null) {
                    item.setPictureUrl(imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("div.price span.standard-price");
                if (priceElement != null) {
                    item.setPrice(priceElement.text());
                }
                item.setStore("Instar");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
