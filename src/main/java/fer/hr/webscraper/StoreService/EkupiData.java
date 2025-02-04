package fer.hr.webscraper.StoreService;

import fer.hr.webscraper.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class EkupiData {
    public void extractDataFromEkupi(Set<Item> items, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("product-item");

            for (Element ads : elements) {
                Item item = new Item();
                Element linkElement = ads.selectFirst("a.thumb");
                Element imgElement = ads.selectFirst("a.thumb > img:not(.PdpStaticSticker)");
                Element titleElement = ads.selectFirst("a.name");
                Element priceElement = ads.selectFirst("div.price");
                Element oldPriceElement = ads.selectFirst("div.item-old-price");
                Element discountElement = ads.selectFirst("div.price-block p span");
                Element availabilityElement = ads.selectFirst("p.delivery-date span.ddate");

                if (linkElement != null) {
                    item.setName(linkElement.attr("title"));
                    item.setUrl("https://www.ekupi.hr/hr/" + linkElement.attr("href"));
                }
                if (imgElement != null) {
                    item.setImageUrl(imgElement.attr("src"));
                }
                if (titleElement != null) {
                    item.setName(titleElement.text().trim());
                }
                if (priceElement != null) {
                    item.setPrice(priceElement.text().trim());
                }
                if (oldPriceElement != null) {
                    item.setOldPrice(oldPriceElement.text().trim());
                }
                if (discountElement != null) {
                    item.setDiscount(discountElement.text().trim());
                    item.setDiscounted(true);
                }
                if (availabilityElement != null) {
                    item.setState("Dostupno");
                } else {
                    item.setState("Nedostupno");
                }
                item.setStoreName("ekupi");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}