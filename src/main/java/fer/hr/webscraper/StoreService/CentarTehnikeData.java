package fer.hr.webscraper.StoreService;

import fer.hr.webscraper.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;

public class CentarTehnikeData {
    public void extractDataFromCentarTehnike(Set<Item> items, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("cp");

            for (Element ads : elements) {
                Item item = new Item();
                Element linkElement = ads.selectFirst("a.cp-header-cnt");
                Element imgElement = ads.selectFirst("img[data-product_main_image]");
                Element titleElement = ads.selectFirst("h2.cp-title");
                Element priceElement = ads.selectFirst("div.cp-current-price");
                Element oldPriceElement = ads.selectFirst("div.modal-old-price");
                Element discountElement = ads.selectFirst("span.cp-card-discount");
                Element availabilityElement = ads.selectFirst("span.cp-unavailable-btn span");

                if (linkElement != null) {
                    item.setName(titleElement.text());
                    item.setUrl(linkElement.attr("href"));
                }
                if (imgElement != null) {
                    item.setImageUrl(imgElement.attr("src"));
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
                    item.setState("Nedostupno");
                } else {
                    item.setState("Dostupno");
                }
                item.setStoreName("Centar Tehnike");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}