package fer.hr.webscraper;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScraperServiceImpl {
    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;

    public Set<Item> getData(String query) {
        Set<Item> items = new HashSet<>();
        for (String url: urls) {
            if (url.contains("instar-informatika")) {
                extractDataFromInstar(items, url + query);
            } else if (url.contains("links.hr")) {
                extractDataFromLinks(items, url + query);
            } else if (url.contains("sancta-domenica")) {
                extractDataFromSanctaDomenica(items, url + query);
            } else if (url.contains("mikronis")) {
                extractDataFromMikronis(items, url + query);
            }
        }
        return items;
    }

    private void extractDataFromInstar(Set<Item> items, String url) {
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

    private void extractDataFromLinks(Set<Item> items, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("col-6 col-md-4");

            for (Element ads : elements) {
                Item item = new Item();
                Element linkElement = ads.selectFirst("a.card-link");
                Element imgElement = ads.selectFirst("img.img-fluid");
                if (linkElement != null) {
                    item.setTitle(linkElement.attr("title"));
                    item.setUrl("https://www.links.hr" + linkElement.attr("href"));
                }
                if (imgElement != null) {
                    item.setPictureUrl(imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("div.product-price span.active");
                if (priceElement != null) {
                    item.setPrice(priceElement.text());
                }
                item.setStore("Links");
                items.add(item);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void extractDataFromSanctaDomenica(Set<Item> items, String url) {
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
    private void extractDataFromMikronis(Set<Item> items, String url) {
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