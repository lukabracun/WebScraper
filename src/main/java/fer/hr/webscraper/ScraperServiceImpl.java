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

    public Set<ResponseDTO> getData(String query) {
        Set<ResponseDTO> responseDTOS = new HashSet<>();
        for (String url: urls) {
            if (url.contains("instar-informatika")) {
                extractDataFromInstar(responseDTOS, url + query);
            } else if (url.contains("links.hr")) {
                extractDataFromLinks(responseDTOS, url + query);
            } else if (url.contains("sancta-domenica")) {
                extractDataFromSanctaDomenica(responseDTOS, url + query);
            } else if (url.contains("mikronis")) {
                extractDataFromMikronis(responseDTOS, url + query);
            }
        }
        return responseDTOS;
    }

    private void extractDataFromInstar(Set<ResponseDTO> responseDTOS, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("product-item-box");

            for (Element ads: elements) {
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setTitle(ads.select("a.productEntityClick span").text());
                responseDTO.setUrl("https://www.instar-informatika.hr" + ads.select("a.productEntityClick").attr("href"));
                Element imgElement = ads.selectFirst("div.product-image img");
                if (imgElement != null) {
                    responseDTO.setPictureUrl(imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("div.price span.standard-price");
                if (priceElement != null) {
                    responseDTO.setPrice(priceElement.text());
                }
                responseDTO.setStore("Instar");
                responseDTOS.add(responseDTO);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void extractDataFromLinks(Set<ResponseDTO> responseDTOS, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("col-6 col-md-4");

            for (Element ads : elements) {
                ResponseDTO responseDTO = new ResponseDTO();
                Element linkElement = ads.selectFirst("a.card-link");
                Element imgElement = ads.selectFirst("img.img-fluid");
                if (linkElement != null) {
                    responseDTO.setTitle(linkElement.attr("title"));
                    responseDTO.setUrl("https://www.links.hr" + linkElement.attr("href"));
                }
                if (imgElement != null) {
                    responseDTO.setPictureUrl(imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("div.product-price span.active");
                if (priceElement != null) {
                    responseDTO.setPrice(priceElement.text());
                }
                responseDTO.setStore("Links");
                responseDTOS.add(responseDTO);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void extractDataFromSanctaDomenica(Set<ResponseDTO> responseDTOS, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("product-item-info");

            for (Element ads : elements) {
                ResponseDTO responseDTO = new ResponseDTO();
                Element linkElement = ads.selectFirst("a.product-item-link");
                Element imgElement = ads.selectFirst("img.photo");
                if (linkElement != null) {
                    responseDTO.setTitle(linkElement.text().trim());
                    responseDTO.setUrl(linkElement.attr("href"));
                }
                if (imgElement != null) {
                    responseDTO.setPictureUrl(imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("div.price-box span span span");
                if (priceElement != null) {
                    responseDTO.setPrice(priceElement.text().trim());
                }
                responseDTO.setStore("Sancta Domenica");
                responseDTOS.add(responseDTO);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void extractDataFromMikronis(Set<ResponseDTO> responseDTOS, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("prod-item");

            for (Element ads : elements) {
                ResponseDTO responseDTO = new ResponseDTO();
                Element linkElement = ads.selectFirst("a[onclick]");
                Element imgElement = ads.selectFirst("img.img-responsive");
                if (linkElement != null) {
                    responseDTO.setTitle(linkElement.attr("title"));
                    String onclickAttr = linkElement.attr("onclick");
                    String extractedUrl = StringUtils.substringBetween(onclickAttr, "', '", "',");
                    System.out.println(extractedUrl);
                    responseDTO.setUrl("https://www.mikronis.hr" + extractedUrl);
                }
                if (imgElement != null) {
                    responseDTO.setPictureUrl("https://www.mikronis.hr" + imgElement.attr("src"));
                }
                Element priceElement = ads.selectFirst("li.item-price a");
                if (priceElement != null) {
                    responseDTO.setPrice(priceElement.text());
                }
                responseDTO.setStore("Mikronis");
                responseDTOS.add(responseDTO);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}