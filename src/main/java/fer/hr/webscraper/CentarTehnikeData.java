package fer.hr.webscraper;

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

                if (linkElement != null) {
                    item.setTitle(titleElement.text());
                    item.setUrl(linkElement.attr("href"));
                }
                if (imgElement != null) {
                    item.setPictureUrl(imgElement.attr("src"));
                }
                if (priceElement != null) {
                    item.setPrice(priceElement.text().trim());
                }
                /*if (oldPriceElement != null) {
                    item.setOldPrice(oldPriceElement.text().trim());
                }
                if (discountElement != null) {
                    item.setDiscount(discountElement.text().trim());
                }*/
                item.setStore("Centar Tehnike");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}