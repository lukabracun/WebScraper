package fer.hr.webscraper;

import org.apache.commons.lang3.StringUtils;
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
                Element linkElement = ads.selectFirst("a[onclick]");
                Element imgElement = ads.selectFirst("img.img-responsive");
                if (linkElement != null) {
                    item.setTitle(linkElement.attr("title"));
                    String onclickAttr = linkElement.attr("onclick");
                    String extractedUrl = StringUtils.substringBetween(onclickAttr, "', '", "',");
                    System.out.println(extractedUrl);
                    item.setUrl("https://www.mikronis.hr" + extractedUrl);
                }
                if (imgElement != null) {
                    item.setPictureUrl("https://www.mikronis.hr" + imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("li.item-price a");
                if (priceElement != null) {
                    item.setPrice(priceElement.text());
                }
                item.setStore("Mikronis");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
