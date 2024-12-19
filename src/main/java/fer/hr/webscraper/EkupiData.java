package fer.hr.webscraper;

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
                Element imgElement = ads.selectFirst("a.thumb img");
                Element titleElement = ads.selectFirst("a.name");
                Element priceElement = ads.selectFirst("div.price");
                Element oldPriceElement = ads.selectFirst("div.item-old-price p.altPrice");
                Element availabilityElement = ads.selectFirst("p.delivery-date span.ddate");

                if (linkElement != null) {
                    item.setTitle(linkElement.attr("title"));
                    item.setUrl("https://www.ekupi.hr/hr/" + linkElement.attr("href"));
                }
                if (imgElement != null) {
                    System.out.println(imgElement);
                    System.out.println("-----------");
                    item.setPictureUrl(imgElement.attr("src"));
                }
                if (priceElement != null) {
                    item.setPrice(priceElement.text().trim());
                }
                /*if (oldPriceElement != null) {
                    item.setOldPrice(oldPriceElement.text().trim());
                }
                if (availabilityElement != null) {
                    item.setAvailability(availabilityElement.text().trim());
                }*/
                item.setStore("ekupi");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}