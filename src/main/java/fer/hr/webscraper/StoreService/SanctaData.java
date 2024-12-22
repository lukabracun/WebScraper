package fer.hr.webscraper.StoreService;

import fer.hr.webscraper.Item;
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
                Element oldPriceElement = ads.selectFirst("span.old-price span.price");
                if (oldPriceElement != null) {
                    item.setOldPrice(oldPriceElement.text().trim());
                }
                Element discountElement = ads.selectFirst("span.discount-percent");
                if (discountElement != null) {
                    item.setDiscount(discountElement.text().trim());
                }
                Element additionalInfoElement = ads.selectFirst("div.promo-code-wrapper span.promo-percent");
                if (additionalInfoElement != null) {
                    item.setAdditionalInfo(additionalInfoElement.text().trim());
                }
                item.setStore("Sancta Domenica");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}